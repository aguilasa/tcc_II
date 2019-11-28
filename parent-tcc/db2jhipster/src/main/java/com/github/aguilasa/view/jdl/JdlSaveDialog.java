package com.github.aguilasa.view.jdl;

import javax.swing.JFileChooser;

public class JdlSaveDialog extends JFileChooser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4480261913162851767L;

	private JdlFileFilter filter = new JdlFileFilter();

	public JdlFileFilter getFilter() {
		return filter;
	}

	public JdlSaveDialog() {
		super();
		setDialogTitle("Salvar...");
		setDialogType(SAVE_DIALOG);
		addChoosableFileFilter(filter);
	}

}
