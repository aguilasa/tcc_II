package com.github.aguilasa.view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;

import com.github.aguilasa.db.DatabaseConfiguration;
import com.github.aguilasa.db.DatabaseType;

public class DatabaseConfigView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4198299660865086518L;
	private JComboBox<DatabaseType> cbDatabaseType;
	private JTextField edtHost;
	private JTextField edtPort;
	private JTextField edtDatabase;
	private JTextField edtSchema;
	private JTextField edtUser;
	private JPasswordField edtPassword;

	/**
	 * Create the panel.
	 */
	public DatabaseConfigView() {
		setLayout(null);

		JLabel lblBancoDeDados = new JLabel("Banco de Dados");
		lblBancoDeDados.setBounds(37, 11, 326, 14);
		add(lblBancoDeDados);

		cbDatabaseType = new JComboBox<>();
		cbDatabaseType.setModel(new DefaultComboBoxModel<>(DatabaseType.values()));
		cbDatabaseType.setSelectedIndex(2);
		cbDatabaseType.setBounds(37, 31, 326, 20);
		add(cbDatabaseType);

		JLabel lblServidor = new JLabel("Servidor");
		lblServidor.setBounds(37, 62, 243, 14);
		add(lblServidor);

		edtHost = new JTextField();
		edtHost.setText("localhost");
		edtHost.setBounds(37, 78, 243, 20);
		add(edtHost);
		edtHost.setColumns(10);

		JLabel lblSchema = new JLabel("Porta");
		lblSchema.setBounds(290, 62, 73, 14);
		add(lblSchema);

		edtPort = new JTextField();
		edtPort.setText("5432");
		edtPort.setColumns(10);
		edtPort.setBounds(290, 78, 73, 20);
		add(edtPort);

		JLabel lblDatabase = new JLabel("Database");
		lblDatabase.setBounds(37, 109, 158, 14);
		add(lblDatabase);

		edtDatabase = new JTextField();
		edtDatabase.setText("postgres");
		edtDatabase.setColumns(10);
		edtDatabase.setBounds(37, 125, 158, 20);
		add(edtDatabase);

		JLabel lblSchema_1 = new JLabel("Schema");
		lblSchema_1.setBounds(205, 109, 158, 14);
		add(lblSchema_1);

		edtSchema = new JTextField();
		edtSchema.setText("music");
		edtSchema.setColumns(10);
		edtSchema.setBounds(205, 125, 158, 20);
		add(edtSchema);

		JLabel lblUsurio = new JLabel("Usu\u00E1rio");
		lblUsurio.setBounds(37, 156, 158, 14);
		add(lblUsurio);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(205, 156, 158, 14);
		add(lblSenha);

		edtUser = new JTextField();
		edtUser.setText("postgres");
		edtUser.setColumns(10);
		edtUser.setBounds(37, 172, 158, 20);
		add(edtUser);

		edtPassword = new JPasswordField();
		edtPassword.setText("postgres");
		edtPassword.setColumns(10);
		edtPassword.setBounds(205, 172, 158, 20);
		add(edtPassword);
	}

	public DatabaseConfiguration getDatabaseConfiguration() {
		DatabaseConfiguration configuration = new DatabaseConfiguration();
		configuration.setDatabaseType((DatabaseType) cbDatabaseType.getSelectedItem());
		configuration.setHost(edtHost.getText());
		configuration.setPort(getPortValue());
		configuration.setDatabase(edtDatabase.getText());
		configuration.setSchema(edtSchema.getText());
		configuration.setUsername(edtUser.getText());
		configuration.setPassword(String.valueOf(edtPassword.getPassword()));
		return configuration;
	}

	private int getPortValue() {
		String portText = edtPort.getText();
		if (StringUtils.isNotEmpty(portText) && StringUtils.isNumeric(portText)) {
			return Integer.valueOf(portText);
		}
		return -1;
	}
}
