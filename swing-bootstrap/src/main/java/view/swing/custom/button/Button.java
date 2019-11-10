package view.swing.custom.button;

import static view.swing.utils.AlterFonts.FONT_NAME;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;

import sun.swing.SwingUtilities2;
import view.swing.custom.commons.BorderSize;
import view.swing.custom.commons.ComponentSize;
import view.swing.custom.commons.Constants;

public class Button extends JButton {

	private static final long serialVersionUID = -8984172940729206373L;

	private ButtonColor buttonColor;
	private ButtonType buttonType = ButtonType.PRIMARY;
	private ComponentSize componentSize = ComponentSize.DEFAULT;
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
			buttonColor = new ButtonColor().copyFrom(Constants.getButtonColor(ButtonType.PRIMARY));
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
		r.height = componentSize.getHeight();
		resetFont();
		super.setBounds(r);
	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		height = componentSize.getHeight();
		resetFont();
		super.setBounds(x, y, width, height);
	}

	private void resetFont() {
		setFont(new Font(FONT_NAME, Font.PLAIN, componentSize.getFontSize()));
	}

	public void setButtonType(ButtonType type) {
		this.buttonType = type;
		getButtonColor().copyFrom(Constants.getButtonColor(buttonType));
		repaint();
	}

	public ButtonType getButtonType() {
		return buttonType;
	}

	public ComponentSize getComponentSize() {
		return componentSize;
	}

	public void setComponentSize(ComponentSize buttonSize) {
		this.componentSize = buttonSize;
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
		borderSize = new BorderSize(getWidth(), getHeight(), componentSize.getBorder());
	}

	@Override
	public void paintComponent(Graphics pg) {
		final Graphics2D g = (Graphics2D) pg;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		Color fontColor = getButtonColor().getFontColor();
		Color background = getButtonColor().getBackground();
		Color borderColor = getButtonColor().getBorder();
		boolean drawBorder = true;
		if (getModel().isEnabled()) {
			if (getModel().isPressed()) {
				background = getButtonColor().getPressed();
				borderColor = getButtonColor().getPressedBorder();
			} else if (getModel().isRollover()) {
				background = getButtonColor().getHover();
				borderColor = getButtonColor().getHoverBorder();
			}
		} else {
			drawBorder = false;
			fontColor = Constants.applyAlpha(fontColor, 0.65f);
			background = Constants.applyAlpha(background, 0.65f);
		}

		g.setColor(background);
		g.fillRoundRect(0, 0, getWidth(), getHeight(), getBorderSize().getSize(), getBorderSize().getSize());

		if (drawBorder) {
			g.setColor(borderColor);
			g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getBorderSize().getSize(), getBorderSize().getSize());
		}

		g.setColor(fontColor);
		int auxY = ((getBounds().height - g.getFontMetrics().getHeight()) / 2) + g.getFontMetrics().getMaxAscent();
		int auxX = ((getBounds().width - g.getFontMetrics().stringWidth(getText())) / 2);
		SwingUtilities2.drawString(this, g, getText(), auxX, auxY);
	}

}
