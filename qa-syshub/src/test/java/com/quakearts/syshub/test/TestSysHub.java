package com.quakearts.syshub.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;

import javax.enterprise.inject.spi.CDI;
import javax.transaction.UserTransaction;
import org.junit.BeforeClass;
import org.junit.Test;

import com.quakearts.appbase.Main;
import com.quakearts.appbase.spi.factory.JavaTransactionManagerSpiFactory;
import com.quakearts.syshub.SysHub;
import com.quakearts.syshub.SysHubMain;
import com.quakearts.syshub.agent.ProcessingAgent;
import com.quakearts.syshub.agent.builder.ProcessingAgentBuilder;
import com.quakearts.syshub.core.DataSpooler;
import com.quakearts.syshub.core.Message;
import com.quakearts.syshub.core.MessageFormatter;
import com.quakearts.syshub.core.Messenger;
import com.quakearts.syshub.core.Result;
import com.quakearts.syshub.core.factory.DataSpoolerFactory;
import com.quakearts.syshub.core.factory.MessageFormatterFactory;
import com.quakearts.syshub.core.factory.MessengerFactory;
import com.quakearts.syshub.core.impl.MessageByteImpl;
import com.quakearts.syshub.core.impl.MessageStringImpl;
import com.quakearts.syshub.core.impl.ResultImpl;
import com.quakearts.syshub.core.runner.AgentRunner;
import com.quakearts.syshub.core.runner.impl.LoopedAgentRunner;
import com.quakearts.syshub.core.runner.impl.ScheduledAgentRunner;
import com.quakearts.syshub.core.runner.impl.TriggeredAgentRunner;
import com.quakearts.syshub.core.utils.MapRowBuilder;
import com.quakearts.syshub.core.utils.Serializer;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.exception.ProcessingException;
import com.quakearts.syshub.log.LoggerImpl;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentConfigurationParameter;
import com.quakearts.syshub.model.AgentConfiguration.RunType;
import com.quakearts.syshub.model.AgentConfigurationParameter.ParameterType;
import com.quakearts.syshub.model.AgentModule.ModuleType;
import com.quakearts.syshub.model.ProcessingLog.LogType;
import com.quakearts.syshub.model.ResultExceptionLog;
import com.quakearts.syshub.model.AgentModule;
import com.quakearts.syshub.model.ProcessingLog;
import com.quakearts.syshub.test.helper.ErrorObserver;
import com.quakearts.syshub.test.helper.ErrorThrowingModule;
import com.quakearts.syshub.test.helper.ErrorThrowingModule.ExceptionLocation;
import com.quakearts.syshub.test.helper.TestAgentTrigger2;
import com.quakearts.syshub.test.helper.TestDataSpooler;
import com.quakearts.syshub.test.helper.TestFormatter1;
import com.quakearts.syshub.test.helper.TestFormatter2;
import com.quakearts.syshub.test.helper.TestMessenger1;
import com.quakearts.syshub.test.helper.TestMessenger2;
import com.quakearts.syshub.test.helper.Trigger1;
import com.quakearts.syshub.test.helper.Trigger2;
import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStoreFactory;

public class TestSysHub {
	
	@BeforeClass
	public static void startAndSetup() {
		try {
			LogManager.getLogManager().readConfiguration(Thread.currentThread().getContextClassLoader().getResourceAsStream("logging.properties"));
		} catch (SecurityException | IOException e) {
		}
		StartUp.getStartUp().initiateSystem();
	}
	
	@Test
	public void testStartingSysHubMainTwice() throws Exception {
		SysHubMain sysHubMain = new SysHubMain();
		sysHubMain.init();//If not blocked will result in nullpointer exception
	}
	
	@Test
	public void testAgentsDeployed() throws Exception {
		SysHub sysHubMain = SysHubMain.getInstance();
		assertThat(sysHubMain, is(notNullValue()));
		assertThat(sysHubMain.listAgentRunners().size(), is(3));

		UserTransaction transaction = JavaTransactionManagerSpiFactory
				.getInstance()
				.getJavaTransactionManagerSpi()
				.getUserTransaction();
		
		transaction.begin();
		List<AgentConfiguration> agentConfigurations;
		try {
			agentConfigurations = DataStoreFactory
				.getInstance()
				.getDataStore("system")
				.find(AgentConfiguration.class)
				.filterBy("active")
				.withAValueEqualTo(Boolean.TRUE)
				.thenList();
		
			assertThat(agentConfigurations.size(), is(4));
			
			Trigger1 trigger = CDI.current().select(Trigger1.class).get();
			trigger.fire();
			
			Thread.sleep(2000);//give Looped and Scheduled Agents some time to run
			
			int countDeployed=0;
			for(AgentConfiguration agentConfiguration:agentConfigurations) {
				if(!sysHubMain.isDeployed(agentConfiguration))
					continue;
				
				countDeployed++;
				
				AgentRunner agentRunner = sysHubMain.fetchAgentRunner(agentConfiguration);
				assertThat(agentRunner, is(notNullValue()));
				switch (agentConfiguration.getType()) {
				case LOOPED:
					assertThat(agentRunner instanceof LoopedAgentRunner, is(true));
					assertThat(agentRunner.getRunType(), is(RunType.LOOPED));
	
					break;
				case SCHEDULED:
					assertThat(agentRunner instanceof ScheduledAgentRunner, is(true));
					assertThat(agentRunner.getRunType(), is(RunType.SCHEDULED));
			
					break;
				case TRIGGERED:
					assertThat(agentRunner.getRunType(), is(RunType.TRIGGERED));
					assertThat(agentRunner instanceof TriggeredAgentRunner, is(true));
					
					break;
				default:
					break;
				}
				validateAgentRunner(agentConfiguration, agentRunner);
				assertThat(agentRunner.start(), is(true));
			}
			assertThat(countDeployed, is(3));
		} finally {
			transaction.commit();
		}
	}

