package com.quakearts.syshub.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.core.IsNot.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
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
import com.quakearts.syshub.core.event.ParameterEvent;
import com.quakearts.syshub.core.event.ParameterEvent.EventType;
import com.quakearts.syshub.core.event.ParameterEventBroadcaster;
import com.quakearts.syshub.core.event.ParameterEventListener;
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
import com.quakearts.syshub.model.AgentConfigurationModuleMapping;
import com.quakearts.syshub.model.AgentConfigurationParameter.ParameterType;
import com.quakearts.syshub.model.AgentModule.ModuleType;
import com.quakearts.syshub.model.MaxID;
import com.quakearts.syshub.model.ProcessingLog.LogType;
import com.quakearts.syshub.model.ResultExceptionLog;
import com.quakearts.syshub.model.TransactionLog;
import com.quakearts.syshub.model.VariableCache;
import com.quakearts.syshub.model.AgentModule;
import com.quakearts.syshub.model.ProcessingLog;
import com.quakearts.syshub.test.helper.ErrorObserver;
import com.quakearts.syshub.test.helper.ErrorThrowingModule;
import com.quakearts.syshub.test.helper.ErrorThrowingModule.ExceptionLocation;
import com.quakearts.syshub.test.helper.ShutdownMonitor;
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
		try {
			SysHubMain sysHubMain = new SysHubMain();
			sysHubMain.init();//If not blocked will result in nullpointer exception
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testAgentsDeployed() throws Exception {
		SysHub sysHubMain = CDI.current().select(SysHub.class).get();
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
			
			pause(2000);//give Looped and Scheduled Agents some time to run
			
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
		
		SysHub sysHub = CDI.current().select(SysHub.class).get();
		assertThat(sysHub, is(notNullValue()));
		assertThat(sysHub.isDeployed(agentConfiguration), is(false));
	}
	
	@Test
	public void testUndeployAgent() throws Exception {
		SysHub sysHub = CDI.current().select(SysHub.class).get();
		assertThat(sysHub, is(notNullValue()));
		AgentConfiguration agentConfiguration = createAgentConfiguration(RunType.LOOPED, "Test Agent 5");
		
		sysHub.deployAgent(agentConfiguration);
		pause(600); //give it a chance to run
		AgentRunner agentRunner = sysHub.fetchAgentRunner(agentConfiguration);
		ProcessingAgent agent = agentRunner.getProcessingAgent();
		sysHub.undeployAgent(agentConfiguration);
		assertThat(sysHub.isDeployed(agentConfiguration), is(false));
		pause(100); //give it a chance to die
		assertThat(agent.getLastRunTime(), is(notNullValue()));
		Date lastrun = agent.getLastRunTime();
		pause(600); //give it a chance to run
		assertThat(agent.getLastRunTime(), is(lastrun));
		assertThat(agentRunner.isRunning(), is(false));
		assertThat(agentRunner.isShutDown(), is(true));
		
		agentConfiguration = createAgentConfiguration(RunType.SCHEDULED, "Test Agent 6");
		sysHub.deployAgent(agentConfiguration);
		agentRunner = sysHub.fetchAgentRunner(agentConfiguration);
		agent = agentRunner.getProcessingAgent();
		pause(1000); //give it a chance to run
		sysHub.undeployAgent(agentConfiguration);
		assertThat(sysHub.isDeployed(agentConfiguration), is(false));
		pause(1000); //give it a chance to die
		assertThat(agent.getLastRunTime(), is(notNullValue()));
		lastrun = agent.getLastRunTime();
		pause(1000); //give it a chance to run again
		assertThat(agent.getLastRunTime().getTime(), is(lastrun.getTime()));
		assertThat(agentRunner.isRunning(), is(false));
		assertThat(agentRunner.isShutDown(), is(true));
		
		agentConfiguration = createAgentConfiguration(RunType.TRIGGERED, "Test Agent 7");
		sysHub.deployAgent(agentConfiguration);
		pause(500);//allow agent to startup
		Trigger2 trigger = CDI.current().select(Trigger2.class).get();
		trigger.fire();
		pause(500);//allow processing to complete
		agentRunner = sysHub.fetchAgentRunner(agentConfiguration);
		agent = agentRunner.getProcessingAgent();
		sysHub.undeployAgent(agentConfiguration);
		assertThat(sysHub.isDeployed(agentConfiguration), is(false));
		assertThat(agent.getLastRunTime(), is(notNullValue()));
		lastrun = agent.getLastRunTime();
		pause(1000);//allow processing to die
		trigger.fire();
		assertThat(agent.getLastRunTime(), is(lastrun));
		assertThat(agentRunner.isRunning(), is(false));
		assertThat(agentRunner.isShutDown(), is(true));
		
		sysHub.undeployAgent(agentConfiguration);
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
		agent.setDataSpoolers(Arrays.asList(DataSpoolerFactory.getFactory().getInstance(TestDataSpooler.class.getName())));
		try {
			agent.processData();
		} finally {
			agent.shutdown();
		}
	}

	@Test(expected=ProcessingException.class)
	public void testProcessDataWithNoMappings() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(DataSpoolerFactory.getFactory().getInstance(TestDataSpooler.class.getName())));
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
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(DataSpoolerFactory.getFactory().getInstance(TestDataSpooler.class.getName())));
		agent.reprocessProcessingLog(new ProcessingLog());
	}

	@Test(expected=ProcessingException.class)
	public void testReprocessProcessingLogWithNoMappings() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(DataSpoolerFactory.getFactory().getInstance(TestDataSpooler.class.getName())));
		agent.setMessengerFormatterMapper(Collections.emptyMap());
		agent.reprocessProcessingLog(new ProcessingLog());
	}

	@Test(expected=ProcessingException.class)
	public void testReprocessProcessingLogWithNullProcessingLog() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(DataSpoolerFactory.getFactory().getInstance(TestDataSpooler.class.getName())));
		Map<Messenger, MessageFormatter> map = new HashMap<>();
		TestMessenger1 messenger = (TestMessenger1) MessengerFactory.getFactory().getInstance(TestMessenger1.class.getName());
		messenger.setAgentModule(new AgentModule());
		messenger.getAgentModule().setModuleName("Test");
		map.put(messenger,  MessageFormatterFactory.getFactory().getInstance(TestFormatter1.class.getName()));
		agent.setMessengerFormatterMapper(map);
		
		agent.reprocessProcessingLog(null);
	}
	
	@Test(expected=ProcessingException.class)
	public void testReprocessProcessingLogWithNullAgentModule() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(DataSpoolerFactory.getFactory().getInstance(TestDataSpooler.class.getName())));
		Map<Messenger, MessageFormatter> map = new HashMap<>();
		TestMessenger1 messenger = (TestMessenger1) MessengerFactory.getFactory().getInstance(TestMessenger1.class.getName());
		messenger.setAgentModule(new AgentModule());
		messenger.getAgentModule().setModuleName("Test");
		map.put(messenger,  MessageFormatterFactory.getFactory().getInstance(TestFormatter1.class.getName()));
		agent.setMessengerFormatterMapper(map);
		
		agent.reprocessProcessingLog(new ProcessingLog());
	}
	
	@Test(expected=ProcessingException.class)
	public void testReprocessProcessingLogWithNullMessageData() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(DataSpoolerFactory.getFactory().getInstance(TestDataSpooler.class.getName())));
		Map<Messenger, MessageFormatter> map = new HashMap<>();
		TestMessenger1 messenger = (TestMessenger1) MessengerFactory.getFactory().getInstance(TestMessenger1.class.getName());
		messenger.setAgentModule(new AgentModule());
		messenger.getAgentModule().setModuleName("Test");
		map.put(messenger,  MessageFormatterFactory.getFactory().getInstance(TestFormatter1.class.getName()));
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
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(DataSpoolerFactory.getFactory().getInstance(TestDataSpooler.class.getName())));
		Map<Messenger, MessageFormatter> map = new HashMap<>();
		TestMessenger1 messenger = (TestMessenger1) MessengerFactory.getFactory().getInstance(TestMessenger1.class.getName());
		messenger.setAgentModule(new AgentModule());
		messenger.getAgentModule().setModuleName("Test");
		map.put(messenger,  MessageFormatterFactory.getFactory().getInstance(TestFormatter1.class.getName()));
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
		agent.setDataSpoolers(Arrays.asList(DataSpoolerFactory.getFactory().getInstance(TestDataSpooler.class.getName())));
		Map<Messenger, MessageFormatter> map = new HashMap<>();
		TestMessenger1 messenger = (TestMessenger1) MessengerFactory.getFactory().getInstance(TestMessenger1.class.getName());
		messenger.setAgentModule(new AgentModule());
		messenger.getAgentModule().setModuleName("Test");
		map.put(messenger,  MessageFormatterFactory.getFactory().getInstance(TestFormatter1.class.getName()));
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
	public void testReprocessProcessingLogWithNonResendCapableModule() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(DataSpoolerFactory.getFactory().getInstance(TestDataSpooler.class.getName())));
		Map<Messenger, MessageFormatter> map = new HashMap<>();
		TestMessenger2 messenger = (TestMessenger2) MessengerFactory.getFactory().getInstance(TestMessenger2.class.getName());
		messenger.setAgentModule(new AgentModule());
		messenger.getAgentModule().setModuleName("Test");
		map.put(messenger,  MessageFormatterFactory.getFactory().getInstance(TestFormatter1.class.getName()));
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
	}
	
	@Test(expected=ProcessingException.class)
	public void testReprocessResultExceptionLogWithNullMappins() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(DataSpoolerFactory.getFactory().getInstance(TestDataSpooler.class.getName())));
		agent.reprocessResultExceptionLog(null);
	}

	@Test(expected=ProcessingException.class)
	public void testReprocessResultExceptionLogWithNoMappings() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(DataSpoolerFactory.getFactory().getInstance(TestDataSpooler.class.getName())));
		agent.setMessengerFormatterMapper(Collections.emptyMap());
		agent.reprocessResultExceptionLog(null);;
	}
	
	@Test(expected=ProcessingException.class)
	public void testReprocessResultExceptionLogWithNullResultExceptionLog() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(DataSpoolerFactory.getFactory().getInstance(TestDataSpooler.class.getName())));
		Map<Messenger, MessageFormatter> map = new HashMap<>();
		map.put(MessengerFactory.getFactory().getInstance(TestMessenger1.class.getName()), 
				MessageFormatterFactory.getFactory().getInstance(TestFormatter1.class.getName()));
		agent.setDataSpoolers(Arrays.asList(DataSpoolerFactory.getFactory().getInstance(TestDataSpooler.class.getName())));
		agent.setMessengerFormatterMapper(map);
		
		agent.reprocessResultExceptionLog(null);		
	}

	@Test(expected=ProcessingException.class)
	public void testReprocessResultExceptionLogWithNullExceptionData() throws Exception {
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		agent.setAgentConfiguration(new AgentConfiguration());
		agent.setName("Name");
		agent.setDataSpoolers(Arrays.asList(DataSpoolerFactory.getFactory().getInstance(TestDataSpooler.class.getName())));
		Map<Messenger, MessageFormatter> map = new HashMap<>();
		map.put(MessengerFactory.getFactory().getInstance(TestMessenger1.class.getName()), 
				MessageFormatterFactory.getFactory().getInstance(TestFormatter1.class.getName()));
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
		agent.setDataSpoolers(Arrays.asList(DataSpoolerFactory.getFactory().getInstance(TestDataSpooler.class.getName())));
		Map<Messenger, MessageFormatter> map = new HashMap<>();
		map.put(MessengerFactory.getFactory().getInstance(TestMessenger1.class.getName()), 
				MessageFormatterFactory.getFactory().getInstance(TestFormatter1.class.getName()));
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
		agent.setDataSpoolers(Arrays.asList(DataSpoolerFactory.getFactory().getInstance(TestDataSpooler.class.getName())));
		Map<Messenger, MessageFormatter> map = new HashMap<>();
		TestMessenger1 messenger = (TestMessenger1) MessengerFactory.getFactory().getInstance(TestMessenger1.class.getName());
		map.put(messenger, 
				MessageFormatterFactory.getFactory().getInstance(TestFormatter1.class.getName()));
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
			.dataSpooler(TestDataSpooler.class, "Test ProcessingAgentBuilder Module 1")
			.addBinaryParameter("test1", "value".getBytes())
			.messageFormatter(TestFormatter1.class,  "Test ProcessingAgentBuilder Module 2")
			.addBooleanParameter("test2", true)
			.messenger(TestMessenger1.class, "Test ProcessingAgentBuilder Module 3")
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
			assertThat(parameters.size(), is(7));
			parameters = agentConfiguration.getParameters();
			assertThat(parameters.size(), is(7));

			List<String> lists = new ArrayList<>(Arrays.asList("maxFormatterWorkers","maxDataSpoolerWorkers",
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
		agentConfiguration.setId(5);
		
		AgentModule formatterModule = new AgentModule();
		formatterModule.setId(15);
		
		AgentModule messengerModule = new AgentModule();
		messengerModule.setId(16);
				
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
			pause(1000);
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
					assertThat(processingLog.getAgentConfiguration().getId(), is(5));
					assertThat(processingLog.getAgentModule(), is(notNullValue()));
					assertThat(processingLog.getAgentModule().getId(), is(16));
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
			assertThat(exceptionLogs.get(0).getAgentConfiguration().getId(), is(5));
			assertThat(exceptionLogs.get(0).getAgentModule(), is(notNullValue()));
			assertThat(exceptionLogs.get(0).getAgentModule().getId(), is(15));
			assertThat(serializer.toObject(exceptionLogs.get(0).getResultData()), is(result));
			assertThat(((Exception)serializer.toObject(exceptionLogs.get(0).getExceptionData())).getMessage(), is(e.getMessage()));
			assertThat(exceptionLogs.get(0).getExceptionType(), is(e.getClass().getName()));
		} finally {
			transaction.commit();
		}
	}
	
	@Test
	public void testModelEquals() throws Exception {
		assertThat(new AgentConfiguration(), 
				is(new AgentConfiguration()));
		AgentConfiguration agentConfiguration1 = new AgentConfiguration();
		
		agentConfiguration1.setAgentName("Test");
		assertThat(agentConfiguration1, is(agentConfiguration1));
		assertThat(agentConfiguration1.hashCode(), is(agentConfiguration1.hashCode()));

		AgentConfiguration agentConfiguration2 = new AgentConfiguration();

		agentConfiguration1.setAgentName("Test");
		assertThat(agentConfiguration1, is(not(agentConfiguration2)));
		assertThat(agentConfiguration1.hashCode(), is(not(agentConfiguration2.hashCode())));

		agentConfiguration2.setAgentName("Test");
		assertThat(agentConfiguration1, is(agentConfiguration2));
		assertThat(agentConfiguration1.hashCode(), is(agentConfiguration2.hashCode()));
		
		assertThat(agentConfiguration1.equals(null), is(false));
		assertThat(((Object)agentConfiguration1).equals(""), is(false));

		assertThat(new AgentModule(), is(new AgentModule()));		
		AgentModule agentModule1 = new AgentModule();
		AgentModule agentModule2 = new AgentModule();
		assertThat(agentModule1, is(agentModule1));
		assertThat(agentModule1.hashCode(), is(agentModule1.hashCode()));
		agentModule1.setAgentConfiguration(agentConfiguration1);
		assertThat(agentModule1, is(not(agentModule2)));
		assertThat(agentModule1.hashCode(), is(not(agentModule2.hashCode())));
		agentModule2.setAgentConfiguration(agentConfiguration1);
		assertThat(agentModule1, is(agentModule2));
		assertThat(agentModule1.hashCode(), is(agentModule2.hashCode()));
		assertThat(agentModule1, is(agentModule1));
		assertThat(agentModule1.hashCode(), is(agentModule1.hashCode()));
		agentModule1.setModuleName("Test");
		assertThat(agentModule1, is(not(agentModule2)));
		assertThat(agentModule1.hashCode(), is(not(agentModule2.hashCode())));
		agentModule2.setModuleName("Test");
		assertThat(agentModule1, is(agentModule2));
		assertThat(agentModule1.hashCode(), is(agentModule2.hashCode()));
		assertThat(agentModule1.equals(null), is(false));
		assertThat(((Object)agentModule1).equals(""), is(false));
		
		assertThat(new AgentConfigurationModuleMapping(), 
				is(new AgentConfigurationModuleMapping()));
		
		AgentConfigurationModuleMapping agentConfigurationModuleMapping1 =
				new AgentConfigurationModuleMapping();		
		AgentConfigurationModuleMapping agentConfigurationModuleMapping2 =
				new AgentConfigurationModuleMapping();
		
		assertThat(agentConfigurationModuleMapping1, is(agentConfigurationModuleMapping1));
		assertThat(agentConfigurationModuleMapping1.hashCode(), is(agentConfigurationModuleMapping1.hashCode()));
		agentConfigurationModuleMapping1.setAcid(1);
		assertThat(agentConfigurationModuleMapping1, is(not(agentConfigurationModuleMapping2)));
		assertThat(agentConfigurationModuleMapping1.hashCode(), is(not(agentConfigurationModuleMapping2.hashCode())));
		agentConfigurationModuleMapping2.setAcid(1);
		assertThat(agentConfigurationModuleMapping1, is(agentConfigurationModuleMapping2));
		assertThat(agentConfigurationModuleMapping1.hashCode(), is(agentConfigurationModuleMapping2.hashCode()));
		assertThat(agentConfigurationModuleMapping1, is(agentConfigurationModuleMapping1));
		assertThat(agentConfigurationModuleMapping1.hashCode(), is(agentConfigurationModuleMapping1.hashCode()));
		agentConfigurationModuleMapping1.setAmid(2);
		assertThat(agentConfigurationModuleMapping1, is(not(agentConfigurationModuleMapping2)));
		assertThat(agentConfigurationModuleMapping1.hashCode(), is(not(agentConfigurationModuleMapping2.hashCode())));
		agentConfigurationModuleMapping2.setAmid(2);
		assertThat(agentConfigurationModuleMapping1, is(agentConfigurationModuleMapping2));
		assertThat(agentConfigurationModuleMapping1.hashCode(), is(agentConfigurationModuleMapping2.hashCode()));
		
		assertThat(agentConfigurationModuleMapping1.equals(null), is(false));
		assertThat(((Object)agentConfigurationModuleMapping1).equals(""), is(false));
		
		assertThat(new AgentConfigurationParameter(), 
				is(new AgentConfigurationParameter()));
		
		AgentConfigurationParameter agentConfigurationParameter1 =
				new AgentConfigurationParameter();		
		AgentConfigurationParameter agentConfigurationParameter2 =
				new AgentConfigurationParameter();
		
		assertThat(agentConfigurationParameter1, is(agentConfigurationParameter1));
		assertThat(agentConfigurationParameter1.hashCode(), is(agentConfigurationParameter1.hashCode()));
		agentConfigurationParameter1.setAgentConfiguration(agentConfiguration1);
		assertThat(agentConfigurationParameter1, is(not(agentConfigurationParameter2)));
		assertThat(agentConfigurationParameter1.hashCode(), is(not(agentConfigurationParameter2.hashCode())));
		agentConfigurationParameter2.setAgentConfiguration(agentConfiguration1);
		assertThat(agentConfigurationParameter1, is(agentConfigurationParameter2));
		assertThat(agentConfigurationParameter1.hashCode(), is(agentConfigurationParameter2.hashCode()));
		assertThat(agentConfigurationParameter1, is(agentConfigurationParameter1));
		assertThat(agentConfigurationParameter1.hashCode(), is(agentConfigurationParameter1.hashCode()));
		agentConfigurationParameter1.setAgentModule(agentModule1);
		assertThat(agentConfigurationParameter1, is(not(agentConfigurationParameter2)));
		assertThat(agentConfigurationParameter1.hashCode(), is(not(agentConfigurationParameter2.hashCode())));
		agentConfigurationParameter2.setAgentModule(agentModule1);
		assertThat(agentConfigurationParameter1, is(agentConfigurationParameter2));
		assertThat(agentConfigurationParameter1.hashCode(), is(agentConfigurationParameter2.hashCode()));
		assertThat(agentConfigurationParameter1, is(agentConfigurationParameter1));
		assertThat(agentConfigurationParameter1.hashCode(), is(agentConfigurationParameter1.hashCode()));
		agentConfigurationParameter1.setName("Test");
		assertThat(agentConfigurationParameter1, is(not(agentConfigurationParameter2)));
		assertThat(agentConfigurationParameter1.hashCode(), is(not(agentConfigurationParameter2.hashCode())));
		agentConfigurationParameter2.setName("Test");
		assertThat(agentConfigurationParameter1, is(agentConfigurationParameter2));
		assertThat(agentConfigurationParameter1.hashCode(), is(agentConfigurationParameter2.hashCode()));

		assertThat(agentConfigurationParameter1.equals(null), is(false));
		assertThat(((Object)agentConfigurationParameter1).equals(""), is(false));
				
		assertThat(new MaxID(), is(new MaxID()));
		MaxID maxID1 = new MaxID();
		maxID1.setMaxIDName("Test");
		MaxID maxID2 = new MaxID();
		assertThat(maxID1, is(not(maxID2)));
		maxID2.setMaxIDName("Test");
		assertThat(maxID1, is(maxID2));
		assertThat(maxID1.equals(null), is(false));
		assertThat(((Object)maxID1).equals(""), is(false));
		
		assertThat(new ProcessingLog(), is(new ProcessingLog()));
		ProcessingLog processingLog1 = new ProcessingLog();
		processingLog1.setMid("Test");
		ProcessingLog processingLog2 = new ProcessingLog();
		assertThat(processingLog1, is(not(processingLog2)));
		processingLog2.setMid("Test");
		assertThat(processingLog1, is(processingLog2));
		assertThat(processingLog1.equals(null), is(false));
		assertThat(((Object)processingLog1).equals(""), is(false));


		assertThat(new ResultExceptionLog(), is(new ResultExceptionLog()));
		
		ResultExceptionLog resultExceptionLog1 = new ResultExceptionLog();
		ResultExceptionLog resultExceptionLog2 = new ResultExceptionLog();

		Date date = new Date(System.currentTimeMillis()-100000);
		
		assertThat(resultExceptionLog1, is(resultExceptionLog1));
		assertThat(resultExceptionLog1.hashCode(), is(resultExceptionLog1.hashCode()));
		resultExceptionLog1.setAgentConfiguration(agentConfiguration1);
		assertThat(resultExceptionLog1, is(not(resultExceptionLog2)));
		assertThat(resultExceptionLog1.hashCode(), is(not(resultExceptionLog2.hashCode())));
		resultExceptionLog2.setAgentConfiguration(agentConfiguration1);
		assertThat(resultExceptionLog1, is(resultExceptionLog2));
		assertThat(resultExceptionLog1.hashCode(), is(resultExceptionLog2.hashCode()));
		assertThat(resultExceptionLog1, is(resultExceptionLog1));
		assertThat(resultExceptionLog1.hashCode(), is(resultExceptionLog1.hashCode()));
		resultExceptionLog1.setAgentModule(agentModule1);
		assertThat(resultExceptionLog1, is(not(resultExceptionLog2)));
		assertThat(resultExceptionLog1.hashCode(), is(not(resultExceptionLog2.hashCode())));
		resultExceptionLog2.setAgentModule(agentModule1);
		assertThat(resultExceptionLog1, is(resultExceptionLog2));
		assertThat(resultExceptionLog1.hashCode(), is(resultExceptionLog2.hashCode()));
		assertThat(resultExceptionLog1, is(resultExceptionLog1));
		assertThat(resultExceptionLog1.hashCode(), is(resultExceptionLog1.hashCode()));
		resultExceptionLog1.setExceptionData("Test".getBytes());
		assertThat(resultExceptionLog1, is(not(resultExceptionLog2)));
		assertThat(resultExceptionLog1.hashCode(), is(not(resultExceptionLog2.hashCode())));
		resultExceptionLog2.setExceptionData("Test".getBytes());
		assertThat(resultExceptionLog1, is(resultExceptionLog2));
		assertThat(resultExceptionLog1.hashCode(), is(resultExceptionLog2.hashCode()));
		assertThat(resultExceptionLog1, is(resultExceptionLog1));
		assertThat(resultExceptionLog1.hashCode(), is(resultExceptionLog1.hashCode()));
		resultExceptionLog1.setExceptionDt(date);
		assertThat(resultExceptionLog1, is(not(resultExceptionLog2)));
		assertThat(resultExceptionLog1.hashCode(), is(not(resultExceptionLog2.hashCode())));
		resultExceptionLog2.setExceptionDt(date);
		assertThat(resultExceptionLog1, is(resultExceptionLog2));
		assertThat(resultExceptionLog1.hashCode(), is(resultExceptionLog2.hashCode()));
		assertThat(resultExceptionLog1, is(resultExceptionLog1));
		assertThat(resultExceptionLog1.hashCode(), is(resultExceptionLog1.hashCode()));
		resultExceptionLog1.setExceptionType("Test2");
		assertThat(resultExceptionLog1, is(not(resultExceptionLog2)));
		assertThat(resultExceptionLog1.hashCode(), is(not(resultExceptionLog2.hashCode())));
		resultExceptionLog2.setExceptionType("Test2");
		assertThat(resultExceptionLog1, is(resultExceptionLog2));
		assertThat(resultExceptionLog1.hashCode(), is(resultExceptionLog2.hashCode()));
		assertThat(resultExceptionLog1, is(resultExceptionLog1));
		assertThat(resultExceptionLog1.hashCode(), is(resultExceptionLog1.hashCode()));
		resultExceptionLog1.setResultData("Test3".getBytes());
		assertThat(resultExceptionLog1, is(not(resultExceptionLog2)));
		assertThat(resultExceptionLog1.hashCode(), is(not(resultExceptionLog2.hashCode())));
		resultExceptionLog2.setResultData("Test3".getBytes());
		assertThat(resultExceptionLog1, is(resultExceptionLog2));
		assertThat(resultExceptionLog1.hashCode(), is(resultExceptionLog2.hashCode()));
		assertThat(resultExceptionLog1.equals(null), is(false));
		assertThat(((Object)resultExceptionLog1).equals(""), is(false));
		
		assertThat(new TransactionLog(), is(new TransactionLog()));

		TransactionLog transactionLog1 = new TransactionLog();
		TransactionLog transactionLog2 = new TransactionLog();
		
		assertThat(transactionLog1, is(transactionLog1));
		assertThat(transactionLog1.hashCode(), is(transactionLog1.hashCode()));
		transactionLog1.setAction("Test");
		assertThat(transactionLog1, is(not(transactionLog2)));
		assertThat(transactionLog1.hashCode(), is(not(transactionLog2.hashCode())));
		transactionLog2.setAction("Test");
		assertThat(transactionLog1, is(transactionLog2));
		assertThat(transactionLog1.hashCode(), is(transactionLog2.hashCode()));
		assertThat(transactionLog1, is(transactionLog1));
		assertThat(transactionLog1.hashCode(), is(transactionLog1.hashCode()));
		transactionLog1.setProcessingLog(processingLog1);
		assertThat(transactionLog1, is(not(transactionLog2)));
		assertThat(transactionLog1.hashCode(), is(not(transactionLog2.hashCode())));
		transactionLog2.setProcessingLog(processingLog1);
		assertThat(transactionLog1, is(transactionLog2));
		assertThat(transactionLog1.hashCode(), is(transactionLog2.hashCode()));
		assertThat(transactionLog1, is(transactionLog1));
		assertThat(transactionLog1.hashCode(), is(transactionLog1.hashCode()));
		transactionLog1.setTranDt(date);
		assertThat(transactionLog1, is(not(transactionLog2)));
		assertThat(transactionLog1.hashCode(), is(not(transactionLog2.hashCode())));
		transactionLog2.setTranDt(date);
		assertThat(transactionLog1, is(transactionLog2));
		assertThat(transactionLog1.hashCode(), is(transactionLog2.hashCode()));
		assertThat(transactionLog1, is(transactionLog1));
		assertThat(transactionLog1.hashCode(), is(transactionLog1.hashCode()));
		transactionLog1.setUsername("Name");
		assertThat(transactionLog1, is(not(transactionLog2)));
		assertThat(transactionLog1.hashCode(), is(not(transactionLog2.hashCode())));
		transactionLog2.setUsername("Name");
		assertThat(transactionLog1, is(transactionLog2));
		assertThat(transactionLog1.hashCode(), is(transactionLog2.hashCode()));
		assertThat(transactionLog1.equals(null), is(false));
		assertThat(((Object)transactionLog1).equals(""), is(false));

		assertThat(new VariableCache(), is(new VariableCache()));

		VariableCache variableCache1 = new VariableCache();
		VariableCache variableCache2 = new VariableCache();
		
		assertThat(variableCache1, is(variableCache1));
		assertThat(variableCache1.hashCode(), is(variableCache1.hashCode()));
		variableCache1.setAppKey("Test");
		assertThat(variableCache1, is(not(variableCache2)));
		assertThat(variableCache1.hashCode(), is(not(variableCache2.hashCode())));
		variableCache2.setAppKey("Test");
		assertThat(variableCache1, is(variableCache2));
		assertThat(variableCache1.hashCode(), is(variableCache2.hashCode()));
		assertThat(variableCache1.equals(null), is(false));
		assertThat(((Object)variableCache1).equals(""), is(false));
	}
	
	private void pause(long time){
		long start = System.currentTimeMillis();
		while (System.currentTimeMillis() - start < time);
	}
	
	@Test
	public void testShutdown() throws Exception {
		AgentConfiguration agentConfiguration = new AgentConfiguration();
		agentConfiguration.setActive(true);
		agentConfiguration.setAgentName("Test Shutdown Agent 1");
		agentConfiguration.setType(RunType.LOOPED);
		
		AgentModule agentModule = new AgentModule();
		agentModule.setId(1);
		agentModule.setModuleClassName(TestDataSpooler.class.getName());
		agentModule.setModuleName("Test Module 1.1");
		agentModule.setModuleType(ModuleType.DATASPOOLER);
		
		agentConfiguration.getAgentModules().add(agentModule);
		
		agentModule = new AgentModule();
		agentModule.setId(22);
		agentModule.setAgentConfiguration(agentConfiguration);
		agentModule.setModuleClassName(TestFormatter1.class.getName());
		agentModule.setModuleName("Test Shutdown Formatter");
		agentModule.setModuleType(ModuleType.FORMATTER);
		
		agentConfiguration.getAgentModules().add(agentModule);

		agentModule = new AgentModule();
		agentModule.setId(22);
		agentModule.setAgentConfiguration(agentConfiguration);
		agentModule.setModuleClassName(TestMessenger1.class.getName());
		agentModule.setModuleName("Test Shutdown Messenger");
		agentModule.setModuleType(ModuleType.MESSENGER);
		agentConfiguration.getAgentModules().add(agentModule);

		SysHub sysHubMain = CDI.current().select(SysHub.class).get();
		
		sysHubMain.deployAgent(agentConfiguration);
		ProcessingAgent processingAgent = sysHubMain.fetchAgentRunner(agentConfiguration).getProcessingAgent();
		sysHubMain.undeployAgent(agentConfiguration);
		
		assertThat(processingAgent.getDataSpoolers().size(), is(1));
		assertThat(processingAgent.getMessengerFormatterMapper().size(), is(1));
		processingAgent.getDataSpoolers()
				.forEach(dataSpooler -> assertThat(((ShutdownMonitor) dataSpooler).isShutdown(), is(false)));
		processingAgent.getMessengerFormatterMapper().keySet()
				.forEach(formatter -> assertThat(((ShutdownMonitor) formatter).isShutdown(), is(true)));
		processingAgent.getMessengerFormatterMapper().values()
				.forEach(messenger -> assertThat(((ShutdownMonitor) messenger).isShutdown(), is(true)));
		
		processingAgent = new ProcessingAgent();
		processingAgent.shutdown();
	}
	
	@Test
	public void testParameterBroadcast() throws Exception {
		AgentConfigurationParameter parameter = new AgentConfigurationParameter();
		EventType eventType = EventType.UPDATED;
		
		ParameterEventBroadcaster parameterEventBroadcaster = CDI.current()
				.select(ParameterEventBroadcaster.class).get();
		
		List<ParameterEventListener> listeners = Collections
				.synchronizedList(new ArrayList<>());
		for(int i=0;i<1000;i++) {
			listeners.add(e->{});
		}
		
		Executor executor1 = Executors.newFixedThreadPool(4),
				executor2 = Executors.newFixedThreadPool(4);
		
		class ExceptionHolder {
			Exception e;
		}
		
		ExceptionHolder holder = new ExceptionHolder();
		
		for(int i=0;i<1000;i++) {
			ParameterEventListener listener = listeners.get(i);
			parameterEventBroadcaster.registerListener(listener);
			executor1.execute(()->{
				try {
					parameterEventBroadcaster.broadcast(new ParameterEvent(parameter, eventType));
					executor2.execute(()->{
						try {
							parameterEventBroadcaster.unregisterListener(listener);
						} catch (ConcurrentModificationException e) {
							holder.e = e;
						}
					});
				} catch (ConcurrentModificationException e) {
					holder.e = e;
				}
			});
		}

		assertNull(holder.e);
	}
}
