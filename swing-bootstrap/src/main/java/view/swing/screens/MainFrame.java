package view.swing.screens;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import view.swing.custom.bs.BsButton;
import view.swing.custom.bs.BsButtonType;
import view.swing.custom.bs.BsCheckBox;
import view.swing.custom.bs.BsFormField;
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

		BsFormField jpFirstName = new BsFormField("/view/images/profile-icon.png", "First Name");
		jpFirstName.setBounds(40, 20, 340, 60);
		content.add(jpFirstName);

		BsFormField jpLastName = new BsFormField("/view/images/profile-icon.png", "Last Name");
		jpLastName.setBounds(400, 20, 340, 60);
		content.add(jpLastName);

		BsFormField jpEmail = new BsFormField("/view/images/mail.png", "E-Mail");
		jpEmail.setBounds(40, 100, 700, 60);
		content.add(jpEmail);

		BsCheckBox jcb = new BsCheckBox("Send me promotions and offers", new Color(138, 147, 179), new Color(114, 132, 193));
		jcb.setBounds(40, 180, 700, 60);
		content.add(jcb);

		BsButton jb2 = new BsButton("Reset", BsButtonType.DANGER);
		jb2.setBounds(590, 265, 150, 50);
		content.add(jb2);

		BsButton jb1 = new BsButton("Submit", BsButtonType.PRIMARY);
		jb1.setBounds(420, 265, 150, 50);
		content.add(jb1);

		BsButton rectlookButton = new BsButton("Default", BsButtonType.DEFAULT);
		rectlookButton.setBounds(10, 265, 80, 50);
		content.add(rectlookButton);

		BsButton rectlookButton_1 = new BsButton("Success", BsButtonType.SUCCESS);
		rectlookButton_1.setBounds(96, 265, 80, 50);
		content.add(rectlookButton_1);

		BsButton rectlookButton_2 = new BsButton("Info", BsButtonType.INFO);
		rectlookButton_2.setBounds(182, 265, 80, 50);
		content.add(rectlookButton_2);

		BsButton rectlookButton_3 = new BsButton("Warning", BsButtonType.WARNING);
		rectlookButton_3.setBounds(268, 265, 80, 50);
		content.add(rectlookButton_3);

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
