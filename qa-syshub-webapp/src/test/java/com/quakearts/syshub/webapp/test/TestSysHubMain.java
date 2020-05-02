package com.quakearts.syshub.webapp.test;

import java.util.Base64;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.UserTransaction;

import com.quakearts.appbase.cdi.annotation.Transaction;
import com.quakearts.security.cryptography.jpa.EncryptedValue;
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
		dataStore.save(agentConfiguration);
		dataStore.flushBuffers();
		
		AgentModule agentModule = new AgentModule();
		agentModule.setId(1);
		agentModule.setAgentConfiguration(agentConfiguration);
		agentModule.setModuleClassName(TestDataSpooler.class.getName());
		agentModule.setModuleName("Test Module 1.1");
		agentModule.setModuleType(ModuleType.DATASPOOLER);
		
		AgentConfigurationParameter	parameter = new AgentConfigurationParameter("test.class", ParameterType.CLASS);
		parameter.setStringValue(TestDataSpooler.class.getName());
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);
		
		parameter = new AgentConfigurationParameter("test.cronconfiguration", ParameterType.CRONCONFIGURATION);
		parameter.setStringValue("0 0/1 * * * ? *");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);
		
		parameter = new AgentConfigurationParameter("test.email", ParameterType.EMAIL);
		parameter.setStringValue("name@server.com");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);

		parameter = new AgentConfigurationParameter("test.endpointaddress", ParameterType.ENDPOINTADDRESS);
		parameter.setStringValue("name@server.com");
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
		
		parameter = new AgentConfigurationParameter("test.file", ParameterType.FILE);
		parameter.setStringValue("/test/file");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);
		
		parameter = new AgentConfigurationParameter("test.jndiname", ParameterType.JNDINAME);
		parameter.setStringValue("java:/test/name");
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

		parameter = new AgentConfigurationParameter("test.password", ParameterType.PASSWORD);
		EncryptedValue encryptedValue = new EncryptedValue();
		encryptedValue.setStringValue("Password1");
		parameter.setEncryptedValue(encryptedValue);
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);

		parameter = new AgentConfigurationParameter("test.string", ParameterType.STRING);
		parameter.setStringValue("3DEF");
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
		parameter.setStringValue("0/15 * * * * ? *");
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
		
		parameter = new AgentConfigurationParameter("test.class", ParameterType.CLASS);
		parameter.setStringValue(TestDataSpooler.class.getName());
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);
		
		parameter = new AgentConfigurationParameter("test.cronconfiguration", ParameterType.CRONCONFIGURATION);
		parameter.setStringValue("0 * * * * ? *");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);
		
		parameter = new AgentConfigurationParameter("test.email", ParameterType.EMAIL);
		parameter.setStringValue("name@server.com");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);

		parameter = new AgentConfigurationParameter("test.endpointaddress", ParameterType.ENDPOINTADDRESS);
		parameter.setStringValue("name@server.com");
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
		
		parameter = new AgentConfigurationParameter("test.class", ParameterType.CLASS);
		parameter.setStringValue(TestDataSpooler.class.getName());
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);
		
		parameter = new AgentConfigurationParameter("test.cronconfiguration", ParameterType.CRONCONFIGURATION);
		parameter.setStringValue("0 * * * * ? *");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);
		
		parameter = new AgentConfigurationParameter("test.email", ParameterType.EMAIL);
		parameter.setStringValue("name@server.com");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);

		parameter = new AgentConfigurationParameter("test.endpointaddress", ParameterType.ENDPOINTADDRESS);
		parameter.setStringValue("name@server.com");
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
		
		parameter = new AgentConfigurationParameter("test.file", ParameterType.FILE);
		parameter.setStringValue("/test/file");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);
		
		parameter = new AgentConfigurationParameter("test.jndiname", ParameterType.JNDINAME);
		parameter.setStringValue("java:/test/name");
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
		
		parameter = new AgentConfigurationParameter("test.list", ParameterType.LIST);
		parameter.setStringValue("value3");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);

		parameter = new AgentConfigurationParameter("test.numeric", ParameterType.NUMERIC);
		parameter.setNumericValue(20.0d);
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
		
		parameter = new AgentConfigurationParameter("test.password", ParameterType.PASSWORD);
		parameter.setStringValue("Password1");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);

		parameter = new AgentConfigurationParameter("test.string", ParameterType.STRING);
		parameter.setStringValue("3DEF");
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
		
		parameter = new AgentConfigurationParameter();
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setName("test.binary");
		parameter.setParameterType(ParameterType.BINARY);
		parameter.setStringValue(Base64.getEncoder().encodeToString("Test".getBytes()));
		agentConfiguration.getParameters().add(parameter);

		parameter = new AgentConfigurationParameter();
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setName("test.boolean");
		parameter.setParameterType(ParameterType.BOOLEAN);
		parameter.setBooleanValue(false);
		agentConfiguration.getParameters().add(parameter);
		
		dataStore.save(agentConfiguration);
		dataStore.flushBuffers();

		agentModule = new AgentModule();
		agentModule.setId(10);
		agentModule.setAgentConfiguration(agentConfiguration);
		agentModule.setModuleClassName(TestDataSpooler.class.getName());
		agentModule.setModuleName("Test Module 3.1");
		agentModule.setModuleType(ModuleType.DATASPOOLER);
		
		parameter = new AgentConfigurationParameter("test.class", ParameterType.CLASS);
		parameter.setStringValue(TestDataSpooler.class.getName());
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);
		
		parameter = new AgentConfigurationParameter("test.cronconfiguration", ParameterType.CRONCONFIGURATION);
		parameter.setStringValue("0 * * * * ? *");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);
		
		parameter = new AgentConfigurationParameter("test.email", ParameterType.EMAIL);
		parameter.setStringValue("name@server.com");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);

		parameter = new AgentConfigurationParameter("test.endpointaddress", ParameterType.ENDPOINTADDRESS);
		parameter.setStringValue("name@server.com");
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
		
		parameter = new AgentConfigurationParameter("test.list", ParameterType.LIST);
		parameter.setStringValue("value3");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);

		parameter = new AgentConfigurationParameter("test.numeric", ParameterType.NUMERIC);
		parameter.setNumericValue(20.0d);
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
		
		parameter = new AgentConfigurationParameter();
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setName("test.binary");
		parameter.setParameterType(ParameterType.BINARY);
		parameter.setStringValue(Base64.getEncoder().encodeToString("Test".getBytes()));
		agentConfiguration.getParameters().add(parameter);

		parameter = new AgentConfigurationParameter();
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setName("test.boolean");
		parameter.setParameterType(ParameterType.BOOLEAN);
		parameter.setBooleanValue(false);
		agentConfiguration.getParameters().add(parameter);

		dataStore.save(agentConfiguration);
		dataStore.flushBuffers();

		agentModule = new AgentModule();
		agentModule.setId(14);
		agentModule.setAgentConfiguration(agentConfiguration);
		agentModule.setModuleClassName(TestDataSpooler.class.getName());
		agentModule.setModuleName("Test Error Throwing 1");
		agentModule.setModuleType(ModuleType.DATASPOOLER);
		
		parameter = new AgentConfigurationParameter("test.class", ParameterType.CLASS);
		parameter.setStringValue(TestDataSpooler.class.getName());
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);
		
		parameter = new AgentConfigurationParameter("test.cronconfiguration", ParameterType.CRONCONFIGURATION);
		parameter.setStringValue("0 * * * * ? *");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);
		
		parameter = new AgentConfigurationParameter("test.email", ParameterType.EMAIL);
		parameter.setStringValue("name@server.com");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);

		parameter = new AgentConfigurationParameter("test.endpointaddress", ParameterType.ENDPOINTADDRESS);
		parameter.setStringValue("name@server.com");
		parameter.setAgentConfiguration(agentConfiguration);
		parameter.setAgentModule(agentModule);
		agentModule.getParameters().add(parameter);
		
		dataStore.save(agentModule);
		dataStore.flushBuffers();
		userTransaction.commit();
		
		sysHubMain.init();
	}
}
