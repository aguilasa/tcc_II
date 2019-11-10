package view.swing.screens;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import view.swing.custom.button.Button;
import view.swing.custom.button.ButtonType;
import view.swing.custom.button.CheckBox;
import view.swing.custom.button.FormField;
import view.swing.custom.commons.ComponentSize;
import view.swing.other.BottomPanel;
import view.swing.other.RainbowPanel;
import view.swing.other.TitlePanel;

public class MainFrame extends JFrame implements ActionListener, WindowListener {

	private static final long serialVersionUID = 1L;

	private JMenuBar jmBarraMenu;
	private JMenu jmArquivo;
	private JMenuItem jmiArquivoSair;

	static Container container;

	public MainFrame() {
		super("Title");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		// setResizable(false);
		// setLayout(null);
		addWindowListener(this);

		container = getContentPane();
		container.setLayout(null);

		initComponents();

		this.addComponentListener(new ComponentListener() {
			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentResized(ComponentEvent arg0) {

				for (Component c : container.getComponents()) {
					if (c.getClass().getSimpleName().equals("JMenuBar") || c.getClass().getSimpleName().equals("RainbowPanel") || c.getClass().getSimpleName().equals("TitlePanel") || c.getClass().getSimpleName().equals("BottomPanel"))
						c.setSize(getWidth(), c.getHeight());
				}

			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void initComponents() {
		jmBarraMenu = new JMenuBar();
		jmBarraMenu.setBounds(0, 0, 800, 20);
		container.add(jmBarraMenu);

		// BEGIN - ARQUIVO
		jmArquivo = new JMenu("Arquivo");
		jmBarraMenu.add(jmArquivo);

		jmiArquivoSair = new JMenuItem("Sair");
		jmiArquivoSair.addActionListener(this);
		jmArquivo.add(jmiArquivoSair);
		// END - ARQUIVO

		RainbowPanel rbp = new RainbowPanel(new Rectangle(0, 20, 800, 15));
		container.add(rbp);

		TitlePanel tp = new TitlePanel(new Rectangle(0, 35, 800, 100));
		container.add(tp);

		JPanel content = new JPanel();
		content.setBounds(new Rectangle(0, 135, 800, 340));
		content.setLayout(null);
		container.add(content);

		FormField jpFirstName = new FormField("/view/images/profile-icon.png", "First Name");
		jpFirstName.setBounds(40, 20, 340, 60);
		content.add(jpFirstName);

		FormField jpLastName = new FormField("/view/images/profile-icon.png", "Last Name");
		jpLastName.setBounds(400, 20, 340, 60);
		content.add(jpLastName);

		FormField jpEmail = new FormField("/view/images/mail.png", "E-Mail");
		jpEmail.setBounds(40, 100, 700, 60);
		content.add(jpEmail);

		CheckBox jcb = new CheckBox("Send me promotions and offers", new Color(138, 147, 179), new Color(114, 132, 193));
		jcb.setBounds(40, 171, 700, 38);
		content.add(jcb);

		Button jb2 = new Button(ButtonType.DANGER);
		jb2.setButtonSize(ComponentSize.SMALL);
		jb2.setButtonType(ButtonType.INFO);
		jb2.setText("Info");
		jb2.setBounds(480, 265, 80, 38);
		content.add(jb2);

		Button jb1 = new Button(ButtonType.SECONDARY);
		jb1.setButtonType(ButtonType.WARNING);
		jb1.setText("Warning");
		jb1.setBounds(390, 265, 80, 38);
		content.add(jb1);

		Button rectlookButton = new Button(ButtonType.PRIMARY);
		rectlookButton.setText("Primary");
		rectlookButton.setBounds(10, 265, 80, 50);
		content.add(rectlookButton);

		Button rectlookButton_1 = new Button(ButtonType.SUCCESS);
		rectlookButton_1.setButtonType(ButtonType.SECONDARY);
		rectlookButton_1.setText("Secondary");
		rectlookButton_1.setBounds(96, 265, 100, 38);
		content.add(rectlookButton_1);

		Button rectlookButton_2 = new Button(ButtonType.INFO);
		rectlookButton_2.setButtonType(ButtonType.SUCCESS);
		rectlookButton_2.setText("Success");
		rectlookButton_2.setBounds(205, 265, 80, 50);
		content.add(rectlookButton_2);

		Button rectlookButton_3 = new Button(ButtonType.WARNING);
		rectlookButton_3.setButtonType(ButtonType.DANGER);
		rectlookButton_3.setText("Danger");
		rectlookButton_3.setBounds(284, 265, 96, 38);
		content.add(rectlookButton_3);
		
		Button btnLight = new Button(ButtonType.DANGER);
		btnLight.setButtonType(ButtonType.LIGHT);
		btnLight.setText("Light");
		btnLight.setBounds(565, 265, 80, 38);
		content.add(btnLight);
		
		Button btnDark = new Button(ButtonType.DANGER);
		btnDark.setButtonSize(ComponentSize.LARGE);
		btnDark.setButtonType(ButtonType.DARK);
		btnDark.setText("Dark");
		btnDark.setBounds(660, 265, 80, 38);
		content.add(btnDark);
		
		JButton btnPrimary = new JButton("Primary");
		btnPrimary.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnPrimary.setBounds(10, 216, 144, 38);
		content.add(btnPrimary);

		BottomPanel bp = new BottomPanel(new Rectangle(0, 475, 800, 100));
		container.add(bp);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (Component i : getContentPane().getComponents()) {
			if (!i.equals(jmBarraMenu))
				i.setVisible(false);
		}

		if (e.getSource().equals(jmiArquivoSair)) {
			System.exit(EXIT_ON_CLOSE);
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
	}
}
