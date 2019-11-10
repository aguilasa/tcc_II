package view.swing.custom.commons;

import lombok.Getter;

public enum ComponentSize {

	DEFAULT(38, 1, 0.25f, 1), //
	LARGE(48, 1.25f, 0.30f, 0.75f), //
	SMALL(30, 0.875f, 0.20f, 0.5f);

	private static final int BASE_SIZE = 16;

	@Getter
	private int height;
	private float scale;
	@Getter
	private float border;
	@Getter
	private float padding;

	private ComponentSize(int buttonHeight, float scale, float border, float padding) {
		this.height = buttonHeight;
		this.scale = scale;
		this.border = border;
		this.padding = padding;
	}

	public int getFontSize() {
		return (int) (BASE_SIZE * scale);
	}

}
