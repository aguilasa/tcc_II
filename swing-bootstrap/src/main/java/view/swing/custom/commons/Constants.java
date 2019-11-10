package view.swing.custom.commons;

import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.Map;

import view.swing.custom.button.ButtonColor;
import view.swing.custom.button.ButtonType;

enum ColorProperties {
	fontColor, background, border, hover, hoverBorder, pressed, pressedBorder;
}

public class Constants {

	private static final String[][] COLORS = { //
			{ "#FFFFFF", "#007BFF", "#007BFF", "#0062CC", "#005CBF", "#0062CC", "#005CBF" }, //
			{ "#FFFFFF", "#6C757D", "#6C757D", "#545B62", "#4E555B", "#545B62", "#4E555B" }, //
			{ "#FFFFFF", "#28A745", "#28A745", "#1E7E34", "#1C7430", "#1E7E34", "#1C7430" }, //
			{ "#FFFFFF", "#DC3545", "#DC3545", "#BD2130", "#B21F2D", "#BD2130", "#B21F2D" }, //
			{ "#21251A", "#FFC107", "#FFC107", "#D39E00", "#C69500", "#D39E00", "#C69500" }, //
			{ "#FFFFFF", "#17A2B8", "#17A2B8", "#117A8B", "#10707F", "#117A8B", "#10707F" }, //
			{ "#21251A", "#F8F9FA", "#F8F9FA", "#DAE0E5", "#D3D9DF", "#DAE0E5", "#D3D9DF" }, //
			{ "#FFFFFF", "#343A40", "#343A40", "#1D2124", "#171A1D", "#1D2124", "#171A1D" }//
	};

	private static final Map<ButtonType, ButtonColor> BUTTONS_COLORS = new LinkedHashMap<>();

	private static final void initialize() {
		ButtonType[] values = ButtonType.values();
		for (ButtonType buttonType : values) {
			ButtonColor buttonColor = getButtonColorFromString(COLORS[buttonType.ordinal()]);
			BUTTONS_COLORS.put(buttonType, buttonColor);
		}
	}

	private static ButtonColor getButtonColorFromString(String[] colors) {
		ButtonColor buttonColor = new ButtonColor();
		buttonColor.setFontColor(Color.decode(colors[ColorProperties.fontColor.ordinal()]));
		buttonColor.setBackground(Color.decode(colors[ColorProperties.background.ordinal()]));
		buttonColor.setBorder(Color.decode(colors[ColorProperties.border.ordinal()]));
		buttonColor.setHover(Color.decode(colors[ColorProperties.hover.ordinal()]));
		buttonColor.setHoverBorder(Color.decode(colors[ColorProperties.hoverBorder.ordinal()]));
		buttonColor.setPressed(Color.decode(colors[ColorProperties.pressed.ordinal()]));
		buttonColor.setPressedBorder(Color.decode(colors[ColorProperties.pressedBorder.ordinal()]));
		return buttonColor;
	}

	public static final ButtonColor getButtonColor(ButtonType buttonType) {
		return BUTTONS_COLORS.get(buttonType);
	}

	public static final Color applyAlpha(Color color, float alpha) {
		return new Color(color.getRed(), color.getGreen(), color.getBlue(), Math.round(255 * alpha));
	}

	public static final Color INPUT_FONT = Color.decode("#495057");
	public static final Color INPUT_BORDER = Color.decode("#CED4DA");
	public static final Color INPUT_SELECTION = Color.decode("#338FFF");
	public static final Color INPUT_SELECTED = Color.WHITE;
	public static final Color INPUT_BORDER_FOCUS = Color.decode("#80BDFF");
	public static final Color INPUT_BORDER_SHADOW = applyAlpha(Color.decode("#007BFF"), 0.25f);

	static {
		initialize();
	}

}
