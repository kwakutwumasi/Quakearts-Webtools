package com.quakearts.syshub.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.UserTransaction;

import com.quakearts.appbase.Main;
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
import com.quakearts.syshub.test.helper.TestAgentTrigger1;
import com.quakearts.syshub.test.helper.TestDataSpooler;
import com.quakearts.syshub.test.helper.TestFormatter1;
import com.quakearts.syshub.test.helper.TestFormatter2;
import com.quakearts.syshub.test.helper.TestMessenger1;
import com.quakearts.syshub.test.helper.TestMessenger2;
import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStoreConnection;

@Singleton
public class TestSysHubMain {

	@Inject @Transaction
	private UserTransaction userTransaction;
	
	@Inject
	private SystemDataStoreManager storeManager;

	@Inject
	SysHubMain sysHubMain;
	
	public void init() throws Exception {
		try {
			userTransaction.begin();
			
			DataStore dataStore = storeManager.getDataStore();
					
			dataStore.executeFunction(this::createAndDropDatabase);
			
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
			EncryptedValue encryptedValue = new EncryptedValue();
			encryptedValue.setStringValue("password");
			parameter.setEncryptedValue(encryptedValue);
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
		} finally {
			StartUp.getStartUp().startUpComplete();
		}
	}
	
	private void createAndDropDatabase(DataStoreConnection connection){
		Connection sqlConnection = connection.getConnection(Connection.class);
		executeStatement("ALTER TABLE DBO.RESULT_EXCEPTION_LOG DROP CONSTRAINT FK6AGN6815VRMA9Q0583T3HW5R4", sqlConnection);
		executeStatement("ALTER TABLE DBO.AGENT_MODULE DROP CONSTRAINT FK50Y4HDXX533JN9MDX79PGID3K", sqlConnection);
		executeStatement("ALTER TABLE DBO.PROCESSING_LOG DROP CONSTRAINT FKEV743F87VM83YFOE0UT8HVRH2", sqlConnection);
		executeStatement("ALTER TABLE DBO.AGENT_CONFIGURATION_PARAMETERS DROP CONSTRAINT FKHW9418RROYOT0HPBA9C272HNM", sqlConnection);
		executeStatement("ALTER TABLE DBO.TRANSACTION_LOG DROP CONSTRAINT FKEOJ9BANKVKRYMAUOVGLQGUO4S", sqlConnection);
		executeStatement("ALTER TABLE DBO.AGENT_CONFIGURATION_MODULE_MAPPING DROP CONSTRAINT FKIP9VF4FH61RD2TBWUDQWCEJ66", sqlConnection);
		executeStatement("ALTER TABLE DBO.AGENT_CONFIGURATION_MODULE_MAPPING DROP CONSTRAINT FKH752X0CL8W8F5UO8K062EF00", sqlConnection);
		executeStatement("ALTER TABLE DBO.RESULT_EXCEPTION_LOG DROP CONSTRAINT FK6V1NA8MAKFP62E2KYP5L8RRDI", sqlConnection);
		executeStatement("ALTER TABLE DBO.AGENT_CONFIGURATION_PARAMETERS DROP CONSTRAINT FK8EFMPHG62YMQUSFPEHY445VEJ", sqlConnection);
		executeStatement("ALTER TABLE DBO.PROCESSING_LOG DROP CONSTRAINT FKA31ELILHWYO079WBFDEJ72384", sqlConnection);
		executeStatement("ALTER TABLE DBO.MAX_ID DROP CONSTRAINT SQL190319113342920", sqlConnection);
		executeStatement("ALTER TABLE DBO.AGENT_CONFIGURATION_MODULE_MAPPING DROP CONSTRAINT SQL190319113342850", sqlConnection);
		executeStatement("ALTER TABLE DBO.PROCESSING_LOG DROP CONSTRAINT SQL190319113342950", sqlConnection);
		executeStatement("ALTER TABLE DBO.VARIABLE_CACHE DROP CONSTRAINT SQL190319113343050", sqlConnection);
		executeStatement("ALTER TABLE DBO.AGENT_CONFIGURATION_PARAMETERS DROP CONSTRAINT SQL190319113342880", sqlConnection);
		executeStatement("ALTER TABLE DBO.TRANSACTION_LOG DROP CONSTRAINT SQL190319113343010", sqlConnection);
		executeStatement("ALTER TABLE DBO.AGENT_MODULE DROP CONSTRAINT SQL190319113342910", sqlConnection);
		executeStatement("ALTER TABLE DBO.AGENT_CONFIGURATION DROP CONSTRAINT SQL190319113342810", sqlConnection);
		executeStatement("ALTER TABLE DBO.RESULT_EXCEPTION_LOG DROP CONSTRAINT SQL190319113342980", sqlConnection);
		executeStatement("DROP TABLE DBO.AGENT_CONFIGURATION_MODULE_MAPPING", sqlConnection);
		executeStatement("DROP TABLE DBO.TRANSACTION_LOG", sqlConnection);
		executeStatement("DROP TABLE DBO.AGENT_CONFIGURATION", sqlConnection);
		executeStatement("DROP TABLE DBO.MAX_ID", sqlConnection);
		executeStatement("DROP TABLE DBO.RESULT_EXCEPTION_LOG", sqlConnection);
		executeStatement("DROP TABLE DBO.PROCESSING_LOG", sqlConnection);
		executeStatement("DROP TABLE DBO.AGENT_MODULE", sqlConnection);
		executeStatement("DROP TABLE DBO.AGENT_CONFIGURATION_PARAMETERS", sqlConnection);
		executeStatement("DROP TABLE DBO.VARIABLE_CACHE", sqlConnection);
		executeStatement("CREATE TABLE DBO.AGENT_CONFIGURATION_MODULE_MAPPING ( AMID INTEGER NOT NULL, ACID INTEGER NOT NULL )", sqlConnection);
		executeStatement("CREATE TABLE DBO.TRANSACTION_LOG ( ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 ,INCREMENT BY 1), ACTION VARCHAR(100) NOT NULL, TRANDT TIMESTAMP NOT NULL, USERNAME VARCHAR(100) NOT NULL, PROCESSINGLOG_LOGID BIGINT )", sqlConnection);
		executeStatement("CREATE TABLE DBO.AGENT_CONFIGURATION ( ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 ,INCREMENT BY 1), ACTIVE BOOLEAN NOT NULL, AGENTNAME VARCHAR(100) NOT NULL, TYPE INTEGER )", sqlConnection);
		executeStatement("CREATE TABLE DBO.MAX_ID ( ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 ,INCREMENT BY 1), MAXIDNAME VARCHAR(50) NOT NULL, MAXIDVALUE BIGINT NOT NULL )", sqlConnection);
		executeStatement("CREATE TABLE DBO.RESULT_EXCEPTION_LOG ( ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 ,INCREMENT BY 1), EXCEPTIONDATA VARCHAR (4096) FOR BIT DATA NOT NULL, EXCEPTIONDT TIMESTAMP NOT NULL, EXCEPTIONTYPE VARCHAR(100) NOT NULL, RESULTDATA VARCHAR (4096) FOR BIT DATA NOT NULL, AGENTCONFIGURATION_ID INTEGER NOT NULL, AGENTMODULE_ID INTEGER NOT NULL )", sqlConnection);
		executeStatement("CREATE TABLE DBO.PROCESSING_LOG ( LOGID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 ,INCREMENT BY 1), ERROR BOOLEAN  NOT NULL, LOGDT TIMESTAMP NOT NULL, MESSAGEDATA BLOB(5M) NOT NULL, MID VARCHAR(50) NOT NULL, RECIPIENT VARCHAR(100), RETRIES BIGINT NOT NULL, STATUSMESSAGE VARCHAR(200) NOT NULL, TYPE INTEGER, AGENTCONFIGURATION_ID INTEGER NOT NULL, AGENTMODULE_ID INTEGER NOT NULL )", sqlConnection);
		executeStatement("CREATE TABLE DBO.AGENT_MODULE ( ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 ,INCREMENT BY 1), MAPPEDMODULENAME VARCHAR(100), MODULECLASSNAME VARCHAR(100) NOT NULL, MODULENAME VARCHAR(100), MODULETYPE INTEGER NOT NULL, AGENTCONFIGURATION_ID INTEGER )", sqlConnection);
		executeStatement("CREATE TABLE DBO.AGENT_CONFIGURATION_PARAMETERS ( ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 ,INCREMENT BY 1), BOOLEANVALUE BOOLEAN, ENCRYPTEDVALUE VARCHAR(1024), ISGLOBAL BOOLEAN  NOT NULL, NAME VARCHAR(100) NOT NULL, NUMERICVALUE DOUBLE, PARAMETERTYPE INTEGER NOT NULL, STRINGVALUE VARCHAR(1024), AGENTCONFIGURATION_ID INTEGER NOT NULL, AGENTMODULE_ID INTEGER )", sqlConnection);
		executeStatement("CREATE TABLE DBO.VARIABLE_CACHE ( APPKEY VARCHAR(100) NOT NULL, APPDATA VARCHAR (4096) FOR BIT DATA NOT NULL )", sqlConnection);
		executeStatement("CREATE UNIQUE INDEX SQL190319113342980 ON DBO.RESULT_EXCEPTION_LOG (ID ASC)", sqlConnection);
		executeStatement("CREATE UNIQUE INDEX SQL190319113343060 ON DBO.AGENT_CONFIGURATION (AGENTNAME ASC)", sqlConnection);
		executeStatement("CREATE UNIQUE INDEX SQL190319113342920 ON DBO.MAX_ID (ID ASC)", sqlConnection);
		executeStatement("CREATE INDEX SQL190319113343090 ON DBO.AGENT_CONFIGURATION_MODULE_MAPPING (ACID ASC)", sqlConnection);
		executeStatement("CREATE INDEX SQL190319113343220 ON DBO.TRANSACTION_LOG (PROCESSINGLOG_LOGID ASC)", sqlConnection);
		executeStatement("CREATE INDEX SQL190319113343140 ON DBO.AGENT_CONFIGURATION_PARAMETERS (AGENTMODULE_ID ASC)", sqlConnection);
		executeStatement("CREATE INDEX SQL190319113343110 ON DBO.AGENT_CONFIGURATION_MODULE_MAPPING (AMID ASC)", sqlConnection);
		executeStatement("CREATE UNIQUE INDEX SQL190319113342910 ON DBO.AGENT_MODULE (ID ASC)", sqlConnection);
		executeStatement("CREATE INDEX SQL190319113343180 ON DBO.PROCESSING_LOG (AGENTMODULE_ID ASC)", sqlConnection);
		executeStatement("CREATE UNIQUE INDEX SQL190319113342950 ON DBO.PROCESSING_LOG (LOGID ASC)", sqlConnection);
		executeStatement("CREATE INDEX SQL190319113343130 ON DBO.AGENT_CONFIGURATION_PARAMETERS (AGENTCONFIGURATION_ID ASC)", sqlConnection);
		executeStatement("CREATE UNIQUE INDEX SQL190319113342850 ON DBO.AGENT_CONFIGURATION_MODULE_MAPPING (AMID ASC, ACID ASC)", sqlConnection);
		executeStatement("CREATE UNIQUE INDEX SQL190319113343050 ON DBO.VARIABLE_CACHE (APPKEY ASC)", sqlConnection);
		executeStatement("CREATE UNIQUE INDEX SQL190319113343010 ON DBO.TRANSACTION_LOG (ID ASC)", sqlConnection);
		executeStatement("CREATE INDEX SQL190319113343190 ON DBO.RESULT_EXCEPTION_LOG (AGENTCONFIGURATION_ID ASC)", sqlConnection);
		executeStatement("CREATE INDEX SQL190319113343210 ON DBO.RESULT_EXCEPTION_LOG (AGENTMODULE_ID ASC)", sqlConnection);
		executeStatement("CREATE UNIQUE INDEX SQL190319113342810 ON DBO.AGENT_CONFIGURATION (ID ASC)", sqlConnection);
		executeStatement("CREATE INDEX SQL190319113343150 ON DBO.AGENT_MODULE (AGENTCONFIGURATION_ID ASC)", sqlConnection);
		executeStatement("CREATE INDEX SQL190319113343170 ON DBO.PROCESSING_LOG (AGENTCONFIGURATION_ID ASC)", sqlConnection);
		executeStatement("CREATE UNIQUE INDEX SQL190319113342880 ON DBO.AGENT_CONFIGURATION_PARAMETERS (ID ASC)", sqlConnection);
		executeStatement("ALTER TABLE AGENT_CONFIGURATION ADD CONSTRAINT SQL190319113342993 UNIQUE (AGENTNAME)", sqlConnection);
		executeStatement("CREATE UNIQUE INDEX SQL190319113342624 ON AGENT_CONFIGURATION_PARAMETERS (AGENTCONFIGURATION_ID, AGENTMODULE_ID, NAME)", sqlConnection);
		executeStatement("CREATE UNIQUE INDEX SQL190319113342250 ON AGENT_MODULE (AGENTCONFIGURATION_ID, MODULENAME)", sqlConnection);
		executeStatement("ALTER TABLE DBO.MAX_ID ADD CONSTRAINT SQL190319113342920 PRIMARY KEY (ID)", sqlConnection);
		executeStatement("ALTER TABLE DBO.AGENT_CONFIGURATION_MODULE_MAPPING ADD CONSTRAINT SQL190319113342850 PRIMARY KEY (AMID, ACID)", sqlConnection);
		executeStatement("ALTER TABLE DBO.PROCESSING_LOG ADD CONSTRAINT SQL190319113342950 PRIMARY KEY (LOGID)", sqlConnection);
		executeStatement("ALTER TABLE DBO.VARIABLE_CACHE ADD CONSTRAINT SQL190319113343050 PRIMARY KEY (APPKEY)", sqlConnection);
		executeStatement("ALTER TABLE DBO.AGENT_CONFIGURATION_PARAMETERS ADD CONSTRAINT SQL190319113342880 PRIMARY KEY (ID)", sqlConnection);
		executeStatement("ALTER TABLE DBO.TRANSACTION_LOG ADD CONSTRAINT SQL190319113343010 PRIMARY KEY (ID)", sqlConnection);
		executeStatement("ALTER TABLE DBO.AGENT_MODULE ADD CONSTRAINT SQL190319113342910 PRIMARY KEY (ID)", sqlConnection);
		executeStatement("ALTER TABLE DBO.AGENT_CONFIGURATION ADD CONSTRAINT SQL190319113342810 PRIMARY KEY (ID)", sqlConnection);
		executeStatement("ALTER TABLE DBO.RESULT_EXCEPTION_LOG ADD CONSTRAINT SQL190319113342980 PRIMARY KEY (ID)", sqlConnection);
		executeStatement("ALTER TABLE DBO.RESULT_EXCEPTION_LOG ADD CONSTRAINT FK6AGN6815VRMA9Q0583T3HW5R4 FOREIGN KEY (AGENTMODULE_ID) REFERENCES DBO.AGENT_MODULE (ID)", sqlConnection);
		executeStatement("ALTER TABLE DBO.AGENT_MODULE ADD CONSTRAINT FK50Y4HDXX533JN9MDX79PGID3K FOREIGN KEY (AGENTCONFIGURATION_ID) REFERENCES DBO.AGENT_CONFIGURATION (ID)", sqlConnection);
		executeStatement("ALTER TABLE DBO.PROCESSING_LOG ADD CONSTRAINT FKEV743F87VM83YFOE0UT8HVRH2 FOREIGN KEY (AGENTMODULE_ID) REFERENCES DBO.AGENT_MODULE (ID)", sqlConnection);
		executeStatement("ALTER TABLE DBO.AGENT_CONFIGURATION_PARAMETERS ADD CONSTRAINT FKHW9418RROYOT0HPBA9C272HNM FOREIGN KEY (AGENTMODULE_ID) REFERENCES DBO.AGENT_MODULE (ID)", sqlConnection);
		executeStatement("ALTER TABLE DBO.TRANSACTION_LOG ADD CONSTRAINT FKEOJ9BANKVKRYMAUOVGLQGUO4S FOREIGN KEY (PROCESSINGLOG_LOGID) REFERENCES DBO.PROCESSING_LOG (LOGID)", sqlConnection);
		executeStatement("ALTER TABLE DBO.AGENT_CONFIGURATION_MODULE_MAPPING ADD CONSTRAINT FKIP9VF4FH61RD2TBWUDQWCEJ66 FOREIGN KEY (AMID) REFERENCES DBO.AGENT_MODULE (ID)", sqlConnection);
		executeStatement("ALTER TABLE DBO.AGENT_CONFIGURATION_MODULE_MAPPING ADD CONSTRAINT FKH752X0CL8W8F5UO8K062EF00 FOREIGN KEY (ACID) REFERENCES DBO.AGENT_CONFIGURATION (ID)", sqlConnection);
		executeStatement("ALTER TABLE DBO.RESULT_EXCEPTION_LOG ADD CONSTRAINT FK6V1NA8MAKFP62E2KYP5L8RRDI FOREIGN KEY (AGENTCONFIGURATION_ID) REFERENCES DBO.AGENT_CONFIGURATION (ID)", sqlConnection);
		executeStatement("ALTER TABLE DBO.AGENT_CONFIGURATION_PARAMETERS ADD CONSTRAINT FK8EFMPHG62YMQUSFPEHY445VEJ FOREIGN KEY (AGENTCONFIGURATION_ID) REFERENCES DBO.AGENT_CONFIGURATION (ID)", sqlConnection);
		executeStatement("ALTER TABLE DBO.PROCESSING_LOG ADD CONSTRAINT FKA31ELILHWYO079WBFDEJ72384 FOREIGN KEY (AGENTCONFIGURATION_ID) REFERENCES DBO.AGENT_CONFIGURATION (ID)", sqlConnection);
	}
	
	private void executeStatement(String statement, Connection connection){
		try(Statement sqlstatement = connection.createStatement()) {
			sqlstatement.execute(statement);
			Main.log.debug("Executed '{}'.", statement);
		} catch (SQLException e) {
			Main.log.error("Unable to exeute query '{}'\n{};{}",statement, e.getMessage(), e.getCause()!=null?e.getCause().getMessage():""); 
		}
	}

}
