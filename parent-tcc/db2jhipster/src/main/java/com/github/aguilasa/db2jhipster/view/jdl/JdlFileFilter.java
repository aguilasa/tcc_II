package com.github.aguilasa.db2jhipster.view.jdl;

import java.io.File;
import java.util.Locale;

import javax.swing.filechooser.FileFilter;

public class JdlFileFilter extends FileFilter {

	private final String description;
	private final String[] extensions;
	private final String[] lowerCaseExtensions;

	public JdlFileFilter() {
		this.description = "Arquivos JDL";
		this.extensions = new String[] { "jdl" };
		this.lowerCaseExtensions = new String[this.extensions.length];
		for (int i = 0; i < this.extensions.length; i++) {
			this.lowerCaseExtensions[i] = this.extensions[i].toLowerCase(Locale.ENGLISH);
		}
	}

	public JdlFileFilter(String description, String... extensions) {
		if (extensions == null || extensions.length == 0) {
			throw new IllegalArgumentException("As extensões devem ser informadas");
		}
		this.description = description;
		this.extensions = new String[extensions.length];
		this.lowerCaseExtensions = new String[extensions.length];
		for (int i = 0; i < extensions.length; i++) {
			if (extensions[i] == null || extensions[i].length() == 0) {
				throw new IllegalArgumentException("Cada extensão deve ser informada.");
			}
			this.extensions[i] = extensions[i];
			lowerCaseExtensions[i] = extensions[i].toLowerCase(Locale.ENGLISH);
		}
	}

	public boolean accept(File f) {
		if (f != null) {
			if (f.isDirectory()) {
				return true;
			}

			String fileName = f.getName();
			int i = fileName.lastIndexOf('.');
			if (i > 0 && i < fileName.length() - 1) {
				String desiredExtension = fileName.substring(i + 1).toLowerCase(Locale.ENGLISH);
				for (String extension : lowerCaseExtensions) {
					if (desiredExtension.equals(extension)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public String getDescription() {
		return description;
	}

	public String[] getExtensions() {
		String[] result = new String[extensions.length];
		System.arraycopy(extensions, 0, result, 0, extensions.length);
		return result;
	}

	public String toString() {
		return super.toString() + "[description=" + getDescription() + " extensions="
				+ java.util.Arrays.asList(getExtensions()) + "]";
	}

}
