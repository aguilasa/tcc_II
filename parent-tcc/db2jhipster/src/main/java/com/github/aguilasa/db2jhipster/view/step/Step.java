package com.github.aguilasa.db2jhipster.view.step;

import java.util.LinkedList;
import java.util.List;

public class Step {

	private List<StepEnum> steps = new LinkedList<>();
	private int index = 0;

	public Step() {
		initialize();
	}

	private void initialize() {
		for (StepEnum s : StepEnum.values()) {
			steps.add(s);
		}
	}

	public StepEnum step() {
		return steps.get(index);
	}

	public StepEnum next() {
		if (index < (steps.size() - 1)) {
			index++;
		}
		return step();
	}

	public StepEnum previous() {
		if (index > 0) {
			index--;
		}
		return step();
	}

	public void moveLast() {
		index = steps.size() - 1;
	}

	public void moveFirst() {
		index = 0;
	}

	public StepEnum last() {
		return steps.get(steps.size() - 1);
	}

	public StepEnum first() {
		return steps.get(0);
	}

	public boolean isFirst() {
		return step().equals(StepEnum.START);
	}

	public boolean isLast() {
		return step().equals(StepEnum.END);
	}

}
