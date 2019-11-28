package com.github.aguilasa.db2jhipster.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class TableRelationshipView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5018678072289912330L;

	/**
	 * Create the panel.
	 */
	public TableRelationshipView() {
		setLayout(null);
		
		JLabel lblTabelas = new JLabel("Entidades");
		lblTabelas.setBounds(10, 11, 265, 14);
		add(lblTabelas);
		
		JComboBox cbEntities = new JComboBox();
		cbEntities.setBounds(10, 27, 400, 20);
		add(cbEntities);
		
		JLabel lblRelacionamentos = new JLabel("Relacionamentos");
		lblRelacionamentos.setBounds(10, 58, 179, 14);
		add(lblRelacionamentos);

	}

}
