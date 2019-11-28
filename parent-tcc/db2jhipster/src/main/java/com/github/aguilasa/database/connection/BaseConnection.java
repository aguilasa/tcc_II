package com.github.aguilasa.database.connection;

import java.sql.Connection;
import java.sql.SQLException;

import com.github.aguilasa.database.DatabaseConfiguration;

public abstract class BaseConnection {

	protected DatabaseConfiguration configuration;

	public BaseConnection(DatabaseConfiguration configuration) {
		this.configuration = configuration;
	}

	public abstract Connection getConnection() throws SQLException, ClassNotFoundException;

	public abstract String getJdbcUrl();

}
