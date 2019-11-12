package view.swing.other;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

import view.swing.custom.commons.ComponentSize;

public class ButtonTest extends JFrame {

	private static final long serialVersionUID = -6086336995839318176L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ButtonTest frame = new ButtonTest();
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
	public ButtonTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Primary");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setBounds(10, 11, 178, 57);
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		contentPane.add(btnNewButton);

		JComboBox<ComponentSize> comboBox = new JComboBox<>();
		ListCellRenderer<? super ComponentSize> renderer = comboBox.getRenderer();
		System.out.println(renderer.getClass().getName());
//		final Color bg = comboBox.getBackground();
//		comboBox.setUI(new BasicComboBoxUI() {
//			@Override
//			protected JButton createArrowButton() {
//				JButton b = super.createArrowButton();
//				b.setBackground(bg);
//				b.setBorderPainted(false);
//				return b;
//			}
//		});
		comboBox.setModel(new DefaultComboBoxModel<ComponentSize>(ComponentSize.values()));
		comboBox.setBounds(10, 79, 178, 37);
		contentPane.add(comboBox);
	}
}

class ColorArrowUI extends BasicComboBoxUI {

	public static ComboBoxUI createUI(JComponent c) {
		return new ColorArrowUI();
	}

	@Override
	protected JButton createArrowButton() {
		return new BasicArrowButton(BasicArrowButton.SOUTH, Color.cyan, Color.magenta, Color.yellow, Color.blue);
	}
}