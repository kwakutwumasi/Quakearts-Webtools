package com.quakearts.syshub.webapp.test;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.UserTransaction;

import com.quakearts.appbase.cdi.annotation.Transaction;
import com.quakearts.syshub.SysHubMain;
import com.quakearts.syshub.core.utils.SystemDataStoreManager;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentConfiguration.RunType;
import com.quakearts.syshub.model.AgentConfigurationParameter;
import com.quakearts.syshub.model.AgentConfigurationParameter.ParameterType;
import com.quakearts.syshub.model.AgentModule;
import com.quakearts.syshub.model.AgentModule.ModuleType;
import com.quakearts.webapp.orm.DataStore;

@Singleton
public class TestSysHubMain {

	@Inject @Transaction
	private UserTransaction userTransaction;
	
	@Inject
	private SystemDataStoreManager storeManager;

	@Inject
	SysHubMain sysHubMain;
	
	public void init() throws Exception {
	
		userTransaction.begin();
		
		DataStore dataStore = storeManager.getDataStore();
				
		AgentConfiguration agentConfiguration = new AgentConfiguration();
		agentConfiguration.setId(1);
		agentConfiguration.setActive(true);
		agentConfiguration.setAgentName("Test Agent 1");
		agentConfiguration.setType(RunType.LOOPED);
		
		AgentConfigurationParameter parameter = new AgentConfigurationParameter("testParameter1");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setBase64String("Test Bytes".getBytes());
		agentConfiguration.getParameters().add(parameter);

		dataStore.save(agentConfiguration);
		dataStore.flushBuffers();
		
		AgentModule agentModule = new AgentModule();
		agentModule.setId(1);
		agentModule.setAgentConfiguration(agentConfiguration);
		agentModule.setModuleClassName(TestDataSpooler.class.getName());
		agentModule.setModuleName("Test Module 1.1");
		agentModule.setModuleType(ModuleType.DATASPOOLER);
		parameter = new AgentConfigurationParameter("testParameter1", ParameterType.BOOLEAN);
		parameter.setBooleanValue(true);
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);
		
		dataStore.save(agentModule);
		
