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
					LOGGER.debug("Operation 1 #  Column -> " + (i - 1) + " and Column -> " + i + " are merged");
					for(int j = 0; j < y; j++){
						int calculatedGroup = firstColumn[j].getGroup();
						secondColumn[j].setGroup(calculatedGroup);
						firstColumn[j].setGroup(calculatedGroup);
					}

					mergeColumns(i - 1, i);

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
					LOGGER.debug("Operation 1 #  Row -> " + (j - 1) + " and Row -> " + j + " are merged");
					for(int i = 0; i < y; i++){
						int calculatedGroup = desitionTable[i][j - 1].getGroup();
						desitionTable[i][j - 1].setGroup(calculatedGroup);
						desitionTable[i][j].setGroup(calculatedGroup);
					}

					mergeRows(j - 1, j);

				}
			}
		}
	}

	public void mergeAdjacentColumsForOperation2() {
		if(desitionTable != null){
			int x = desitionTable.length;
			int y = desitionTable[0].length;
			DecisionTableElement[] firstColumn = desitionTable[0];
			for(int i = 1; i < x; i++){
				DecisionTableElement[] secondColumn = desitionTable[i];
				if(this.columns[i] != 0 && this.columns[i - 1] != 0 && this.columns[i] == this.columns[i - 1]){
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
					mergeColumns(i - 1, i);
					setCalculatedColumnToMergedColumns(calculatedColumn, columns[i]);

				}
				firstColumn = secondColumn;
			}
		}
	}

	public void mergeAdjacentRowsForOperation2() {
		if(desitionTable != null){
			int x = desitionTable.length;
			int y = desitionTable[0].length;
			for(int j = 1; j < y; j++){
				if(this.rows[j] != 0 && this.rows[j - 1] != 0 && this.rows[j] == this.rows[j - 1]){
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

					mergeRows(j - 1, j);
					setCalculatedRowToMergedColumns(calculatedRow, rows[j]);
				}
			}
		}
	}

	public void mergeRowsForOperation3() {
		if(desitionTable != null){
			int x = desitionTable.length;
			int y = desitionTable[0].length;
			for(int i = 1; i < y; i++){

			}
		}
	}

	public void mergeColumnsForOperation3() {
		if(desitionTable != null){
			int x = desitionTable.length;
			int y = desitionTable[0].length;
			for(int i = 1; i < y - 1; i++){
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

		}
	}

	private void setCalculatedRowToMergedColumns(int[] calculatedRow, int mergedRowIndex) {
		for(int i = 0; i < this.rows.length; i++){
			if(this.rows[i] == mergedRowIndex){
				for(int j = 0; j < columns.length; j++){
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

	private void setCalculatedColumnToMergedColumns(int[] calculatedColumn, int mergedColumnIndex) {
		for(int i = 0; i < this.columns.length; i++){
			if(this.columns[i] == mergedColumnIndex){
				for(int j = 0; j < rows.length; j++){
					this.desitionTable[i][j].setGroup(calculatedColumn[j]);
				}
			}
		}

	}

	private int mergeColumns(int currentColumnIndex) {
		int previousCoulumnIndex = currentColumnIndex - 1;
		int nextCoulumnIndex = currentColumnIndex + 1;
		int columnIndex = 0;
		if(previousCoulumnIndex > 0 && nextCoulumnIndex < this.columns.length){
			if(columns[nextCoulumnIndex] != 0){
				columnIndex = columns[nextCoulumnIndex];
			} else if(columns[previousCoulumnIndex] != 0){
				columnIndex = columns[previousCoulumnIndex];
			} else{
				columnIndex = this.mergedColumnIndex;
				this.mergedColumnIndex++;
			}
		}
		this.columns[previousCoulumnIndex] = columnIndex;
		this.columns[currentColumnIndex] = columnIndex;
		this.columns[nextCoulumnIndex] = columnIndex;
		return columnIndex;

	}

	private int mergeColumns(int firstIndex, int secondIndex) {
		int columnIndex = 0;
		if(columns[secondIndex] != 0){
			columnIndex = columns[secondIndex];
		} else if(columns[firstIndex] != 0){
			columnIndex = columns[firstIndex];
		} else{
			columnIndex = this.mergedColumnIndex;
			this.mergedColumnIndex++;
		}

		this.columns[secondIndex] = columnIndex;
		this.columns[firstIndex] = columnIndex;
		return columnIndex;
	}

	private void mergeRows(int firstRowIndex, int secondRowIndex) {

		int rowIndex = 0;
		if(rows[secondRowIndex] != 0){
			rowIndex = rows[secondRowIndex];
		} else if(rows[firstRowIndex] != 0){
			rowIndex = rows[firstRowIndex];
		} else{
			rowIndex = this.mergedRowIndex;
			this.mergedRowIndex++;
		}
		this.rows[firstRowIndex] = rowIndex;
		this.rows[secondRowIndex] = rowIndex;

	}

	public void mergeColumnsForOperation4() {
		if(desitionTable != null){
			int x = desitionTable.length;
			int y = desitionTable[0].length;
			for(int i = 1; i < y - 1; i++){
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
							mergeColumns(i);
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
								desitionTable[previousColumnIndex][j].setGroup(columnValue);
								desitionTable[i][j].setGroup(columnValue);
								desitionTable[nextColumnIndex][j].setGroup(columnValue);
							}
						}

					}
				}
			}

		}
	}

	public void mergeRowsForOperation4() {
		// TODO Auto-generated method stub
		
	}

}
