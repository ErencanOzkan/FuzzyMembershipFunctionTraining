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

	private MembershipModel() {

	}

	public static class MembershipModelBuilder {

		private double a;
		private double b;
		private double c;

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
			return model;
		}
	}

	public void markGroupNumber(int groupNumber) {
		this.goupingNumber = groupNumber;
		this.mergeCompleted = false;
	}

	public void mergeForOperation1(MembershipModel[] values) {
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
		for(MembershipModel model : values){
			if(calculatedValues.containsKey(model.goupingNumber)){
				model.a = calculatedValues.get(model.goupingNumber).a;
				model.b = calculatedValues.get(model.goupingNumber).b ;
				model.c = calculatedValues.get(model.goupingNumber).c;
				model.mergeCompleted = true;
			}
		}

	}

	public int getGoupingNumber() {
		return goupingNumber;
	}

	@Override
	public String toString() {
		return "MembershipModel [a=" + a + ", b=" + b + ", c=" + c + ", goupingNumber=" + goupingNumber + ", mergeCompleted=" + mergeCompleted + "]";
	}

}
