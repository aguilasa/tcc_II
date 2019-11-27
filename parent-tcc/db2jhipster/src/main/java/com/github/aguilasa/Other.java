package com.github.aguilasa;

import java.sql.Connection;
import java.sql.SQLException;

import com.github.aguilasa.database.DatabaseConfiguration;
import com.github.aguilasa.database.DatabaseType;
import com.github.aguilasa.database.connection.ConnectionFactory;
import com.github.aguilasa.metadata.DatabaseLoader;

public class Other {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DatabaseConfiguration sql = new DatabaseConfiguration();
		sql.setHost("localhost");
		sql.setPort(1433);
		sql.setDatabase("other");
		sql.setUsername("sa");
		sql.setPassword("12345678");
		sql.setDatabaseType(DatabaseType.SqlServer);

		DatabaseConfiguration pg_local = new DatabaseConfiguration();
		pg_local.setHost("localhost");
		pg_local.setPort(5432);
		pg_local.setDatabase("postgres");
		pg_local.setUsername("postgres");
		pg_local.setPassword("postgres");
		pg_local.setSchema("other");
		pg_local.setDatabaseType(DatabaseType.PostgreSQL);

		DatabaseConfiguration ora = new DatabaseConfiguration();
		ora.setHost("localhost");
		ora.setPort(1521);
		ora.setDatabase("XE");
		ora.setUsername("rhpayroll");
		ora.setPassword("rhpayroll");
		ora.setDatabaseType(DatabaseType.Oracle);

		DatabaseConfiguration adv = new DatabaseConfiguration();
		adv.setHost("localhost");
		adv.setPort(1433);
		adv.setDatabase("AdventureWorksLT2014");
		adv.setUsername("sa");
		adv.setPassword("12345678");
		adv.setSchema("SalesLT");
		adv.setDatabaseType(DatabaseType.SqlServer);

		DatabaseConfiguration furb = new DatabaseConfiguration();
		furb.setHost("localhost");
		furb.setPort(3306);
		furb.setDatabase("furb");
		furb.setUsername("root");
		furb.setPassword("root");
		furb.setDatabaseType(DatabaseType.MySql);

		DatabaseConfiguration furb2 = new DatabaseConfiguration();
		furb2.setHost("localhost");
		furb2.setPort(3306);
		furb2.setDatabase("furb2");
		furb2.setUsername("root");
		furb2.setPassword("root");
		furb2.setDatabaseType(DatabaseType.MySql);

		DatabaseConfiguration[] confs = { ora, sql, pg_local, adv, furb, furb2 };

		boolean header = true;
		for (DatabaseConfiguration conf : confs) {
			Connection connection = ConnectionFactory.createConnection(conf);
			DatabaseLoader databaseLoader = new DatabaseLoader(connection, conf);
			databaseLoader.loadTables();
			databaseLoader.loadAllTablesColumns();
			databaseLoader.printTypeNames(header);
			header = false;
//			databaseLoader.loadAll();
//			databaseLoader.printTables();
		}

	}

}
