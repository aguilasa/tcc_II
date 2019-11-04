package view.swing.custom.bs;

import static view.swing.utils.AlterFonts.FONT_NAME;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class BsButton extends JButton {

	private static final long serialVersionUID = -8984172940729206373L;

	private static final int BORDER_SIZE = 10;

	private BsButtonColor buttonColor;

	private String text;
	private Font font;

	private BsButtonType buttonType = BsButtonType.DEFAULT;
	private BsButtonSize buttonSize = BsButtonSize.Default;

	public BsButton(String text, BsButtonType type) {
		setContentAreaFilled(false);
		setOpaque(false);
		setBorderPainted(false);
		setButtonType(type);
		setSize(50, 25);
		resetFont();
		this.text = text;
	}

	private BsButtonColor getButtonColor() {
		if (buttonColor == null) {
			buttonColor = new BsButtonColor().copyFrom(BsButtonConstants.DEFAULT);
		}
		return buttonColor;
	}

	@Override
	public void setBackground(Color bg) {
		getButtonColor().setColorBg(bg);
	}

	public void setPressedBackground(Color bg) {
		getButtonColor().setColorBgPressed(bg);
	}

	public void setHoverBackground(Color bg) {
		getButtonColor().setColorBgHover(bg);
	}

	@Override
	public void setText(String text) {
		this.text = text;
		repaint();
	}

	private void resetBounds() {
		Rectangle bounds = getBounds();
		setBounds(bounds);
	}

	@Override
	public void setBounds(Rectangle r) {
		r.height = buttonSize.getButtonHeight();
		resetFont();
		super.setBounds(r);
	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		height = buttonSize.getButtonHeight();
		resetFont();
		super.setBounds(x, y, width, height);
	}

	private void resetFont() {
		font = new Font(FONT_NAME, Font.TRUETYPE_FONT, buttonSize.getFontSize());
		setFont(font);
	}

	public void setButtonType(BsButtonType type) {
		this.buttonType = type;
		getButtonColor().copyFrom(type.getButtonColor());
		repaint();
	}

	public BsButtonType getButtonType() {
		return buttonType;
	}

	public BsButtonSize getButtonSize() {
		return buttonSize;
	}

	public void setButtonSize(BsButtonSize buttonSize) {
		this.buttonSize = buttonSize;
		resetBounds();
		resetFont();
		repaint();
	}

	@Override
	public void paint(Graphics pg) {
		final Graphics2D g = (Graphics2D) pg;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		super.paint(pg);

		if (getModel().isPressed())
			g.setColor(getButtonColor().getColorBgPressed());
		else if (getModel().isRollover())
			g.setColor(getButtonColor().getColorBgHover());
		else
			g.setColor(getButtonColor().getColorBg());

		g.fillRoundRect(0, 0, getWidth(), getHeight(), BORDER_SIZE, BORDER_SIZE);
		g.setColor(getButtonColor().getColorBorder());
		g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, BORDER_SIZE, BORDER_SIZE);

		g.setColor(getButtonColor().getColorFont());
		int auxY = ((getBounds().height - g.getFontMetrics().getHeight()) / 2);
		int auxX = ((getBounds().width - g.getFontMetrics().stringWidth(text)) / 2);
		g.drawString(text, auxX, g.getFontMetrics().getMaxAscent() + auxY);
	}

}
