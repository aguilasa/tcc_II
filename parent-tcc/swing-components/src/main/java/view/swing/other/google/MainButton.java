package view.swing.other.google;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainButton {

	public static void main(String argv[]) {
		JFrame f = new JFrame();
		f.setSize(400, 300);
		f.getContentPane().setLayout(new FlowLayout());

		JPanel p = new JPanel();
		JButton bt1 = new JButton("Click Me");
		bt1.setUI(new MyButtonUI());
		p.add(bt1);
		f.getContentPane().add(p);
		WindowListener wndCloser = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		f.addWindowListener(wndCloser);
		f.setVisible(true);
	}
}
