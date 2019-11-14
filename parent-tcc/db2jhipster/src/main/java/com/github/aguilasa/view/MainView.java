package com.github.aguilasa.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.github.aguilasa.view.base.AreaPanel;
import com.github.aguilasa.view.observables.ConnectionObservable;
import com.github.aguilasa.view.observables.LoadingObservable;
import com.github.aguilasa.view.step.Step;

import view.swing.custom.button.Button;
import view.swing.custom.button.ButtonType;
import view.swing.image.ImagePanel;
import view.swing.custom.commons.ComponentSize;

public class MainView extends JFrame implements Observer {

	private static final long serialVersionUID = -732397022688096890L;
	private static final int WIDTH = 960;
	private static final int HEIGHT = 600;
	private static final int AREA_WIDTH = 585;
	private static final int AREA_HEIGHT = 480;

	public static final Dimension AREA_SIZE = new Dimension(AREA_WIDTH, AREA_HEIGHT);
	public static final boolean TESTING = true;

	private Button btnNext;
	private Button btnBack;
	private JPanel area;
	private DBConfigView dbConfigView;
	private LoadingView loadingView;
	private JdlView jdlView;
	private Step step = new Step();

	public MainView() {
		initialize();
	}

	private void initialize() {
		getContentPane().setLayout(null);
		setSize(WIDTH, HEIGHT);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImagePanel panel = new ImagePanel("src/main/resources/images/side.jpg");
		panel.setBounds(0, 0, 350, 600);
		getContentPane().add(panel);

		JPanel buttons = new JPanel();
		buttons.setBackground(Color.WHITE);
		buttons.setBounds(360, 500, 585, 60);
		getContentPane().add(buttons);
		buttons.setLayout(null);

		btnNext = new Button(ButtonType.PRIMARY);
		btnNext.setComponentSize(ComponentSize.SMALL);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				next();
			}
		});
		btnNext.setEnabled(false);
		btnNext.setText("Avançar");
		btnNext.setBounds(486, 11, 89, 23);
		buttons.add(btnNext);

		btnBack = new Button(ButtonType.PRIMARY);
		btnBack.setComponentSize(ComponentSize.SMALL);
		btnBack.setEnabled(false);
		btnBack.setText("Voltar");
		btnBack.setBounds(387, 11, 89, 38);
		buttons.add(btnBack);

		area = new JPanel();
		area.setBackground(Color.WHITE);
		area.setBounds(360, 10, 585, 480);
		getContentPane().add(area);
		area.setLayout(null);

		dbConfigView = new DBConfigView();
		dbConfigView.setBounds(0, 5, 585, 480);
		dbConfigView.setMainView(this);
		area.add(dbConfigView);
	}

	@Override
	public void update(Observable observable, Object value) {
		if (observable instanceof ConnectionObservable) {
			btnNext.setEnabled((boolean) value);
		} else if (observable instanceof LoadingObservable) {
			btnNext.setEnabled((boolean) value);
		}

	}

	private LoadingView getLoadingView() {
		if (loadingView == null) {
			loadingView = new LoadingView();
			loadingView.setBounds(0, 0, AREA_WIDTH, AREA_HEIGHT);
			loadingView.setMainView(this);
		}
		return loadingView;
	}

	private JdlView getJdlView() {
		if (jdlView == null) {
			jdlView = new JdlView();
			jdlView.setBounds(0, 0, AREA_WIDTH, AREA_HEIGHT);
			jdlView.setObservable(this);
		}
		return jdlView;
	}

	private void next() {
		step.next();
		processStep(true);
	}

	private void processStep(boolean fromNext) {
		switch (step.step()) {
		case START:
			break;
		case LOADING:
			processLoading(fromNext);
			break;
		case TABLES:
			processTables(fromNext);
			break;
		case RELATIONSHIPS:
			processRelationships(fromNext);
			break;
		case END:
			processEnd(fromNext);
			break;
		default:
			break;
		}
	}

	private void addAreaView(AreaPanel view) {
		area.removeAll();
		area.add(view);
		repaint();
	}

	private void processLoading(boolean fromNext) {
		addAreaView(getLoadingView());
		enableButtons(fromNext);
		getLoadingView().setDatabaseConfiguration(dbConfigView.getDatabaseConfiguration());
		getLoadingView().setConnection(dbConfigView.getConnection());
		getLoadingView().loadAll();
	}

	private void processTables(boolean fromNext) {
		// TODO Auto-generated method stub
		if (fromNext) {
			next();
		}

	}

	private void processRelationships(boolean fromNext) {
		// TODO Auto-generated method stub
		if (fromNext) {
			next();
		}

	}

	private void processEnd(boolean fromNext) {
		addAreaView(getJdlView());
		enableButtons(fromNext);
		getJdlView().setText(getLoadingView().getEntityLoader().toJdl());
	}

	public void enableButtons(boolean fromNext) {
		btnNext.setEnabled(!fromNext);
		btnBack.setEnabled(fromNext);
	}
}
