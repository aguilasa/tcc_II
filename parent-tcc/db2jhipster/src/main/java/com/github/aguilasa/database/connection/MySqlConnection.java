package com.github.aguilasa.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;

import com.github.aguilasa.database.DatabaseConfiguration;

public class MySqlConnection extends BaseConnection {

	private static final String JDBC_URL_MY = "jdbc:mysql://%s:%d/%s";

	public MySqlConnection(DatabaseConfiguration configuration) {
		super(configuration);
	}

	@Override
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(getJdbcUrl(), configuration.getUsername(),
				configuration.getPassword());
		if (!StringUtils.isEmpty(configuration.getSchema())) {
			connection.setSchema(configuration.getSchema());
		}
		return connection;
	}

	@Override
	public String getJdbcUrl() {
		return String.format(JDBC_URL_MY, configuration.getHost(), configuration.getPort(),
				configuration.getDatabase());
	}

}
