package com.github.aguilasa.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.github.aguilasa.jhipster.EntityLoader;
import com.github.aguilasa.jhipster.types.Entity;
import com.github.aguilasa.jhipster.types.Relationship;
import com.github.aguilasa.view.base.AreaPanel;
import com.github.aguilasa.view.relationship.Header;
import com.github.aguilasa.view.relationship.Row;

import view.swing.custom.combo.ComboBox;

public class RelationshipView extends AreaPanel {

	private static final long serialVersionUID = 213060434661988263L;

	private ComboBox<Entity> comboEntities;
	private EntityLoader entityLoader;
	private JPanel innerPanel = new JPanel(new GridLayout(0, 1));
	private JPanel tablePanel = new JPanel(new BorderLayout());

	private boolean comboLoaded = false;

	public RelationshipView() {
		innerPanel.setBorder(BorderFactory.createEmptyBorder());
		JLabel lblEntidades = new JLabel("Entidades");
		lblEntidades.setVerticalAlignment(SwingConstants.BOTTOM);
		lblEntidades.setHorizontalAlignment(SwingConstants.LEFT);
		lblEntidades.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblEntidades.setBounds(10, 10, 326, 20);
		add(lblEntidades);

		JPanel borderLayoutPanel = new JPanel(new BorderLayout());
		borderLayoutPanel.setBackground(Color.WHITE);
		borderLayoutPanel.setBorder(BorderFactory.createEmptyBorder());
		borderLayoutPanel.add(innerPanel, BorderLayout.PAGE_START);
		JScrollPane scrollPane = new JScrollPane(borderLayoutPanel);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		tablePanel.setBackground(Color.WHITE);
		tablePanel.setBorder(BorderFactory.createEmptyBorder());
		tablePanel.setBounds(10, 95, 565, 375);
		tablePanel.add(scrollPane);
		add(tablePanel);

		tablePanel.add(new Header(), BorderLayout.PAGE_START);

		comboEntities = new ComboBox<>();
		comboEntities.setModel(new DefaultComboBoxModel<>());
		comboEntities.setBounds(10, 35, 570, 38);
		comboEntities.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				loadComponents();
			}

		});
		if (!comboLoaded) {
			add(comboEntities);
			comboLoaded = true;
		}
	}

	@Override
	protected Observable getObservable() {
		return null;
	}

	public EntityLoader getEntityLoader() {
		return entityLoader;
	}

	public void setEntityLoader(EntityLoader entityLoader) {
		this.entityLoader = entityLoader;
	}

	public void loadEntities() {
		if (getEntityLoader() != null) {
			comboEntities.removeAllItems();
			for (Entity entity : getEntityLoader().getEntities()) {
				comboEntities.addItem(entity);
			}
			if (!getEntityLoader().getEntities().isEmpty()) {
				comboEntities.setSelectedIndex(0);
			}
		}
	}

	public void loadComponents() {
		Entity entity = (Entity) comboEntities.getSelectedItem();
		innerPanel.removeAll();
		for (Relationship relationship : entity.getRelationships()) {
			Row row = new Row();
			innerPanel.add(row);
			row.setRelationship(relationship);
		}

		revalidate();
		repaint();
	}
}
