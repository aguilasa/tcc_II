package view.swing.other.google.combo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Objects;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicComboBoxUI;

import com.sun.java.swing.plaf.windows.WindowsComboBoxUI;

import view.swing.custom.combo.ComboBox;
import view.swing.custom.input.Input;
import view.swing.custom.commons.ComponentSize;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ComboPanel extends JFrame {

	private static final long serialVersionUID = -2070616198298900034L;
	private JPanel contentPane;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox2;
	private ComboBox<String> comboBox3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
						ex.printStackTrace();
						Toolkit.getDefaultToolkit().beep();
					}
					ComboPanel frame = new ComboPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ComboPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 651, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		comboBox = new JComboBox<>(makeModel());
		comboBox.setBounds(10, 11, 318, 38);
		comboBox.setBorder(new KamabokoBorder());
		if (comboBox.getUI() instanceof WindowsComboBoxUI) {
			comboBox.setUI(new WindowsComboBoxUI() {
				@Override
				protected JButton createArrowButton() {
					JButton b = new JButton(new ArrowIcon(Color.BLACK, Color.BLUE)); // .createArrowButton();
					b.setContentAreaFilled(false);
					b.setFocusPainted(false);
					b.setBorder(BorderFactory.createEmptyBorder());
					return b;
				}
			});
		}
		contentPane.add(comboBox);

		comboBox2 = new JComboBox<>(makeModel());
		comboBox2.setBounds(10, 60, 318, 38);
		comboBox2.setBorder(new KamabokoBorder());
		comboBox2.setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				JButton b = new JButton(new ArrowIcon(Color.BLACK, Color.BLACK));
				b.setContentAreaFilled(false);
				b.setFocusPainted(false);
				b.setBorder(BorderFactory.createEmptyBorder());
				return b;
			}
		});
		comboBox2.addMouseListener(new ComboRolloverHandler());
		contentPane.add(comboBox2);

		comboBox3 = new ComboBox<>(makeModel());
		comboBox3.setComponentSize(ComponentSize.LARGE);
		comboBox3.setBounds(10, 119, 340, 38);
		contentPane.add(comboBox3);

		Input input = new Input("Texto");
		input.setText("Input Padr\u00E3o");
		input.setBounds(10, 191, 340, 38);
		contentPane.add(input);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnNewButton.setBounds(10, 266, 151, 23);
		contentPane.add(btnNewButton);

	}

	private static DefaultComboBoxModel<String> makeModel() {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		model.addElement("One");
		model.addElement("Two");
		model.addElement("Three");
		model.addElement("Four");
		return model;
	}
}

class ComboRolloverHandler extends MouseAdapter {
	private static ButtonModel getButtonModel(MouseEvent e) {
		Container c = (Container) e.getComponent();
		JButton b = (JButton) c.getComponent(0);
		return b.getModel();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		getButtonModel(e).setRollover(true);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		getButtonModel(e).setRollover(false);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		getButtonModel(e).setPressed(true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		getButtonModel(e).setPressed(false);
	}
}

class ArrowIcon implements Icon {
	private final Color color;
	private final Color rollover;

	protected ArrowIcon(Color color, Color rollover) {
		this.color = color;
		this.rollover = rollover;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setPaint(color);
		int shift = 0;
		if (c instanceof AbstractButton) {
			ButtonModel m = ((AbstractButton) c).getModel();
			if (m.isPressed()) {
				shift = 1;
			} else {
				if (m.isRollover()) {
					g2.setPaint(rollover);
				}
			}
		}
		g2.translate(x, y + shift);
		g2.drawLine(2, 3, 6, 3);
		g2.drawLine(3, 4, 5, 4);
		g2.drawLine(4, 5, 4, 5);
		g2.dispose();
	}

	@Override
	public int getIconWidth() {
		return 8;
	}

	@Override
	public int getIconHeight() {
		return 8;
	}
}

class RoundedCornerBorder extends AbstractBorder {
	private static final long serialVersionUID = 6532715597268988478L;

	@Override
	public void paintBorder(Component c, Graphics pg, int x, int y, int width, int height) {
		Graphics2D g = (Graphics2D) pg.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int r = 12;
		int w = width - 1;
		int h = height - 1;

		Area round = new Area(new RoundRectangle2D.Double(x, y, w, h, r, r));

		Container parent = c.getParent();
		if (Objects.nonNull(parent)) {
			g.setPaint(parent.getBackground());
			Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
			corner.subtract(round);
			g.fill(corner);
		}
		g.setPaint(c.getForeground());
		g.draw(round);
		g.dispose();
	}

	@Override
	public Insets getBorderInsets(Component c) {
		return new Insets(4, 8, 4, 8);
	}

	@Override
	public Insets getBorderInsets(Component c, Insets insets) {
		insets.set(4, 8, 4, 8);
		return insets;
	}
}

class KamabokoBorder extends RoundedCornerBorder {
	private static final long serialVersionUID = 8802329595837001945L;

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int r = 12;
		int w = width - 1;
		int h = height - 1;

		Path2D p = new Path2D.Double();
		p.moveTo(x, y + h);
		p.lineTo(x, y + r);
		p.quadTo(x, y, x + r, y);
		p.lineTo(x + w - r, y);
		p.quadTo(x + w, y, x + w, y + r);
		p.lineTo(x + w, y + h);
		p.closePath();
		Area round = new Area(p);

		Container parent = c.getParent();
		if (Objects.nonNull(parent)) {
			g2.setPaint(parent.getBackground());
			Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
			corner.subtract(round);
			g2.fill(corner);
		}
		g2.setPaint(c.getForeground());
		g2.draw(round);
		g2.dispose();
	}
}
