package com.github.aguilasa.view.jdl;

import javax.swing.JFileChooser;

import lombok.Getter;

public class JdlSaveDialog extends JFileChooser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4480261913162851767L;

	@Getter
	private JdlFileFilter filter = new JdlFileFilter();

	public JdlSaveDialog() {
		super();
		setDialogTitle("Salvar...");
		setDialogType(SAVE_DIALOG);
		addChoosableFileFilter(filter);
	}

}
