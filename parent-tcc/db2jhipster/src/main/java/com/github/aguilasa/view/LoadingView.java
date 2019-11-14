package com.github.aguilasa.view;

import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import com.github.aguilasa.db.DatabaseConfiguration;
import com.github.aguilasa.jhipster.EntityLoader;
import com.github.aguilasa.metadata.MetaDataLoader;
import com.github.aguilasa.view.base.AreaPanel;

public class LoadingView extends AreaPanel {

	private static final long serialVersionUID = 4975193434721287570L;

	private JProgressBar progressBar;
	private JLabel lblOperation;
	private DatabaseConfiguration databaseConfiguration;
	private Connection connection;

	public LoadingView() {
		super();
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setBounds(10, 230, 565, 20);
		add(progressBar);

		lblOperation = new JLabel("Carregando tabelas da base de dados");
		lblOperation.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblOperation.setHorizontalAlignment(SwingConstants.CENTER);
		lblOperation.setVerticalAlignment(SwingConstants.BOTTOM);
		lblOperation.setBounds(10, 205, 565, 20);
		add(lblOperation);
	}

	public DatabaseConfiguration getDatabaseConfiguration() {
		return databaseConfiguration;
	}

	public void setDatabaseConfiguration(DatabaseConfiguration databaseConfiguration) {
		this.databaseConfiguration = databaseConfiguration;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	private void initializeLoading() {
		lblOperation.setText("");
		progressBar.setVisible(true);
	}

	private void finalizeLoading() {
		lblOperation.setText("Carga finalizada");
		progressBar.setVisible(false);
	}

	public void loadAll() {
		final SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				MetaDataLoader metadataLoader = new MetaDataLoader(connection, databaseConfiguration);
				EntityLoader entityLoader = new EntityLoader(metadataLoader);
				initializeLoading();
				try {
					try {
						lblOperation.setText("Carregando tabelas da base de dados");
						metadataLoader.loadTables();
						sleep();
						lblOperation.setText("Carregando campos da base de dados");
						metadataLoader.loadAllTablesColumns();
						sleep();
						lblOperation.setText("Carregando chaves primárias da base de dados");
						metadataLoader.loadAllTablesPrimaryKeys();
						sleep();
						lblOperation.setText("Carregando chaves estrangeiras da base de dados");
						metadataLoader.loadAllTablesForeignKeys();
						sleep();
						lblOperation.setText("Carregando constraints da base de dados");
						metadataLoader.loadAllTablesUniqueConstraints();
						sleep();
						lblOperation.setText("Convertendo tabelas para entidades JHipster");
						entityLoader.loadEntities(false);
						sleep();
						lblOperation.setText("Convertendo relacionamentos das entidades JHipster");
						entityLoader.loadRelationships();
						sleep();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				} finally {
					finalizeLoading();
				}
				return null;
			}
		};
		worker.execute();
	}

	private void sleep() {
		if (MainView.TESTING) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
		}
	}
}
