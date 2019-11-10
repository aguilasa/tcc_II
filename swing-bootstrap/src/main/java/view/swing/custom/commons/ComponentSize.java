package view.swing.custom.commons;

import lombok.Getter;

public enum ComponentSize {

	DEFAULT(38, 1, 0.25f), //
	LARGE(48, 1.25f, 0.30f), //
	SMALL(30, 0.875f, 0.20f);

	private static final int BASE_SIZE = 16;

	@Getter
	private int height;
	private float scale;
	@Getter
	private float border;

	private ComponentSize(int buttonHeight, float scale, float border) {
		this.height = buttonHeight;
		this.scale = scale;
		this.border = border;
	}

	public int getFontSize() {
		return (int) (BASE_SIZE * scale);
	}

}
