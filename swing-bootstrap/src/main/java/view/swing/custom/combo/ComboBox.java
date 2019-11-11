package view.swing.custom.combo;

import static view.swing.utils.AlterFonts.FONT_NAME;

import java.awt.Font;
import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import view.swing.custom.commons.BorderSize;
import view.swing.custom.commons.ComponentSize;

public class ComboBox<E> extends JComboBox<E> {

	private static final long serialVersionUID = 3547044725412006253L;

	private ComboBoxEditor<E> editor;
	private ComponentSize componentSize;

	private BorderSize borderSize;

	public ComboBox(ComboBoxModel<E> aModel) {
		super(aModel);
		init();
	}

	public ComboBox(E[] items) {
		super(items);
		init();
	}

	public ComboBox(Vector<E> items) {
		super(items);
		init();
	}

	public ComboBox() {
		super();
		init();
	}

	@Override
	public void setEditor(javax.swing.ComboBoxEditor anEditor) {
		super.setEditor(getEditor());
	}

	@Override
	public javax.swing.ComboBoxEditor getEditor() {
		if (editor == null) {
			editor = new ComboBoxEditor<>(this);
		}
		return editor;
	}

	@SuppressWarnings("unchecked")
	private void init() {
		componentSize = ComponentSize.DEFAULT;
		setEditable(false);
		setSize(100, ComponentSize.DEFAULT.getHeight());
		setBorder(new ComboBoxBorder<>(this));
		setUI(new ComboBoxUICustom());
		setRenderer(new ComboBoxRendererCustom<>(this));
	}

	public ComponentSize getComponentSize() {
		return componentSize;
	}

	public void setComponentSize(ComponentSize componentSize) {
		this.componentSize = componentSize;
		resetBorderSize();
		resetBounds();
		resetFont();
		repaint();
	}

	@Override
	public void setBounds(Rectangle r) {
		r.height = componentSize.getHeight();
		super.setBounds(r);
		resetSizes();
	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		height = componentSize.getHeight();
		super.setBounds(x, y, width, height);
		resetSizes();
	}

	public BorderSize getBorderSize() {
		if (borderSize == null) {
			resetBorderSize();
		}
		return borderSize;
	}

	private void resetBorderSize() {
		borderSize = new BorderSize(getWidth(), getHeight(), componentSize.getBorder());
	}

	private void resetBounds() {
		Rectangle bounds = getBounds();
		setBounds(bounds);
	}

	private void resetFont() {
		setFont(new Font(FONT_NAME, Font.PLAIN, componentSize.getFontSize()));
	}

	@SuppressWarnings("unchecked")
	private void resetSizes() {
		resetFont();
		((ComboBoxEditor<E>) getEditor()).resizeEditor();
	}

}
