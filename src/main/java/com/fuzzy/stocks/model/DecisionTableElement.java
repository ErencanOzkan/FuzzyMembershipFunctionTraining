package com.fuzzy.stocks.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DecisionTableElement {

	private int group;

	private int calculatedGroup;

	private DecisionTableElement() {
	}

	public static class DecisionTableElementBuilder {

		private int group;

		public DecisionTableElementBuilder(int group) {
			this.group = group;
		}

		public DecisionTableElement build() {
			DecisionTableElement element = new DecisionTableElement();
			element.group = this.group;
			element.calculatedGroup = this.group;
			return element;
		}

	}

	public int getGroup() {
		return group;
	}

	@Override
	public String toString() {
		return "DecisionTableElement [group=" + group + "]";
	}

	public int getCalculatedGroup() {
		return calculatedGroup;
	}

	public void setCalculatedGroup(int calculatedGroup) {
		this.calculatedGroup = calculatedGroup;
	}

}
