package com.github.aguilasa.view;

import java.awt.Font;
import java.util.Observable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.github.aguilasa.jhipster.EntityLoader;
import com.github.aguilasa.jhipster.types.Entity;
import com.github.aguilasa.view.base.AreaPanel;

import view.swing.custom.combo.ComboBox;

public class RelationshipView extends AreaPanel {

	private static final long serialVersionUID = 213060434661988263L;

	private ComboBox<Entity> cbEntities;
	private EntityLoader entityLoader;

	public RelationshipView() {
		JLabel lblEntidades = new JLabel("Entidades");
		lblEntidades.setVerticalAlignment(SwingConstants.BOTTOM);
		lblEntidades.setHorizontalAlignment(SwingConstants.LEFT);
		lblEntidades.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblEntidades.setBounds(10, 10, 326, 20);
		add(lblEntidades);

		cbEntities = new ComboBox<>();
		cbEntities.setModel(new DefaultComboBoxModel<>());
		cbEntities.setBounds(10, 35, 570, 38);
		add(cbEntities);
	}

	@Override
	protected Observable getObservable() {
		// TODO Auto-generated method stub
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
			cbEntities.removeAllItems();
			for (Entity entity : getEntityLoader().getEntities()) {
				cbEntities.addItem(entity);
			}
			if (!getEntityLoader().getEntities().isEmpty()) {
				cbEntities.setSelectedIndex(0);
			}
		}
	}
}
