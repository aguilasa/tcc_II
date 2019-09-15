package com.github.aguilasa.db.connection;

import java.sql.SQLException;

import com.github.aguilasa.db.DatabaseConfiguration;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class BaseConnection {

	protected DatabaseConfiguration configuration;

	public abstract HikariDataSource getConnection() throws SQLException;

	public abstract String getJdbcUrl();

	public HikariConfig getBaseConfig() {
		HikariConfig config = new HikariConfig();
		config.setMaximumPoolSize(1);
		return config;
	}

}
