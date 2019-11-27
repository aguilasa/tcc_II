package com.github.aguilasa.database;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DatabaseConfiguration {

	private String host = "";
	private String database = "";
	private int port = 0;
	private String username = "";
	private String password = "";
	private String schema = "";
	private DatabaseType databaseType;

}
