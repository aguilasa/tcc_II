package view.swing.custom.button;

import static view.swing.utils.AlterFonts.FONT_NAME;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;

public class CheckBox extends JCheckBox{

	private static final long serialVersionUID = 1L;
	private String text = "";
	private Color primaryColor;
	private Color secundaryColor;
	private int iconSize;
	private int borderSize;
	
	public CheckBox(String text, Color primary, Color secundary){
		this.text = text;
		this.primaryColor = primary;
		this.secundaryColor = secundary;
		this.iconSize = 20;
		this.borderSize = 1;
		loadCheckBox();
	}
	
	public void loadCheckBox(){
		setText(text);
		setFont(new Font(FONT_NAME, Font.TRUETYPE_FONT, 16));
		setBorder(BorderFactory.createLineBorder(new Color(201, 208, 232)));
		setForeground(new Color(115,115,115));
		
		loadIcon();
	}
	
	public void loadIcon(){
		setIcon(new CheckBoxIcon(primaryColor, secundaryColor, CheckBoxIcon.NORMAL, borderSize, iconSize, iconSize));
		setSelectedIcon(new CheckBoxIcon(primaryColor, secundaryColor, CheckBoxIcon.SELECTED, borderSize, iconSize, iconSize));
		setRolloverIcon(new CheckBoxIcon(primaryColor, secundaryColor, CheckBoxIcon.ROLLOVER, borderSize, iconSize, iconSize));
		setPressedIcon(new CheckBoxIcon(primaryColor, secundaryColor, CheckBoxIcon.PRESSED, borderSize, iconSize, iconSize));
		setDisabledIcon(new CheckBoxIcon(primaryColor, secundaryColor, CheckBoxIcon.DISABLED, borderSize, iconSize, iconSize));
		setDisabledSelectedIcon(new CheckBoxIcon(primaryColor, secundaryColor, CheckBoxIcon.DISABLED_SELECTED, borderSize, iconSize, iconSize));
		setRolloverSelectedIcon(new CheckBoxIcon(primaryColor, secundaryColor, CheckBoxIcon.ROLLOVER_SELECTED, borderSize, iconSize, iconSize));
	}
	
	@Override
	public String getText(){
		return text;
	}
	
	@Override
	public void setText(String text){
		this.text = text;
	}
	
	public void setBorderSize(int size){
		borderSize = size;
		loadIcon();
	}
	
	public void setIconSize(int size){
		iconSize = size;
		loadIcon();
	}
	
    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    }

}
