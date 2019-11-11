package com.github.aguilasa;

import java.sql.Connection;
import java.sql.SQLException;

import com.github.aguilasa.db.DatabaseConfiguration;
import com.github.aguilasa.db.DatabaseType;
import com.github.aguilasa.db.connection.ConnectionFactory;
import com.github.aguilasa.jhipster.EntityLoader;
import com.github.aguilasa.metadata.MetaDataLoader;

public class Db2JHipster {

	public static void main(String[] args) throws SQLException {

		DatabaseConfiguration pg = new DatabaseConfiguration();
		pg.setHost("teste65");
		pg.setPort(5432);
		pg.setDatabase("hcm");
		pg.setUsername("postgres");
		pg.setPassword("admin");
		pg.setSchema("test_e");
		pg.setDatabaseType(DatabaseType.PostgreSQL);

		DatabaseConfiguration pg_local = new DatabaseConfiguration();
		pg_local.setHost("localhost");
		pg_local.setPort(5432);
		pg_local.setDatabase("postgres");
		pg_local.setUsername("postgres");
		pg_local.setPassword("postgres");
		pg_local.setSchema("music");
		pg_local.setDatabaseType(DatabaseType.PostgreSQL);

		DatabaseConfiguration sql = new DatabaseConfiguration();
		sql.setHost("teste65");
		sql.setPort(1433);
		sql.setDatabase("test_e");
		sql.setUsername("sa");
		sql.setPassword("S3nior2018");
		sql.setDatabaseType(DatabaseType.SqlServer);

		DatabaseConfiguration ora = new DatabaseConfiguration();
		ora.setHost("teste65");
		ora.setPort(1521);
		ora.setDatabase("XE");
		ora.setUsername("rhpayroll");
		ora.setPassword("rhpayroll");
		ora.setDatabaseType(DatabaseType.Oracle);

		DatabaseConfiguration conf = pg_local;
		Connection connection = ConnectionFactory.createConnection(conf);
		MetaDataLoader metaDataLoader = new MetaDataLoader(connection, conf);
//		metaDataLoader.loadAll();
//		metaDataLoader.printTables();
		EntityLoader entityLoader = new EntityLoader(metaDataLoader);
		entityLoader.loadAll();
		System.out.println(entityLoader.toJdl());
	}

}
