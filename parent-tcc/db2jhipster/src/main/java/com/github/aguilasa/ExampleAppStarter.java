package com.github.aguilasa;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ExampleAppStarter {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new ExampleAppStarter().start();
			}
		});
	}

	private void start() {
		MainWindow m = new MainWindow();
		new DialogWindow(m);
	}
}

class MainWindow implements Observer {

	private JLabel label;

	@Override // Observer interface's implemented method
	public void update(Observable o, Object data) {

		label.setText((String) data); // displays new text in JLabel
	}

	MainWindow() {
		JFrame frame = new JFrame("Main Window");
		frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		label = new JLabel("Click button in Dialog...");
		label.setFont(new Font("Dialog", Font.PLAIN, 20));
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 150);
		frame.setLocation(200, 200);
		frame.setVisible(true);
	}
}

class DialogWindow {

	private int clicks;

	DialogWindow(MainWindow mainWindow) {
		// Create Observable and add Observer
		final MessageObservable observable = new MessageObservable();
		observable.addObserver(mainWindow);

		// Display Dialog
		JFrame dialog = new JFrame("Dialog");
		JButton button = new JButton("Press me");
		button.setFont(new Font("Dialog", Font.PLAIN, 20));

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String data = "button clicked in dialog [" + ++clicks + "]";
				observable.changeData(data);
			}
		});

		dialog.add(button);
		dialog.setSize(250, 150);
		dialog.setLocation(600, 200);
		dialog.setVisible(true);
	}
}

class MessageObservable extends Observable {

	MessageObservable() {
		super();
	}

	void changeData(Object data) {
		setChanged(); // the two methods of Observable class
		notifyObservers(data);
	}
}