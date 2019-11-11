package view.swing.other.google.combo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.MetalComboBoxButton;

public class MyComboBox {

	private Vector<String> listSomeString = new Vector<String>();
	private JComboBox someComboBox = new JComboBox(listSomeString);
	private JComboBox editableComboBox = new JComboBox(listSomeString);
	private JComboBox non_EditableComboBox = new JComboBox(listSomeString);
	private JFrame frame;

	public MyComboBox() {
		listSomeString.add("-");
		listSomeString.add("Snowboarding");
		listSomeString.add("Rowing");
		listSomeString.add("Knitting");
		listSomeString.add("Speed reading");
//
		someComboBox.setPrototypeDisplayValue("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		someComboBox.setFont(new Font("Serif", Font.BOLD, 16));
		someComboBox.setEditable(true);
		someComboBox.getEditor().getEditorComponent().setBackground(Color.YELLOW);
		((JTextField) someComboBox.getEditor().getEditorComponent()).setBackground(Color.YELLOW);
//
		editableComboBox.setPrototypeDisplayValue("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		editableComboBox.setFont(new Font("Serif", Font.BOLD, 16));
		editableComboBox.setEditable(true);
		JTextField text = ((JTextField) editableComboBox.getEditor().getEditorComponent());
		text.setBackground(Color.YELLOW);
		JComboBox coloredArrowsCombo = editableComboBox;
		Component[] comp = coloredArrowsCombo.getComponents();
		for (int i = 0; i < comp.length; i++) {// hack valid only for Metal L&F
			if (comp[i] instanceof MetalComboBoxButton) {
				MetalComboBoxButton coloredArrowsButton = (MetalComboBoxButton) comp[i];
				coloredArrowsButton.setBackground(null);
				break;
			}
		}
//
		non_EditableComboBox.setPrototypeDisplayValue("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		non_EditableComboBox.setFont(new Font("Serif", Font.BOLD, 16));
//
		frame = new JFrame();
		frame.setLayout(new GridLayout(0, 1, 10, 10));
		frame.add(someComboBox);
		frame.add(editableComboBox);
		frame.add(non_EditableComboBox);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(100, 100);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		UIManager.put("ComboBox.background", new ColorUIResource(Color.yellow));
		UIManager.put("JTextField.background", new ColorUIResource(Color.yellow));
		UIManager.put("ComboBox.selectionBackground", new ColorUIResource(Color.magenta));
		UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.blue));
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				MyComboBox aCTF = new MyComboBox();
			}
		});
	}
}