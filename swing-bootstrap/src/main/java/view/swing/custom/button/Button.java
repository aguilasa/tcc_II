package view.swing.custom.button;

import static view.swing.utils.AlterFonts.FONT_NAME;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;

import lombok.Getter;
import sun.swing.SwingUtilities2;

public class Button extends JButton {

	private static final long serialVersionUID = -8984172940729206373L;

	private ButtonColor buttonColor;
	private ButtonType buttonType = ButtonType.PRIMARY;
	private ButtonSize buttonSize = ButtonSize.DEFAULT;
	private BorderSize borderSize;

	public Button(ButtonType type) {
		setContentAreaFilled(false);
		setOpaque(false);
		setBorderPainted(false);
		setButtonType(type);
		setSize(50, 25);
		resetFont();
	}

	private ButtonColor getButtonColor() {
		if (buttonColor == null) {
			buttonColor = new ButtonColor().copyFrom(ButtonConstants.getButtonColor(ButtonType.PRIMARY));
		}
		return buttonColor;
	}

	@Override
	public void setBackground(Color bg) {
		getButtonColor().setBackground(bg);
	}

	public void setPressedBackground(Color bg) {
		getButtonColor().setPressed(bg);
	}

	public void setHoverBackground(Color bg) {
		getButtonColor().setHover(bg);
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
		setFont(new Font(FONT_NAME, Font.PLAIN, buttonSize.getFontSize()));
	}

	public void setButtonType(ButtonType type) {
		this.buttonType = type;
		getButtonColor().copyFrom(ButtonConstants.getButtonColor(buttonType));
		repaint();
	}

	public ButtonType getButtonType() {
		return buttonType;
	}

	public ButtonSize getButtonSize() {
		return buttonSize;
	}

	public void setButtonSize(ButtonSize buttonSize) {
		this.buttonSize = buttonSize;
		setBorderSize();
		resetBounds();
		resetFont();
		repaint();
	}

	private BorderSize getBorderSize() {
		if (borderSize == null) {
			setBorderSize();
		}
		return borderSize;
	}

	private void setBorderSize() {
		borderSize = new BorderSize(getWidth(), getHeight(), buttonSize.getBorder());
	}

	@Override
	public void paint(Graphics pg) {
		final Graphics2D g = (Graphics2D) pg;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		super.paint(pg);

		Color background = getButtonColor().getBackground();
		Color border = getButtonColor().getBorder();
		if (getModel().isPressed()) {
			background = getButtonColor().getPressed();
			border = getButtonColor().getPressedBorder();
		} else if (getModel().isRollover()) {
			background = getButtonColor().getHover();
			border = getButtonColor().getHoverBorder();
		}

		g.setColor(background);
		g.fillRoundRect(0, 0, getWidth(), getHeight(), getBorderSize().getSize(), getBorderSize().getSize());

		g.setColor(border);
		g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getBorderSize().getSize(), getBorderSize().getSize());

		g.setColor(getButtonColor().getFontColor());
		int auxY = ((getBounds().height - g.getFontMetrics().getHeight()) / 2) + g.getFontMetrics().getMaxAscent();
		int auxX = ((getBounds().width - g.getFontMetrics().stringWidth(getText())) / 2);
		SwingUtilities2.drawString(this, g, getText(), auxX, auxY);
	}

}

@Getter
class BorderSize {

	private int size;

	public BorderSize(int width, int height, float scale) {
		int value = Math.min(width, height);
		size = (int) (value * scale);
	}
}
