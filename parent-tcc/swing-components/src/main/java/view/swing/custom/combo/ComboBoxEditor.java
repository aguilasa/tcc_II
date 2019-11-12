package view.swing.custom.combo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboBoxEditor;

import view.swing.custom.commons.ComponentSize;

class ComboBoxEditor<E> extends BasicComboBoxEditor {

	private E selectedValue;
	private ComboBox<E> comboBox;
	private JPanel editor = new JPanel();
	private JLabel labelItem = new JLabel();

	public ComboBoxEditor(ComboBox<E> comboBox) {
		this.comboBox = comboBox;
		init();
	}

	private void init() {
		editor.setLayout(null);
		editor.setBorder(BorderFactory.createEmptyBorder());
		editor.setMinimumSize(new Dimension(100, ComponentSize.DEFAULT.getHeight()));
		editor.setBackground(Color.BLUE);
		resizeEditor();
	}

	void resizeEditor() {
		Rectangle bounds = comboBox.getBounds();
		adjustBounds(bounds);
		editor.setBounds(bounds);
	}

	private void adjustBounds(Rectangle bounds) {
		ComponentSize componentSize = comboBox.getComponentSize() != null ? comboBox.getComponentSize() : ComponentSize.DEFAULT;
		bounds.x = (int) (componentSize.getFontSize() * componentSize.getPadding());
		if (bounds.height == 0) {
			bounds.height = componentSize.getHeight();
		}
		if (bounds.width == 0) {
			bounds.width = 100;
		}
		bounds.width -= bounds.x;
	}

	@Override
	public Component getEditorComponent() {
		resizeEditor();
		return editor;
	}

	@Override
	public Object getItem() {
		return this.selectedValue;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setItem(Object item) {
		if (item == null) {
			return;
		}
		selectedValue = (E) item;
		labelItem.setText(selectedValue.toString());
	}

}
