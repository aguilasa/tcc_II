package com.github.aguilasa.view.base;

import java.awt.Color;

import javax.swing.JPanel;

import com.github.aguilasa.view.MainView;

public class AreaPanel extends JPanel {

	private static final long serialVersionUID = 4048339675007575890L;

	public AreaPanel() {
		setBackground(Color.WHITE);
		setLayout(null);
		setPreferredSize(MainView.AREA_SIZE);
	}

}
