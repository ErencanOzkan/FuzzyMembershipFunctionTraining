package com.fuzzy.stocks.service.impl;

import java.util.List;

import com.fuzzy.stocks.enums.FuzzyMembershipCalculationStatusEnum;
import com.fuzzy.stocks.model.FuzzyData;
import com.fuzzy.stocks.service.FuzzyMembershipConstructionService;
import com.fuzzy.stocks.util.FuzzyDataUtil;

public class FuzzyMembershipConstructionServiceImpl implements FuzzyMembershipConstructionService {

	private List<FuzzyData> data;
	double smallesetPredefinedUnitForAge = 0;
	double smallesetPredefinedUnitForProperty = 0;
	
	int [][] desitionTable;
	
	private FuzzyMembershipConstructionServiceImpl(List<FuzzyData> data) {
		this.data = data;
	}

	public static class FuzzyMembershipConstructionServiceBuilder {

		private List<FuzzyData> data;
		final private FuzzyMembershipCalculationStatusEnum initialStatusForAllData = FuzzyMembershipCalculationStatusEnum.MEMBERSHIP_VALUES_ARE_CALCULATED;

		public FuzzyMembershipConstructionServiceBuilder(List<FuzzyData> data) {
			if(data != null && data.size() > 0){
				boolean allStatusSet = true;
				for(FuzzyData d : data){
					if(!initialStatusForAllData.equals(d.getStatus())){
						allStatusSet = false;
						break;
					}
				}

				if(allStatusSet){
					this.data = data;
				} else{
					throw new IllegalArgumentException();
				}
			} else{
				throw new IllegalArgumentException();
			}

		}

		public FuzzyMembershipConstructionService build() {

			FuzzyMembershipConstructionService service = new FuzzyMembershipConstructionServiceImpl(data);
			return service;
		}

	}

	double findAndSetSmallestPredefinedUnitForAge() {
		FuzzyData previousData = this.data.get(0);
		double smallestPredefinedUnit = Double.MAX_VALUE;
		for(int i = 1; i < this.data.size(); i++){
			FuzzyData currentData = this.data.get(i);
			double diff = Math.abs(previousData.getAge() - currentData.getAge());
			if(currentData != null && previousData != null && diff < smallestPredefinedUnit && diff > 0){
				smallestPredefinedUnit = diff;
			}
			previousData = currentData;
		}
		smallesetPredefinedUnitForAge = smallestPredefinedUnit;
		return smallestPredefinedUnit;

	}

	double findAndSetSmallestPredefinedUnitForProperty() {
		FuzzyData previousData = this.data.get(0);
		double smallestPredefinedUnit = Double.MAX_VALUE;
		for(int i = 1; i < this.data.size(); i++){
			FuzzyData currentData = this.data.get(i);
			double diff = Math.abs(previousData.getProperty() - currentData.getProperty());
			if(currentData != null && previousData != null && diff < smallestPredefinedUnit && diff > 0){
				smallestPredefinedUnit = diff;
			}
			previousData = currentData;
		}
		smallesetPredefinedUnitForProperty = smallestPredefinedUnit;
		return smallestPredefinedUnit;
	}

	public void constructInitialDecisionTable() {
		this.smallesetPredefinedUnitForProperty = 5;
		double minimumAge = FuzzyDataUtil.findMinimumAge(this.data);
		double maximumAge = FuzzyDataUtil.findMaximumAge(this.data);
		int decisionTableSizeX = (int)((maximumAge - minimumAge) / this.smallesetPredefinedUnitForAge)+1;
		
		double minimumProperty = FuzzyDataUtil.findMinimumProperty(this.data);
		double maximumProperty = FuzzyDataUtil.findMaximumProperty(this.data);
		int decisionTableSizeY = (int)((maximumProperty - minimumProperty) / this.smallesetPredefinedUnitForProperty)+1;
		
		
		desitionTable = new int[decisionTableSizeX][decisionTableSizeY];
		for(FuzzyData d : this.data)
		{
			int dataLocationForAge = (int)((d.getAge() - minimumAge) / this.smallesetPredefinedUnitForAge);
			int dataLocationForProperty = (int)((d.getProperty() - minimumProperty) / this.smallesetPredefinedUnitForProperty);
			desitionTable[dataLocationForAge][dataLocationForProperty] = d.getGroup();
		}
		
		
	}

	

}
