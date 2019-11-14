package com.github.aguilasa.view;

import java.awt.Font;
import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import com.github.aguilasa.view.base.AreaPanel;

import view.swing.custom.button.Button;
import view.swing.custom.button.ButtonType;
import view.swing.custom.commons.ComponentSize;

public class JdlView extends AreaPanel {

	private static final long serialVersionUID = -8692593091481877625L;

	private Button btnSave;
	private JTextArea textArea;

	/**
	 * Create the panel.
	 */
	public JdlView() {

		JLabel lblJdlGerada = new JLabel("JDL gerada");
		lblJdlGerada.setVerticalAlignment(SwingConstants.BOTTOM);
		lblJdlGerada.setHorizontalAlignment(SwingConstants.LEFT);
		lblJdlGerada.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblJdlGerada.setBounds(10, 10, 326, 20);
		add(lblJdlGerada);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 570, 393);
		add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		btnSave = new Button(ButtonType.PRIMARY);
		btnSave.setText("Salvar");
		btnSave.setComponentSize(ComponentSize.SMALL);
		btnSave.setBounds(475, 439, 100, 30);
		add(btnSave);
	}

	public void setText(String text) {
		textArea.setText(text);

	}

	private void save() {

	}

	public void setObservable(MainView mainView) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Observable getObservable() {
		return null;
	}
}
