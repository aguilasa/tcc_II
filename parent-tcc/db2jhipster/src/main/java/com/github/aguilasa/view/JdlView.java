package com.github.aguilasa.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.github.aguilasa.selenium.SendToJdlStudio;
import com.github.aguilasa.view.base.AreaPanel;
import com.github.aguilasa.view.jdl.JdlSaveDialog;

import view.swing.custom.button.Button;
import view.swing.custom.button.ButtonType;
import view.swing.custom.commons.ComponentSize;

public class JdlView extends AreaPanel {

	private static final long serialVersionUID = -8692593091481877625L;

	private static final String EXTENSION = "jh";

	private Button btnSave;
	private Button btnJdlStudio;
	private JTextArea textArea;
	private SendToJdlStudio sender = new SendToJdlStudio();

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
		textArea.setFont(new Font("Courier New", Font.PLAIN, 14));
		scrollPane.setViewportView(textArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		btnSave = new Button(ButtonType.PRIMARY);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		btnSave.setText("Salvar");
		btnSave.setComponentSize(ComponentSize.SMALL);
		btnSave.setBounds(475, 439, 100, 30);
		add(btnSave);

		btnJdlStudio = new Button(ButtonType.PRIMARY);
		btnJdlStudio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jdlStudio();
			}
		});
		btnJdlStudio.setText("JDL Studio");
		btnJdlStudio.setComponentSize(ComponentSize.SMALL);
		btnJdlStudio.setBounds(365, 439, 100, 30);
		add(btnJdlStudio);
	}

	public void setText(String text) {
		textArea.setText(text);
	}

	private boolean canSave() {
		return StringUtils.isNotEmpty(textArea.getText());
	}

	private void jdlStudio() {
		String tmp = System.getProperty("java.io.tmpdir");
		if (!tmp.endsWith(File.separator)) {
			tmp = tmp.concat(File.separator);
		}
		String fileName = String.format("%sout.%s", tmp, EXTENSION);
		saveJdl(fileName);
		sender.setFilePath(fileName);
		sender.send();
	}

	private void save() {
		if (!canSave())
			return;

		String fileName = getFilename();

		if (StringUtils.isNotEmpty(fileName)) {
			saveJdl(fileName);
		}
	}

	public void saveJdl(String fileName) {
		try {
			FileUtils.write(new File(fileName), textArea.getText(), "UTF-8");
		} catch (IOException e) {
			StringBuilder message = new StringBuilder();
			message.append("Erro ao salvar arquivo JDL:").append("\r\n");
			message.append(e.getMessage());
			JOptionPane.showMessageDialog(getMainView(), message.toString());
			e.printStackTrace();
		}
	}

	public String getFilename() {
		JdlSaveDialog saveDialog = new JdlSaveDialog();
		String fileName = "";
		if (saveDialog.showDialog(getMainView(), "Salvar") == JdlSaveDialog.APPROVE_OPTION) {
			fileName = saveDialog.getSelectedFile().getPath();
			if (!validaNomeArquivo(fileName)) {
				fileName += fileName.concat(EXTENSION);
			}
		}
		return fileName;
	}

	Pattern pattern = Pattern.compile("([^\\s]+(\\.(" + EXTENSION + "))$)");

	private boolean validaNomeArquivo(String nome) {
		if (nome == null || nome.isEmpty()) {
			return false;
		}
		Matcher matcher = pattern.matcher(nome);
		return matcher.matches();
	}

	@Override
	protected Observable getObservable() {
		return null;
	}
}
