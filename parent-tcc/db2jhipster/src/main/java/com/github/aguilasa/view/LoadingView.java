package com.github.aguilasa.view;

import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.github.aguilasa.database.DatabaseConfiguration;
import com.github.aguilasa.jhipster.EntityLoader;
import com.github.aguilasa.metadata.DatabaseLoader;
import com.github.aguilasa.view.base.AreaPanel;
import com.github.aguilasa.view.observables.LoadingObservable;
import com.github.aguilasa.view.utils.SimpleSwingWorker;

public class LoadingView extends AreaPanel {

	private static final long serialVersionUID = 4975193434721287570L;

	private JProgressBar progressBar;
	private JLabel lblOperation;
	private DatabaseConfiguration databaseConfiguration;
	private Connection connection;
	private EntityLoader entityLoader;

	private LoadingObservable observable;

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

	public EntityLoader getEntityLoader() {
		return entityLoader;
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
		final SimpleSwingWorker worker = new SimpleSwingWorker() {

			@Override
			protected Void doInBackground() throws Exception {
				DatabaseLoader databaseLoader = new DatabaseLoader(connection, databaseConfiguration);
				entityLoader = new EntityLoader(databaseLoader);
				initializeLoading();
				try {
					try {
						lblOperation.setText("Carregando tabelas da base de dados");
						databaseLoader.loadTables();
						sleep();
						lblOperation.setText("Carregando campos da base de dados");
						databaseLoader.loadAllTablesColumns();
						sleep();
						lblOperation.setText("Carregando chaves primárias da base de dados");
						databaseLoader.loadAllTablesPrimaryKeys();
						sleep();
						lblOperation.setText("Carregando chaves estrangeiras da base de dados");
						databaseLoader.loadAllTablesForeignKeys();
						sleep();
						lblOperation.setText("Carregando constraints da base de dados");
						databaseLoader.loadAllTablesUniqueConstraints();
						sleep();
						lblOperation.setText("Convertendo tabelas para entidades JHipster");
						entityLoader.loadEntities(false);
						sleep();
						lblOperation.setText("Convertendo relacionamentos das entidades JHipster");
						entityLoader.loadRelationships();
						sleep();
					} catch (final SQLException e) {
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								e.printStackTrace();
								JOptionPane.showMessageDialog(getMainView(), e.getMessage());
							}
						});

					}
				} finally {
					finalizeLoading();
					notifyFinalize();
				}
				return null;
			}

			@Override
			protected void done() {

			}
		};
		worker.execute();
	}

	private void sleep() {
		if (MainView.TESTING) {
			try {
				Thread.sleep(MainView.SLEEP);
			} catch (InterruptedException e) {
			}
		}
	}

	private void notifyFinalize() {
		if (observable != null) {
			observable.changeData(true);
		}
	}

	@Override
	protected Observable getObservable() {
		if (observable == null) {
			observable = new LoadingObservable();
		}
		return observable;
	}

}
