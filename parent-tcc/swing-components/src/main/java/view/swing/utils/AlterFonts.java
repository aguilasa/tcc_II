package view.swing.utils;

import java.awt.Font;

import javax.swing.UIManager;

public class AlterFonts {
	public static final String SEGOE = "Segoe UI";
	public static final String ROBOTO = "Roboto";
	public static final String FONT_NAME = SEGOE;

	public static final Font DEFAULT_FONT = new Font(FONT_NAME, Font.PLAIN, 12);

	public static void alterFonts() {
		UIManager.put("Menu.font", DEFAULT_FONT);
		UIManager.put("MenuItem.font", DEFAULT_FONT);
		UIManager.put("Label.font", DEFAULT_FONT);
		UIManager.put("Button.font", DEFAULT_FONT);
		UIManager.put("RadioButton.font", DEFAULT_FONT);
		UIManager.put("CheckBox.font", DEFAULT_FONT);
		UIManager.put("TitledBorder.font", DEFAULT_FONT);
		UIManager.put("ComboBox.font", DEFAULT_FONT);
	}
}
