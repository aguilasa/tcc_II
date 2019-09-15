package com.github.aguilasa.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import com.github.aguilasa.db.DatabaseType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@RequiredArgsConstructor
public class MetaDataLoader {

	private static final String[] TABLE_TYPE = { "TABLE" };

	@Getter
	@Setter
	@NonNull
	private Connection connection;
	@Getter
	@Setter
	@NonNull
	private DatabaseType databaseType;

	private DatabaseMetaData metaData;

	@Getter
	private Set<Table> tables = new LinkedHashSet<>();

	private DatabaseMetaData getMetaData() throws SQLException {
		if (metaData == null) {
			metaData = connection.getMetaData();
		}
		return metaData;
	}

	public void loadTables() throws SQLException {
		checkConnection();
		tables.clear();
		ResultSet result = getMetaData().getTables(null, null, null, TABLE_TYPE);
		while (result.next()) {
			Table table = new Table(result.getString(3));
			tables.add(table);
		}
	}

	private void checkConnection() {
		if (connection == null) {
			throw new RuntimeException("O objeto de conexão com o banco de dados não foi informado.");
		}
	}

}
