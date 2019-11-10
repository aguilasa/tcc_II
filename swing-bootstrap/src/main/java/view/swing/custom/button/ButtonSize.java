package view.swing.custom.button;

import lombok.Getter;

public enum ButtonSize {

	DEFAULT(38, 1, 0.25f), //
	LARGE(48, 1.25f, 0.25f), //
	SMALL(30, 0.875f, 0.25f);

	private static final int BASE_SIZE = 16;

	@Getter
	private int buttonHeight;
	private float scale;
	@Getter
	private float border;

	private ButtonSize(int buttonHeight, float scale, float border) {
		this.buttonHeight = buttonHeight;
		this.scale = scale;
		this.border = border;
	}

	public int getFontSize() {
		return (int) (BASE_SIZE * scale);
	}

}
