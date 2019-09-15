package com.github.aguilasa.db.connection;

import java.sql.SQLException;

import com.github.aguilasa.db.DatabaseConfiguration;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class PostgreSQLConnection extends BaseConnection {

	private static final String JDBC_URL_PG = "jdbc:postgresql://%s:%d/%s";

	public PostgreSQLConnection(DatabaseConfiguration configuration) {
		super(configuration);
	}

	@Override
	public HikariDataSource getConnection() throws SQLException {
		HikariConfig config = getBaseConfig();
		config.setDriverClassName("org.postgresql.ds.PGSimpleDataSource");
		config.setJdbcUrl(getJdbcUrl());
		config.setUsername(configuration.getUsername());
		config.setPassword(configuration.getPassword());
		return new HikariDataSource(config);
	}

	@Override
	public String getJdbcUrl() {
		return String.format(JDBC_URL_PG, configuration.getHost(), configuration.getPort(), configuration.getDatabase());
	}

}
