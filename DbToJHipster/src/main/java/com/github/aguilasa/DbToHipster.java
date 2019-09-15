package com.github.aguilasa;

import java.sql.Connection;
import java.sql.SQLException;

import com.github.aguilasa.db.DatabaseConfiguration;
import com.github.aguilasa.db.DatabaseType;
import com.github.aguilasa.db.connection.ConnectionFactory;
import com.github.aguilasa.metadata.MetaDataLoader;

public class DbToHipster {

	public static void main(String[] args) throws SQLException {

		DatabaseConfiguration configuration = new DatabaseConfiguration();
		configuration.setHost("localhost");
		configuration.setPort(5432);
		configuration.setDatabase("postgres");
		configuration.setUsername("postgres");
		configuration.setPassword("postgres");

		DatabaseType postgresql = DatabaseType.PostgreSQL;
		Connection connection = ConnectionFactory.createConnection(postgresql, configuration);
		MetaDataLoader metaDataLoader = new MetaDataLoader(connection, postgresql);
		metaDataLoader.loadTables();
	}

}
