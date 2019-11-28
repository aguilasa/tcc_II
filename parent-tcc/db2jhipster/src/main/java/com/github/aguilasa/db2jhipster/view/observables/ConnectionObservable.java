package com.github.aguilasa.db2jhipster.view.observables;

import java.util.Observable;

public class ConnectionObservable extends Observable {

	public ConnectionObservable() {
		super();
	}

	public void changeData(Object data) {
		setChanged();
		notifyObservers(data);
	}

}
