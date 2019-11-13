package view.swing.custom.combo;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import view.swing.custom.commons.Constants;

class ComboBoxRendererCustom<E> extends JPanel implements ListCellRenderer<E> {

	private static final long serialVersionUID = 2239272568633707374L;

	ComboBox<E> comboBox;

	private JPanel content = new JPanel(new GridBagLayout());
	private JLabel label = new JLabel();

	public ComboBoxRendererCustom(ComboBox<E> comboBox) {
		this.comboBox = comboBox;
		init(comboBox);
	}

	private void init(ComboBox<E> comboBox) {
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1.0;
		constraints.insets = new Insets(0, 0, 0, 0);
		add(content, constraints);

		GridBagConstraints contentConstraints = new GridBagConstraints();
		contentConstraints.fill = GridBagConstraints.HORIZONTAL;
		contentConstraints.weightx = 1.0;
		contentConstraints.insets = new Insets(0, 16, 0, 0);

		label.setOpaque(true);
		label.setHorizontalAlignment(JLabel.LEFT);
		label.setFont(comboBox.getFont());
		content.add(label, contentConstraints);
		setBackground(Color.WHITE);
		setForeground(Constants.INPUT_FONT);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		label.setText(value.toString());

		if (isSelected) {
			content.setBackground(Constants.COMBO_SELECTED);
			label.setBackground(Constants.COMBO_SELECTED);
			label.setForeground(Color.WHITE);
		} else {
			content.setBackground(Color.WHITE);
			label.setBackground(Color.WHITE);
			label.setForeground(Constants.INPUT_FONT);
		}

		return this;
	}

	@Override
	public void setBackground(Color bg) {
		super.setBackground(Color.WHITE);
		if (content != null) {
			content.setBackground(getBackground());
		}
		if (label != null) {
			label.setBackground(getBackground());
		}
	}

	@Override
	public void setForeground(Color fg) {
		super.setForeground(Constants.INPUT_FONT);
		if (content != null) {
			content.setForeground(getForeground());
		}
		if (label != null) {
			label.setForeground(getForeground());
		}
	}

}
