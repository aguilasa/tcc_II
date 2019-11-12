package com.github.aguilasa.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import com.github.aguilasa.jhipster.generators.JdlWriter;
import org.apache.commons.lang.StringUtils;

import com.github.aguilasa.db.DatabaseConfiguration;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class MetaDataLoader {

	private static final String TABLE = "TABLE";
	private static final String[] TABLE_TYPE = { TABLE };

	@Getter
	@Setter
	@NonNull
	private Connection connection;
	@Getter
	@Setter
	@NonNull
	private DatabaseConfiguration configuration;

	private DatabaseMetaData metaData;

	private String schema = null;

	@Getter
	private Set<Table> tables = new LinkedHashSet<>();

	public MetaDataLoader(Connection connection, DatabaseConfiguration configuration) {
		this.connection = connection;
		this.configuration = configuration;
		this.schema = getSchema();
	}

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

	public Table findTableByName(String tableName) {
		return tables.stream() //
				.filter(t -> t.getName().equalsIgnoreCase(tableName)) //
				.findFirst() //
				.orElseThrow(() -> new RuntimeException(String.format("Tabela '%s' não encontrada.", tableName)));
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
				String tableSchema = result.getString("TABLE_SCHEM");
				String tableType = result.getString("TABLE_TYPE");
				if (isTable(tableType) && validateSchema(tableSchema)) {
					Table table = new Table(result.getString("TABLE_NAME"));
					tables.add(table);
				}
			}
		}
	}

	/**
	 * Carrega todas as tabelas, campos, chaves primárias e chaves estrangeiras
	 */
	public void loadAll() throws SQLException {
		loadTables();
		loadAllTablesColumns();
		loadAllTablesPrimaryKeys();
		loadAllTablesForeignKeys();
		loadAllTablesUniqueConstraints();
	}

	private void loadAllTablesColumns() throws SQLException {
		for (Table table : tables) {
			loadColumns(table);
		}
	}

	private void loadAllTablesPrimaryKeys() throws SQLException {
		for (Table table : tables) {
			loadPrimaryKeys(table);
		}
	}

	private void loadAllTablesForeignKeys() throws SQLException {
		for (Table table : tables) {
			loadForeignKeys(table);
		}
	}

	private void loadAllTablesUniqueConstraints() throws SQLException {
		for (Table table : tables) {
			loadUniqueConstraints(table);
		}
	}

	public void printTables() {
		JdlWriter writer = new JdlWriter();
		for (Table table : tables) {
//            System.out.println(table);
			System.out.println(writer.tableToJHipsterEntity(table));
		}
	}

	private boolean validateSchema(String tableSchema) {
		if (this.schema != null) {
			return this.schema.equalsIgnoreCase(tableSchema);
		}
		return true;
	}

	private String getSchema() {
		return !StringUtils.isEmpty(configuration.getSchema()) ? configuration.getSchema() : null;
	}

	private boolean isTable(String tableType) {
		return tableType.equalsIgnoreCase(TABLE);
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
		try (ResultSet result = getMetaData().getColumns(null, null, table.getName(), null);) {
			while (result.next()) {
				Column column = new Column();
				loadColumnProperties(column, result);
				table.addColumn(column);
			}
		}
	}

	/**
	 * Carrega as chaves primárias da tabela
	 *
	 * @param table tabela de onde serão carregados as chaves
	 * @throws SQLException
	 */
	public void loadPrimaryKeys(Table table) throws SQLException {
		try (ResultSet result = getMetaData().getPrimaryKeys(null, schema, table.getName());) {
			boolean hasSetName = false;
			while (result.next()) {
				String columnName = result.getString("COLUMN_NAME");
				int keyPosition = result.getInt("KEY_SEQ");
				table.addPrimaryKey(columnName, keyPosition);
				if (!hasSetName) {
					table.getPrimaryKey().setName(result.getString("PK_NAME"));
					hasSetName = true;
				}
			}
		}
	}

	/**
	 * Carrega as chaves estrangeiras da tabela
	 *
	 * @param table tabela de onde serão carregados as chaves
	 * @throws SQLException
	 */
	public void loadForeignKeys(Table table) throws SQLException {
		try (ResultSet result = getMetaData().getImportedKeys(null, schema, table.getName());) {
			while (result.next()) {
				String tableName = result.getString("FKTABLE_NAME");
				if (!tableName.equalsIgnoreCase(table.getName())) {
					throw new RuntimeException(String.format("Não foi possível carregar as chaves estrangeiras da tabela '%s'.", table.getName()));
				}
				String columnName = result.getString("FKCOLUMN_NAME");
				String referenceTableName = result.getString("PKTABLE_NAME");
				String referenceColumnName = result.getString("PKCOLUMN_NAME");
				Column column = table.findColumnByName(columnName);
				Table referenceTable = findTableByName(referenceTableName);
				Column referenceColumn = referenceTable.findColumnByName(referenceColumnName);
				ForeignKey foreignKey = table.addForeignKey(column, referenceColumn);
				foreignKey.setName(result.getString("FK_NAME"));
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
		column.setPosition(result.getInt("ORDINAL_POSITION"));
//		printResultset(result);
	}

	/**
	 * Carrega as chaves estrangeiras da tabela
	 *
	 * @param table tabela de onde serão carregados as chaves
	 * @throws SQLException
	 */
	public void loadUniqueConstraints(Table table) throws SQLException {
		try (ResultSet result = getMetaData().getIndexInfo(null, schema, table.getName(), true, true);) {
			while (result.next()) {
				String tableName = result.getString("TABLE_NAME");
				if (!tableName.equalsIgnoreCase(table.getName())) {
					throw new RuntimeException(String.format("Não foi possível carregar as constraints únicas da tabela '%s'.", table.getName()));
				}
				String columnName = result.getString("COLUMN_NAME");
				if (columnName != null) {
					Column column = table.findColumnByName(columnName);

					UniqueConstraint unique = table.addUniqueConstraint(column);
					if (unique != null) {
						unique.setName(result.getString("INDEX_NAME"));
					}
				}
			}
		}
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