	private void validateAgentRunner(AgentConfiguration agentConfiguration, AgentRunner agentRunner)
			throws ConfigurationException {
		System.out.println("Validating "+agentConfiguration.getAgentName());
		assertThat(agentRunner.isShutDown(), is(false));
		assertThat(agentRunner.isInErrorState(), is(false));
		assertThat(agentRunner.isInRestartRequiredMode(), is(false));
		assertThat(agentRunner.isRunning(), is(true));
		
		ProcessingAgent agent = agentRunner.getProcessingAgent();
		//Confirm proper mapping happened
		assertThat(agent.getAgentConfiguration().getId(), is(agentConfiguration.getId()));
		assertThat(agent.getAgentConfiguration().getAgentName(), is(agentConfiguration.getAgentName()));
		assertThat(agent.getName(), is(agentConfiguration.getAgentName()));

		//Stats indicating the agent is actually working
		assertThat(agent.getStartTime(), is(notNullValue()));
		assertThat(agent.getLastRunTime(), is(notNullValue()));
		Main.log.trace(agent.getName()+" getTaskCount() "+agent.getTaskCount());
		Main.log.trace(agent.getName()+" getCompletedTaskCount() "+agent.getCompletedTaskCount());
		Main.log.trace(agent.getName()+" getAgentWorkersCreated() "+agent.getAgentWorkersCreated());
		Main.log.trace(agent.getName()+" getDataSpoolerWorkersCreated() "+agent.getDataSpoolerWorkersCreated());
		Main.log.trace(agent.getName()+" getFormatterMessengerWorkersCreated() "+agent.getFormatterMessengerWorkersCreated());
		Main.log.trace(agent.getName()+" getLargestPoolSize() "+agent.getLargestPoolSize());
		
		//Confirm configuration is correct
		assertThat(agent.getMessengerFormatterMapper().size()>0, is(true));
		assertThat(agent.getKeepAliveTime(), is(60L));		
		assertThat(agent.getQueueSize(), is(10));
		switch (agentConfiguration.getAgentName()) {
		case "Test Agent 1":
			assertThat(agent.getDataSpoolers().size(), is(1));
			assertThat(agent.getMessengerFormatterMapper().size(), is(1));
			assertThat(agent.getMaximumPoolSize(), is(5));
			assertThat(agent.getMaxDataSpoolerWorkers(), is(5));
			assertThat(agent.getDataSpoolerWorkersCreated()<=5, is(true));
			assertThat(agent.getMaxFormatterMessengerWorkers(), is(5));
			assertThat(agent.getFormatterMessengerWorkersCreated()<=5, is(true));
			break;
		case "Test Agent 2":
			assertThat(agent.getDataSpoolers().size(), is(2));
			assertThat(agent.getMessengerFormatterMapper().size(), is(2));
			assertThat(agent.getMaximumPoolSize(), is(8));
			assertThat(agent.getMaxDataSpoolerWorkers(), is(10));
			assertThat(agent.getDataSpoolerWorkersCreated()<=10, is(true));
			assertThat(agent.getMaxFormatterMessengerWorkers(), is(10));
			assertThat(agent.getFormatterMessengerWorkersCreated()<=10, is(true));
			break;
		case "Test Agent 3":
			assertThat(agent.getDataSpoolers().size(), is(2));
			assertThat(agent.getMessengerFormatterMapper().size(), is(1));
			assertThat(agent.getMaximumPoolSize(), is(5));
			assertThat(agent.getMaxDataSpoolerWorkers(), is(5));
			assertThat(agent.getDataSpoolerWorkersCreated()<=5, is(true));
			assertThat(agent.getMaxFormatterMessengerWorkers(), is(5));
			assertThat(agent.getFormatterMessengerWorkersCreated()<=5, is(true));
			break;
		default:
			break;
		}
		
		for(AgentModule module:agentConfiguration.getAgentModules()) {
			switch (module.getModuleType()) {
			case DATASPOOLER:
				DataSpooler spooler = DataSpoolerFactory.getFactory().getInstance(null, module);
				assertThat(spooler instanceof TestDataSpooler, is(true));
				assertThat(spooler.getAgentConfiguration().getId(), is(agentConfiguration.getId()));
				assertThat(spooler.getAgentConfiguration().getAgentName(), is(agentConfiguration.getAgentName()));
				assertThat(spooler.getAgentModule().getId(), is(module.getId()));
				assertThat(spooler.getAgentModule().getModuleName(), is(module.getModuleName()));
				
				break;
			case FORMATTER:
				MessageFormatter formatter = MessageFormatterFactory.getFactory().getInstance(null, module);
				if(agentConfiguration.getAgentName().equals("Test Agent 2")) {
					if(module.getModuleName().equals("Test Module 2.3"))
						assertThat(formatter instanceof TestFormatter1, is(true));	
					else
						assertThat(formatter instanceof TestFormatter2, is(true));
				} else if(agentConfiguration.getAgentName().equals("Test Agent 3")) {
					assertThat(formatter instanceof TestFormatter2, is(true));							
				} else {
					assertThat(formatter instanceof TestFormatter1, is(true));							
				}
				assertThat(formatter.getAgentConfiguration().getId(), is(agentConfiguration.getId()));
				assertThat(formatter.getAgentConfiguration().getAgentName(), is(agentConfiguration.getAgentName()));
				assertThat(formatter.getAgentModule().getId(), is(module.getId()));
				assertThat(formatter.getAgentModule().getModuleName(), is(module.getModuleName()));

				break;
			case MESSENGER:
				Messenger messenger = MessengerFactory.getFactory().getInstance(null, module);
				if(agentConfiguration.getAgentName().equals("Test Agent 2")) {
					if(module.getModuleName().equals("Test Module 2.5")) {
						assertThat(messenger instanceof TestMessenger1, is(true));
						Main.log.trace(agent.getName()+" Messenger sent "+((TestMessenger1)messenger).getSendCount());
					} else {
						assertThat(messenger instanceof TestMessenger2, is(true));
						Main.log.trace(agent.getName()+" Messenger sent "+((TestMessenger2)messenger).getSendCount());
					}
				} else if(agentConfiguration.getAgentName().equals("Test Agent 3")) {
					assertThat(messenger instanceof TestMessenger2, is(true));
					Main.log.trace(agent.getName()+" Messenger sent "+((TestMessenger2)messenger).getSendCount());
				} else {
					assertThat(messenger instanceof TestMessenger1, is(true));	
					Main.log.trace(agent.getName()+" Messenger sent "+((TestMessenger1)messenger).getSendCount());
				}
				assertThat(messenger.getAgentConfiguration().getId(), is(agentConfiguration.getId()));
				assertThat(messenger.getAgentConfiguration().getAgentName(), is(agentConfiguration.getAgentName()));
				assertThat(messenger.getAgentModule().getId(), is(module.getId()));
				assertThat(messenger.getAgentModule().getModuleName(), is(module.getModuleName()));
			default:
				break;
			}
		}
	}
	
