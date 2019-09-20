package com.github.aguilasa;

import java.sql.Connection;
import java.sql.SQLException;

import com.github.aguilasa.db.DatabaseConfiguration;
import com.github.aguilasa.db.DatabaseType;
import com.github.aguilasa.db.connection.ConnectionFactory;
import com.github.aguilasa.metadata.MetaDataLoader;

public class DbToHipster {

	public static void main(String[] args) throws SQLException {

		DatabaseConfiguration pg = new DatabaseConfiguration();
		pg.setHost("teste65");
		pg.setPort(5432);
		pg.setDatabase("hcm");
		pg.setUsername("postgres");
		pg.setPassword("admin");
		pg.setSchema("test_e");
		
		DatabaseConfiguration sql = new DatabaseConfiguration();
		sql.setHost("teste65");
		sql.setPort(1433);
		sql.setDatabase("test_e");
		sql.setUsername("sa");
		sql.setPassword("S3nior2018");

		DatabaseType dbType = DatabaseType.SqlServer;
		Connection connection = ConnectionFactory.createConnection(dbType, sql);
		MetaDataLoader metaDataLoader = new MetaDataLoader(connection, dbType);
		metaDataLoader.loadTables();
	}

}
