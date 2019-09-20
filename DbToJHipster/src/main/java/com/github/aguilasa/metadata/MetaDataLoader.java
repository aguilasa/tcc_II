package com.github.aguilasa.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

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
		checkConnection();
		if (metaData == null) {
			metaData = connection.getMetaData();
		}
		return metaData;
	}

	private boolean header = false;

	public void printResultset(ResultSet result) throws SQLException {
		ResultSetMetaData md = result.getMetaData();
		int columnCount = md.getColumnCount();
		if (header) {
			for (int i = 1; i <= columnCount; i++) {
				if (i > 1)
					System.out.print("\t");
				System.out.print(md.getColumnName(i));
			}
			System.out.println();
			header = false;
		}
		for (int i = 1; i <= columnCount; i++) {
			if (i > 1)
				System.out.print("\t");
			System.out.print(result.getString(i));
		}
		System.out.println();
	}

	/**
	 * Carrega as tabelas da base dados
	 * 
	 * @throws SQLException
	 */
	public void loadTables() throws SQLException {
		header = true;
		tables.clear();
		try (ResultSet result = getMetaData().getTables(null, null, null, TABLE_TYPE);) {
			while (result.next()) {
				Table table = new Table(result.getString("TABLE_NAME"));
				loadColumns(table);
				tables.add(table);
			}
			printTypes();
		}
	}

	public void printTypes() {
		for (String type : types) {
			System.out.println(String.format("%s(\"%s\"), //", type.toUpperCase(), type.toLowerCase()));
		}
	}

	/**
	 * Carrega os campos da tabela
	 * 
	 * @param table tabela de onde serão carregados os campos
	 * @throws SQLException
	 */
	public void loadColumns(Table table) throws SQLException {
		System.out.println(table.getName());
		try (ResultSet result = getMetaData().getColumns(null, null, table.getName(), null);) {
			while (result.next()) {
				Column column = new Column();
				loadColumnProperties(column, result);
				table.addColumn(column);
			}
		}
	}

	/**
	 * Carrega as informações do campo
	 * 
	 * @param column campo a ser carregado
	 * @param result ResultSet com as informações do campo
	 * @throws SQLException
	 */
	private void loadColumnProperties(Column column, ResultSet result) throws SQLException {
		column.setName(result.getString("COLUMN_NAME"));
		String typeName = result.getString("TYPE_NAME");
		types.add(typeName);
		column.setType(ColumnType.getEnum(typeName));
		String columnSize = result.getString("COLUMN_SIZE");
		if (StringUtils.isNumeric(columnSize)) {
			column.setLength(Integer.valueOf(columnSize));
		}
		column.setPrecision(column.getLength());
		String decimalDigits = result.getString("DECIMAL_DIGITS");
		if (StringUtils.isNumeric(decimalDigits)) {
			column.setScale(Integer.valueOf(decimalDigits));
		}
		column.setNotNull(result.getString("IS_NULLABLE").equalsIgnoreCase("YES"));
		if (findColumn("IS_AUTOINCREMENT", result)) {
			column.setAutoIncrement(result.getString("IS_AUTOINCREMENT").equalsIgnoreCase("YES"));
		}
//		printResultset(result);
	}

	private boolean findColumn(String name, ResultSet result) {
		try {
			return result.findColumn(name) > 0;
		} catch (SQLException e) {
		}
		return false;
	}

	private void checkConnection() {
		if (connection == null) {
			throw new RuntimeException("O objeto de conexão com o banco de dados não foi informado.");
		}
	}

	private Set<String> types = new LinkedHashSet<>();

}
