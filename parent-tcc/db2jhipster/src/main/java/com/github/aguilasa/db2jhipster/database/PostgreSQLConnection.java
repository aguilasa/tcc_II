package com.github.aguilasa.db2jhipster.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;

public class PostgreSQLConnection extends BaseConnection {

	private static final String JDBC_URL_PG = "jdbc:postgresql://%s:%d/%s";

	public PostgreSQLConnection(DatabaseConfiguration configuration) {
		super(configuration);
	}

	@Override
	public Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(//
				getJdbcUrl(), //
				configuration.getUsername(), //
				configuration.getPassword()//
		);
		if (!StringUtils.isEmpty(configuration.getSchema())) {
			connection.setSchema(configuration.getSchema());
		}
		return connection;
	}

	@Override
	public String getJdbcUrl() {
		return String.format(JDBC_URL_PG, //
				configuration.getHost(), //
				configuration.getPort(), //
				configuration.getDatabase());
	}

}
