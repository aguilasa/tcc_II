package view.swing.custom.combo;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;

import view.swing.custom.commons.Constants;

class ComboBoxArrowIcon implements Icon {

	private static final double POINTS[][] = { { 2, 2 }, { 8, 2 }, { 5, 8 }, { 2, 2 } };

	@Override
	public void paintIcon(Component c, Graphics pg, int x, int y) {
		Graphics2D g = (Graphics2D) pg.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		g.setPaint(Constants.ARROW);
		int shift = 0;
		if (c instanceof AbstractButton) {
			ButtonModel m = ((AbstractButton) c).getModel();
			if (m.isPressed()) {
				shift = 1;
			}
		}
		g.translate(x + 8, y + shift);
		GeneralPath arrow = new GeneralPath();
		arrow.moveTo(POINTS[0][0], POINTS[0][1]);

		for (int k = 1; k < POINTS.length; k++)
			arrow.lineTo(POINTS[k][0], POINTS[k][1]);

		arrow.closePath();

		g.fill(arrow);
		g.dispose();
	}

	@Override
	public int getIconWidth() {
		return 6;
	}

	@Override
	public int getIconHeight() {
		return 6;
	}
}