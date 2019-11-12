package com.github.aguilasa.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.github.aguilasa.db.DatabaseConfiguration;

public class SqlServerConnection extends BaseConnection {

	private static final String JDBC_URL_SQL = "jdbc:sqlserver://%s:%d;databaseName=%s";

	public SqlServerConnection(DatabaseConfiguration configuration) {
		super(configuration);
	}

	@Override
	public Connection getConnection() throws SQLException {
		try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

		return DriverManager.getConnection(getJdbcUrl(), configuration.getUsername(), configuration.getPassword());
	}

	@Override
	public String getJdbcUrl() {
		return String.format(JDBC_URL_SQL, configuration.getHost(), configuration.getPort(), configuration.getDatabase());
	}

}