package com.github.aguilasa;

import java.sql.Connection;
import java.sql.SQLException;

import com.github.aguilasa.db.DatabaseConfiguration;
import com.github.aguilasa.db.DatabaseType;
import com.github.aguilasa.db.connection.ConnectionFactory;

public class DbToHipster {

	public static void main(String[] args) throws SQLException {

		DatabaseConfiguration configuration = new DatabaseConfiguration();
		configuration.setHost("localhost");
		configuration.setPort(5432);
		configuration.setDatabase("postgres");
		configuration.setUsername("postgres");
		configuration.setPassword("postgres");

		Connection connection = ConnectionFactory.createConnection(DatabaseType.PostgreSQL, configuration);
	}

}
