package com.fuzzy.stocks.model;

public class DecisionTableElement {

	private int group;

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

	public void setGroup(int value) {
		this.group = value;
	}
}
