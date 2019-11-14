package com.github.aguilasa.view.step;

public class StepText {

	public static void main(String[] args) {
		Step step = new Step();
		while (!step.isLast()) {
			step.next();
			System.out.println(step.step());
		}

		System.out.println();
		step.moveLast();
		while (!step.isFirst()) {
			step.previous();
			System.out.println(step.step());
		}
	}

}
