package com.github.aguilasa.db.connection;

import java.sql.Connection;
import java.sql.SQLException;

import com.github.aguilasa.db.DatabaseConfiguration;

import oracle.jdbc.pool.OracleDataSource;

public class OracleConnection extends BaseConnection {

	private static final String JDBC_URL_ORA = "jdbc:oracle:thin:@%s:%d:%s";

	public OracleConnection(DatabaseConfiguration configuration) {
		super(configuration);
	}

	@Override
	public Connection getConnection() throws SQLException {
		OracleDataSource dataSource = new OracleDataSource();
		dataSource.setURL(getJdbcUrl());
		return dataSource.getConnection(configuration.getUsername(), configuration.getPassword());
	}

	@Override
	public String getJdbcUrl() {
		return String.format(JDBC_URL_ORA, configuration.getHost(), configuration.getPort(), configuration.getDatabase());
	}

}
