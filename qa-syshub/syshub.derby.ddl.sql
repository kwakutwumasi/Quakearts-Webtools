--<ScriptOptions statementTerminator=";"/>

ALTER TABLE RESULT_EXCEPTION_LOG DROP CONSTRAINT FK6AGN6815VRMA9Q0583T3HW5R4;

ALTER TABLE AGENT_MODULE DROP CONSTRAINT FK50Y4HDXX533JN9MDX79PGID3K;

ALTER TABLE PROCESSING_LOG DROP CONSTRAINT FKEV743F87VM83YFOE0UT8HVRH2;

ALTER TABLE AGENT_CONFIGURATION_PARAMETERS DROP CONSTRAINT FKHW9418RROYOT0HPBA9C272HNM;

ALTER TABLE TRANSACTION_LOG DROP CONSTRAINT FKEOJ9BANKVKRYMAUOVGLQGUO4S;

ALTER TABLE AGENT_CONFIGURATION_MODULE_MAPPING DROP CONSTRAINT FKIP9VF4FH61RD2TBWUDQWCEJ66;

ALTER TABLE AGENT_CONFIGURATION_MODULE_MAPPING DROP CONSTRAINT FKH752X0CL8W8F5UO8K062EF00;

ALTER TABLE RESULT_EXCEPTION_LOG DROP CONSTRAINT FK6V1NA8MAKFP62E2KYP5L8RRDI;

ALTER TABLE AGENT_CONFIGURATION_PARAMETERS DROP CONSTRAINT FK8EFMPHG62YMQUSFPEHY445VEJ;

ALTER TABLE PROCESSING_LOG DROP CONSTRAINT FKA31ELILHWYO079WBFDEJ72384;

ALTER TABLE MAX_ID DROP CONSTRAINT SQL190319113342920;

ALTER TABLE AGENT_CONFIGURATION_MODULE_MAPPING DROP CONSTRAINT SQL190319113342850;

ALTER TABLE PROCESSING_LOG DROP CONSTRAINT SQL190319113342950;

ALTER TABLE VARIABLE_CACHE DROP CONSTRAINT SQL190319113343050;

ALTER TABLE AGENT_CONFIGURATION_PARAMETERS DROP CONSTRAINT SQL190319113342880;

ALTER TABLE TRANSACTION_LOG DROP CONSTRAINT SQL190319113343010;

ALTER TABLE AGENT_MODULE DROP CONSTRAINT SQL190319113342910;

ALTER TABLE AGENT_CONFIGURATION DROP CONSTRAINT SQL190319113342810;

ALTER TABLE RESULT_EXCEPTION_LOG DROP CONSTRAINT SQL190319113342980;

DROP INDEX SQL190319113342980;

DROP INDEX SQL190319113343060;

DROP INDEX SQL190319113342920;

DROP INDEX SQL190319113343090;

DROP INDEX SQL190319113343220;

DROP INDEX SQL190319113343140;

DROP INDEX SQL190319113343110;

DROP INDEX SQL190319113342910;

DROP INDEX SQL190319113343180;

DROP INDEX SQL190319113342950;

DROP INDEX SQL190319113343130;

DROP INDEX SQL190319113342850;

DROP INDEX SQL190319113343050;

DROP INDEX SQL190319113343010;

DROP INDEX SQL190319113343190;

DROP INDEX SQL190319113343210;

DROP INDEX SQL190319113342810;

DROP INDEX SQL190319113343150;

DROP INDEX SQL190319113343170;

DROP INDEX SQL190319113342880;

DROP TABLE AGENT_CONFIGURATION_MODULE_MAPPING;

DROP TABLE TRANSACTION_LOG;

DROP TABLE AGENT_CONFIGURATION;

DROP TABLE MAX_ID;

DROP TABLE RESULT_EXCEPTION_LOG;

DROP TABLE PROCESSING_LOG;

DROP TABLE AGENT_MODULE;

DROP TABLE AGENT_CONFIGURATION_PARAMETERS;

DROP TABLE VARIABLE_CACHE;

CREATE TABLE AGENT_CONFIGURATION_MODULE_MAPPING ( AMID INTEGER NOT NULL, ACID INTEGER NOT NULL );

CREATE TABLE TRANSACTION_LOG ( ID BIGINT DEFAULT GENERATED_BY_DEFAULT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 ,INCREMENT BY 1), ACTION VARCHAR(100) NOT NULL, TRANDT TIMESTAMP NOT NULL, USERNAME VARCHAR(100) NOT NULL, PROCESSINGLOG_LOGID BIGINT );

