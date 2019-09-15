package com.github.aguilasa.db.connection;

import java.sql.Connection;
import java.sql.SQLException;

import com.github.aguilasa.db.DatabaseConfiguration;
import com.github.aguilasa.db.DatabaseType;

public class ConnectionFactory {

	public static Connection createConnection(DatabaseType databaseType, DatabaseConfiguration configuration) throws SQLException {
		switch (databaseType) {
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
