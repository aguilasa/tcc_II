package view.swing.custom.input;

import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.Document;

public class Input2 extends JTextField {

	private static final long serialVersionUID = -4177396588741203553L;
	private static final Border emptyBorder = BorderFactory.createEmptyBorder();

	public Input2() {

	}

	public Input2(final Document doc, final String text, final int columns) {
		super(doc, text, columns);
	}

	public Input2(final int columns) {
		super(columns);
	}

	public Input2(final String text) {
		super(text);
	}

	public Input2(final String text, final int columns) {
		super(text, columns);
	}

	@Override
	public Border getBorder() {
		return emptyBorder;
	}

	@Override
	public final void setBorder(Border border) {
		super.setBorder(emptyBorder);
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
	}

}
