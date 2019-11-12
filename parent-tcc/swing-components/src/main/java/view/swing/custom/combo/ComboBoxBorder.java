package view.swing.custom.combo;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Objects;

import javax.swing.border.AbstractBorder;

import view.swing.custom.commons.Constants;

public class ComboBoxBorder<E> extends AbstractBorder {

	private static final long serialVersionUID = 6064110310915047212L;

	private ComboBox<E> comboBox;

	public ComboBoxBorder(ComboBox<E> comboBox) {
		this.comboBox = comboBox;
	}

	@Override
	public void paintBorder(Component c, Graphics pg, int x, int y, int width, int height) {
		Graphics2D g = (Graphics2D) pg.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		int border = comboBox.getBorderSize().getSize();
		int w = width - 1;
		int h = height - 1;

		Area round = new Area(new RoundRectangle2D.Double(x, y, w, h, border, border));

		Container parent = c.getParent();
		if (Objects.nonNull(parent)) {
			g.setPaint(parent.getBackground());
			Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
			corner.subtract(round);
			g.fill(corner);
		}
		g.setPaint(Constants.INPUT_BORDER);
		g.draw(round);
		g.dispose();
	}

	@Override
	public Insets getBorderInsets(Component c) {
		return new Insets(1, 1, 1, 1);
	}

	@Override
	public Insets getBorderInsets(Component c, Insets insets) {
		insets.set(1, 1, 1, 1);
		return insets;
	}

}
