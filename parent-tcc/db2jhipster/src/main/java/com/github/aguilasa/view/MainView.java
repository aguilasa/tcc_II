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
import view.swing.custom.commons.ComponentSize;
import view.swing.image.ImagePanel;

public class MainView extends JFrame implements Observer {

	private static final long serialVersionUID = -732397022688096890L;
	private static final int WIDTH = 960;
	private static final int HEIGHT = 600;
	private static final int AREA_WIDTH = 585;
	private static final int AREA_HEIGHT = 480;

	public static final Dimension AREA_SIZE = new Dimension(AREA_WIDTH, AREA_HEIGHT);
	public static final boolean TESTING = false;
	public static final int SLEEP = 1 * 1000;

	private Button btnNext;
	private Button btnBack;
	private JPanel area;
	private DBConfigView dbConfigView;
	private LoadingView loadingView;
	private RelationshipView relationshipView;
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

		ImagePanel panel = new ImagePanel("/images/side.jpg");
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
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				previous();
			}
		});
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
		processStart(true);
	}

	@Override
	public void update(Observable observable, Object value) {
		if (observable instanceof ConnectionObservable) {
			btnNext.setEnabled((boolean) value);
		} else if (observable instanceof LoadingObservable) {
			btnNext.setEnabled((boolean) value);
		}

	}

	private DBConfigView getDbConfigView() {
		if (dbConfigView == null) {
			dbConfigView = new DBConfigView();
			dbConfigView.setBounds(0, 0, AREA_WIDTH, AREA_HEIGHT);
			dbConfigView.setMainView(this);
		}
		return dbConfigView;
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
			jdlView.setMainView(this);
		}
		return jdlView;
	}

	private RelationshipView getRelationshipView() {
		if (relationshipView == null) {
			relationshipView = new RelationshipView();
			relationshipView.setBounds(0, 0, AREA_WIDTH, AREA_HEIGHT);
			relationshipView.setMainView(this);
		}
		return relationshipView;
	}

	private void next() {
		step.next();
		processStep(true);
	}

	private void previous() {
		step.previous();
		processStep(false);
	}

	private void processStep(boolean fromNext) {
		enableButtons(fromNext);
		switch (step.step()) {
		case START:
			processStart(fromNext);
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

	private void processStart(boolean fromNext) {
		btnNext.setEnabled(true);
		addAreaView(getDbConfigView());
	}

	private void processLoading(boolean fromNext) {
		if (fromNext) {
			getDbConfigView().testConnection();
			if (getDbConfigView().isValidConnection()) {
				addAreaView(getLoadingView());
				getLoadingView().setDatabaseConfiguration(dbConfigView.getDatabaseConfiguration());
				getLoadingView().setConnection(dbConfigView.getConnection());
				getLoadingView().loadAll();
			} else {
				previous();
			}
		} else {
			previous();
		}
	}

	private void processTables(boolean fromNext) {
		if (fromNext) {
			next();
		} else {
			previous();
		}
	}

	private void processRelationships(boolean fromNext) {
		addAreaView(getRelationshipView());
		getRelationshipView().setEntityLoader(getLoadingView().getEntityLoader());
		getRelationshipView().loadEntities();
		getRelationshipView().loadComponents();
		btnNext.setEnabled(true);
		btnBack.setEnabled(true);
	}

	private void processEnd(boolean fromNext) {
		addAreaView(getJdlView());
		getJdlView().setText(getLoadingView().getEntityLoader().toJdl());
	}

	public void enableButtons(boolean fromNext) {
		btnNext.setEnabled(!fromNext);
		btnBack.setEnabled(fromNext);
	}
}
