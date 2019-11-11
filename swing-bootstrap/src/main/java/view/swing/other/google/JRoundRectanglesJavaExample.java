package view.swing.other.google;

import java.awt.Graphics;

import javax.swing.JFrame;

public class JRoundRectanglesJavaExample extends JFrame {
	private static final long serialVersionUID = 1896774006472118973L;

	public void paint(Graphics gr) {
		super.paint(gr);
		int x = 30;
		int y = 50;
		final int WIDTH = 90, HEIGHT = 100;
		final int HORIZ_GAPP = 110;
		gr.drawRoundRect(x, y, WIDTH, HEIGHT, 0, 0);
		x += HORIZ_GAPP;
		gr.drawRoundRect(x, y, WIDTH, HEIGHT, 30, 30);
		x += HORIZ_GAPP;
		gr.drawRoundRect(x, y, WIDTH, HEIGHT, 50, 50);
		x += HORIZ_GAPP;
		gr.drawRoundRect(x, y, WIDTH, HEIGHT, 90, 90);
	}

	public static void main(String[] ars) {
		JRoundRectanglesJavaExample frm = new JRoundRectanglesJavaExample();
		frm.setSize(470, 200);
		frm.setVisible(true);
	}
}