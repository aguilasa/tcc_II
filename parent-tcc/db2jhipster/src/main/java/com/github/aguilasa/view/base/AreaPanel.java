package com.github.aguilasa.view.base;

import java.awt.Color;
import java.util.Observable;

import javax.swing.JPanel;

import com.github.aguilasa.view.MainView;

public abstract class AreaPanel extends JPanel {

	private static final long serialVersionUID = 4048339675007575890L;

	private MainView mainView;

	public AreaPanel() {
		setBackground(Color.WHITE);
		setLayout(null);
		setPreferredSize(MainView.AREA_SIZE);
	}

	public MainView getMainView() {
		return mainView;
	}

	public void setMainView(MainView mainView) {
		this.mainView = mainView;
		setObservable();
	}

	public void setObservable() {
		if (mainView != null) {
			if (getObservable() != null) {
				getObservable().addObserver(mainView);
			}
		}
	}

	protected abstract Observable getObservable();

}
