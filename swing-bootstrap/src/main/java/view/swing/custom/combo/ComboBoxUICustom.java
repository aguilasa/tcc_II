package view.swing.custom.combo;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

class ComboBoxUICustom extends BasicComboBoxUI {

	@Override
	protected JButton createArrowButton() {
		JButton b = new JButton(new ComboBoxArrowIcon());
		b.setContentAreaFilled(false);
		b.setFocusPainted(false);
		b.setBorder(BorderFactory.createEmptyBorder());
		return b;
	}

}
