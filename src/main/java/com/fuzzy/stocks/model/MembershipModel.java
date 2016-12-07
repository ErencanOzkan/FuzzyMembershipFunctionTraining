package com.fuzzy.stocks.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MembershipModel {

	private double a;
	private double b;
	private double c;

	private int goupingNumber;
	private boolean mergeCompleted = true;
	double membershipValue;
	
	int matchingRuleIndex;

	private MembershipModel() {

	}

	public static class MembershipModelBuilder {

		private double a;
		private double b;
		private double c;
		private int groupingNumber;
		private double membershipValue;

		public MembershipModelBuilder(double a, double b, double c, int groupingNumber, double membershipValue) {
			this.a = a;
			this.b = b;
			this.c = c;
			this.groupingNumber = groupingNumber;
			this.membershipValue = membershipValue;
		}

		public MembershipModelBuilder(double a, double b, double c, int groupingNumber) {
			this.a = a;
			this.b = b;
			this.c = c;
			this.groupingNumber = groupingNumber;
		}

		public MembershipModelBuilder(double a, double b, double c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

		public MembershipModel build() {
			MembershipModel model = new MembershipModel();
			model.a = this.a;
			model.b = this.b;
			model.c = this.c;
			model.goupingNumber = this.groupingNumber;
			model.membershipValue = membershipValue;
			return model;
		}
	}

	public void markGroupNumber(int groupNumber) {
		this.goupingNumber = groupNumber;
		this.mergeCompleted = false;
	}

	private Map<Integer, List<MembershipModel>> getValuesToCalculate(MembershipModel[] values) {
		Map<Integer, List<MembershipModel>> valuesToCalculate = new HashMap<Integer, List<MembershipModel>>();
		for(MembershipModel model : values){
			if(!model.mergeCompleted){
				if(valuesToCalculate.containsKey(model.goupingNumber)){
					valuesToCalculate.get(model.goupingNumber).add(model);
				} else{
					List<MembershipModel> groupList = new ArrayList<MembershipModel>();
					groupList.add(model);
					valuesToCalculate.put(model.goupingNumber, groupList);
				}
			}
		}
		return valuesToCalculate;
	}

	private void setCalculatedValuesToModelValues(MembershipModel[] values, Map<Integer, MembershipModel> calculatedValues) {
		for(MembershipModel model : values){
			if(calculatedValues.containsKey(model.goupingNumber)){
				model.a = calculatedValues.get(model.goupingNumber).a;
				model.b = calculatedValues.get(model.goupingNumber).b;
				model.c = calculatedValues.get(model.goupingNumber).c;
				model.mergeCompleted = true;
			}
		}
	}

	public void mergeForOperation1and2(MembershipModel[] values) {
		Map<Integer, List<MembershipModel>> valuesToCalculate = getValuesToCalculate(values);

		Map<Integer, MembershipModel> calculatedValues = new HashMap<Integer, MembershipModel>();
		Iterator<Integer> entries = valuesToCalculate.keySet().iterator();
		while(entries.hasNext()){
			Integer key = (Integer) entries.next();
			List<MembershipModel> groupList = (List<MembershipModel>) valuesToCalculate.get(key);
			double a = Double.MAX_VALUE;
			double b = 0;
			double c = Double.MIN_VALUE;
			for(MembershipModel model : groupList){
				if(model.a < a){
					a = model.a;
				}
				b += model.b;
				if(model.c > c){
					c = model.c;
				}

			}
			b = b / groupList.size();
			MembershipModel calculatedModel = new MembershipModel.MembershipModelBuilder(a, b, c).build();
			calculatedModel.goupingNumber = key;
			calculatedValues.put(key, calculatedModel);
		}

		setCalculatedValuesToModelValues(values, calculatedValues);

	}

	public int getGoupingNumber() {
		return goupingNumber;
	}

	@Override
	public String toString() {
		return "MembershipModel [a=" + a + ", b=" + b + ", c=" + c + ", goupingNumber=" + goupingNumber + ", mergeCompleted=" + mergeCompleted + "]";
	}

	public void mergeForOperation3and4(MembershipModel[] values) {
		Map<Integer, List<MembershipModel>> valuesToCalculate = getValuesToCalculate(values);

		Map<Integer, MembershipModel> calculatedValues = new HashMap<Integer, MembershipModel>();
		Iterator<Integer> entries = valuesToCalculate.keySet().iterator();
		while(entries.hasNext()){
			Integer key = (Integer) entries.next();
			List<MembershipModel> groupList = (List<MembershipModel>) valuesToCalculate.get(key);
			double a = Double.MAX_VALUE;
			double b = 0;
			double c = Double.MIN_VALUE;
			for(MembershipModel model : groupList){
				if(model.a < a){
					a = model.a;
				}
				b += model.b;
				if(model.c > c){
					c = model.c;
				}

			}
			b = b / groupList.size();
			MembershipModel calculatedModel = new MembershipModel.MembershipModelBuilder(a, b, c).build();
			calculatedModel.goupingNumber = key;
			calculatedValues.put(key, calculatedModel);
		}

		setCalculatedValuesToModelValues(values, calculatedValues);

	}

	public void mergeForOperation5(MembershipModel[] values, int previousRegionIndex, int currentRegionIndex, int nextRegionIndex) {
		Map<Integer, List<MembershipModel>> valuesToCalculate = getValuesToCalculate(values);

		double ai = values[previousRegionIndex].a;
		double bi = values[previousRegionIndex].b;
		double ci = values[previousRegionIndex].c;

		double aj = values[currentRegionIndex].a;
		double bj = values[currentRegionIndex].b;
		double cj = values[currentRegionIndex].c;

		double ak = values[nextRegionIndex].a;
		double bk = values[nextRegionIndex].b;
		double ck = values[nextRegionIndex].c;

		int previousGroupNumber = values[previousRegionIndex].getGoupingNumber();
		int nextGroupNumber = values[nextRegionIndex].getGoupingNumber();

		for(MembershipModel model : values){
			if(model.goupingNumber == previousGroupNumber){
				model.a = ai;
				model.b = bi;
				model.c = cj;
				model.mergeCompleted = true;
			}
			if(model.goupingNumber == nextGroupNumber){
				model.a = aj;
				model.b = bk;
				model.c = ck;
				model.mergeCompleted = true;
			}
		}
	}

	public static List<MembershipModel> getMembershipRules(MembershipModel[] values) {
		Map<Integer, MembershipModel> rules = new HashMap<Integer, MembershipModel>();
		for(MembershipModel model : values){
			rules.put(model.getGoupingNumber(), model);
		}
		List<MembershipModel> results = new ArrayList<MembershipModel>();
		results.addAll(rules.values());
		return results;
	}

	public boolean isValueMatches(double value) {
		if(value >= this.a && value <= this.c){
			return true;
		}
		return false;
	}

	public double calculateMembershipValue(double value) {

		double a = this.a == Double.MIN_VALUE ? 0d : this.a;
		if(c == Double.MAX_VALUE && value > this.b) return 1;
		if(this.b > value){
			membershipValue = (value - a) / (this.b - a);

		} else{
			membershipValue = (c - value) / (c - this.b);
		}
		this.membershipValue = membershipValue;
		return membershipValue;
	}

	public static List<MembershipModel> getMatchingRules(List<MembershipModel> ruleList, double value) {		
		List<MembershipModel> matchingRules = new ArrayList<MembershipModel>();
		
		int index = 0;
		if(ruleList != null){
			for(MembershipModel rule : ruleList){
				if(rule.isValueMatches(value)){
					rule.matchingRuleIndex = index;
					matchingRules.add(rule);
				}
				index++;
			}
		}

		return matchingRules;
	}

	public static List<MembershipModel> calculateMembershipValues(List<MembershipModel> ruleList, double value) {
		List<MembershipModel> matchingRules = MembershipModel.getMatchingRules(ruleList, value);
	
		for(MembershipModel rule : matchingRules){
			rule.calculateMembershipValue(value);
		}
		return matchingRules;
	}

	public static List<MembershipModel> getRuleWhichHasMinimumMembershipValue(List<MembershipModel> membershipValues1, List<MembershipModel> membershipValues2) {
		List<MembershipModel> results = new ArrayList<MembershipModel>();
		for(MembershipModel input1 : membershipValues1){
			for(MembershipModel input2 : membershipValues2){
				if(input1.membershipValue < input2.membershipValue){
					results.add(input1);
				} else{
					results.add(input2);
				}
			}
		}
		return results;
	}

	public static double caculateResultingValue(List<MembershipModel> results, List<Double> centralPoints) {
		double result = 0d;
		double numerator = 0d;
		double denominator = 0d;
		if(results != null && centralPoints != null){
			for(int i = 0; i < results.size(); i++){
				
//				int centralPointIndex = results.get(i).matchingRuleIndex;
//				if(centralPointIndex >= results.size()){
//					centralPointIndex = results.size();
//				}
//				numerator += results.get(i).membershipValue * centralPoints.get(centralPointIndex); //results.get(i).goupingNumber-1
				
				
				numerator += results.get(i).membershipValue * centralPoints.get(i); //results.get(i).goupingNumber-1
				denominator += results.get(i).membershipValue;
			}

		}
		result = (double)(numerator / denominator);
		return result;
	}
}
