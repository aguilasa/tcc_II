package com.github.aguilasa.db.connection;

import java.sql.SQLException;

import com.github.aguilasa.db.DatabaseConfiguration;
import com.github.aguilasa.db.DatabaseType;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionFactory {

	public static HikariDataSource createConnection(DatabaseType databaseType, DatabaseConfiguration configuration) throws SQLException {
		switch (databaseType) {
		case Oracle:
			return new OracleConnection(configuration).getConnection();
		case PostgreSQL:
			new PostgreSQLConnection(configuration).getConnection();;
		case SqlServer:
			new SqlServerConnection(configuration).getConnection();;
		default:
			break;
		}

		throw new RuntimeException("Tipo de banco de dados não suportado.");
	}

}
