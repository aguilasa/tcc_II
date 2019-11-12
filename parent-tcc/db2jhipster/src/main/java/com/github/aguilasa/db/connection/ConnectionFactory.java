package com.github.aguilasa.db.connection;

import java.sql.Connection;
import java.sql.SQLException;

import com.github.aguilasa.db.DatabaseConfiguration;

public class ConnectionFactory {

	public static Connection createConnection(DatabaseConfiguration configuration) throws SQLException {
		switch (configuration.getDatabaseType()) {
		case Oracle:
			return new OracleConnection(configuration).getConnection();
		case PostgreSQL:
			return new PostgreSQLConnection(configuration).getConnection();
		case SqlServer:
			return new SqlServerConnection(configuration).getConnection();
		default:
			break;
		}

		throw new RuntimeException("Tipo de banco de dados não suportado.");
	}

}
