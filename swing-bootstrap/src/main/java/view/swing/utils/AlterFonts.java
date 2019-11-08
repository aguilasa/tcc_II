package view.swing.utils;

import java.awt.Font;

import javax.swing.UIManager;

public class AlterFonts {
	public static final String DEFAULT_FONT_NAME = "SansSerif";
	public static final String ALTERNATIVE_FONT_NAME = "HelveticaNeue";
	public static final String SEGOE = "Segoe UI Light";
	public static final String FONT_NAME = DEFAULT_FONT_NAME;
	
	public static final Font DEFAULT_FONT = new Font(FONT_NAME, Font.TRUETYPE_FONT, 12);
	public static final Font ALTERNATIVE_FONT = new Font(ALTERNATIVE_FONT_NAME, Font.TRUETYPE_FONT, 12);

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
