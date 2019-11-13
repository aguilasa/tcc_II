package view.swing.custom.button;

import java.awt.Color;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ButtonColor {

	private Color fontColor;
	private Color background;
	private Color border;
	private Color hover;
	private Color hoverBorder;
	private Color pressed;
	private Color pressedBorder;
	private Color fontColorDisabled;

	public ButtonColor copyFrom(ButtonColor other) {
		setFontColor(other.getFontColor());
		setBackground(other.getBackground());
		setBorder(other.getBorder());
		setHover(other.getHover());
		setHoverBorder(other.getHoverBorder());
		setPressed(other.getPressed());
		setPressedBorder(other.getPressedBorder());
		setFontColorDisabled(other.getFontColorDisabled());
		return this;
	}

}