		agentModule = new AgentModule();
		agentModule.setId(2);
		agentModule.setAgentConfiguration(agentConfiguration);
		agentModule.setModuleClassName(TestFormatter1.class.getName());
		agentModule.setModuleName("Test Module 1.2");
		agentModule.setModuleType(ModuleType.FORMATTER);
		parameter = new AgentConfigurationParameter("testParameter1", ParameterType.NUMERIC);
		parameter.setNumericValue(20.0d);
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);

		dataStore.save(agentModule);
		
		agentModule = new AgentModule();
		agentModule.setId(3);
		agentModule.setAgentConfiguration(agentConfiguration);
		agentModule.setModuleClassName(TestMessenger1.class.getName());
		agentModule.setModuleName("Test Module 1.3");
		agentModule.setModuleType(ModuleType.MESSENGER);
		parameter = new AgentConfigurationParameter("testParameter1", ParameterType.EMAIL);
		parameter.setStringValue("test@server.com");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);

		dataStore.save(agentModule);	
		
		agentConfiguration = new AgentConfiguration();
		agentConfiguration.setId(2);
		agentConfiguration.setActive(true);
		agentConfiguration.setAgentName("Test Agent 2");
		agentConfiguration.setType(RunType.SCHEDULED);
		
		parameter = new AgentConfigurationParameter();
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setGlobal(true);
		parameter.setName("schedule.cron");
		parameter.setParameterType(ParameterType.CRONCONFIGURATION);
		parameter.setStringValue("* * * ? * *");
		agentConfiguration.getParameters().add(parameter);
		
		parameter = new AgentConfigurationParameter();
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setGlobal(true);
		parameter.setName("maxFormatterWorkers");
		parameter.setParameterType(ParameterType.NUMERIC);
		parameter.setNumericValue(10.0d);
		agentConfiguration.getParameters().add(parameter);
		
		parameter = new AgentConfigurationParameter();
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setGlobal(true);
		parameter.setName("maxDataSpoolerWorkers");
		parameter.setParameterType(ParameterType.NUMERIC);
		parameter.setNumericValue(10.0d);
		agentConfiguration.getParameters().add(parameter);

		parameter = new AgentConfigurationParameter();
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setGlobal(true);
		parameter.setName("maximumPoolSize");
		parameter.setParameterType(ParameterType.NUMERIC);
		parameter.setNumericValue(8.0d);
		agentConfiguration.getParameters().add(parameter);

		dataStore.save(agentConfiguration);
		dataStore.flushBuffers();

		agentModule = new AgentModule();
		agentModule.setId(4);
		agentModule.setAgentConfiguration(agentConfiguration);
		agentModule.setModuleClassName(TestDataSpooler.class.getName());
		agentModule.setModuleName("Test Module 2.1");
		agentModule.setModuleType(ModuleType.DATASPOOLER);
		parameter = new AgentConfigurationParameter("testParameter1", ParameterType.ENDPOINTADDRESS);
		parameter.setStringValue("127.0.0.1");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);
		
		dataStore.save(agentModule);

		agentModule = new AgentModule();
		agentModule.setId(5);
		agentModule.setAgentConfiguration(agentConfiguration);
		agentModule.setModuleClassName(TestDataSpooler.class.getName());
		agentModule.setModuleName("Test Module 2.2");
		agentModule.setModuleType(ModuleType.DATASPOOLER);
		parameter = new AgentConfigurationParameter("testParameter1", ParameterType.ENDPOINTADDRESS);
		parameter.setStringValue("127.0.0.1");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);
		
		dataStore.save(agentModule);

		agentModule = new AgentModule();
		agentModule.setId(6);
		agentModule.setAgentConfiguration(agentConfiguration);
		agentModule.setModuleClassName(TestFormatter1.class.getName());
		agentModule.setModuleName("Test Module 2.3");
		agentModule.setModuleType(ModuleType.FORMATTER);
		parameter = new AgentConfigurationParameter("testParameter1", ParameterType.FILE);
		parameter.setStringValue("test/file.ext");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);

		dataStore.save(agentModule);
		
		agentModule = new AgentModule();
		agentModule.setId(7);
		agentModule.setAgentConfiguration(agentConfiguration);
		agentModule.setModuleClassName(TestFormatter2.class.getName());
		agentModule.setModuleName("Test Module 2.4");
		agentModule.setModuleType(ModuleType.FORMATTER);
		parameter = new AgentConfigurationParameter("testParameter1", ParameterType.JNDINAME);
		parameter.setStringValue("java:/comp/Address");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);

		dataStore.save(agentModule);

		agentModule = new AgentModule();
		agentModule.setId(8);
		agentModule.setAgentConfiguration(agentConfiguration);
		agentModule.setModuleClassName(TestMessenger1.class.getName());
		agentModule.setModuleName("Test Module 2.5");
		agentModule.setModuleType(ModuleType.MESSENGER);
		agentModule.setMappedModuleName("Test Module 2.3");
		parameter = new AgentConfigurationParameter("testParameter1", ParameterType.LIST);
		parameter.setGlobal(true);
		parameter.setStringValue("Option1");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);

		dataStore.save(agentModule);
		
		agentModule = new AgentModule();
		agentModule.setId(9);
		agentModule.setAgentConfiguration(agentConfiguration);
		agentModule.setModuleClassName(TestMessenger2.class.getName());
		agentModule.setModuleName("Test Module 2.6");
		agentModule.setModuleType(ModuleType.MESSENGER);
		parameter = new AgentConfigurationParameter("testParameter1", ParameterType.PASSWORD);
		parameter.setGlobal(true);
		parameter.setStringValue("password1");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);

		dataStore.save(agentModule);

		agentConfiguration = new AgentConfiguration();
		agentConfiguration.setId(3);
		agentConfiguration.setActive(true);
		agentConfiguration.setAgentName("Test Agent 3");
		agentConfiguration.setType(RunType.TRIGGERED);
		
		parameter = new AgentConfigurationParameter();
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setName("trigger.class");
		parameter.setParameterType(ParameterType.CLASS);
		parameter.setStringValue(TestAgentTrigger1.class.getName());
		agentConfiguration.getParameters().add(parameter);
		
		dataStore.save(agentConfiguration);
		dataStore.flushBuffers();

		agentModule = new AgentModule();
		agentModule.setId(10);
		agentModule.setAgentConfiguration(agentConfiguration);
		agentModule.setModuleClassName(TestDataSpooler.class.getName());
		agentModule.setModuleName("Test Module 3.1");
		agentModule.setModuleType(ModuleType.DATASPOOLER);
		parameter = new AgentConfigurationParameter("testParameter1", ParameterType.ENDPOINTADDRESS);
		parameter.setStringValue("127.0.0.1");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);
		
		dataStore.save(agentModule);
		
		agentModule = new AgentModule();
		agentModule.setId(11);
		agentModule.setAgentConfiguration(agentConfiguration);
		agentModule.setModuleClassName(TestDataSpooler.class.getName());
		agentModule.setModuleName("Test Module 3.2");
		agentModule.setModuleType(ModuleType.DATASPOOLER);
		parameter = new AgentConfigurationParameter("testParameter1", ParameterType.FILE);
		parameter.setStringValue("test/file.ext");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);

		dataStore.save(agentModule);
		
		agentModule = new AgentModule();
		agentModule.setId(12);
		agentModule.setAgentConfiguration(agentConfiguration);
		agentModule.setModuleClassName(TestFormatter2.class.getName());
		agentModule.setModuleName("Test Module 3.3");
		agentModule.setModuleType(ModuleType.FORMATTER);
		parameter = new AgentConfigurationParameter("testParameter1", ParameterType.JNDINAME);
		parameter.setStringValue("java:/comp/Address");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);

		dataStore.save(agentModule);
		
		agentModule = new AgentModule();
		agentModule.setId(13);
		agentModule.setAgentConfiguration(agentConfiguration);
		agentModule.setModuleClassName(TestMessenger2.class.getName());
		agentModule.setModuleName("Test Module 3.4");
		agentModule.setModuleType(ModuleType.MESSENGER);
		agentModule.setMappedModuleName("Test Module 3.3");
		parameter = new AgentConfigurationParameter("testParameter1", ParameterType.LIST);
		parameter.setStringValue("Option1");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);

		dataStore.save(agentModule);
									
		agentConfiguration = new AgentConfiguration();
		agentConfiguration.setId(4);
		agentConfiguration.setActive(true);
		agentConfiguration.setAgentName("Test Error Throwing Agent");
		agentConfiguration.setType(RunType.TRIGGERED);
		
		parameter = new AgentConfigurationParameter();
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setName("trigger.class");
		parameter.setParameterType(ParameterType.CLASS);
		parameter.setStringValue(TestAgentTrigger1.class.getName());
		agentConfiguration.getParameters().add(parameter);
		
		dataStore.save(agentConfiguration);
		dataStore.flushBuffers();

		agentModule = new AgentModule();
		agentModule.setId(14);
		agentModule.setAgentConfiguration(agentConfiguration);
		agentModule.setModuleClassName(TestDataSpooler.class.getName());
		agentModule.setModuleName("Test Error Throwing 1");
		agentModule.setModuleType(ModuleType.DATASPOOLER);
		parameter = new AgentConfigurationParameter("throw.error", ParameterType.STRING);
		parameter.setStringValue("Error");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);
		
		dataStore.save(agentModule);
		dataStore.flushBuffers();
		userTransaction.commit();
		
		sysHubMain.init();
	}
}
