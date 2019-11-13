package com.github.aguilasa.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.swing.custom.button.Button;
import view.swing.custom.button.ButtonType;
import view.swing.image.ImagePanel;

public class MainView extends JFrame {

	private static final long serialVersionUID = -732397022688096890L;
	private static final int WIDTH = 960;
	private static final int HEIGHT = 600;
	private static final int AREA_WIDTH = 585;
	private static final int AREA_HEIGHT = 480;
	public static final Dimension AREA_SIZE = new Dimension(AREA_WIDTH, AREA_HEIGHT);

	private Button btnNext;
	private Button btnBack;
	private JPanel area;
	private DBConfigView dbConfigView;

	public MainView() {
		initialize();
	}

	private void initialize() {
		getContentPane().setLayout(null);
		setSize(WIDTH, HEIGHT);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImagePanel panel = new ImagePanel("src/main/resources/images/side.jpg");
		panel.setBounds(0, 0, 350, 600);
		getContentPane().add(panel);

		JPanel buttons = new JPanel();
		buttons.setBackground(Color.WHITE);
		buttons.setBounds(360, 500, 585, 60);
		getContentPane().add(buttons);
		buttons.setLayout(null);

		btnNext = new Button(ButtonType.PRIMARY);
		btnNext.setText("Avançar");
		btnNext.setBounds(486, 11, 89, 23);
		buttons.add(btnNext);

		btnBack = new Button(ButtonType.PRIMARY);
		btnBack.setEnabled(false);
		btnBack.setText("Voltar");
		btnBack.setBounds(387, 11, 89, 38);
		buttons.add(btnBack);

		JPanel area = new JPanel();
		area.setBackground(Color.WHITE);
		area.setBounds(360, 10, 585, 480);
		getContentPane().add(area);

		dbConfigView = new DBConfigView();
		dbConfigView.setBounds(360, 10, AREA_WIDTH, AREA_HEIGHT);
		area.add(dbConfigView);

	}
}
