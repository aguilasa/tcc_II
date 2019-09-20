package com.github.aguilasa.db;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatabaseConfiguration {

	private String host = "";
	private String database = "";
	private int port = 0;
	private String username = "";
	private String password = "";
	private String schema = "";
	private DatabaseType databaseType;

}