CREATE TABLE AGENT_CONFIGURATION ( ID INTEGER DEFAULT GENERATED_BY_DEFAULT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 ,INCREMENT BY 1), ACTIVE null NOT NULL, AGENTNAME VARCHAR(100) NOT NULL, TYPE INTEGER );

CREATE TABLE MAX_ID ( ID INTEGER DEFAULT GENERATED_BY_DEFAULT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 ,INCREMENT BY 1), MAXIDNAME VARCHAR(50) NOT NULL, MAXIDVALUE BIGINT NOT NULL );

CREATE TABLE RESULT_EXCEPTION_LOG ( ID BIGINT DEFAULT GENERATED_BY_DEFAULT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 ,INCREMENT BY 1), EXCEPTIONDATA VARCHAR (4096) FOR BIT DATA NOT NULL, EXCEPTIONDT TIMESTAMP NOT NULL, EXCEPTIONTYPE VARCHAR(100) NOT NULL, RESULTDATA VARCHAR (4096) FOR BIT DATA NOT NULL, AGENTCONFIGURATION_ID INTEGER NOT NULL, AGENTMODULE_ID INTEGER NOT NULL );

CREATE TABLE PROCESSING_LOG ( LOGID BIGINT DEFAULT GENERATED_BY_DEFAULT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 ,INCREMENT BY 1), ERROR null NOT NULL, LOGDT TIMESTAMP NOT NULL, MESSAGEDATA BLOB(5M) NOT NULL, MID VARCHAR(50) NOT NULL, RECIPIENT VARCHAR(100), RETRIES BIGINT NOT NULL, STATUSMESSAGE VARCHAR(200) NOT NULL, TYPE INTEGER, AGENTCONFIGURATION_ID INTEGER NOT NULL, AGENTMODULE_ID INTEGER NOT NULL );

CREATE TABLE AGENT_MODULE ( ID INTEGER DEFAULT GENERATED_BY_DEFAULT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 ,INCREMENT BY 1), MAPPEDMODULENAME VARCHAR(100), MODULECLASSNAME VARCHAR(100) NOT NULL, MODULENAME VARCHAR(100), MODULETYPE INTEGER NOT NULL, AGENTCONFIGURATION_ID INTEGER );

CREATE TABLE AGENT_CONFIGURATION_PARAMETERS ( ID INTEGER DEFAULT GENERATED_BY_DEFAULT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 ,INCREMENT BY 1), BOOLEANVALUE null, ISGLOBAL null NOT NULL, NAME VARCHAR(100) NOT NULL, NUMERICVALUE DOUBLE, PARAMETERTYPE INTEGER NOT NULL, STRINGVALUE VARCHAR(1024), AGENTCONFIGURATION_ID INTEGER NOT NULL, AGENTMODULE_ID INTEGER );

CREATE TABLE VARIABLE_CACHE ( APPKEY VARCHAR(100) NOT NULL, APPDATA VARCHAR (4096) FOR BIT DATA NOT NULL );

CREATE UNIQUE INDEX SQL190319113342980 ON RESULT_EXCEPTION_LOG (ID ASC);

CREATE UNIQUE INDEX SQL190319113343060 ON AGENT_CONFIGURATION (AGENTNAME ASC);

CREATE UNIQUE INDEX SQL190319113342920 ON MAX_ID (ID ASC);

CREATE INDEX SQL190319113343090 ON AGENT_CONFIGURATION_MODULE_MAPPING (ACID ASC);

CREATE INDEX SQL190319113343220 ON TRANSACTION_LOG (PROCESSINGLOG_LOGID ASC);

CREATE INDEX SQL190319113343140 ON AGENT_CONFIGURATION_PARAMETERS (AGENTMODULE_ID ASC);

CREATE INDEX SQL190319113343110 ON AGENT_CONFIGURATION_MODULE_MAPPING (AMID ASC);

CREATE UNIQUE INDEX SQL190319113342910 ON AGENT_MODULE (ID ASC);

CREATE INDEX SQL190319113343180 ON PROCESSING_LOG (AGENTMODULE_ID ASC);

CREATE UNIQUE INDEX SQL190319113342950 ON PROCESSING_LOG (LOGID ASC);

CREATE INDEX SQL190319113343130 ON AGENT_CONFIGURATION_PARAMETERS (AGENTCONFIGURATION_ID ASC);

CREATE UNIQUE INDEX SQL190319113342850 ON AGENT_CONFIGURATION_MODULE_MAPPING (AMID ASC, ACID ASC);

CREATE UNIQUE INDEX SQL190319113343050 ON VARIABLE_CACHE (APPKEY ASC);

CREATE UNIQUE INDEX SQL190319113343010 ON TRANSACTION_LOG (ID ASC);