	@Test
	public void testIsNotDeployed() throws Exception {
		AgentConfiguration agentConfiguration = new AgentConfiguration();
		agentConfiguration.setActive(true);
		agentConfiguration.setAgentName("Test Agent 4");
		agentConfiguration.setType(RunType.LOOPED);
		
		SysHub sysHub = SysHubMain.getInstance();
		assertThat(sysHub, is(notNullValue()));
		assertThat(sysHub.isDeployed(agentConfiguration), is(false));
	}
	
	@Test
	public void testUndeployAgent() throws Exception {
		SysHub sysHubMain = SysHubMain.getInstance();
		assertThat(sysHubMain, is(notNullValue()));
		AgentConfiguration agentConfiguration = createAgentConfiguration(RunType.LOOPED, "Test Agent 5");
		
		sysHubMain.deployAgent(agentConfiguration);
		Thread.sleep(600); //give it a chance to run
		AgentRunner agentRunner = sysHubMain.fetchAgentRunner(agentConfiguration);
		ProcessingAgent agent = agentRunner.getProcessingAgent();
		sysHubMain.undeployAgent(agentConfiguration);
		assertThat(sysHubMain.isDeployed(agentConfiguration), is(false));
		Thread.sleep(100); //give it a chance to die
		assertThat(agent.getLastRunTime(), is(notNullValue()));
		Date lastrun = agent.getLastRunTime();
		Thread.sleep(600); //give it a chance to run
		assertThat(agent.getLastRunTime(), is(lastrun));
		assertThat(agentRunner.isRunning(), is(false));
		assertThat(agentRunner.isShutDown(), is(true));
		
		agentConfiguration = createAgentConfiguration(RunType.SCHEDULED, "Test Agent 6");
		sysHubMain.deployAgent(agentConfiguration);
		agentRunner = sysHubMain.fetchAgentRunner(agentConfiguration);
		agent = agentRunner.getProcessingAgent();
		Thread.sleep(1000); //give it a chance to run
		sysHubMain.undeployAgent(agentConfiguration);
		assertThat(sysHubMain.isDeployed(agentConfiguration), is(false));
		Thread.sleep(1000); //give it a chance to die
		assertThat(agent.getLastRunTime(), is(notNullValue()));
		lastrun = agent.getLastRunTime();
		Thread.sleep(1000); //give it a chance to run again
		assertThat(agent.getLastRunTime().getTime(), is(lastrun.getTime()));
		assertThat(agentRunner.isRunning(), is(false));
		assertThat(agentRunner.isShutDown(), is(true));
		
		agentConfiguration = createAgentConfiguration(RunType.TRIGGERED, "Test Agent 7");
		sysHubMain.deployAgent(agentConfiguration);
		Thread.sleep(500);//allow agent to startup
		Trigger2 trigger = CDI.current().select(Trigger2.class).get();
		trigger.fire();
		Thread.sleep(500);//allow processing to complete
		agentRunner = sysHubMain.fetchAgentRunner(agentConfiguration);
		agent = agentRunner.getProcessingAgent();
		sysHubMain.undeployAgent(agentConfiguration);
		assertThat(sysHubMain.isDeployed(agentConfiguration), is(false));
		assertThat(agent.getLastRunTime(), is(notNullValue()));
		lastrun = agent.getLastRunTime();
		Thread.sleep(1000);//allow processing to die
		trigger.fire();
		assertThat(agent.getLastRunTime(), is(lastrun));
		assertThat(agentRunner.isRunning(), is(false));
		assertThat(agentRunner.isShutDown(), is(true));
		
		sysHubMain.undeployAgent(agentConfiguration);
	}

	private AgentConfiguration createAgentConfiguration(RunType runType, String agentName) {
		AgentConfiguration agentConfiguration = new AgentConfiguration();
		agentConfiguration.setActive(true);
		agentConfiguration.setAgentName(agentName);
		agentConfiguration.setType(runType);
		
		AgentConfigurationParameter parameter = new AgentConfigurationParameter("testParameter1");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setName("schedule.cron");
		parameter.setParameterType(ParameterType.CRONCONFIGURATION);
		parameter.setStringValue("* * * ? * *");
		agentConfiguration.getParameters().add(parameter);
		parameter = new AgentConfigurationParameter();
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setName("trigger.class");
		parameter.setParameterType(ParameterType.CLASS);
		parameter.setStringValue(TestAgentTrigger2.class.getName());
		agentConfiguration.getParameters().add(parameter);
		
		AgentModule agentModule = new AgentModule();
		agentModule.setAgentConfiguration(agentConfiguration);
		agentModule.setModuleClassName(TestDataSpooler.class.getName());
		agentModule.setModuleName(agentName+".1");
		agentModule.setModuleType(ModuleType.DATASPOOLER);
		parameter = new AgentConfigurationParameter("testParameter1", ParameterType.BOOLEAN);
		parameter.setBooleanValue(true);
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);
		
