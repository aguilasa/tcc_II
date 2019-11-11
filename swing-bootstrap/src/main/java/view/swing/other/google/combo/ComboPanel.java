package view.swing.other.google.combo;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ComboPanel extends JFrame {

	private static final long serialVersionUID = -2070616198298900034L;
	private JPanel contentPane;
	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		comboBox = new JComboBox<>(makeModel());
		comboBox.setBounds(10, 11, 318, 20);
		contentPane.add(comboBox);
	}

	private static DefaultComboBoxModel<String> makeModel() {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		model.addElement("1234");
		model.addElement("5555555555555555555555");
		model.addElement("6789000000000");
		return model;
	}

}