CREATE INDEX SQL190319113343190 ON RESULT_EXCEPTION_LOG (AGENTCONFIGURATION_ID ASC);

CREATE INDEX SQL190319113343210 ON RESULT_EXCEPTION_LOG (AGENTMODULE_ID ASC);

CREATE UNIQUE INDEX SQL190319113342810 ON AGENT_CONFIGURATION (ID ASC);

CREATE INDEX SQL190319113343150 ON AGENT_MODULE (AGENTCONFIGURATION_ID ASC);

CREATE INDEX SQL190319113343170 ON PROCESSING_LOG (AGENTCONFIGURATION_ID ASC);

CREATE UNIQUE INDEX SQL190319113342880 ON AGENT_CONFIGURATION_PARAMETERS (ID ASC);

ALTER TABLE MAX_ID ADD CONSTRAINT SQL190319113342920 PRIMARY KEY (ID);

ALTER TABLE AGENT_CONFIGURATION_MODULE_MAPPING ADD CONSTRAINT SQL190319113342850 PRIMARY KEY (AMID, ACID);

ALTER TABLE PROCESSING_LOG ADD CONSTRAINT SQL190319113342950 PRIMARY KEY (LOGID);

ALTER TABLE VARIABLE_CACHE ADD CONSTRAINT SQL190319113343050 PRIMARY KEY (APPKEY);

ALTER TABLE AGENT_CONFIGURATION_PARAMETERS ADD CONSTRAINT SQL190319113342880 PRIMARY KEY (ID);

ALTER TABLE TRANSACTION_LOG ADD CONSTRAINT SQL190319113343010 PRIMARY KEY (ID);

ALTER TABLE AGENT_MODULE ADD CONSTRAINT SQL190319113342910 PRIMARY KEY (ID);

ALTER TABLE AGENT_CONFIGURATION ADD CONSTRAINT SQL190319113342810 PRIMARY KEY (ID);

ALTER TABLE RESULT_EXCEPTION_LOG ADD CONSTRAINT SQL190319113342980 PRIMARY KEY (ID);

ALTER TABLE RESULT_EXCEPTION_LOG ADD CONSTRAINT FK6AGN6815VRMA9Q0583T3HW5R4 FOREIGN KEY (AGENTMODULE_ID) REFERENCES AGENT_MODULE (ID);

ALTER TABLE AGENT_MODULE ADD CONSTRAINT FK50Y4HDXX533JN9MDX79PGID3K FOREIGN KEY (AGENTCONFIGURATION_ID) REFERENCES AGENT_CONFIGURATION (ID);

ALTER TABLE PROCESSING_LOG ADD CONSTRAINT FKEV743F87VM83YFOE0UT8HVRH2 FOREIGN KEY (AGENTMODULE_ID) REFERENCES AGENT_MODULE (ID);

ALTER TABLE AGENT_CONFIGURATION_PARAMETERS ADD CONSTRAINT FKHW9418RROYOT0HPBA9C272HNM FOREIGN KEY (AGENTMODULE_ID) REFERENCES AGENT_MODULE (ID);

ALTER TABLE TRANSACTION_LOG ADD CONSTRAINT FKEOJ9BANKVKRYMAUOVGLQGUO4S FOREIGN KEY (PROCESSINGLOG_LOGID) REFERENCES PROCESSING_LOG (LOGID);

ALTER TABLE AGENT_CONFIGURATION_MODULE_MAPPING ADD CONSTRAINT FKIP9VF4FH61RD2TBWUDQWCEJ66 FOREIGN KEY (AMID) REFERENCES AGENT_MODULE (ID);

ALTER TABLE AGENT_CONFIGURATION_MODULE_MAPPING ADD CONSTRAINT FKH752X0CL8W8F5UO8K062EF00 FOREIGN KEY (ACID) REFERENCES AGENT_CONFIGURATION (ID);

ALTER TABLE RESULT_EXCEPTION_LOG ADD CONSTRAINT FK6V1NA8MAKFP62E2KYP5L8RRDI FOREIGN KEY (AGENTCONFIGURATION_ID) REFERENCES AGENT_CONFIGURATION (ID);

ALTER TABLE AGENT_CONFIGURATION_PARAMETERS ADD CONSTRAINT FK8EFMPHG62YMQUSFPEHY445VEJ FOREIGN KEY (AGENTCONFIGURATION_ID) REFERENCES AGENT_CONFIGURATION (ID);

ALTER TABLE PROCESSING_LOG ADD CONSTRAINT FKA31ELILHWYO079WBFDEJ72384 FOREIGN KEY (AGENTCONFIGURATION_ID) REFERENCES AGENT_CONFIGURATION (ID);
