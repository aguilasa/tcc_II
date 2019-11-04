package view.swing.custom.bs;

import lombok.Getter;

public enum BsButtonSize {

	Default(34, 14), //
	Large(46, 18), //
	Small(30, 12), //
	ExtraSmall(22, 12);

	@Getter
	private int buttonHeight;
	@Getter
	private int fontSize;

	private BsButtonSize(int buttonHeight, int fontSize) {
		this.buttonHeight = buttonHeight;
		this.fontSize = fontSize;
	}

}
