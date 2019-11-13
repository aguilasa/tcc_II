package com.github.aguilasa.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.apache.commons.lang.StringUtils;

import com.github.aguilasa.db.DatabaseConfiguration;
import com.github.aguilasa.db.DatabaseType;
import com.github.aguilasa.db.connection.ConnectionFactory;
import com.github.aguilasa.view.observables.ConnectionObservable;

import view.swing.custom.button.Button;
import view.swing.custom.button.ButtonType;
import view.swing.custom.combo.ComboBox;
import view.swing.custom.commons.ComponentSize;
import view.swing.custom.input.Input;
import view.swing.custom.input.Password;

public class DBConfigView extends JPanel {

	private static final long serialVersionUID = 4198299660865086518L;

	private static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 16);

	private ComboBox<DatabaseType> cbDatabaseType;
	private Input edtHost;
	private Input edtPort;
	private Input edtDatabase;
	private Input edtSchema;
	private Input edtUser;
	private Password edtPassword;
	private Button btnTestConn;

	private ConnectionObservable observable;
	private boolean validConnection = false;

	/**
	 * Create the panel.
	 */
	public DBConfigView() {
		setBackground(Color.WHITE);
		setLayout(null);
		setPreferredSize(MainView.AREA_SIZE);

		JLabel lblBancoDeDados = new JLabel("Banco de Dados");
		lblBancoDeDados.setVerticalAlignment(SwingConstants.BOTTOM);
		lblBancoDeDados.setHorizontalAlignment(SwingConstants.LEFT);
		lblBancoDeDados.setFont(LABEL_FONT);
		lblBancoDeDados.setBounds(10, 10, 326, 20);
		add(lblBancoDeDados);

		cbDatabaseType = new ComboBox<>();
		cbDatabaseType.setModel(new DefaultComboBoxModel<>(DatabaseType.values()));
		cbDatabaseType.setSelectedIndex(2);
		cbDatabaseType.setBounds(10, 35, 570, 38);
		add(cbDatabaseType);

		JLabel lblServidor = new JLabel("Servidor");
		lblServidor.setVerticalAlignment(SwingConstants.BOTTOM);
		lblServidor.setHorizontalAlignment(SwingConstants.LEFT);
		lblServidor.setFont(LABEL_FONT);
		lblServidor.setBounds(10, 90, 243, 20);
		add(lblServidor);

		edtHost = new Input();
		edtHost.setText("localhost");
		edtHost.setBounds(10, 115, 444, 38);
		add(edtHost);

		JLabel lblPort = new JLabel("Porta");
		lblPort.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPort.setHorizontalAlignment(SwingConstants.LEFT);
		lblPort.setFont(LABEL_FONT);
		lblPort.setBounds(464, 90, 73, 20);
		add(lblPort);

		edtPort = new Input();
		edtPort.setText("5432");
		edtPort.setBounds(464, 115, 116, 38);
		add(edtPort);

		JLabel lblDatabase = new JLabel("Database");
		lblDatabase.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDatabase.setHorizontalAlignment(SwingConstants.LEFT);
		lblDatabase.setFont(LABEL_FONT);
		lblDatabase.setBounds(10, 170, 158, 20);
		add(lblDatabase);

		edtDatabase = new Input();
		edtDatabase.setText("postgres");
		edtDatabase.setBounds(10, 195, 280, 38);
		add(edtDatabase);

		JLabel lblSchema = new JLabel("Schema");
		lblSchema.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSchema.setHorizontalAlignment(SwingConstants.LEFT);
		lblSchema.setFont(LABEL_FONT);
		lblSchema.setBounds(300, 170, 158, 20);
		add(lblSchema);

		edtSchema = new Input();
		edtSchema.setText("music");
		edtSchema.setBounds(300, 195, 280, 38);
		add(edtSchema);

		JLabel lblUsurio = new JLabel("Usu\u00E1rio");
		lblUsurio.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUsurio.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsurio.setFont(LABEL_FONT);
		lblUsurio.setBounds(10, 250, 158, 20);
		add(lblUsurio);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSenha.setHorizontalAlignment(SwingConstants.LEFT);
		lblSenha.setFont(LABEL_FONT);
		lblSenha.setBounds(300, 250, 158, 20);
		add(lblSenha);

		edtUser = new Input();
		edtUser.setText("postgres");
		edtUser.setBounds(10, 275, 280, 38);
		add(edtUser);

		edtPassword = new Password();
		edtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		edtPassword.setText("postgres");
		edtPassword.setBounds(300, 275, 280, 38);
		add(edtPassword);

		btnTestConn = new Button(ButtonType.PRIMARY);
		btnTestConn.setComponentSize(ComponentSize.SMALL);
		btnTestConn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testConnection();
			}
		});
		btnTestConn.setText("Testar conexão");
		btnTestConn.setBounds(10, 330, 158, 38);
		add(btnTestConn);
	}

	public void setObservable(MainView mainView) {
		if (mainView != null) {
			observable = new ConnectionObservable();
			observable.addObserver(mainView);
		}
	}

	private void notifyValidConnection() {
		if (observable != null) {
			observable.changeData(validConnection);
		}
	}

	protected void testConnection() {
		try {
			validConnection = false;
			ConnectionFactory.createConnection(getDatabaseConfiguration());
			JOptionPane.showMessageDialog(null, "Conectado");
			validConnection = true;
			notifyValidConnection();
		} catch (Exception e) {
			validConnection = false;
			notifyValidConnection();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public DatabaseConfiguration getDatabaseConfiguration() {
		DatabaseConfiguration configuration = new DatabaseConfiguration();
		configuration.setDatabaseType((DatabaseType) cbDatabaseType.getSelectedItem());
		configuration.setHost(edtHost.getText());
		configuration.setPort(getPortValue());
		configuration.setDatabase(edtDatabase.getText());
		configuration.setSchema(edtSchema.getText());
		configuration.setUsername(edtUser.getText());
		configuration.setPassword(edtPassword.getText());
		return configuration;
	}

	public boolean isValidConnection() {
		return validConnection;
	}

	private int getPortValue() {
		String portText = edtPort.getText();
		if (StringUtils.isNotEmpty(portText) && StringUtils.isNumeric(portText)) {
			return Integer.valueOf(portText);
		}
		return -1;
	}
}
