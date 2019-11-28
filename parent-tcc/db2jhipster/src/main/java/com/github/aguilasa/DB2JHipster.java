package com.github.aguilasa;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.UIManager;

import com.github.aguilasa.database.ConnectionFactory;
import com.github.aguilasa.database.DatabaseConfiguration;
import com.github.aguilasa.database.DatabaseType;
import com.github.aguilasa.metadata.DatabaseLoader;
import com.github.aguilasa.view.MainView;

import view.swing.utils.AlterFonts;

public class DB2JHipster {

	private MainView view;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		runWithScreen();
	}

	public static void runWithScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterFonts.alterFonts();
					setLook();
					DB2JHipster application = new DB2JHipster();
					application.view.setVisible(true);
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					application.view.setLocation(dim.width / 2 - application.view.getSize().width / 2,
							dim.height / 2 - application.view.getSize().height / 2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DB2JHipster() {
		initialize();
	}

	private void initialize() {
		view = new MainView();
	}

	private static void setLook() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void runWithoutScreen() throws SQLException, ClassNotFoundException {
		DatabaseConfiguration pg = new DatabaseConfiguration();
		pg.setHost("teste65");
		pg.setPort(5432);
		pg.setDatabase("hcm");
		pg.setUsername("postgres");
		pg.setPassword("admin");
		pg.setSchema("test_e");
		pg.setDatabaseType(DatabaseType.PostgreSQL);

		DatabaseConfiguration pg_local = new DatabaseConfiguration();
		pg_local.setHost("localhost");
		pg_local.setPort(5432);
		pg_local.setDatabase("postgres");
		pg_local.setUsername("postgres");
		pg_local.setPassword("postgres");
		pg_local.setSchema("music");
		pg_local.setDatabaseType(DatabaseType.PostgreSQL);

		DatabaseConfiguration sql = new DatabaseConfiguration();
		sql.setHost("teste65");
		sql.setPort(1433);
		sql.setDatabase("test_e");
		sql.setUsername("sa");
		sql.setPassword("S3nior2018");
		sql.setDatabaseType(DatabaseType.SqlServer);

		DatabaseConfiguration adv = new DatabaseConfiguration();
		adv.setHost("localhost");
		adv.setPort(1433);
		adv.setDatabase("AdventureWorksLT2014");
		adv.setUsername("sa");
		adv.setPassword("12345678");
		adv.setSchema("SalesLT");
		adv.setDatabaseType(DatabaseType.SqlServer);

		DatabaseConfiguration ora = new DatabaseConfiguration();
		ora.setHost("localhost");
		ora.setPort(1521);
		ora.setDatabase("XE");
		ora.setUsername("rhpayroll");
		ora.setPassword("rhpayroll");
		ora.setDatabaseType(DatabaseType.Oracle);

		DatabaseConfiguration conf = pg;
		Connection connection = ConnectionFactory.createConnection(conf);
		DatabaseLoader databaseLoader = new DatabaseLoader(connection, conf);
//		databaseLoader.loadTypeInfo();

		databaseLoader.loadTables();
		databaseLoader.loadAllTablesColumns();
		databaseLoader.loadAllTablesForeignKeys();
//		databaseLoader.printTypeNames(true);

//		metaDataLoader.loadAll();
//		metaDataLoader.printTables();
//		EntityLoader entityLoader = new EntityLoader(databaseLoader);
//		entityLoader.loadAll();
//		System.out.println(entityLoader.toJdl());
	}

}
