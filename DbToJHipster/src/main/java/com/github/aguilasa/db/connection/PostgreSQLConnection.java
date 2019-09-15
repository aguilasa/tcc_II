package com.github.aguilasa.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.github.aguilasa.db.DatabaseConfiguration;

public class PostgreSQLConnection extends BaseConnection {

	private static final String JDBC_URL_PG = "jdbc:postgresql://%s:%d/%s";

	public PostgreSQLConnection(DatabaseConfiguration configuration) {
		super(configuration);
	}

	@Override
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(getJdbcUrl(), configuration.getUsername(), configuration.getPassword());
	}

	@Override
	public String getJdbcUrl() {
		return String.format(JDBC_URL_PG, configuration.getHost(), configuration.getPort(), configuration.getDatabase());
	}

}