		agentConfiguration.getAgentModules().add(agentModule);
		
		agentModule = new AgentModule();
		agentModule.setAgentConfiguration(agentConfiguration);
		agentModule.setModuleClassName(TestFormatter1.class.getName());
		agentModule.setModuleName(agentName+".2");
		agentModule.setModuleType(ModuleType.FORMATTER);
		parameter = new AgentConfigurationParameter("testParameter1", ParameterType.NUMERIC);
		parameter.setNumericValue(20.0d);
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);

		agentConfiguration.getAgentModules().add(agentModule);
		
		agentModule = new AgentModule();
		agentModule.setAgentConfiguration(agentConfiguration);
		agentModule.setModuleClassName(TestMessenger1.class.getName());
		agentModule.setModuleName(agentName+".3");
		agentModule.setModuleType(ModuleType.MESSENGER);
		parameter = new AgentConfigurationParameter("testParameter1", ParameterType.EMAIL);
		parameter.setStringValue("test@server.com");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);

		agentConfiguration.getAgentModules().add(agentModule);
		
		return agentConfiguration;
	}
	
	@Test(expected=ProcessingException.class)
	public void testProcessDataWithNullName() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setAgentConfiguration(new AgentConfiguration());
		try {
			agent.processData();
		} finally {
			agent.shutdown();
		}
	}
	
	@Test(expected=ProcessingException.class)
	public void testProcessDataWithEmptyName() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("");
		try {
			agent.processData();
		} finally {
			agent.shutdown();
		}
	}

	@Test(expected=ProcessingException.class)
	public void testProcessDataWithNullAgentConfiguration() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		try {
			agent.processData();
		} finally {
			agent.shutdown();
		}
	}
	
	@Test(expected=ProcessingException.class)
	public void testProcessDataWithNullDataSpoolers() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		try {
			agent.processData();
		} finally {
			agent.shutdown();
		}
	}

	@Test(expected=ProcessingException.class)
	public void testProcessDataWithNoDataSpoolers() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Collections.emptyList());
		try {
			agent.processData();
		} finally {
			agent.shutdown();
		}
	}

	@Test(expected=ProcessingException.class)
	public void testProcessDataWithNullMappings() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(new TestDataSpooler()));
		try {
			agent.processData();
		} finally {
			agent.shutdown();
		}
	}

	@Test(expected=ProcessingException.class)
	public void testProcessDataWithNoMappings() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setResendCapable(true);
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(new TestDataSpooler()));
		agent.setMessengerFormatterMapper(Collections.emptyMap());
		try {
			agent.processData();
		} finally {
			agent.shutdown();
		}
	}
	
	@Test
	public void testProcessDataWithCloseableIteratorThrowingIOException() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		ErrorThrowingModule errorThrowingModule = new ErrorThrowingModule(ExceptionLocation.CLOSE, agent);
		ErrorObserver observer = CDI.current().select(ErrorObserver.class).get();
		agent.setDataSpoolers(Arrays.asList(errorThrowingModule));
		Map<Messenger, MessageFormatter> map = new HashMap<>();
		map.put(errorThrowingModule, errorThrowingModule);
		agent.setMessengerFormatterMapper(map);
		try {
			agent.processData();
			assertThat(observer.getQueue().poll(1, TimeUnit.SECONDS) instanceof IOException, is(true));
		} finally {
			agent.shutdown();
		}
	}
	
	@Test
	public void testProcessDataWithDataSpoolerPrepareThrowingProcessingException() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		ErrorThrowingModule errorThrowingModule = new ErrorThrowingModule(ExceptionLocation.PREPARE, agent);
		ErrorObserver observer = CDI.current().select(ErrorObserver.class).get();
		agent.setDataSpoolers(Arrays.asList(errorThrowingModule));
		Map<Messenger, MessageFormatter> map = new HashMap<>();
		map.put(errorThrowingModule, errorThrowingModule);
		agent.setMessengerFormatterMapper(map);
		try {
			agent.processData();
			assertThat(observer.getQueue().poll(1, TimeUnit.SECONDS) instanceof ProcessingException, is(true));
		} finally {
			agent.shutdown();
		}
	}

	@Test
	public void testProcessDataWithMessageFormatterThrowingProcessingException() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		ErrorThrowingModule errorThrowingModule = new ErrorThrowingModule(ExceptionLocation.FORMATDATA, agent);
		ErrorObserver observer = CDI.current().select(ErrorObserver.class).get();
		agent.setDataSpoolers(Arrays.asList(errorThrowingModule));
		Map<Messenger, MessageFormatter> map = new HashMap<>();
		map.put(errorThrowingModule, errorThrowingModule);
		agent.setMessengerFormatterMapper(map);
		try {
			agent.processData();
			assertThat(observer.getQueue().poll(1, TimeUnit.SECONDS) instanceof ProcessingException, is(true));
		} finally {
			agent.shutdown();
		}
	}

	@Test
	public void testProcessDataWithMessengerThrowingProcessingException() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		ErrorThrowingModule errorThrowingModule = new ErrorThrowingModule(ExceptionLocation.SENDMESSAGE, agent);
		ErrorObserver observer = CDI.current().select(ErrorObserver.class).get();
		agent.setDataSpoolers(Arrays.asList(errorThrowingModule));
		Map<Messenger, MessageFormatter> map = new HashMap<>();
		map.put(errorThrowingModule, errorThrowingModule);
		agent.setMessengerFormatterMapper(map);
		try {
			agent.processData();
			assertThat(observer.getQueue().poll(1, TimeUnit.SECONDS) instanceof ProcessingException, is(true));
		} finally {
			agent.shutdown();
		}
	}
	
	@Test
	public void testProcessDataWithDataSpoolerUpdateThrowingProcessingException() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		ErrorThrowingModule errorThrowingModule = new ErrorThrowingModule(ExceptionLocation.UPDATEDATA, agent);
		ErrorObserver observer = CDI.current().select(ErrorObserver.class).get();
		agent.setDataSpoolers(Arrays.asList(errorThrowingModule));
		Map<Messenger, MessageFormatter> map = new HashMap<>();
		map.put(errorThrowingModule, errorThrowingModule);
		agent.setMessengerFormatterMapper(map);

		try {
			agent.processData();
			assertThat(observer.getQueue().poll(1, TimeUnit.SECONDS) instanceof ProcessingException, is(true));
		} finally {
			agent.shutdown();
		}
	}

	@Test
	public void testSettingInvalidProperties() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		ErrorThrowingModule errorThrowingModule = new ErrorThrowingModule(ExceptionLocation.NONE, agent);
		agent.setDataSpoolers(Arrays.asList(errorThrowingModule, errorThrowingModule, errorThrowingModule));
		Map<Messenger, MessageFormatter> map = new HashMap<>();
		map.put(errorThrowingModule, errorThrowingModule);
		agent.setMessengerFormatterMapper(map);
		agent.setCorePoolSize(0);
		assertThat(agent.getCorePoolSize(), is(5));
		agent.setCorePoolSize(10);
		assertThat(agent.getCorePoolSize(), is(5));
		agent.setCorePoolSize(2);
		assertThat(agent.getCorePoolSize(), is(3));
		agent.setMaximumPoolSize(0);
		assertThat(agent.getMaximumPoolSize(), is(5));
		agent.setMaximumPoolSize(2);
		assertThat(agent.getMaximumPoolSize(), is(5));
		agent.setMaximumPoolSize(10);
		assertThat(agent.getMaximumPoolSize(), is(10));
		agent.setQueueSize(0);
		assertThat(agent.getQueueSize(), is(10));		
		agent.setQueueSize(20);
		assertThat(agent.getQueueSize(), is(20));
		agent.setKeepAliveTime(0);
		assertThat(agent.getKeepAliveTime(), is(60L));
		agent.setKeepAliveTime(30);
		assertThat(agent.getKeepAliveTime(), is(30L));
		agent.processData();
		agent.setMaxDataSpoolerWorkers(10);
		assertThat(agent.getMaxDataSpoolerWorkers(), is(5));
		agent.setMaxFormatterMessengerWorkers(10);
		assertThat(agent.getMaxFormatterMessengerWorkers(), is(5));
	}
	
	@Test(expected=ProcessingException.class)
	public void testReprocessProcessingLogWithNullMappings() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setResendCapable(true);
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(new TestDataSpooler()));
		agent.reprocessProcessingLog(new ProcessingLog());
	}

	@Test(expected=ProcessingException.class)
	public void testReprocessProcessingLogWithNoMappings() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setResendCapable(true);
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(new TestDataSpooler()));
		agent.setMessengerFormatterMapper(Collections.emptyMap());
		agent.reprocessProcessingLog(new ProcessingLog());
	}

	@Test(expected=ProcessingException.class)
	public void testReprocessProcessingLogWithResendIncapableAgent() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.reprocessProcessingLog(new ProcessingLog());
	}

	@Test(expected=ProcessingException.class)
	public void testReprocessProcessingLogWithNullProcessingLog() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setResendCapable(true);
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(new TestDataSpooler()));
		Map<Messenger, MessageFormatter> map = new HashMap<>();
		TestMessenger1 messenger = new TestMessenger1();
		messenger.setAgentModule(new AgentModule());
		messenger.getAgentModule().setModuleName("Test");
		map.put(messenger, new TestFormatter1());
		agent.setMessengerFormatterMapper(map);
		
		agent.reprocessProcessingLog(null);
	}
	
	@Test(expected=ProcessingException.class)
	public void testReprocessProcessingLogWithNullAgentModule() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setResendCapable(true);
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(new TestDataSpooler()));
		Map<Messenger, MessageFormatter> map = new HashMap<>();
		TestMessenger1 messenger = new TestMessenger1();
		messenger.setAgentModule(new AgentModule());
		messenger.getAgentModule().setModuleName("Test");
		map.put(messenger, new TestFormatter1());
		agent.setMessengerFormatterMapper(map);
		
		agent.reprocessProcessingLog(new ProcessingLog());
	}
	
	@Test(expected=ProcessingException.class)
	public void testReprocessProcessingLogWithNullMessageData() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setResendCapable(true);
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(new TestDataSpooler()));
		Map<Messenger, MessageFormatter> map = new HashMap<>();
		TestMessenger1 messenger = new TestMessenger1();
		messenger.setAgentModule(new AgentModule());
		messenger.getAgentModule().setModuleName("Test");
		map.put(messenger, new TestFormatter1());
		agent.setMessengerFormatterMapper(map);
		
		ProcessingLog processingLog = new ProcessingLog();
		processingLog.setLogID(1);
		processingLog.setAgentConfiguration(agent.getAgentConfiguration());
		processingLog.setAgentModule(messenger.getAgentModule());
		processingLog.setLogDt(new Date());

		agent.reprocessProcessingLog(processingLog);
	}
	
	@Test
	public void testReprocessProcessingLog() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setResendCapable(true);
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(new TestDataSpooler()));
		Map<Messenger, MessageFormatter> map = new HashMap<>();
		TestMessenger1 messenger = new TestMessenger1();
		messenger.setAgentModule(new AgentModule());
		messenger.getAgentModule().setModuleName("Test");
		map.put(messenger, new TestFormatter1());
		agent.setMessengerFormatterMapper(map);

		ProcessingLog processingLog = new ProcessingLog();
		processingLog.setLogID(1);
		processingLog.setAgentConfiguration(agent.getAgentConfiguration());
		processingLog.setAgentModule(messenger.getAgentModule());
		processingLog.setLogDt(new Date());
		
		MessageStringImpl messageData = new MessageStringImpl();
		messageData.appendBody("Test");
		
		Serializer serializer = CDI.current().select(Serializer.class).get();
		processingLog.setMessageData(serializer.toByteArray(messageData));
		processingLog.setMid(messageData.getId());
		processingLog.setRecipient("Test");
		processingLog.setRetries(0);
		processingLog.setStatusMessage("Test");
		processingLog.setType(LogType.INFO);
		
		agent.reprocessProcessingLog(processingLog);
		assertThat(messenger.getSendCount()>0, is(true));
	}

	@Test(expected=ProcessingException.class)
	public void testReprocessProcessingLogWithoutMatchingModule() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(new TestDataSpooler()));
		Map<Messenger, MessageFormatter> map = new HashMap<>();
		TestMessenger1 messenger = new TestMessenger1();
		messenger.setAgentModule(new AgentModule());
		messenger.getAgentModule().setModuleName("Test");
		map.put(messenger, new TestFormatter1());
		agent.setMessengerFormatterMapper(map);

		ProcessingLog processingLog = new ProcessingLog();
		processingLog.setLogID(1);
		processingLog.setAgentConfiguration(agent.getAgentConfiguration());
		processingLog.setAgentModule(new AgentModule());
		processingLog.getAgentModule().setModuleName("Wrong Test");
		processingLog.setLogDt(new Date());
		
		MessageByteImpl messageData = new MessageByteImpl();
		messageData.appendBody("Test".getBytes());
		
		Serializer serializer = CDI.current().select(Serializer.class).get();
		processingLog.setMessageData(serializer.toByteArray(messageData));
		processingLog.setMid(messageData.getId());
		processingLog.setRecipient("Test");
		processingLog.setRetries(0);
		processingLog.setStatusMessage("Test");
		processingLog.setType(LogType.INFO);
		
		agent.reprocessProcessingLog(processingLog);
	}
	
	@Test(expected=ProcessingException.class)
	public void testReprocessResultExceptionLogWithNullMappins() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(new TestDataSpooler()));
		agent.reprocessResultExceptionLog(null);
	}

	@Test(expected=ProcessingException.class)
	public void testReprocessResultExceptionLogWithNoMappings() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(new TestDataSpooler()));
		agent.setMessengerFormatterMapper(Collections.emptyMap());
		agent.reprocessResultExceptionLog(null);;
	}
	
	@Test(expected=ProcessingException.class)
	public void testReprocessResultExceptionLogWithNullResultExceptionLog() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(new TestDataSpooler()));
		Map<Messenger, MessageFormatter> map = new HashMap<>();
		map.put(new TestMessenger1(), new TestFormatter1());
		agent.setMessengerFormatterMapper(map);
		
		agent.reprocessResultExceptionLog(null);		
	}

	@Test(expected=ProcessingException.class)
	public void testReprocessResultExceptionLogWithNullExceptionData() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(new TestDataSpooler()));
		Map<Messenger, MessageFormatter> map = new HashMap<>();
		map.put(new TestMessenger1(), new TestFormatter1());
		agent.setMessengerFormatterMapper(map);
		ResultExceptionLog exceptionLog = new ResultExceptionLog();
		
		Serializer serializer = CDI.current().select(Serializer.class).get();
		Result<Object> result = generateResult();
		exceptionLog.setResultData(serializer.toByteArray(result));
		
		agent.reprocessResultExceptionLog(exceptionLog);		
	}

	@Test(expected=ProcessingException.class)
	public void testReprocessResultExceptionLogWithNullResultData() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(new TestDataSpooler()));
		Map<Messenger, MessageFormatter> map = new HashMap<>();
		map.put(new TestMessenger1(), new TestFormatter1());
		agent.setMessengerFormatterMapper(map);
		ResultExceptionLog exceptionLog = new ResultExceptionLog();
		
		Serializer serializer = CDI.current().select(Serializer.class).get();
		exceptionLog.setExceptionData(serializer.toByteArray(new Exception("test")));
		
		agent.reprocessResultExceptionLog(exceptionLog);		
	}

	@Test
	public void testReprocessResultExceptionLog() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(new TestDataSpooler()));
		Map<Messenger, MessageFormatter> map = new HashMap<>();
		TestMessenger1 messenger = new TestMessenger1();
		map.put(messenger, new TestFormatter1());
		agent.setMessengerFormatterMapper(map);
		
		ResultExceptionLog exceptionLog = new ResultExceptionLog();
		
		Serializer serializer = CDI.current().select(Serializer.class).get();
		Result<Object> result = generateResult();
		exceptionLog.setResultData(serializer.toByteArray(result));
		exceptionLog.setExceptionData(serializer.toByteArray(new Exception("test")));
		agent.reprocessResultExceptionLog(exceptionLog);
		
		assertThat(messenger.getSendCount()>0, is(true));
	}

	private Result<Object> generateResult() {
		Result<Object> result = new ResultImpl();
		result.addRow(MapRowBuilder.create()
				.row()
				.addColumn("agent.name", "Test")
				.thenBuild())
			.addMetaDataResult(MapRowBuilder.create()
					.row()
					.addColumn("agent.name", "Test 2")
					.thenBuild());
		List<Map<String, Object>> rowsList = Arrays.asList(MapRowBuilder.create()
				.row()
				.addColumn("agent.module.name", "Test")
				.thenBuild());
		result.addAllRows(rowsList);
		result.addAllMetaData(rowsList);
		result.addProperty("test", "value");
		return result;
	}
	
	@Test
	public void testProcessingAgentBuilder() throws Exception {
		UserTransaction transaction = JavaTransactionManagerSpiFactory
				.getInstance()
				.getJavaTransactionManagerSpi()
				.getUserTransaction();
		
		transaction.begin();
		
		AgentModule agentModule1, agentModule2;
		try {
			DataStore dataStore = DataStoreFactory.getInstance().getDataStore("system");
			agentModule1 = dataStore.get(AgentModule.class, 2);
			agentModule2 = dataStore.get(AgentModule.class, 3);
		
			AgentConfigurationParameter parameter = new AgentConfigurationParameter();
			parameter.setName("numericParamter");
			parameter.setNumericValue(1.0);
			parameter.setParameterType(ParameterType.NUMERIC);
			
			ProcessingAgentBuilder agentBuilder = new ProcessingAgentBuilder();
			ProcessingAgent processingAgent = agentBuilder.name("Test ProcessingAgentBuilder")
			.addConfigurationParameter(parameter)
			.maxDataSpoolerWorkers(10)
			.maxFormatterWorkers(15.0)
			.queueSize(30.0)
			.keepAliveTime(120.0)
			.corePoolSize( 5.0)
			.maximumPoolSize(45.0)
			.resendCapable(true)
			.dataSpooler(TestDataSpooler.class, "Test ProcessingAgentBuilder Module 1")
			.addBinaryParameter("test1", "value".getBytes())
			.messageFormatter(TestFormatter1.class,  "Test ProcessingAgentBuilder Module 2")
			.addBooleanParameter("test2", true)
			.messenger(TestMessenger1.class, "Test ProcessingAgentBuilder Module 2")
			.addStringParameter("test3", "value", ParameterType.PASSWORD)
			.map(agentModule1).map(agentModule2)
			.build();
		
			AgentConfiguration agentConfiguration = agentBuilder.getConfiguration();
			
			for(AgentModule agentModule:agentConfiguration.getAgentModules()) {
				parameter = agentModule.getParameters().iterator().next();
				switch (agentModule.getModuleType()) {
				case DATASPOOLER:
					assertThat(parameter.getName(), is("test1"));
					assertThat(parameter.getParameterType(), is(ParameterType.BINARY));
					assertThat(parameter.getStringValue(), is("dmFsdWU="));
					
					break;
				case FORMATTER:
					assertThat(parameter.getName(), is("test2"));
					assertThat(parameter.getParameterType(), is(ParameterType.BOOLEAN));
					assertThat(parameter.getBooleanValue(), is(true));
					break;
				case MESSENGER:
					assertThat(parameter.getName(), is("test3"));
					assertThat(parameter.getParameterType(), is(ParameterType.PASSWORD));
					assertThat(parameter.getStringValue(), is("value"));
					break;
				}
			}
			
			assertThat(processingAgent.getDataSpoolers().size(), is(1));
			assertThat(processingAgent.getDataSpoolers().get(0) instanceof TestDataSpooler, is(true));
						
			assertThat(processingAgent.getMessengerFormatterMapper().size(), is(2));
			
			for(Entry<Messenger, MessageFormatter> entry :processingAgent.getMessengerFormatterMapper().entrySet()) {
				assertThat(entry.getKey() instanceof TestMessenger1, is(true));
				assertThat(entry.getValue() instanceof TestFormatter1, is(true));
			}
			
			Set<AgentConfigurationParameter> parameters = processingAgent.getAgentConfiguration().getParameters();
			assertThat(parameters.size(), is(8));
			parameters = agentConfiguration.getParameters();
			assertThat(parameters.size(), is(8));

			List<String> lists = new ArrayList<>(Arrays.asList("isResendCapable",
					"maxFormatterWorkers","maxDataSpoolerWorkers",
					"queueSize","keepAliveTime",
					"corePoolSize","maximumPoolSize"));
			for(AgentConfigurationParameter agentConfigurationParameter:parameters) {
				switch (agentConfigurationParameter.getName()) {
				
				case "numericParamter":
					assertThat(agentConfigurationParameter.getParameterType(), is(ParameterType.NUMERIC));
					assertThat(agentConfigurationParameter.getNumericValue(), is(1.0d));
					break;
				case "maxDataSpoolerWorkers":
					assertThat(agentConfigurationParameter.getParameterType(), is(ParameterType.NUMERIC));
					assertThat(agentConfigurationParameter.getNumericValue(), is(10.0d));
					assertThat(processingAgent.getMaxDataSpoolerWorkers(), is(10));
					break;
				case "maxFormatterWorkers":
					assertThat(agentConfigurationParameter.getParameterType(), is(ParameterType.NUMERIC));
					assertThat(agentConfigurationParameter.getNumericValue(), is(15.0d));				
					assertThat(processingAgent.getMaxFormatterMessengerWorkers(), is(15));
					break;
				case "queueSize":
					assertThat(agentConfigurationParameter.getParameterType(), is(ParameterType.NUMERIC));
					assertThat(agentConfigurationParameter.getNumericValue(), is(30.0d));				
					assertThat(processingAgent.getQueueSize(), is(30));
					break;
				case "keepAliveTime":
					assertThat(agentConfigurationParameter.getParameterType(), is(ParameterType.NUMERIC));
					assertThat(agentConfigurationParameter.getNumericValue(), is(120.0d));				
					assertThat(processingAgent.getKeepAliveTime(), is(120l));
					break;
				case "corePoolSize":
					assertThat(agentConfigurationParameter.getParameterType(), is(ParameterType.NUMERIC));
					assertThat(agentConfigurationParameter.getNumericValue(), is(5.0d));				
					assertThat(processingAgent.getCorePoolSize(), is(5));
					break;
				case "maximumPoolSize":
					assertThat(agentConfigurationParameter.getParameterType(), is(ParameterType.NUMERIC));
					assertThat(agentConfigurationParameter.getNumericValue(), is(45.0d));				
					assertThat(processingAgent.getMaximumPoolSize(), is(45));
					break;
				case "isResendCapable":
					assertThat(agentConfigurationParameter.getParameterType(), is(ParameterType.BOOLEAN));
					assertThat(agentConfigurationParameter.getBooleanValue(), is(true));				
					assertThat(processingAgent.isResendCapable(), is(true));
					break;	
				default:
					fail("Uknown parameter: "+agentConfigurationParameter.getName());
					break;
				}
				
				lists.remove(agentConfigurationParameter.getName());
			}
			
			assertThat(lists.size(), is(0));
		} finally {
			transaction.commit();
		}
	}
	
	@Test(expected=ConfigurationException.class)
	public void testProcessingAgentBuilderWithNoAgentName() throws Exception {
		ProcessingAgentBuilder agentBuilder = new ProcessingAgentBuilder();
		agentBuilder.build();
	}

	@Test(expected=ConfigurationException.class)
	public void testProcessingAgentBuilderWithNoDataSpoolers() throws Exception {
		ProcessingAgentBuilder agentBuilder = new ProcessingAgentBuilder();
		agentBuilder.name("Test Error")
		.messageFormatter(TestFormatter1.class,  "Test ProcessingAgentBuilder Module 2")
		.messenger(TestMessenger1.class, "Test ProcessingAgentBuilder Module 2")
		.build();
	}
	
	@Test(expected=ConfigurationException.class)
	public void testProcessingAgentBuilderWithNoMessenger() throws Exception {
		ProcessingAgentBuilder agentBuilder = new ProcessingAgentBuilder();
		agentBuilder.name("Test Error")
		.dataSpooler(TestDataSpooler.class, "Test ProcessingAgentBuilder Module 1")
		.messageFormatter(TestFormatter1.class,  "Test ProcessingAgentBuilder Module 2")
		.build();
	}
	
	@Test(expected=ConfigurationException.class)
	public void testProcessingAgentBuilderWithNoMessageFormatter() throws Exception {
		ProcessingAgentBuilder agentBuilder = new ProcessingAgentBuilder();
		agentBuilder.name("Test Error")
		.dataSpooler(TestDataSpooler.class, "Test ProcessingAgentBuilder Module 1")
		.messenger(TestMessenger1.class, "Test ProcessingAgentBuilder Module 2")
		.build();
	}
	
	@Test
	public void testLogging() throws Exception {
		LoggerImpl loggerImpl = LoggerImpl.getInstance();
		
		AgentConfiguration agentConfiguration = new AgentConfiguration();
		agentConfiguration.setId(1);
		
		AgentModule formatterModule = new AgentModule();
		formatterModule.setId(2);
		
		AgentModule messengerModule = new AgentModule();
		messengerModule.setId(3);
				
		Result<Object> result = generateResult();
		Exception e = new Exception("Test Error");
		loggerImpl.logResultException(agentConfiguration, 
				formatterModule, 
				e, result);
		
		Message<String> message1 = new MessageStringImpl()
				.appendBody("Test Log"); 
		loggerImpl.logMessage(agentConfiguration,
				messengerModule, message1,"Test response", false);

		Message<String> message2 = new MessageStringImpl()
				.appendBody("Test Queue");
		loggerImpl.queueMessage(agentConfiguration, messengerModule, 
				message2, "Test Queue");

		Message<String> message3 = new MessageStringImpl()
		.appendBody("Test Queue");
		loggerImpl.storeMessage(agentConfiguration, messengerModule, 
				message3, "Test Store");

		if (loggerImpl.getUnpersistedProcessingLogs().size()>0) {
			Thread.sleep(1000);
			if (loggerImpl.getUnpersistedProcessingLogs().size()>0) {
				loggerImpl.pushLogsToDB();
			}
		}
		
		UserTransaction transaction = JavaTransactionManagerSpiFactory
				.getInstance()
				.getJavaTransactionManagerSpi()
				.getUserTransaction();
		
		Serializer serializer = CDI.current().select(Serializer.class).get();
		
		transaction.begin();
		try {
			List<ProcessingLog> processingLogs = DataStoreFactory
				.getInstance()
				.getDataStore("system")
				.find(ProcessingLog.class)
				.filterBy("agentConfiguration")
				.withAValueEqualTo(agentConfiguration)
				.filterBy("agentModule")
				.withAValueEqualTo(messengerModule)
				.thenList();

			assertThat(processingLogs.size(), is(3));
			for(ProcessingLog processingLog:processingLogs) {
				if(processingLog.getType() == LogType.INFO){
					assertThat(processingLog.getAgentConfiguration(), is(notNullValue()));
					assertThat(processingLog.getAgentConfiguration().getId(), is(1));
					assertThat(processingLog.getAgentModule(), is(notNullValue()));
					assertThat(processingLog.getAgentModule().getId(), is(3));
					assertThat(processingLog.getLogDt(), is(notNullValue()));
					assertThat(processingLog.getMessageData(), is(notNullValue()));		
					assertThat(((Message<?>)serializer.toObject(processingLog.getMessageData())).getId(), is(message1.getId()));
					assertThat(processingLog.getMid(), is(message1.getId()));
					assertThat(processingLog.getRecipient(), is(notNullValue()));
					assertThat(processingLog.getStatusMessage(), is("Test response"));
					break;
				}
			}
			List<ResultExceptionLog> exceptionLogs = DataStoreFactory
				.getInstance()
				.getDataStore("system")
				.find(ResultExceptionLog.class)
				.filterBy("agentConfiguration")
				.withAValueEqualTo(agentConfiguration)
				.filterBy("agentModule")
				.withAValueEqualTo(formatterModule)
				.thenList();

			assertThat(exceptionLogs.size(), is(1));
			assertThat(exceptionLogs.get(0).getAgentConfiguration(), is(notNullValue()));
			assertThat(exceptionLogs.get(0).getAgentConfiguration().getId(), is(1));
			assertThat(exceptionLogs.get(0).getAgentModule(), is(notNullValue()));
			assertThat(exceptionLogs.get(0).getAgentModule().getId(), is(2));
			assertThat(serializer.toObject(exceptionLogs.get(0).getResultData()), is(result));
			assertThat(((Exception)serializer.toObject(exceptionLogs.get(0).getExceptionData())).getMessage(), is(e.getMessage()));
			assertThat(exceptionLogs.get(0).getExceptionType(), is(e.getClass().getName()));
		} finally {
			transaction.commit();
		}
	}
}
