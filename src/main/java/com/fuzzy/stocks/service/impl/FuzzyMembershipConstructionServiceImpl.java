package com.fuzzy.stocks.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;

import com.fuzzy.stocks.enums.FuzzyMembershipCalculationStatusEnum;
import com.fuzzy.stocks.model.DecisionTableElement;
import com.fuzzy.stocks.model.FuzzyData;
import com.fuzzy.stocks.model.MembershipModel;
import com.fuzzy.stocks.service.FuzzyMembershipConstructionService;
import com.fuzzy.stocks.util.FuzzyDataUtil;

public class FuzzyMembershipConstructionServiceImpl implements FuzzyMembershipConstructionService {

	Logger LOGGER = org.slf4j.LoggerFactory.getLogger(FuzzyMembershipConstructionServiceImpl.class);

	private List<FuzzyData> data;
	double smallesetPredefinedUnitForAge = 0;
	double smallesetPredefinedUnitForProperty = 0;

	DecisionTableElement[][] desitionTable;

	MembershipModel[] columnValues;
	MembershipModel[] rowValues;

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

	@Override
	public void constructInitialDecisionTable() {
		this.smallesetPredefinedUnitForProperty = findAndSetSmallestPredefinedUnitForProperty();
		this.smallesetPredefinedUnitForAge = findAndSetSmallestPredefinedUnitForAge();
		double minimumAge = FuzzyDataUtil.findMinimumAge(this.data);
		double maximumAge = FuzzyDataUtil.findMaximumAge(this.data);
		int decisionTableSizeX = (int) ((maximumAge - minimumAge) / this.smallesetPredefinedUnitForAge) + 1;

		double minimumProperty = FuzzyDataUtil.findMinimumProperty(this.data);
		double maximumProperty = FuzzyDataUtil.findMaximumProperty(this.data);
		int decisionTableSizeY = (int) ((maximumProperty - minimumProperty) / this.smallesetPredefinedUnitForProperty) + 1;

		desitionTable = new DecisionTableElement[decisionTableSizeX][decisionTableSizeY];

		columnValues = new MembershipModel[decisionTableSizeX];
		rowValues = new MembershipModel[decisionTableSizeY];
		double minimumColumnValue = minimumAge;
		double minimumRowValue = minimumProperty;
		for(int i = 0; i < decisionTableSizeX; i++){
			double a = 0;
			double b = minimumColumnValue;
			double c = minimumColumnValue + smallesetPredefinedUnitForProperty;
			if(minimumColumnValue != minimumAge){
				a = minimumColumnValue - smallesetPredefinedUnitForProperty;
			}
			if(maximumAge == minimumColumnValue){
				c = Double.MAX_VALUE;
			}
			MembershipModel model = new MembershipModel.MembershipModelBuilder(a, b, c).build();
			columnValues[i] = model;
			minimumColumnValue += smallesetPredefinedUnitForProperty;
			this.LOGGER.debug(i + "th column value calculated  " + model.toString());
		}

		for(int i = 0; i < decisionTableSizeY; i++){

			double a = 0;
			double b = minimumRowValue;
			double c = minimumRowValue + smallesetPredefinedUnitForAge;
			if(minimumRowValue != minimumProperty){
				a = minimumRowValue - smallesetPredefinedUnitForAge;
			}
			if(maximumProperty == minimumRowValue){
				c = Double.MAX_VALUE;
			}
			MembershipModel model = new MembershipModel.MembershipModelBuilder(a, b, c).build();
			rowValues[i] = model;
			minimumRowValue += smallesetPredefinedUnitForAge;
			this.LOGGER.debug(i + "th row value calculated  " + model.toString());
		}

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

	@Override
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
					LOGGER.debug("Operation 1 #  Column -> " + (i - 1) + " and Column -> " + i + " are merged");
					for(int j = 0; j < y; j++){
						int calculatedGroup = firstColumn[j].getGroup();
						secondColumn[j].setGroup(calculatedGroup);
						firstColumn[j].setGroup(calculatedGroup);
					}

					markColumnsToMerge(i - 1, i);

				}
				firstColumn = secondColumn;
			}

			this.columnValues[0].mergeForOperation1and2(this.columnValues);
			FuzzyMembershipPrintServiceImpl.printMembershipModels(this.columnValues);

		}

	}
	
	@Override
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
					LOGGER.debug("Operation 1 #  Row -> " + (j - 1) + " and Row -> " + j + " are merged");
					for(int i = 0; i < y; i++){
						int calculatedGroup = desitionTable[i][j - 1].getGroup();
						desitionTable[i][j - 1].setGroup(calculatedGroup);
						desitionTable[i][j].setGroup(calculatedGroup);
					}

					markRowsToMerge(j - 1, j);

				}
			}

			this.rowValues[0].mergeForOperation1and2(this.rowValues);
			FuzzyMembershipPrintServiceImpl.printMembershipModels(this.rowValues);
		}

	}

	@Override
	public void mergeAdjacentColumsForOperation2() {

		if(desitionTable != null){
			int x = desitionTable.length;
			int y = desitionTable[0].length;
			DecisionTableElement[] firstColumn = desitionTable[0];
			for(int i = 1; i < x; i++){
				DecisionTableElement[] secondColumn = desitionTable[i];
				if(this.columnValues[i].getGoupingNumber() != 0 && this.columnValues[i - 1].getGoupingNumber() != 0
						&& this.columnValues[i].getGoupingNumber() == this.columnValues[i - 1].getGoupingNumber()){
					//this 2 columns are already merged
					firstColumn = secondColumn;
					continue;
				}
				if(isColumnValuesAllZero(firstColumn)){
					firstColumn = secondColumn;
					continue;
				}
				if(isColumnValuesAllZero(secondColumn)){
					firstColumn = secondColumn;
					continue;
				}
				boolean columnsAreSame = true;
				for(int j = 0; j < y; j++){
					if(firstColumn[j].getGroup() != secondColumn[j].getGroup() && firstColumn[j].getGroup() != 0 && secondColumn[j].getGroup() != 0){
						columnsAreSame = false;
						break;
					}
				}
				if(columnsAreSame){
					LOGGER.debug("Operation 2 #  Column -> " + (i - 1) + " and Column -> " + i + " are merged");

					int calculatedColumn[] = new int[firstColumn.length];
					for(int j = 0; j < y; j++){
						int firstColumnIndex = firstColumn[j].getGroup();
						int secondColumnIndex = secondColumn[j].getGroup();
						int calculatedGroup = 0;
						if(firstColumnIndex != 0){
							calculatedGroup = firstColumnIndex;
						}
						if(secondColumnIndex != 0){
							calculatedGroup = secondColumnIndex;
						}
						calculatedColumn[j] = calculatedGroup;
					}
					markColumnsToMerge(i - 1, i);
					setCalculatedColumnToMergedColumns(calculatedColumn, this.columnValues[i]);

				}
				firstColumn = secondColumn;
			}
			this.columnValues[0].mergeForOperation1and2(this.columnValues);
			FuzzyMembershipPrintServiceImpl.printMembershipModels(this.columnValues);
		}

	}

	@Override
	public void mergeAdjacentRowsForOperation2() {

		if(desitionTable != null){
			int x = desitionTable.length;
			int y = desitionTable[0].length;
			for(int j = 1; j < y; j++){
				if(this.rowValues[j].getGoupingNumber() != 0 && this.rowValues[j - 1].getGoupingNumber() != 0 && this.rowValues[j].getGoupingNumber() == this.rowValues[j - 1].getGoupingNumber()){
					//this 2 columns are already merged
					continue;
				}
				if(isRowValuesAllZero(j - 1)){
					continue;
				}
				if(isRowValuesAllZero(j)){
					continue;
				}
				boolean rowsAreSame = true;
				for(int i = 0; i < x; i++){
					if(desitionTable[i][j - 1].getGroup() != desitionTable[i][j].getGroup() && desitionTable[i][j - 1].getGroup() != 0 && desitionTable[i][j].getGroup() != 0){
						rowsAreSame = false;
						break;
					}
				}
				if(rowsAreSame){
					LOGGER.debug("Operation 2 #  Row -> " + (j - 1) + " and Row -> " + j + " are merged");
					int[] calculatedRow = new int[desitionTable.length];
					for(int i = 0; i < x; i++){
						int calculatedGroup = 0;
						if(desitionTable[i][j - 1].getGroup() == desitionTable[i][j].getGroup() && desitionTable[i][j].getGroup() != 0 && desitionTable[i][j - 1].getGroup() != 0){
							calculatedGroup = desitionTable[i][j - 1].getGroup();
						} else if(desitionTable[i][j - 1].getGroup() != 0){
							calculatedGroup = desitionTable[i][j - 1].getGroup();
						} else if(desitionTable[i][j].getGroup() != 0){
							calculatedGroup = desitionTable[i][j].getGroup();
						}
						calculatedRow[i] = calculatedGroup;
					}

					markRowsToMerge(j - 1, j);
					setCalculatedRowToMergedColumns(calculatedRow, rowValues[j]);
				}
			}

			this.rowValues[0].mergeForOperation1and2(this.rowValues);
			FuzzyMembershipPrintServiceImpl.printMembershipModels(this.rowValues);
		}

	}

	@Override
	public void mergeRowsForOperation3() {
		if(desitionTable != null){
			int x = desitionTable.length;
			int y = desitionTable[0].length;
			for(int i = 1; i < y - 1; i++){

				if(isRowValuesAllZero(i)){

					if(isRowValuesAllZero(i)){
						int previousRowIndex = calculatePreviousRowIndexBasedOnMergeOperations(i);
						int nextRowIndex = calculateNextRowIndexBasedOnMergeOperations(i);
						if(isRowValuesAllZero(previousRowIndex)){
							continue;
						}
						if(isRowValuesAllZero(nextRowIndex)){
							continue;
						}

						boolean allRowsAreSame = true;
						for(int j = 0; j < x; j++){
							if(desitionTable[j][previousRowIndex].getGroup() != 0 && desitionTable[j][nextRowIndex].getGroup() != 0
									&& desitionTable[j][previousRowIndex].getGroup() == desitionTable[j][nextRowIndex].getGroup()){
								continue;
							} else{
								allRowsAreSame = false;
								break;
							}
						}
						if(allRowsAreSame){
							LOGGER.debug("Operation 4 #  Rows between-> " + previousRowIndex + " and Row -> " + nextRowIndex + " are merged");
							mergeRowsForInterval(previousRowIndex, i, nextRowIndex);
							int[] calculatedRow = new int[x];
							for(int j = 0; j < x; j++){
								int currentValue = 0;
								if(desitionTable[j][previousRowIndex].getGroup() != 0){
									currentValue = desitionTable[j][previousRowIndex].getGroup();
								}
								if(desitionTable[j][nextRowIndex].getGroup() != 0){
									currentValue = desitionTable[j][nextRowIndex].getGroup();
								}
								calculatedRow[j] = currentValue;
							}
							setAllRowsWithSameCalculatedRow(calculatedRow, i);
						}

					}
				}
			}
			this.rowValues[0].mergeForOperation3and4(this.rowValues);
			FuzzyMembershipPrintServiceImpl.printMembershipModels(this.rowValues);
		}

	}

	private void setAllRowsWithSameCalculatedRow(int[] calculatedRow, int currentIndex) {
		if(currentIndex > 0 && currentIndex < this.rowValues.length){
			for(int i = 0; i < this.rowValues.length; i++){
				if(this.rowValues[currentIndex].getGoupingNumber() == this.rowValues[i].getGoupingNumber()){
					for(int j = 0; j < this.columnValues.length; j++){
						this.desitionTable[j][i].setGroup(calculatedRow[j]);
					}
				}
			}
		}
	}

	private void mergeRowsForInterval(int previousRowIndex, int currentRowIndex, int nextRowIndex) {
		int rowIndex = 0;
		if(rowValues[nextRowIndex].getGoupingNumber() != 0){
			rowIndex = rowValues[nextRowIndex].getGoupingNumber();
		} else if(rowValues[previousRowIndex].getGoupingNumber() != 0){
			rowIndex = rowValues[previousRowIndex].getGoupingNumber();
		} else if(rowValues[currentRowIndex].getGoupingNumber() != 0){
			rowIndex = rowValues[currentRowIndex].getGoupingNumber();
		} else{
			rowIndex = this.mergedRowIndex;
			this.mergedRowIndex++;
		}
		int previousMergedValue = rowValues[previousRowIndex].getGoupingNumber();
		int nextMergedValue = rowValues[nextRowIndex].getGoupingNumber();
		int currentValue = rowValues[currentRowIndex].getGoupingNumber();
		if(previousMergedValue == 0){
			this.rowValues[previousRowIndex].markGroupNumber(rowIndex);
		}
		if(currentValue == 0){
			this.rowValues[currentRowIndex].markGroupNumber(rowIndex);
		}
		if(nextMergedValue == 0){
			this.rowValues[nextRowIndex].markGroupNumber( rowIndex);
		}
		for(int i = 0; i < this.rowValues.length; i++){
			if(previousMergedValue != 0 && this.rowValues[i].getGoupingNumber() == previousMergedValue){
				this.rowValues[i].markGroupNumber(rowIndex);
			} else if(nextMergedValue != 0 && this.rowValues[i].getGoupingNumber() == nextMergedValue){
				this.rowValues[i].markGroupNumber(rowIndex);
			} else if(currentValue != 0 && this.rowValues[i].getGoupingNumber() == currentValue){
				this.rowValues[i].markGroupNumber(rowIndex);
			}

		}
	}

	private int calculateNextRowIndexBasedOnMergeOperations(int currentRowIndex) {
		int nextRowIndex = 0;
		if(currentRowIndex > 0 && currentRowIndex < this.rowValues.length){
			for(int i = currentRowIndex; i < this.rowValues.length; i++){
				if(this.rowValues[currentRowIndex].getGoupingNumber() != this.rowValues[i].getGoupingNumber()){
					nextRowIndex = i;
					break;
				}
			}
		}
		return nextRowIndex;
	}

	private int calculatePreviousRowIndexBasedOnMergeOperations(int currentRowIndex) {
		int previousRowIndex = 0;
		if(currentRowIndex > 0 && currentRowIndex < this.rowValues.length){
			for(int i = currentRowIndex; i > 0; i--){
				if(this.rowValues[currentRowIndex].getGoupingNumber() != this.rowValues[i].getGoupingNumber()){
					previousRowIndex = i;
					break;
				}
			}
		}
		return previousRowIndex;
	}

	@Override
	public void mergeColumnsForOperation3() {
		if(desitionTable != null){
			int x = desitionTable.length;
			int y = desitionTable[0].length;
			for(int i = 1; i < x - 1; i++){
				if(isColumnValuesAllZero(desitionTable[i - 1])){
					continue;
				}
				if(isColumnValuesAllZero(desitionTable[i + 1])){
					continue;
				}
				if(isColumnValuesAllZero(desitionTable[i])){
					int previousColumnIndex = i - 1;
					int nextColumnIndex = i + 1;
					if(previousColumnIndex > 0 && nextColumnIndex < y){
						boolean columnsAreSame = true;
						for(int j = 0; j < y; j++){
							if(desitionTable[previousColumnIndex][j].getGroup() != desitionTable[nextColumnIndex][j].getGroup()){
								columnsAreSame = false;
								break;
							}
						}
						if(columnsAreSame){
							LOGGER.debug("Operation 3 #  Column -> " + (i - 1) + " and Column -> " + i + "and Column ->" + (i + 1) + " are merged");
							mergeColumns(i);
							for(int j = 0; j < x; j++){
								desitionTable[i][j].setGroup(desitionTable[previousColumnIndex][j].getGroup());
							}
						}
					}
				}
			}
						
			this.columnValues[0].mergeForOperation3and4(this.columnValues);
			FuzzyMembershipPrintServiceImpl.printMembershipModels(this.columnValues);
		}
	}

	private void setCalculatedRowToMergedColumns(int[] calculatedRow, MembershipModel rowValues) {
		for(int i = 0; i < this.rowValues.length; i++){
			if(this.rowValues[i].getGoupingNumber() == rowValues.getGoupingNumber()){
				for(int j = 0; j < columnValues.length; j++){
					this.desitionTable[j][i].setGroup(calculatedRow[j]);
				}
			}
		}
	}

	private boolean isRowValuesAllZero(int rowIndex) {
		boolean allZero = true;
		int columnsNumber = this.desitionTable.length;
		for(int i = 0; i < columnsNumber; i++){
			if(this.desitionTable[i][rowIndex].getGroup() != 0){
				allZero = false;
				break;
			}
		}
		return allZero;
	}

	private boolean isColumnValuesAllZero(DecisionTableElement[] column) {
		boolean allZero = true;
		for(int i = 0; i < column.length; i++){
			if(column[i].getGroup() != 0){
				allZero = false;
				break;
			}
		}
		return allZero;
	}

	private void setCalculatedColumnToMergedColumns(int[] calculatedColumn, MembershipModel columnValue) {
		for(int i = 0; i < this.columnValues.length; i++){
			if(this.columnValues[i].getGoupingNumber() == columnValue.getGoupingNumber()){
				for(int j = 0; j < rowValues.length; j++){
					this.desitionTable[i][j].setGroup(calculatedColumn[j]);
				}
			}
		}

	}

	private int mergeColumns(int currentColumnIndex) {
		int previousCoulumnIndex = currentColumnIndex - 1;
		int nextCoulumnIndex = currentColumnIndex + 1;
		int columnIndex = 0;
		if(previousCoulumnIndex > 0 && nextCoulumnIndex < this.columnValues.length){
			if(columnValues[nextCoulumnIndex].getGoupingNumber() != 0){
				columnIndex = columnValues[nextCoulumnIndex].getGoupingNumber();
			} else if(columnValues[previousCoulumnIndex].getGoupingNumber() != 0){
				columnIndex = columnValues[previousCoulumnIndex].getGoupingNumber();
			} else{
				columnIndex = this.mergedColumnIndex;
				this.mergedColumnIndex++;
			}
		}
		this.columnValues[previousCoulumnIndex].markGroupNumber(columnIndex);
		this.columnValues[currentColumnIndex].markGroupNumber(columnIndex);
		this.columnValues[nextCoulumnIndex].markGroupNumber(columnIndex);
		return columnIndex;

	}

	private int markColumnsToMerge(int firstIndex, int secondIndex) {
		int columnIndex = 0;
		if(columnValues[secondIndex].getGoupingNumber() != 0){
			columnIndex = columnValues[secondIndex].getGoupingNumber();
		} else if(columnValues[firstIndex].getGoupingNumber() != 0){
			columnIndex = columnValues[firstIndex].getGoupingNumber();
		} else{
			columnIndex = this.mergedColumnIndex;
			this.mergedColumnIndex++;
		}

		this.columnValues[secondIndex].markGroupNumber(columnIndex);
		this.columnValues[firstIndex].markGroupNumber(columnIndex);
		return columnIndex;
	}

	private int markRowsToMerge(int firstRowIndex, int secondRowIndex) {

		int rowIndex = 0;
		if(rowValues[secondRowIndex].getGoupingNumber() != 0){
			rowIndex = rowValues[secondRowIndex].getGoupingNumber();
		} else if(rowValues[firstRowIndex].getGoupingNumber() != 0){
			rowIndex = rowValues[firstRowIndex].getGoupingNumber();
		} else{
			rowIndex = this.mergedRowIndex;
			this.mergedRowIndex++;
		}
		this.rowValues[firstRowIndex].markGroupNumber(rowIndex);
		this.rowValues[secondRowIndex].markGroupNumber(rowIndex);
		return rowIndex;

	}

	@Override
	public void mergeColumnsForOperation4() {
		if(desitionTable != null){
			int x = desitionTable.length;
			int y = desitionTable[0].length;
			for(int i = 1; i < x - 1; i++){
				if(isColumnValuesAllZero(desitionTable[i - 1])){
					continue;
				}
				if(isColumnValuesAllZero(desitionTable[i + 1])){
					continue;
				}
				if(isColumnValuesAllZero(desitionTable[i])){
					int previousColumnIndex = i - 1;
					int nextColumnIndex = i + 1;
					if(previousColumnIndex > 0 && nextColumnIndex < y){
						boolean columnsAreSame = true;
						for(int j = 0; j < y; j++){
							if(desitionTable[previousColumnIndex][j].getGroup() == desitionTable[nextColumnIndex][j].getGroup()
									|| (desitionTable[previousColumnIndex][j].getGroup() == 0 && desitionTable[nextColumnIndex][j].getGroup() != 0)
									|| (desitionTable[previousColumnIndex][j].getGroup() != 0 && desitionTable[nextColumnIndex][j].getGroup() == 0)){
								continue;
							} else{
								columnsAreSame = false;
								break;
							}
						}
						if(columnsAreSame){
							LOGGER.debug("Operation 4 #  Column -> " + (i - 1) + " and Column -> " + i + "and Column ->" + (i + 1) + " are merged");
							int mergedValue = mergeColumns(i);
							int[] calculatedColumn = new int[y];
							for(int j = 0; j < y; j++){
								int columnValue = 0;
								if(desitionTable[nextColumnIndex][j].getGroup() != 0){
									columnValue = desitionTable[nextColumnIndex][j].getGroup();
								} else if(desitionTable[previousColumnIndex][j].getGroup() != 0){
									columnValue = desitionTable[previousColumnIndex][j].getGroup();
								} else{
									columnValue = 0;
									this.mergedColumnIndex++;
								}
								calculatedColumn[j] = columnValue;
							}
							this.setAllColumnsWithSameCalculatedRow(calculatedColumn, mergedValue);
						}

					}
				}
			}
			this.columnValues[0].mergeForOperation3and4(this.columnValues);
			FuzzyMembershipPrintServiceImpl.printMembershipModels(this.columnValues);

		}

	}

	private void setAllColumnsWithSameCalculatedRow(int[] calculatedColumn, int mergedValue) {
		for(int i = 0; i < this.columnValues.length; i++){
			if(this.columnValues[i].getGoupingNumber() == mergedValue){
				for(int j = 0; j < this.rowValues.length; j++){
					this.desitionTable[i][j].setGroup(calculatedColumn[j]);
				}
			}
		}

	}

	@Override
	public void mergeRowsForOperation4() {

		if(desitionTable != null){
			int x = desitionTable.length;
			int y = desitionTable[0].length;
			for(int i = 1; i < y - 1; i++){

				if(isRowValuesAllZero(i)){
					int previousRowIndex = calculatePreviousRowIndexBasedOnMergeOperations(i);
					int nextRowIndex = calculateNextRowIndexBasedOnMergeOperations(i);
					if(isRowValuesAllZero(previousRowIndex)){
						continue;
					}
					if(isRowValuesAllZero(nextRowIndex)){
						continue;
					}

					boolean allRowsAreSame = true;
					for(int j = 0; j < x; j++){
						if((desitionTable[j][previousRowIndex].getGroup() == 0 && desitionTable[j][nextRowIndex].getGroup() != 0)
								|| (desitionTable[j][previousRowIndex].getGroup() != 0 && desitionTable[j][nextRowIndex].getGroup() == 0)
								|| (desitionTable[j][previousRowIndex].getGroup() == 0 && desitionTable[j][nextRowIndex].getGroup() == 0) || (desitionTable[j][previousRowIndex].getGroup() != 0
										&& desitionTable[j][nextRowIndex].getGroup() != 0 && desitionTable[j][previousRowIndex].getGroup() == desitionTable[j][nextRowIndex].getGroup())){
							continue;
						} else{
							allRowsAreSame = false;
							break;
						}
					}
					if(allRowsAreSame){
						LOGGER.debug("Operation 4 #  Rows between-> " + previousRowIndex + " and Row -> " + nextRowIndex + " are merged");
						mergeRowsForInterval(previousRowIndex, i, nextRowIndex);
						int[] calculatedRow = new int[x];
						for(int j = 0; j < x; j++){
							int currentValue = 0;
							if(desitionTable[j][previousRowIndex].getGroup() != 0){
								currentValue = desitionTable[j][previousRowIndex].getGroup();
							}
							if(desitionTable[j][nextRowIndex].getGroup() != 0){
								currentValue = desitionTable[j][nextRowIndex].getGroup();
							}
							calculatedRow[j] = currentValue;
						}
						setAllRowsWithSameCalculatedRow(calculatedRow, i);
					}
				}
			}
			this.rowValues[0].mergeForOperation3and4(this.rowValues);
			FuzzyMembershipPrintServiceImpl.printMembershipModels(this.rowValues);
		}

	}

	@Override
	public void mergeRowsForOperation5() {

		if(desitionTable != null){
			int x = desitionTable.length;
			int y = desitionTable[0].length;
			for(int i = 1; i < y; i++){
				if(this.isRowValuesAllZero(i)){

					int previousRegionIndex = i - 1;
					int nextRegionIndex = calculateNextRowRegionIndex(i);
					int nextRegionIndexTemp = nextRegionIndex;
					int numberOfEmptyColumnsInTheRegion = nextRegionIndex - previousRegionIndex - 1;
					boolean growRegionLeftOrRigth = false; //left -> true, rigth -> true
					while(numberOfEmptyColumnsInTheRegion > 0){
						if(growRegionLeftOrRigth){
							LOGGER.debug("Operation 5 #  Row -> " + previousRegionIndex + " and Row -> " + (previousRegionIndex + 1) + " are merged");
							growRowRegionByIndexes(previousRegionIndex, previousRegionIndex + 1);
							growRegionLeftOrRigth = false;
							previousRegionIndex++;
						} else{
							LOGGER.debug("Operation 5 #  Row -> " + (nextRegionIndex - 1) + " and Row -> " + nextRegionIndex + " are merged");
							growRowRegionByIndexes(nextRegionIndex, nextRegionIndex - 1);
							growRegionLeftOrRigth = true;
							nextRegionIndex--;
						}
						numberOfEmptyColumnsInTheRegion--;
					}
					this.columnValues[0].mergeForOperation5(this.columnValues,i-1,i,nextRegionIndexTemp);
				}
			}
			FuzzyMembershipPrintServiceImpl.printMembershipModels(this.rowValues);
		}

	}
	
	@Override
	public void mergeColumnsForOperation5() {
		if(desitionTable != null){
			int x = desitionTable.length;
			int y = desitionTable[0].length;
			for(int i = 1; i < x - 1; i++){
				if(isColumnValuesAllZero(desitionTable[i])){

					int previousRegionIndex = i - 1;
					int nextRegionIndex = calculateNextColumnRegionIndex(i);
					int nextRegionIndexTemp = nextRegionIndex;
					int numberOfEmptyColumnsInTheRegion = nextRegionIndex - previousRegionIndex - 1;
					boolean growRegionLeftOrRigth = false; //left -> true, rigth -> true
					while(numberOfEmptyColumnsInTheRegion > 0){
						if(growRegionLeftOrRigth){
							LOGGER.debug("Operation 5 #  Column -> " + previousRegionIndex + " and Column -> " + (previousRegionIndex + 1) + " are merged");
							growColumnRegionByIndexes(previousRegionIndex, previousRegionIndex + 1);
							growRegionLeftOrRigth = false;
							previousRegionIndex++;
						} else{
							LOGGER.debug("Operation 5 #  Column -> " + (nextRegionIndex - 1) + " and Column -> " + nextRegionIndex + " are merged");
							growColumnRegionByIndexes(nextRegionIndex, nextRegionIndex - 1);
							growRegionLeftOrRigth = true;
							nextRegionIndex--;
						}
						numberOfEmptyColumnsInTheRegion--;
					}
					this.columnValues[0].mergeForOperation5(this.columnValues,i-1,i,nextRegionIndexTemp);
				}				
			}
			
			FuzzyMembershipPrintServiceImpl.printMembershipModels(this.columnValues);

		}
	}

	private void growColumnRegionByIndexes(int growedColumnIndex, int emptyRowIndex) {
		if(growedColumnIndex >= 0 && emptyRowIndex >= 0 && growedColumnIndex < this.columnValues.length && emptyRowIndex < this.columnValues.length){
			int mergedColumnRegionValue = 0;
			if(this.columnValues[growedColumnIndex].getGoupingNumber() == 0){
				mergedColumnRegionValue = this.mergedColumnIndex;
				this.columnValues[growedColumnIndex].markGroupNumber(mergedColumnRegionValue);
				this.mergedColumnIndex++;
			} else{
				mergedColumnRegionValue = this.columnValues[growedColumnIndex].getGoupingNumber();
			}
			this.columnValues[emptyRowIndex].markGroupNumber(mergedColumnRegionValue);
			for(int i = 0; i < this.rowValues.length; i++){
				this.desitionTable[emptyRowIndex][i].setGroup(this.desitionTable[growedColumnIndex][i].getGroup());
			}
		}

	}

	private void growRowRegionByIndexes(int growedRowIndex, int emptyRowIndex) {
		if(growedRowIndex >= 0 && emptyRowIndex >= 0 && growedRowIndex < this.rowValues.length && emptyRowIndex < this.rowValues.length){
			int mergedRowRegionValue = 0;
			if(this.rowValues[growedRowIndex].getGoupingNumber() == 0){
				mergedRowRegionValue = this.mergedRowIndex;
				this.rowValues[growedRowIndex].markGroupNumber(mergedRowRegionValue);
				this.mergedRowIndex++;
			} else{
				mergedRowRegionValue = this.rowValues[growedRowIndex].getGoupingNumber();
			}
			this.rowValues[emptyRowIndex].markGroupNumber(mergedRowRegionValue);
			for(int i = 0; i < this.columnValues.length; i++){
				this.desitionTable[i][emptyRowIndex].setGroup(this.desitionTable[i][growedRowIndex].getGroup());
			}
		}
	}

	private int calculateNextColumnRegionIndex(int emptyColumnIndex) {
		int regionNumber = this.columnValues[emptyColumnIndex].getGoupingNumber();
		if(this.columnValues[emptyColumnIndex].getGoupingNumber() == regionNumber){
			emptyColumnIndex++;
			while(emptyColumnIndex < this.columnValues.length && this.columnValues[emptyColumnIndex].getGoupingNumber() == regionNumber){
				emptyColumnIndex++;
			}
		}
		return emptyColumnIndex;
	}

	private int calculateNextRowRegionIndex(int emptyRowIndex) {
		int regionNumber = this.rowValues[emptyRowIndex].getGoupingNumber();
		if(this.rowValues[emptyRowIndex].getGoupingNumber() == regionNumber){
			emptyRowIndex++;
			while(emptyRowIndex < this.rowValues.length && this.rowValues[emptyRowIndex].getGoupingNumber() == regionNumber){
				emptyRowIndex++;
			}
		}
		return emptyRowIndex;
	}

	@Override
	public List<MembershipModel> getAgeMembershipRules() {
		List<MembershipModel> rules = new ArrayList<MembershipModel>();
		return FuzzyMembershipPrintServiceImpl.getUniqueGroups(this.columnValues);
	}

	@Override
	public List<MembershipModel> getPropertyMembershipRules() {
		return FuzzyMembershipPrintServiceImpl.getUniqueGroups(this.rowValues);
	}
}
