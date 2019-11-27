package view.swing.custom.input;

import static view.swing.utils.AlterFonts.FONT_NAME;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import view.swing.custom.commons.BorderSize;
import view.swing.custom.commons.ComponentSize;
import view.swing.custom.commons.Constants;

public class Input extends JPanel {

	private static final long serialVersionUID = -4177396588741203553L;
	private static final Border emptyBorder = BorderFactory.createEmptyBorder();

	// vis�veis
	private ComponentSize componentSize = ComponentSize.DEFAULT;
	private boolean enabled;

	// n�o vis�veis
	JTextField textField;
	private BorderSize borderSize;

	boolean focused = false;

	public Input() {
		this(ComponentSize.DEFAULT);
	}

	public Input(ComponentSize componentSize) {
		this.componentSize = componentSize;
		initialize();
	}

	private void initialize() {
		setLayout(null);
		setEnabled(true);
		setFont(new Font(FONT_NAME, Font.PLAIN, componentSize.getFontSize()));
		setSize(50, ComponentSize.DEFAULT.getHeight());

		createTextField();
	}

	private void createTextField() {
		textField = new JTextField();
		textField.addFocusListener(new InputFocusListener(this));
		resetTextFieldSizes();
		textField.setBorder(emptyBorder);
		textField.setFont(getFont());
		textField.setForeground(Constants.INPUT_FONT);
		textField.setDisabledTextColor(Constants.INPUT_FONT);
		textField.setSelectionColor(Constants.INPUT_SELECTION);
		textField.setSelectedTextColor(Constants.INPUT_SELECTED);
		add(textField);
	}

	public ComponentSize getComponentSize() {
		return componentSize;
	}

	public void setComponentSize(ComponentSize componentSize) {
		this.componentSize = componentSize;
		resetBorderSize();
		resetBounds();
		resetFont();
		repaint();
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		resetEnabled();
	}

	private void resetEnabled() {
		if (textField != null) {
			textField.setEnabled(enabled);
		}
	}

	public String getText() {
		if (textField != null) {
			return textField.getText();
		}
		return "";
	}

	public void setText(String text) {
		if (textField != null) {
			textField.setText(text);
		}
	}

	@Override
	public void setBounds(Rectangle r) {
		if (!focused) {
			r.height = componentSize.getHeight();
		}
		super.setBounds(r);
		resetSizes();
		resetEnabled();
	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		if (!focused) {
			height = componentSize.getHeight();
		}
		super.setBounds(x, y, width, height);
		resetSizes();
		resetEnabled();
	}

	private BorderSize getBorderSize() {
		if (borderSize == null) {
			resetBorderSize();
		}
		return borderSize;
	}

	private void resetBorderSize() {
		borderSize = new BorderSize(getWidth(), getHeight(), componentSize.getBorder());
	}

	private void resetSizes() {
		setFont(new Font(FONT_NAME, Font.PLAIN, componentSize.getFontSize()));
		resetTextFieldSizes();
	}

	private void resetTextFieldSizes() {
		if (textField != null) {
			FontMetrics fontMetrics = getFontMetrics(getFont());
			int newY = (getHeight() - fontMetrics.getHeight()) / 2;
			int newX = (int) (componentSize.getFontSize() * componentSize.getPadding());
			int newWidth = getWidth() - newX * 2;

			if (focused) {
				newX += 3;
				newWidth -= 6;
			}

			textField.setFont(getFont());
			textField.setBounds(newX, newY, newWidth, fontMetrics.getHeight());
		}
	}

	private void resetFont() {
		setFont(new Font(FONT_NAME, Font.PLAIN, componentSize.getFontSize()));
	}

	private void resetBounds() {
		Rectangle bounds = getBounds();
		setBounds(bounds);
	}

	@Override
	public void paintComponent(Graphics pg) {
		final Graphics2D g = (Graphics2D) pg;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		int border = getBorderSize().getSize();

		clearBackground(g);

		int auxX = 0;
		int auxY = 0;
		int auxWidth = getWidth();
		int auxHeight = getHeight();

		if (focused) {
			auxX = 3;
			auxY = 3;
			auxWidth -= 6;
			auxHeight -= 6;

			border = new BorderSize(getWidth(), getHeight(), 0.3f).getSize();
			g.setColor(Constants.INPUT_BORDER_SHADOW);
			g.fillRoundRect(0, 0, getWidth(), getHeight(), border, border);

			border = new BorderSize(getWidth() - 6, getHeight() - 6, componentSize.getBorder()).getSize();
		}

		g.setColor(Color.WHITE);
		g.fillRoundRect(auxX, auxY, auxWidth, auxHeight, border, border);

		g.setColor(focused ? Constants.INPUT_BORDER_FOCUS : Constants.INPUT_BORDER);
		g.drawRoundRect(auxX, auxY, auxWidth - 1, auxHeight - 1, border, border);
	}

	private void clearBackground(final Graphics2D g) {
		g.setColor(getParent().getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
	}

}

class InputFocusListener implements FocusListener {

	Input owner;
	private Rectangle bounds;

	public InputFocusListener(Input owner) {
		this.owner = owner;
	}

	@Override
	public void focusGained(FocusEvent e) {
		selectText();
		owner.focused = true;
		bounds = owner.getBounds();
		owner.setBounds(new Rectangle(bounds.x - 3, bounds.y - 3, bounds.width + 6, bounds.height + 6));
	}

	@Override
	public void focusLost(FocusEvent e) {
		clearSelectText();
		owner.focused = false;
		owner.setBounds(bounds);
	}

	private void selectText() {
		if (owner.textField != null) {
			if (owner.textField.getMousePosition() == null) {
				owner.textField.select(0, owner.textField.getText().length());
			}
		}
	}

	private void clearSelectText() {
		if (owner.textField != null) {
			owner.textField.select(0, 0);
		}
	}

}