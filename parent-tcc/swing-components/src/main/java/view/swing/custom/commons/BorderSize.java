package view.swing.custom.commons;

import lombok.Getter;

@Getter
public class BorderSize {

	private int size;

	public BorderSize(int width, int height, float scale) {
		int value = Math.min(width, height);
		size = (int) (value * scale);
	}
}