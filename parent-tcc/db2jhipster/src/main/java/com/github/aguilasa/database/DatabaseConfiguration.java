package com.github.aguilasa.database;

public class DatabaseConfiguration {

	private String host = "";
	private String database = "";
	private int port = 0;
	private String username = "";
	private String password = "";
	private String schema = "";
	private DatabaseType databaseType;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public DatabaseType getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(DatabaseType databaseType) {
		this.databaseType = databaseType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DatabaseConfiguration [host=");
		builder.append(host);
		builder.append(", database=");
		builder.append(database);
		builder.append(", port=");
		builder.append(port);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", schema=");
		builder.append(schema);
		builder.append(", databaseType=");
		builder.append(databaseType);
		builder.append("]");
		return builder.toString();
	}

}
