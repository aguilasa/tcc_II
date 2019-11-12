package com.github.aguilasa.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class DB2JHipsterView {

	private static final int WIDTH = 960;
	private static final int HEIGHT = 600;

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DB2JHipsterView window = new DB2JHipsterView();
					window.frame.setVisible(true);
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					window.frame.setLocation(dim.width / 2 - window.frame.getSize().width / 2, dim.height / 2 - window.frame.getSize().height / 2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DB2JHipsterView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
