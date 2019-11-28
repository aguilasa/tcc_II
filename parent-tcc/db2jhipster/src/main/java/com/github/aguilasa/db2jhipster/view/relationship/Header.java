package com.github.aguilasa.db2jhipster.view.relationship;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class Header extends JPanel {

	private static final long serialVersionUID = 1963750200932814713L;
	public static final Color BORDER_COLOR = Color.decode("#DEE2E6");

	public Header() {
		setLayout(null);
		setBackground(Color.WHITE);
		setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(222, 226, 230)));
		setPreferredSize(new Dimension(545, 50));

		JLabel lblRelationship = new JLabel("Relacionamento");
		lblRelationship.setHorizontalAlignment(SwingConstants.LEFT);
		lblRelationship.setVerticalAlignment(SwingConstants.BOTTOM);
		lblRelationship.setSize(330, 20);
		lblRelationship.setLocation(10, 15);
		lblRelationship.setFont(new Font("Segoe UI", Font.BOLD, 16));
		add(lblRelationship);

		JLabel lblType = new JLabel("Tipo");
		lblType.setHorizontalAlignment(SwingConstants.LEFT);
		lblType.setVerticalAlignment(SwingConstants.BOTTOM);
		lblType.setSize(165, 20);
		lblType.setLocation(365, 15);
		lblType.setFont(new Font("Segoe UI", Font.BOLD, 16));
		add(lblType);
	}

}
