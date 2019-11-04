package view.swing.custom.bs;

import lombok.Getter;

public enum BsButtonType {

	DEFAULT(BsButtonConstants.DEFAULT), //
	PRIMARY(BsButtonConstants.PRIMARY), //
	SUCCESS(BsButtonConstants.SUCCESS), //
	INFO(BsButtonConstants.INFO), //
	WARNING(BsButtonConstants.WARNING), //
	DANGER(BsButtonConstants.DANGER);

	private BsButtonType(BsButtonColor buttonColor) {
		this.buttonColor = buttonColor;
	}

	@Getter
	private BsButtonColor buttonColor;

}
