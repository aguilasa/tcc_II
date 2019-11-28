package com.github.aguilasa.view.relationship;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import com.github.aguilasa.jhipster.Relationship;
import com.github.aguilasa.jhipster.RelationshipType;

import view.swing.custom.combo.ComboBox;

public class Row extends JPanel {

	private static final long serialVersionUID = 1963750200932814713L;
	public static final Color BORDER_COLOR = Color.decode("#DEE2E6");

	private ComboBox<RelationshipType> comboType;
	private JLabel lblRelationship;
	private Relationship relationship;

	public Row() {
		setLayout(null);
		setBackground(Color.WHITE);
		setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(222, 226, 230)));
		setPreferredSize(new Dimension(545, 50));

		lblRelationship = new JLabel("Relacionamento");
		lblRelationship.setHorizontalAlignment(SwingConstants.LEFT);
		lblRelationship.setVerticalAlignment(SwingConstants.BOTTOM);
		lblRelationship.setSize(330, 20);
		lblRelationship.setLocation(10, 15);
		lblRelationship.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		add(lblRelationship);

		comboType = new ComboBox<>();
		comboType.setModel(new DefaultComboBoxModel<>(RelationshipType.values()));
		comboType.setBounds(365, 5, 165, 38);
		add(comboType);

		comboType.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (relationship != null) {
						relationship.setRelationshipType((RelationshipType) e.getItem());
					}
				}
			}
		});
	}

	public Relationship getRelationship() {
		return relationship;
	}

	public void setRelationship(Relationship relationship) {
		this.relationship = relationship;
		comboType.setSelectedIndex(relationship.getRelationshipType().ordinal());
//		lblRelationship.setText(String.format("%s to %s", relationship.getFromEntity(), relationship.getToEntity()));
		lblRelationship.setText(String.format("%s", relationship.getToEntity()));
		lblRelationship.setToolTipText(lblRelationship.getText());
	}

}
