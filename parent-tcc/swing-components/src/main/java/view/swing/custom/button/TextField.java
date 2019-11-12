package view.swing.custom.button;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextField;
import javax.swing.text.Document;

public class TextField extends JTextField {

	private static final long serialVersionUID = 1L;
	private String placeholder;

	public TextField() {

	}

	public TextField(final Document pDoc, final String pText, final int pColumns) {
		super(pDoc, pText, pColumns);
	}

	public TextField(final int pColumns) {
		super(pColumns);
	}

	public TextField(final String pText) {
		super(pText);
	}

	public TextField(final String pText, final int pColumns) {
		super(pText, pColumns);
	}

	public String getPlaceholder() {
		return placeholder;
	}

	@Override
	protected void paintComponent(final Graphics pG) {
		super.paintComponent(pG);

		if (placeholder.length() == 0 || getText().length() > 0) {
			return;
		}

		final Graphics2D g = (Graphics2D) pG;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(getDisabledTextColor());

		int auxY = ((getBounds().height - pG.getFontMetrics().getHeight()) / 2);

		g.drawString(placeholder, 10, pG.getFontMetrics().getMaxAscent() + auxY);
	}

	public void setPlaceholder(final String s) {
		placeholder = s;
	}

}