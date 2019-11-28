package com.github.aguilasa.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.github.aguilasa.database.DatabaseConfiguration;
import com.github.aguilasa.database.DatabaseType;
import com.github.aguilasa.generators.JdlWriter;

public class DatabaseLoader {

	private static final String TABLE = "TABLE";
	private static final String[] TABLE_TYPE = { TABLE };
	private static final String TS_WTZ = "TIMESTAMP(6) WITH TIME ZONE";
	private static final String TS_WLTZ = "TIMESTAMP(6) WITH LOCAL TIME ZONE";

	private Connection connection;
	private DatabaseConfiguration configuration;
	private DatabaseMetaData metaData;
	private String schema = null;
	private Set<Table> tables = new LinkedHashSet<>();
	private HashMap<String, String> typeInfo = new LinkedHashMap<>();

	public DatabaseLoader(Connection connection, DatabaseConfiguration configuration) {
		this.connection = connection;
		this.configuration = configuration;
		this.schema = getSchema();
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public DatabaseConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(DatabaseConfiguration configuration) {
		this.configuration = configuration;
	}

	public Set<Table> getTables() {
		return tables;
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

	public void printColumnRs(ResultSet result) throws SQLException {
		ResultSetMetaData md = result.getMetaData();
		int columnCount = md.getColumnCount();
		if (header) {
			for (int i = 1; i <= columnCount; i++) {
				if (i > 1)
					System.out.print("\t");
				String columnName = md.getColumnName(i);
				System.out.print(columnName);
				if (columnName.equalsIgnoreCase("DATA_TYPE")) {
					System.out.print("\t");
					System.out.print("DATA_TYPE");
				}
			}
			System.out.println();
			header = false;
		}
		for (int i = 1; i <= columnCount; i++) {
			String columnName = md.getColumnName(i);
			if (i > 1)
				System.out.print("\t");
			System.out.print(result.getString(i));
			if (columnName.equalsIgnoreCase("DATA_TYPE")) {
				String typeName = result.getString("TYPE_NAME");
				System.out.print("\t");
				System.out.print(typeInfo.get(typeName.toUpperCase()));
			}
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
		loadTypeInfo();
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

	public void loadAllTablesColumns() throws SQLException {
		for (Table table : tables) {
			loadColumns(table);
		}
	}

	public void loadAllTablesPrimaryKeys() throws SQLException {
		for (Table table : tables) {
			loadPrimaryKeys(table);
		}
	}

	public void loadAllTablesForeignKeys() throws SQLException {
		for (Table table : tables) {
			loadForeignKeys(table);
		}
	}

	public void loadAllTablesUniqueConstraints() throws SQLException {
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

	private boolean validateSchema(String objectSchema) {
		if (this.schema != null) {
			return this.schema.equalsIgnoreCase(objectSchema);
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
		try {
			try (ResultSet result = getMetaData().getColumns(null, null, table.getName(), null);) {
				while (result.next()) {
					String columnSchema = result.getString("TABLE_SCHEM");
					if (validateSchema(columnSchema)) {
//					printColumnRs(result);
						TypeName typeName = new TypeName();
						typeName.fromRs(result);
						typeNames.add(typeName);
						Column column = new Column();
						loadColumnProperties(column, result);
						table.addColumn(column);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Erro ao carregar colunas da tabela:" + table.getName());
			e.printStackTrace();
		}
	}

	/**
	 * Carrega as chaves primárias da tabela
	 *
	 * @param table tabela de onde serão carregados as chaves
	 * @throws SQLException
	 */
	public void loadPrimaryKeys(Table table) throws SQLException {
		String paramSchema = configuration.getDatabaseType().equals(DatabaseType.SqlServer) ? schema : null;
		try (ResultSet result = getMetaData().getPrimaryKeys(null, paramSchema, table.getName());) {
			boolean hasSetName = false;
			while (result.next()) {
//				System.out.println(result.getString("TABLE_SCHEM"));
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
		String paramSchema = configuration.getDatabaseType().equals(DatabaseType.SqlServer) ? schema : null;
		try (ResultSet result = getMetaData().getImportedKeys(null, paramSchema, table.getName());) {
			while (result.next()) {
				try {
					if (!validateSchema(result.getString("FKTABLE_SCHEM"))) {
						continue;
					}
					String tableName = result.getString("FKTABLE_NAME");
					if (!tableName.equalsIgnoreCase(table.getName())) {
						throw new RuntimeException(String.format(
								"Não foi possível carregar as chaves estrangeiras da tabela '%s'.", table.getName()));
					}
					String fkName = result.getString("FK_NAME");
					String columnName = result.getString("FKCOLUMN_NAME");
					String referenceTableName = result.getString("PKTABLE_NAME");
					String referenceColumnName = result.getString("PKCOLUMN_NAME");
					Column column = table.findColumnByName(columnName);
					Table referenceTable = findTableByName(referenceTableName);
					Column referenceColumn = referenceTable.findColumnByName(referenceColumnName);
					table.addForeignKey(fkName, referenceTable.getName(), column, referenceColumn);
				} catch (Exception e) {
					throw new RuntimeException(
							String.format("Tabela '%s', erro ao carregar foreignkey: ", table.getName()), e);
				}
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
		int dataType = result.getInt("DATA_TYPE");
		types.add(typeName);
		ColumnType columnType = getColumnType(dataType, typeName);
		if (columnType == null) {
			System.out.println("Coluna: " + column.getName());
		}
		column.setType(columnType);
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
	 * Determina o tipo do campo
	 * 
	 * @param dataType código do tipo
	 * @param typeName nome do tipo
	 * @return tipo do campo
	 */
	private ColumnType getColumnType(int dataType, String typeName) {
		JDBCType jdbcType = getJDBCType(dataType);
		if (jdbcType != null) {
			switch (jdbcType) {
			case LONGNVARCHAR:
			case LONGVARCHAR:
				if (typeName.equalsIgnoreCase("xml")) {
					return ColumnType.XML;
				}
				return ColumnType.TEXT;
			case LONGVARBINARY:
				if (typeName.equalsIgnoreCase("image")) {
					return ColumnType.IMAGE;
				}
				return ColumnType.BLOB;
			case VARCHAR:
				if (typeName.equalsIgnoreCase("text")) {
					return ColumnType.TEXT;
				}
			case OTHER:
				if (typeName.equalsIgnoreCase("jsonb")) {
					return ColumnType.JSONB;
				}
				if (typeName.equalsIgnoreCase("uuid")) {
					return ColumnType.UUID;
				}
			default:
				ColumnType enum1 = ColumnType.getEnum(jdbcType.getName());
				if (enum1 == null) {
					System.out.println("JDBCType: " + jdbcType.getName() + ", tipo não encontrado: " + typeName
							+ ", dataType: " + dataType);
				}
				return enum1;
			}
		}

		if (typeName.equalsIgnoreCase(TS_WTZ) || typeName.equalsIgnoreCase(TS_WLTZ)) {
			return ColumnType.TIMESTAMP_TZ;
		}

		System.out.println("Tipo não encontrado: " + typeName + ", dataType: " + dataType);
		return null;
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
					throw new RuntimeException(String.format(
							"Não foi possível carregar as constraints únicas da tabela '%s'.", table.getName()));
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

	private String getTypeString(int type) {
		JDBCType jdbcType = getJDBCType(type);
		if (jdbcType != null) {
			return jdbcType.getName();
		}
		return "UNKNOW";
	}

	public JDBCType getJDBCType(int type) {
		try {
			return JDBCType.valueOf(type);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public void loadTypeInfo() throws SQLException {
		typeInfo.clear();
		try (ResultSet result = getMetaData().getTypeInfo();) {
			while (result.next()) {
				typeInfo.put(result.getString("TYPE_NAME").toUpperCase(), getTypeString(result.getInt("DATA_TYPE")));
			}
		}
	}

	public void printTypeNames(boolean header) {
		if (header) {
			System.out.println(
					"DATA_TYPE\tDATA_TYPE_NAME\tTYPE_NAME\tCOLUMN_SIZE\tBUFFER_LENGTH\tDECIMAL_DIGITS\tNUM_PREC_RADIX");
		}
		for (TypeName t : typeNames) {
			System.out.println(t);
		}

	}

	private Set<String> types = new LinkedHashSet<>();
	private Set<TypeName> typeNames = new LinkedHashSet<>();

}
