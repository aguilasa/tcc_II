package general;

import view.swing.screens.MainFrame;
import view.swing.utils.AlterFonts;

public class Main {

	public static void main(String[] x) {
		AlterFonts.alterFonts();

		MainFrame mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}

}
