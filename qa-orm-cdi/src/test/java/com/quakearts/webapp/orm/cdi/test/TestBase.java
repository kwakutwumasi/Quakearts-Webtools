package com.quakearts.webapp.orm.cdi.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import org.junit.BeforeClass;

public class TestBase {
	
	private static boolean createdDatabase;
	
	@BeforeClass
	public static void createDatabase() throws Exception {
		if(!createdDatabase){
			InitialContext  initialContext = new InitialContext();
			UserTransaction transaction = (UserTransaction) 
					initialContext.lookup("java:comp/UserTransaction");
			
			DataSource source = (DataSource) initialContext
					.lookup("java:/jdbc/TestDS");
			
			transaction.begin();
			
			try (Connection connection = source.getConnection()){
				runStatement("DROP TABLE TEST_OBJECT", connection);
				runStatement("CREATE TABLE TEST_OBJECT ( ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 ,INCREMENT BY 1), NAME VARCHAR(50) NOT NULL)", connection);
				runStatement("ALTER TABLE TEST_OBJECT ADD CONSTRAINT TEST_OBJECT_PRIMARY_KEY PRIMARY KEY (ID)", connection);
			} catch (SQLException e) {
				// Do nothing
			} finally {
				transaction.commit();
			}
		}
	}

	private static void runStatement(String statement, Connection connection) {
		try(Statement sqlStatement = connection.createStatement()) {
			sqlStatement.execute(statement);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
