package com.github.aguilasa.database.connection;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;

import com.github.aguilasa.database.DatabaseConfiguration;

import oracle.jdbc.pool.OracleDataSource;

public class OracleConnection extends BaseConnection {

	private static final String ALTER_SCHEMA = "alter session set current_schema=%s";
	private static final String JDBC_URL_ORA = "jdbc:oracle:thin:@%s:%d:%s";

	public OracleConnection(DatabaseConfiguration configuration) {
		super(configuration);
	}

	@Override
	public Connection getConnection() throws SQLException {
		OracleDataSource dataSource = new OracleDataSource();
		dataSource.setURL(getJdbcUrl());
		Connection connection = dataSource.getConnection(configuration.getUsername(), configuration.getPassword());
		if (StringUtils.isEmpty(configuration.getSchema())) {
			configuration.setSchema(configuration.getUsername());
		}
		connection.createStatement().execute(String.format(ALTER_SCHEMA, configuration.getSchema()));
		return connection;
	}

	@Override
	public String getJdbcUrl() {
		return String.format(JDBC_URL_ORA, configuration.getHost(), configuration.getPort(),
				configuration.getDatabase());
	}

}
