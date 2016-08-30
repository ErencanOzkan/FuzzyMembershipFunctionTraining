package com.fuzzy.stocks.service.impl;

import java.util.List;

import org.slf4j.Logger;

import com.fuzzy.stocks.enums.FuzzyMembershipCalculationStatusEnum;
import com.fuzzy.stocks.model.DecisionTableElement;
import com.fuzzy.stocks.model.FuzzyData;
import com.fuzzy.stocks.service.FuzzyMembershipConstructionService;
import com.fuzzy.stocks.util.FuzzyDataUtil;

public class FuzzyMembershipConstructionServiceImpl implements FuzzyMembershipConstructionService {

	Logger LOGGER = org.slf4j.LoggerFactory.getLogger(FuzzyMembershipConstructionServiceImpl.class);

	private List<FuzzyData> data;
	double smallesetPredefinedUnitForAge = 0;
	double smallesetPredefinedUnitForProperty = 0;

	DecisionTableElement[][] desitionTable;

	int[] columns;
	int[] rows;

	int mergedColumnIndex = 1;
	int mergedRowIndex = 1;

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
		int decisionTableSizeX = (int) ((maximumAge - minimumAge) / this.smallesetPredefinedUnitForAge) + 1;

		double minimumProperty = FuzzyDataUtil.findMinimumProperty(this.data);
		double maximumProperty = FuzzyDataUtil.findMaximumProperty(this.data);
		int decisionTableSizeY = (int) ((maximumProperty - minimumProperty) / this.smallesetPredefinedUnitForProperty) + 1;

		desitionTable = new DecisionTableElement[decisionTableSizeX][decisionTableSizeY];
		columns = new int[decisionTableSizeX];
		rows = new int[decisionTableSizeY];
		for(int i = 0; i < decisionTableSizeX; i++){
			for(int j = 0; j < decisionTableSizeY; j++){
				desitionTable[i][j] = new DecisionTableElement.DecisionTableElementBuilder(0).build();
			}
		}
		for(FuzzyData d : this.data){
			int dataLocationForAge = (int) ((d.getAge() - minimumAge) / this.smallesetPredefinedUnitForAge);
			int dataLocationForProperty = (int) ((d.getProperty() - minimumProperty) / this.smallesetPredefinedUnitForProperty);
			desitionTable[dataLocationForAge][dataLocationForProperty] = new DecisionTableElement.DecisionTableElementBuilder(d.getGroup()).build();;
		}
	}

	public void mergeAdjacentColumnsIfTheyAreSame() {
		if(desitionTable != null){
			int x = desitionTable.length;
			int y = desitionTable[0].length;
			DecisionTableElement[] firstColumn = desitionTable[0];
			for(int i = 1; i < x; i++){
				DecisionTableElement[] secondColumn = desitionTable[i];
				boolean columnsAreSame = true;
				for(int j = 0; j < y; j++){
					if(firstColumn[j].getGroup() != secondColumn[j].getGroup()){
						columnsAreSame = false;
						break;
					}
				}
				if(columnsAreSame){
					LOGGER.debug(" Column -> " + (i - 1) + " and Column -> " + i + " is merged");
					for(int j = 0; j < y; j++){
						int calculatedGroup = firstColumn[j].getGroup();
						secondColumn[j].setCalculatedGroup(calculatedGroup);
						firstColumn[j].setCalculatedGroup(calculatedGroup);
					}

					int rowIndex = 0;
					if(columns[i] != 0){
						rowIndex = columns[i];
					} else if(columns[i - 1] != 0){
						rowIndex = columns[i - 1];
					} else{
						rowIndex = this.mergedRowIndex;
						this.mergedRowIndex++;
					}

					this.columns[i] = rowIndex;
					this.columns[i - 1] = rowIndex;

				}
				firstColumn = secondColumn;
			}
		}

	}

	public void mergeAdjacentRowsIfTheyAreSame() {
		if(desitionTable != null){
			int x = desitionTable.length;
			int y = desitionTable[0].length;
			for(int j = 1; j < y; j++){
				boolean rowsAreSame = true;
				for(int i = 0; i < x; i++){
					if(desitionTable[i][j - 1].getGroup() != desitionTable[i][j].getGroup()){
						rowsAreSame = false;
						break;
					}
				}
				if(rowsAreSame){
					LOGGER.debug(" Row -> " + (j - 1) + " and Row -> " + j + " is merged");
					for(int i = 0; i < y; i++){
						int calculatedGroup = desitionTable[i][j - 1].getGroup();
						desitionTable[i][j - 1].setCalculatedGroup(calculatedGroup);
						desitionTable[i][j].setCalculatedGroup(calculatedGroup);
					}

					int columnIndex = 0;
					if(rows[j] != 0){
						columnIndex = rows[j];
					} else if(rows[j - 1] != 0){
						columnIndex = rows[j - 1];
					} else{
						columnIndex = this.mergedColumnIndex;
						this.mergedColumnIndex++;
					}

					this.rows[j] = columnIndex;
					this.rows[j - 1] = columnIndex;

				}
			}
		}
	}

	public void mergeAdjacentColumsFOrOperation2() {
		if(desitionTable != null){
			int x = desitionTable.length;
			int y = desitionTable[0].length;
			DecisionTableElement[] firstRow = desitionTable[0];
			for(int i = 1; i < x; i++){
				DecisionTableElement[] secondRow = desitionTable[i];
				boolean columnsAreSame = true;
				for(int j = 0; j < y; j++){
					if(firstRow[j].getGroup() != secondRow[j].getGroup() && firstRow[j].getGroup() != 0 && secondRow[j].getGroup() != 0){
						columnsAreSame = false;
						break;
					}
				}
				if(columnsAreSame){
					for(int j = 0; j < y; j++){
						int firstColumn = firstRow[j].getGroup();
						int secondColumn = secondRow[j].getGroup();
						int calculatedGroup = 0;
						if(firstColumn != 0){
							calculatedGroup = firstColumn;
						}
						if(secondColumn != 0){
							calculatedGroup = secondColumn;
						}

						secondRow[j].setCalculatedGroup(calculatedGroup);
						firstRow[j].setCalculatedGroup(calculatedGroup);
					}
				}
			}

		}
	}
}
