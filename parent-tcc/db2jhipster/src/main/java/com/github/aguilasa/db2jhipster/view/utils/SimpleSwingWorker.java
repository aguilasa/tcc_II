package com.github.aguilasa.db2jhipster.view.utils;

import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

public abstract class SimpleSwingWorker {

	private final SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
		@Override
		protected Void doInBackground() throws Exception {
			SimpleSwingWorker.this.doInBackground();
			return null;
		}

		@Override
		protected void done() {
			try {
				get();
			} catch (final InterruptedException ex) {
				throw new RuntimeException(ex);
			} catch (final ExecutionException ex) {
				throw new RuntimeException(ex.getCause());
			}
			SimpleSwingWorker.this.done();
		}
	};

	public SimpleSwingWorker() {
	}

	protected abstract Void doInBackground() throws Exception;

	protected abstract void done();

	public void execute() {
		worker.execute();
	}
}