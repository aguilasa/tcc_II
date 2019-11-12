package com.github.aguilasa.db.connection;

import java.sql.Connection;
import java.sql.SQLException;

import com.github.aguilasa.db.DatabaseConfiguration;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class BaseConnection {

	protected DatabaseConfiguration configuration;

	public abstract Connection getConnection() throws SQLException;

	public abstract String getJdbcUrl();

}
