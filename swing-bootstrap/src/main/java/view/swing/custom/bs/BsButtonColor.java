package view.swing.custom.bs;

import java.awt.Color;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BsButtonColor {

	private Color colorFont;
	private Color colorBg;
	private Color colorBgHover;
	private Color colorBgPressed;
	private Color colorBorder;

	public BsButtonColor copyFrom(BsButtonColor other) {
		setColorFont(other.getColorFont());
		setColorBg(other.getColorBg());
		setColorBgHover(other.getColorBgHover());
		setColorBgPressed(other.getColorBgPressed());
		setColorBorder(other.getColorBorder());
		return this;
	}

}
