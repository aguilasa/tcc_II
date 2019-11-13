package com.github.aguilasa.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.github.aguilasa.db.connection.ConnectionFactory;
import com.github.aguilasa.jhipster.EntityLoader;
import com.github.aguilasa.metadata.MetaDataLoader;

import view.swing.custom.button.Button;
import view.swing.custom.button.ButtonType;

public class Other extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5692986452442130061L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Other frame = new Other();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Other() {
		setLook();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 712, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		DBConfigView panel = new DBConfigView();
		panel.setBounds(0, 0, 643, 407);
		contentPane.add(panel);

		Button btnNewButton = new Button(ButtonType.PRIMARY);
		btnNewButton.setText("Carregar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					load(panel);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(0, 418, 132, 23);
		contentPane.add(btnNewButton);
	}

	private void setLook() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void load(DBConfigView panel) throws SQLException {
		Connection connection = ConnectionFactory.createConnection(panel.getDatabaseConfiguration());
		MetaDataLoader metaDataLoader = new MetaDataLoader(connection, panel.getDatabaseConfiguration());
		EntityLoader entityLoader = new EntityLoader(metaDataLoader);
		entityLoader.loadAll();
		System.out.println(entityLoader.toJdl());
	}
}
