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
			element.setGroup(this.group);
			return element;
		}

	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

}
