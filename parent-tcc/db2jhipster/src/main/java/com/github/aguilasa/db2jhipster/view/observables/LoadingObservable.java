package com.github.aguilasa.db2jhipster.view.observables;

import java.util.Observable;

public class LoadingObservable extends Observable {

	public LoadingObservable() {
		super();
	}

	public void changeData(Object data) {
		setChanged();
		notifyObservers(data);
	}

}
