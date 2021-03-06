package com.fuzzy.stocks.service.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fuzzy.stocks.enums.FuzzyMembershipCalculationStatusEnum;
import com.fuzzy.stocks.model.DecisionTableElement;
import com.fuzzy.stocks.model.FuzzyData;
import com.fuzzy.stocks.model.MembershipModel;

public class FuzzyMembershipConstructionServiceTest {

	private FuzzyMembershipConstructionServiceImpl service;

	@Before
	public void initializeTest() {

		List<FuzzyData> data = new ArrayList<FuzzyData>();
		FuzzyData fuzzyData1 = new FuzzyData.FuzzyDataBuilder(20d, 30d, 2000d).build();
		FuzzyData fuzzyData2 = new FuzzyData.FuzzyDataBuilder(25d, 30d, 2100d).build();
		FuzzyData fuzzyData3 = new FuzzyData.FuzzyDataBuilder(30d, 10d, 2200d).build();
		FuzzyData fuzzyData4 = new FuzzyData.FuzzyDataBuilder(45d, 50d, 2500d).build();
		FuzzyData fuzzyData5 = new FuzzyData.FuzzyDataBuilder(50d, 30d, 2600d).build();
		FuzzyData fuzzyData6 = new FuzzyData.FuzzyDataBuilder(60d, 10d, 2700d).build();
		FuzzyData fuzzyData7 = new FuzzyData.FuzzyDataBuilder(80d, 30d, 3200d).build();
		FuzzyData fuzzyData8 = new FuzzyData.FuzzyDataBuilder(80d, 40d, 3300d).build();
		fuzzyData1.setGroup(1);
		fuzzyData1.setMembershipValue(0.828398381754089);
		fuzzyData1.setStatus(FuzzyMembershipCalculationStatusEnum.MEMBERSHIP_VALUES_ARE_CALCULATED);

		fuzzyData2.setGroup(1);
		fuzzyData2.setMembershipValue(1.0);
		fuzzyData2.setStatus(FuzzyMembershipCalculationStatusEnum.MEMBERSHIP_VALUES_ARE_CALCULATED);

		fuzzyData3.setGroup(1);
		fuzzyData3.setMembershipValue(0.828398381754089);
		fuzzyData3.setStatus(FuzzyMembershipCalculationStatusEnum.MEMBERSHIP_VALUES_ARE_CALCULATED);

		fuzzyData4.setGroup(2);
		fuzzyData4.setMembershipValue(0.828398381754089);
		fuzzyData4.setStatus(FuzzyMembershipCalculationStatusEnum.MEMBERSHIP_VALUES_ARE_CALCULATED);

		fuzzyData5.setGroup(2);
		fuzzyData5.setMembershipValue(1.0);
		fuzzyData5.setStatus(FuzzyMembershipCalculationStatusEnum.MEMBERSHIP_VALUES_ARE_CALCULATED);

		fuzzyData6.setGroup(2);
		fuzzyData6.setMembershipValue(0.828398381754089);
		fuzzyData6.setStatus(FuzzyMembershipCalculationStatusEnum.MEMBERSHIP_VALUES_ARE_CALCULATED);

		fuzzyData7.setGroup(3);
		fuzzyData7.setMembershipValue(0.8283983817540891);
		fuzzyData7.setStatus(FuzzyMembershipCalculationStatusEnum.MEMBERSHIP_VALUES_ARE_CALCULATED);

		fuzzyData8.setGroup(3);
		fuzzyData8.setMembershipValue(0.8283983817540891);
		fuzzyData8.setStatus(FuzzyMembershipCalculationStatusEnum.MEMBERSHIP_VALUES_ARE_CALCULATED);

		data.add(fuzzyData1);
		data.add(fuzzyData2);
		data.add(fuzzyData3);
		data.add(fuzzyData4);
		data.add(fuzzyData5);
		data.add(fuzzyData6);
		data.add(fuzzyData7);
		data.add(fuzzyData8);

		assertTrue(data != null);
		assertTrue(data.size() > 0);

		service = (FuzzyMembershipConstructionServiceImpl) new FuzzyMembershipConstructionServiceImpl.FuzzyMembershipConstructionServiceBuilder(data).build();
	}

	@Test
	public void mergeRowsForOperation5_NoParam_MergesSameRows() {
		this.service.mergedColumnIndex = 5;
		this.service.mergedRowIndex = 4;

		DecisionTableElement[][] desitionTable = getInitialDecicionTable();

		desitionTable[0][0] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][0] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][0] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[3][0] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[4][0] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[5][0] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][0] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[7][0] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[8][0] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[9][0] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[10][0] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[11][0] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[12][0] = new DecisionTableElement.DecisionTableElementBuilder(3).build();

		desitionTable[0][1] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][1] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][1] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[3][1] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[4][1] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[5][1] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][1] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[7][1] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[8][1] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[9][1] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[10][1] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[11][1] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[12][1] = new DecisionTableElement.DecisionTableElementBuilder(3).build();

		desitionTable[0][2] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][2] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][2] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[3][2] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[4][2] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[5][2] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][2] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[7][2] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[8][2] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[9][2] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[10][2] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[11][2] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[12][2] = new DecisionTableElement.DecisionTableElementBuilder(3).build();

		desitionTable[0][3] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][3] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][3] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[3][3] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[4][3] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[5][3] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][3] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[7][3] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[8][3] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[9][3] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[10][3] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[11][3] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[12][3] = new DecisionTableElement.DecisionTableElementBuilder(3).build();

		desitionTable[0][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[3][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[4][4] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[5][4] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][4] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[7][4] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[8][4] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[9][4] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[10][4] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[11][4] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[12][4] = new DecisionTableElement.DecisionTableElementBuilder(3).build();

		desitionTable[0][5] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][5] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][5] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[3][5] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[4][5] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[5][5] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][5] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[7][5] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[8][5] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[9][5] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[10][5] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[11][5] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[12][5] = new DecisionTableElement.DecisionTableElementBuilder(3).build();

		desitionTable[0][6] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][6] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][6] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[3][6] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[4][6] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[5][6] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][6] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[7][6] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[8][6] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[9][6] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[10][6] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[11][6] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[12][6] = new DecisionTableElement.DecisionTableElementBuilder(3).build();

		desitionTable[0][7] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][7] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][7] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[3][7] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[4][7] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[5][7] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][7] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[7][7] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[8][7] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[9][7] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[10][7] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[11][7] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[12][7] = new DecisionTableElement.DecisionTableElementBuilder(3).build();

		desitionTable[0][8] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][8] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][8] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[3][8] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[4][8] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[5][8] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][8] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[7][8] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[8][8] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[9][8] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[10][8] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[11][8] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[12][8] = new DecisionTableElement.DecisionTableElementBuilder(3).build();

		MembershipModel[] columnValues = new MembershipModel[13];
		MembershipModel[] rowValues = new MembershipModel[9];

		columnValues[0] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		columnValues[1] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		columnValues[2] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		columnValues[3] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		columnValues[4] = new MembershipModel.MembershipModelBuilder(30, 54.166666666666664, 80, 4).build();
		columnValues[5] = new MembershipModel.MembershipModelBuilder(30, 54.166666666666664, 80, 4).build();
		columnValues[6] = new MembershipModel.MembershipModelBuilder(30, 54.166666666666664, 80, 4).build();
		columnValues[7] = new MembershipModel.MembershipModelBuilder(30, 54.166666666666664, 80, 4).build();
		columnValues[8] = new MembershipModel.MembershipModelBuilder(30, 54.166666666666664, 80, 4).build();
		columnValues[9] = new MembershipModel.MembershipModelBuilder(30, 54.166666666666664, 80, 4).build();
		columnValues[10] = new MembershipModel.MembershipModelBuilder(30, 54.166666666666664, 80, 4).build();
		columnValues[11] = new MembershipModel.MembershipModelBuilder(60, 80, Double.MAX_VALUE, 5).build();
		columnValues[12] = new MembershipModel.MembershipModelBuilder(60, 80, Double.MAX_VALUE, 5).build();

		rowValues[0] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();
		rowValues[1] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();
		rowValues[2] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();
		rowValues[3] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();
		rowValues[4] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();
		rowValues[5] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();
		rowValues[6] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();
		rowValues[7] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();
		rowValues[8] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();

		this.service.desitionTable = desitionTable;
		this.service.columnValues = columnValues;
		this.service.rowValues = rowValues;

		this.service.mergeRowsForOperation5();

		assertTrue(this.service.rowValues[0].getGoupingNumber() == 3);
		assertTrue(this.service.rowValues[1].getGoupingNumber() == 3);
		assertTrue(this.service.rowValues[2].getGoupingNumber() == 3);
		assertTrue(this.service.rowValues[3].getGoupingNumber() == 3);
		assertTrue(this.service.rowValues[4].getGoupingNumber() == 3);
		assertTrue(this.service.rowValues[5].getGoupingNumber() == 3);
		assertTrue(this.service.rowValues[6].getGoupingNumber() == 3);
		assertTrue(this.service.rowValues[7].getGoupingNumber() == 3);
		assertTrue(this.service.rowValues[8].getGoupingNumber() == 3);

	}

	@Test
	public void mergeColumnsForOperation5_NoParam_MergesSameColumns() {

		this.service.mergedColumnIndex = 5;
		this.service.mergedRowIndex = 4;

		DecisionTableElement[][] desitionTable = getInitialDecicionTable();

		desitionTable[0][0] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][0] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][0] = new DecisionTableElement.DecisionTableElementBuilder(1).build();

		desitionTable[0][1] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][1] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][1] = new DecisionTableElement.DecisionTableElementBuilder(1).build();

		desitionTable[0][2] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][2] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][2] = new DecisionTableElement.DecisionTableElementBuilder(1).build();

		desitionTable[0][3] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][3] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][3] = new DecisionTableElement.DecisionTableElementBuilder(1).build();

		desitionTable[0][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();

		desitionTable[0][5] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][5] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][5] = new DecisionTableElement.DecisionTableElementBuilder(1).build();

		desitionTable[0][6] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][6] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][6] = new DecisionTableElement.DecisionTableElementBuilder(1).build();

		desitionTable[0][7] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][7] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][7] = new DecisionTableElement.DecisionTableElementBuilder(1).build();

		desitionTable[0][8] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][8] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][8] = new DecisionTableElement.DecisionTableElementBuilder(1).build();

		desitionTable[5][0] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][0] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[7][0] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[8][0] = new DecisionTableElement.DecisionTableElementBuilder(2).build();

		desitionTable[5][1] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][1] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[7][1] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[8][1] = new DecisionTableElement.DecisionTableElementBuilder(2).build();

		desitionTable[5][2] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][2] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[7][2] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[8][2] = new DecisionTableElement.DecisionTableElementBuilder(2).build();

		desitionTable[5][3] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][3] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[7][3] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[8][3] = new DecisionTableElement.DecisionTableElementBuilder(2).build();

		desitionTable[5][4] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][4] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[7][4] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[8][4] = new DecisionTableElement.DecisionTableElementBuilder(2).build();

		desitionTable[5][5] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][5] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[7][5] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[8][5] = new DecisionTableElement.DecisionTableElementBuilder(2).build();

		desitionTable[5][6] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][6] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[7][6] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[8][6] = new DecisionTableElement.DecisionTableElementBuilder(2).build();

		desitionTable[5][7] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][7] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[7][7] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[8][7] = new DecisionTableElement.DecisionTableElementBuilder(2).build();

		desitionTable[5][8] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][8] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[7][8] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[8][8] = new DecisionTableElement.DecisionTableElementBuilder(2).build();

		desitionTable[12][0] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[12][1] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[12][2] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[12][3] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[12][4] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[12][5] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[12][6] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[12][7] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[12][8] = new DecisionTableElement.DecisionTableElementBuilder(3).build();

		MembershipModel[] columnValues = new MembershipModel[13];
		MembershipModel[] rowValues = new MembershipModel[9];

		columnValues[0] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		columnValues[1] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		columnValues[2] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		columnValues[3] = new MembershipModel.MembershipModelBuilder(30, 37.5, 45, 2).build();
		columnValues[4] = new MembershipModel.MembershipModelBuilder(30, 37.5, 45, 2).build();
		columnValues[5] = new MembershipModel.MembershipModelBuilder(40, 54.166666666666664, 65, 4).build();
		columnValues[6] = new MembershipModel.MembershipModelBuilder(40, 54.166666666666664, 65, 4).build();
		columnValues[7] = new MembershipModel.MembershipModelBuilder(40, 54.166666666666664, 65, 4).build();
		columnValues[8] = new MembershipModel.MembershipModelBuilder(40, 54.166666666666664, 65, 4).build();
		columnValues[9] = new MembershipModel.MembershipModelBuilder(60, 70, 80, 3).build();
		columnValues[10] = new MembershipModel.MembershipModelBuilder(60, 70, 80, 3).build();
		columnValues[11] = new MembershipModel.MembershipModelBuilder(60, 70, 80, 3).build();
		columnValues[12] = new MembershipModel.MembershipModelBuilder(75, 80, Double.MAX_VALUE, 0).build();

		rowValues[0] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();
		rowValues[1] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();
		rowValues[2] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();
		rowValues[3] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();
		rowValues[4] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();
		rowValues[5] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();
		rowValues[6] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();
		rowValues[7] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();
		rowValues[8] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();

		this.service.desitionTable = desitionTable;
		this.service.columnValues = columnValues;
		this.service.rowValues = rowValues;

		this.service.mergeColumnsForOperation5();
		assertTrue(this.service.columnValues[0].getGoupingNumber() == 1);
		assertTrue(this.service.columnValues[1].getGoupingNumber() == 1);
		assertTrue(this.service.columnValues[2].getGoupingNumber() == 1);
		assertTrue(this.service.columnValues[3].getGoupingNumber() == 1);
		assertTrue(this.service.columnValues[4].getGoupingNumber() == 4);
		assertTrue(this.service.columnValues[5].getGoupingNumber() == 4);
		assertTrue(this.service.columnValues[6].getGoupingNumber() == 4);
		assertTrue(this.service.columnValues[7].getGoupingNumber() == 4);
		assertTrue(this.service.columnValues[8].getGoupingNumber() == 4);
		assertTrue(this.service.columnValues[9].getGoupingNumber() == 4);
		assertTrue(this.service.columnValues[10].getGoupingNumber() == 5);
		assertTrue(this.service.columnValues[11].getGoupingNumber() == 5);
		assertTrue(this.service.columnValues[12].getGoupingNumber() == 5);

	}

	@Test
	public void mergeColumnsForOperation4_NoParam_MergesSameColumns() {

		this.service.mergedColumnIndex = 5;
		this.service.mergedRowIndex = 4;

		DecisionTableElement[][] desitionTable = getInitialDecicionTable();

		desitionTable[0][0] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][0] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][0] = new DecisionTableElement.DecisionTableElementBuilder(1).build();

		desitionTable[0][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();

		desitionTable[5][4] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][4] = new DecisionTableElement.DecisionTableElementBuilder(2).build();

		desitionTable[5][8] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][8] = new DecisionTableElement.DecisionTableElementBuilder(2).build();

		desitionTable[8][0] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[12][4] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[12][7] = new DecisionTableElement.DecisionTableElementBuilder(3).build();

		MembershipModel[] columnValues = new MembershipModel[13];
		MembershipModel[] rowValues = new MembershipModel[9];

		columnValues[0] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		columnValues[1] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		columnValues[2] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		columnValues[3] = new MembershipModel.MembershipModelBuilder(30, 37.5, 45, 2).build();
		columnValues[4] = new MembershipModel.MembershipModelBuilder(30, 37.5, 45, 2).build();
		columnValues[5] = new MembershipModel.MembershipModelBuilder(40, 47.5, 55, 4).build();
		columnValues[6] = new MembershipModel.MembershipModelBuilder(40, 47.5, 55, 4).build();
		columnValues[7] = new MembershipModel.MembershipModelBuilder(50, 55, 60, 0).build();
		columnValues[8] = new MembershipModel.MembershipModelBuilder(55, 60, 65, 0).build();
		columnValues[9] = new MembershipModel.MembershipModelBuilder(60, 70, 80, 3).build();
		columnValues[10] = new MembershipModel.MembershipModelBuilder(60, 70, 80, 3).build();
		columnValues[11] = new MembershipModel.MembershipModelBuilder(60, 70, 80, 3).build();
		columnValues[12] = new MembershipModel.MembershipModelBuilder(75, 80, Double.MAX_VALUE, 0).build();

		rowValues[0] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 10, 15).build();
		rowValues[1] = new MembershipModel.MembershipModelBuilder(10, 20, 30, 1).build();
		rowValues[2] = new MembershipModel.MembershipModelBuilder(10, 20, 30, 1).build();
		rowValues[3] = new MembershipModel.MembershipModelBuilder(10, 20, 30, 1).build();
		rowValues[4] = new MembershipModel.MembershipModelBuilder(25, 30, 35, 0).build();
		rowValues[5] = new MembershipModel.MembershipModelBuilder(30, 37.5, 45, 2).build();
		rowValues[6] = new MembershipModel.MembershipModelBuilder(30, 37.5, 45, 2).build();
		rowValues[7] = new MembershipModel.MembershipModelBuilder(40, 47.5, Double.MAX_VALUE, 3).build();
		rowValues[8] = new MembershipModel.MembershipModelBuilder(45, 47.5, Double.MAX_VALUE, 3).build();

		this.service.desitionTable = desitionTable;
		this.service.columnValues = columnValues;
		this.service.rowValues = rowValues;

		this.service.mergeColumnsForOperation4();

		assertTrue(this.service.columnValues[0].getGoupingNumber() == 1);
		assertTrue(this.service.columnValues[1].getGoupingNumber() == 1);
		assertTrue(this.service.columnValues[2].getGoupingNumber() == 1);
		assertTrue(this.service.columnValues[3].getGoupingNumber() == 2);
		assertTrue(this.service.columnValues[4].getGoupingNumber() == 2);
		assertTrue(this.service.columnValues[5].getGoupingNumber() == 4);
		assertTrue(this.service.columnValues[6].getGoupingNumber() == 4);
		assertTrue(this.service.columnValues[7].getGoupingNumber() == 4);
		assertTrue(this.service.columnValues[8].getGoupingNumber() == 4);
		assertTrue(this.service.columnValues[9].getGoupingNumber() == 3);
		assertTrue(this.service.columnValues[10].getGoupingNumber() == 3);
		assertTrue(this.service.columnValues[11].getGoupingNumber() == 3);
		assertTrue(this.service.columnValues[12].getGoupingNumber() == 0);

	}

	@Test
	public void mergeRowsForOperation4_NoParam_MergesSameRows() {
		this.service.mergedColumnIndex = 5;
		this.service.mergedRowIndex = 4;

		DecisionTableElement[][] desitionTable = getInitialDecicionTable();

		desitionTable[0][0] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][0] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][0] = new DecisionTableElement.DecisionTableElementBuilder(1).build();

		desitionTable[0][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();

		desitionTable[5][0] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][0] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[7][0] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[8][0] = new DecisionTableElement.DecisionTableElementBuilder(2).build();

		desitionTable[5][4] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][4] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[7][4] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[8][4] = new DecisionTableElement.DecisionTableElementBuilder(2).build();

		desitionTable[5][8] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][8] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[7][8] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[8][8] = new DecisionTableElement.DecisionTableElementBuilder(2).build();

		desitionTable[12][4] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[12][7] = new DecisionTableElement.DecisionTableElementBuilder(3).build();

		MembershipModel[] columnValues = new MembershipModel[13];
		MembershipModel[] rowValues = new MembershipModel[9];

		columnValues[0] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		columnValues[1] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		columnValues[2] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		columnValues[3] = new MembershipModel.MembershipModelBuilder(30, 37.5, 45, 2).build();
		columnValues[4] = new MembershipModel.MembershipModelBuilder(30, 37.5, 45, 2).build();
		columnValues[5] = new MembershipModel.MembershipModelBuilder(40, 54.166666666666664, 65, 4).build();
		columnValues[6] = new MembershipModel.MembershipModelBuilder(40, 54.166666666666664, 65, 4).build();
		columnValues[7] = new MembershipModel.MembershipModelBuilder(40, 54.166666666666664, 65, 4).build();
		columnValues[8] = new MembershipModel.MembershipModelBuilder(40, 54.166666666666664, 65, 4).build();
		columnValues[9] = new MembershipModel.MembershipModelBuilder(60, 70, 80, 3).build();
		columnValues[10] = new MembershipModel.MembershipModelBuilder(60, 70, 80, 3).build();
		columnValues[11] = new MembershipModel.MembershipModelBuilder(60, 70, 80, 3).build();
		columnValues[12] = new MembershipModel.MembershipModelBuilder(75, 80, Double.MAX_VALUE, 0).build();

		rowValues[0] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, 15).build();
		rowValues[1] = new MembershipModel.MembershipModelBuilder(10, 20, 30, 1).build();
		rowValues[2] = new MembershipModel.MembershipModelBuilder(10, 20, 30, 1).build();
		rowValues[3] = new MembershipModel.MembershipModelBuilder(10, 20, 30, 1).build();
		rowValues[4] = new MembershipModel.MembershipModelBuilder(25, 30, 35, 0).build();
		rowValues[5] = new MembershipModel.MembershipModelBuilder(30, 37.5, 45, 2).build();
		rowValues[6] = new MembershipModel.MembershipModelBuilder(30, 37.5, 45, 2).build();
		rowValues[7] = new MembershipModel.MembershipModelBuilder(40, 47.5, Double.MAX_VALUE, 3).build();
		rowValues[8] = new MembershipModel.MembershipModelBuilder(45, 47.5, Double.MAX_VALUE, 3).build();

		this.service.desitionTable = desitionTable;
		this.service.columnValues = columnValues;
		this.service.rowValues = rowValues;

		this.service.mergeRowsForOperation4();

		assertTrue(this.service.rowValues[1].getGoupingNumber() == 3);
		assertTrue(this.service.rowValues[2].getGoupingNumber() == 3);
		assertTrue(this.service.rowValues[3].getGoupingNumber() == 3);
		assertTrue(this.service.rowValues[4].getGoupingNumber() == 3);
		assertTrue(this.service.rowValues[5].getGoupingNumber() == 3);
		assertTrue(this.service.rowValues[6].getGoupingNumber() == 3);
		assertTrue(this.service.rowValues[7].getGoupingNumber() == 3);
		assertTrue(this.service.rowValues[8].getGoupingNumber() == 3);

	}

	@Test
	public void mergeRowsForOperation3_NoParam_MergesSameRows() {
		this.service.mergedColumnIndex = 4;
		this.service.mergedRowIndex = 3;

		DecisionTableElement[][] desitionTable = getInitialDecicionTable();

		desitionTable[0][0] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][0] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][0] = new DecisionTableElement.DecisionTableElementBuilder(1).build();

		desitionTable[0][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();

		desitionTable[5][4] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][4] = new DecisionTableElement.DecisionTableElementBuilder(2).build();

		desitionTable[5][8] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][8] = new DecisionTableElement.DecisionTableElementBuilder(2).build();

		desitionTable[8][0] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[12][4] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[12][7] = new DecisionTableElement.DecisionTableElementBuilder(3).build();

		MembershipModel[] columnValues = new MembershipModel[13];
		MembershipModel[] rowValues = new MembershipModel[9];

		columnValues[0] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		columnValues[1] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		columnValues[2] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		columnValues[3] = new MembershipModel.MembershipModelBuilder(30, 37.5, 45, 2).build();
		columnValues[4] = new MembershipModel.MembershipModelBuilder(30, 37.5, 45, 2).build();
		columnValues[5] = new MembershipModel.MembershipModelBuilder(40, 47.5, 55, 4).build();
		columnValues[6] = new MembershipModel.MembershipModelBuilder(45, 47.5, 55, 4).build();
		columnValues[7] = new MembershipModel.MembershipModelBuilder(50, 55, 60, 0).build();
		columnValues[8] = new MembershipModel.MembershipModelBuilder(55, 60, 65, 0).build();
		columnValues[9] = new MembershipModel.MembershipModelBuilder(60, 70, 80, 3).build();
		columnValues[10] = new MembershipModel.MembershipModelBuilder(60, 70, 80, 3).build();
		columnValues[11] = new MembershipModel.MembershipModelBuilder(60, 70, 80, 3).build();
		columnValues[12] = new MembershipModel.MembershipModelBuilder(75, 80, Double.MAX_VALUE, 0).build();

		rowValues[0] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 10, 15).build();
		rowValues[1] = new MembershipModel.MembershipModelBuilder(10, 20, 30, 1).build();
		rowValues[2] = new MembershipModel.MembershipModelBuilder(10, 20, 30, 1).build();
		rowValues[3] = new MembershipModel.MembershipModelBuilder(10, 20, 30, 1).build();
		rowValues[4] = new MembershipModel.MembershipModelBuilder(25, 30, 35, 0).build();
		rowValues[5] = new MembershipModel.MembershipModelBuilder(30, 37.5, 45, 2).build();
		rowValues[6] = new MembershipModel.MembershipModelBuilder(30, 37.5, 45, 2).build();
		rowValues[7] = new MembershipModel.MembershipModelBuilder(40, 47.5, Double.MAX_VALUE, 3).build();
		rowValues[8] = new MembershipModel.MembershipModelBuilder(45, 47.5, Double.MAX_VALUE, 3).build();

		this.service.desitionTable = desitionTable;
		this.service.columnValues = columnValues;
		this.service.rowValues = rowValues;

		this.service.mergeRowsForOperation3();

		assertTrue(this.service.rowValues[0].getGoupingNumber() == 0);

		assertTrue(this.service.rowValues[1].getGoupingNumber() == this.service.rowValues[2].getGoupingNumber());
		assertTrue(this.service.rowValues[1].getGoupingNumber() == 1);
		assertTrue(this.service.rowValues[2].getGoupingNumber() == 1);
		assertTrue(this.service.rowValues[1].getGoupingNumber() == this.service.rowValues[3].getGoupingNumber());
		assertTrue(this.service.rowValues[3].getGoupingNumber() == 1);

		assertTrue(this.service.rowValues[4].getGoupingNumber() == 0);

		assertTrue(this.service.rowValues[5].getGoupingNumber() == this.service.rowValues[6].getGoupingNumber());
		assertTrue(this.service.rowValues[5].getGoupingNumber() == 2);
		assertTrue(this.service.rowValues[6].getGoupingNumber() == 2);

		assertTrue(this.service.rowValues[7].getGoupingNumber() == this.service.rowValues[8].getGoupingNumber());
		assertTrue(this.service.rowValues[7].getGoupingNumber() == 3);
		assertTrue(this.service.rowValues[8].getGoupingNumber() == 3);
	}

	@Test
	public void mergeColumnsForOperation3_NoParam_MergesSameColumns() {
		this.service.mergedColumnIndex = 5;
		this.service.mergedRowIndex = 4;

		DecisionTableElement[][] desitionTable = getInitialDecicionTable();

		desitionTable[0][0] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][0] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][0] = new DecisionTableElement.DecisionTableElementBuilder(1).build();

		desitionTable[0][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();

		desitionTable[5][4] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][4] = new DecisionTableElement.DecisionTableElementBuilder(2).build();

		desitionTable[5][8] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][8] = new DecisionTableElement.DecisionTableElementBuilder(2).build();

		desitionTable[8][0] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[12][4] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[12][7] = new DecisionTableElement.DecisionTableElementBuilder(3).build();

		MembershipModel[] columnValues = new MembershipModel[13];
		MembershipModel[] rowValues = new MembershipModel[9];

		columnValues[0] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		columnValues[1] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		columnValues[2] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		columnValues[3] = new MembershipModel.MembershipModelBuilder(30, 37.5, 45, 2).build();
		columnValues[4] = new MembershipModel.MembershipModelBuilder(30, 37.5, 45, 2).build();
		columnValues[5] = new MembershipModel.MembershipModelBuilder(40, 47.5, 55, 4).build();
		columnValues[6] = new MembershipModel.MembershipModelBuilder(45, 47.5, 55, 4).build();
		columnValues[7] = new MembershipModel.MembershipModelBuilder(50, 55, 60, 0).build();
		columnValues[8] = new MembershipModel.MembershipModelBuilder(55, 60, 65, 0).build();
		columnValues[9] = new MembershipModel.MembershipModelBuilder(60, 70, 80, 3).build();
		columnValues[10] = new MembershipModel.MembershipModelBuilder(60, 70, 80, 3).build();
		columnValues[11] = new MembershipModel.MembershipModelBuilder(60, 70, 80, 3).build();
		columnValues[12] = new MembershipModel.MembershipModelBuilder(75, 80, Double.MAX_VALUE, 0).build();

		rowValues[0] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 10, 15).build();
		rowValues[1] = new MembershipModel.MembershipModelBuilder(10, 20, 30, 1).build();
		rowValues[2] = new MembershipModel.MembershipModelBuilder(10, 20, 30, 1).build();
		rowValues[3] = new MembershipModel.MembershipModelBuilder(10, 20, 30, 1).build();
		rowValues[4] = new MembershipModel.MembershipModelBuilder(25, 30, 35, 0).build();
		rowValues[5] = new MembershipModel.MembershipModelBuilder(30, 37.5, 45, 2).build();
		rowValues[6] = new MembershipModel.MembershipModelBuilder(30, 37.5, 45, 2).build();
		rowValues[7] = new MembershipModel.MembershipModelBuilder(40, 47.5, Double.MAX_VALUE, 3).build();
		rowValues[8] = new MembershipModel.MembershipModelBuilder(45, 47.5, Double.MAX_VALUE, 3).build();

		this.service.desitionTable = desitionTable;
		this.service.columnValues = columnValues;
		this.service.rowValues = rowValues;

		this.service.mergeColumnsForOperation3();

		assertTrue(this.service.columnValues[0].getGoupingNumber() == this.service.columnValues[1].getGoupingNumber());
		assertTrue(this.service.columnValues[0].getGoupingNumber() == 1);
		assertTrue(this.service.columnValues[1].getGoupingNumber() == 1);

		assertTrue(this.service.columnValues[0].getGoupingNumber() == this.service.columnValues[2].getGoupingNumber());
		assertTrue(this.service.columnValues[2].getGoupingNumber() == 1);

		assertTrue(this.service.columnValues[3].getGoupingNumber() == this.service.columnValues[4].getGoupingNumber());
		assertTrue(this.service.columnValues[3].getGoupingNumber() == 2);
		assertTrue(this.service.columnValues[4].getGoupingNumber() == 2);

		assertTrue(this.service.columnValues[5].getGoupingNumber() == this.service.columnValues[6].getGoupingNumber());
		assertTrue(this.service.columnValues[5].getGoupingNumber() == 4);
		assertTrue(this.service.columnValues[6].getGoupingNumber() == 4);

		assertTrue(this.service.columnValues[7].getGoupingNumber() == 0);
		assertTrue(this.service.columnValues[8].getGoupingNumber() == 0);

		assertTrue(this.service.columnValues[9].getGoupingNumber() == this.service.columnValues[10].getGoupingNumber());
		assertTrue(this.service.columnValues[9].getGoupingNumber() == 3);
		assertTrue(this.service.columnValues[10].getGoupingNumber() == 3);
		assertTrue(this.service.columnValues[9].getGoupingNumber() == this.service.columnValues[11].getGoupingNumber());
		assertTrue(this.service.columnValues[11].getGoupingNumber() == 3);

		assertTrue(this.service.columnValues[12].getGoupingNumber() == 0);

	}

	@Test
	public void mergeAdjacentRowsForOperation2_NoParam_MergesSameRows() {
		this.service.mergedColumnIndex = 4;
		this.service.mergedRowIndex = 3;

		DecisionTableElement[][] desitionTable = getInitialDecicionTable();

		desitionTable[0][0] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][0] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][0] = new DecisionTableElement.DecisionTableElementBuilder(1).build();

		desitionTable[0][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();

		desitionTable[5][4] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][4] = new DecisionTableElement.DecisionTableElementBuilder(2).build();

		desitionTable[5][8] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][8] = new DecisionTableElement.DecisionTableElementBuilder(2).build();

		desitionTable[8][0] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[12][4] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[12][7] = new DecisionTableElement.DecisionTableElementBuilder(3).build();

		MembershipModel[] columnValues = new MembershipModel[13];
		MembershipModel[] rowValues = new MembershipModel[9];

		columnValues[0] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		columnValues[1] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		columnValues[2] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		columnValues[3] = new MembershipModel.MembershipModelBuilder(30, 37.5, 45, 2).build();
		columnValues[4] = new MembershipModel.MembershipModelBuilder(30, 37.5, 45, 2).build();
		columnValues[5] = new MembershipModel.MembershipModelBuilder(40, 47.5, 55, 4).build();
		columnValues[6] = new MembershipModel.MembershipModelBuilder(45, 47.5, 55, 4).build();
		columnValues[7] = new MembershipModel.MembershipModelBuilder(50, 55, 60, 0).build();
		columnValues[8] = new MembershipModel.MembershipModelBuilder(55, 60, 65, 0).build();
		columnValues[9] = new MembershipModel.MembershipModelBuilder(60, 70, 80, 3).build();
		columnValues[10] = new MembershipModel.MembershipModelBuilder(60, 70, 80, 3).build();
		columnValues[11] = new MembershipModel.MembershipModelBuilder(60, 70, 80, 3).build();
		columnValues[12] = new MembershipModel.MembershipModelBuilder(75, 80, Double.MAX_VALUE, 0).build();

		rowValues[0] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 10, 15).build();
		rowValues[1] = new MembershipModel.MembershipModelBuilder(10, 20, 30, 1).build();
		rowValues[2] = new MembershipModel.MembershipModelBuilder(10, 20, 30, 1).build();
		rowValues[3] = new MembershipModel.MembershipModelBuilder(10, 20, 30, 1).build();
		rowValues[4] = new MembershipModel.MembershipModelBuilder(25, 30, 35, 0).build();
		rowValues[5] = new MembershipModel.MembershipModelBuilder(30, 37.5, 45, 2).build();
		rowValues[6] = new MembershipModel.MembershipModelBuilder(30, 37.5, 45, 2).build();
		rowValues[7] = new MembershipModel.MembershipModelBuilder(40, 45, 50).build();
		rowValues[8] = new MembershipModel.MembershipModelBuilder(45, 50, Double.MAX_VALUE).build();

		this.service.desitionTable = desitionTable;
		this.service.columnValues = columnValues;
		this.service.rowValues = rowValues;

		this.service.mergeAdjacentRowsForOperation2();

		assertTrue(this.service.rowValues[0].getGoupingNumber() == 0);

		assertTrue(this.service.rowValues[1].getGoupingNumber() == this.service.rowValues[2].getGoupingNumber());
		assertTrue(this.service.rowValues[1].getGoupingNumber() == 1);
		assertTrue(this.service.rowValues[2].getGoupingNumber() == 1);
		assertTrue(this.service.rowValues[1].getGoupingNumber() == this.service.rowValues[3].getGoupingNumber());
		assertTrue(this.service.rowValues[3].getGoupingNumber() == 1);

		assertTrue(this.service.rowValues[4].getGoupingNumber() == 0);

		assertTrue(this.service.rowValues[5].getGoupingNumber() == this.service.rowValues[6].getGoupingNumber());
		assertTrue(this.service.rowValues[5].getGoupingNumber() == 2);
		assertTrue(this.service.rowValues[6].getGoupingNumber() == 2);

		assertTrue(this.service.rowValues[7].getGoupingNumber() == this.service.rowValues[8].getGoupingNumber());
		assertTrue(this.service.rowValues[7].getGoupingNumber() == 3);
		assertTrue(this.service.rowValues[8].getGoupingNumber() == 3);

	}

	@Test
	public void mergeAdjacentColumsForOperation2_NoParam_MergesSameColumns() {

		this.service.mergedColumnIndex = 4;
		this.service.mergedRowIndex = 2;

		DecisionTableElement[][] desitionTable = getInitialDecicionTable();

		desitionTable[0][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][0] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[5][8] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][4] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[8][0] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[12][4] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[12][7] = new DecisionTableElement.DecisionTableElementBuilder(3).build();

		desitionTable[0][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][0] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[5][8] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][4] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[8][0] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[12][4] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[12][7] = new DecisionTableElement.DecisionTableElementBuilder(3).build();

		MembershipModel[] columnValues = new MembershipModel[13];
		MembershipModel[] rowValues = new MembershipModel[9];

		columnValues[0] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 22.5, 30, 1).build();
		columnValues[1] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 22.5, 30, 1).build();
		columnValues[2] = new MembershipModel.MembershipModelBuilder(25, 30, 35, 0).build();
		columnValues[3] = new MembershipModel.MembershipModelBuilder(30, 37.5, 45, 2).build();
		columnValues[4] = new MembershipModel.MembershipModelBuilder(30, 37.5, 45, 2).build();
		columnValues[5] = new MembershipModel.MembershipModelBuilder(40, 45, 50, 0).build();
		columnValues[6] = new MembershipModel.MembershipModelBuilder(45, 50, 55, 0).build();
		columnValues[7] = new MembershipModel.MembershipModelBuilder(50, 55, 60, 0).build();
		columnValues[8] = new MembershipModel.MembershipModelBuilder(55, 60, 65, 0).build();
		columnValues[9] = new MembershipModel.MembershipModelBuilder(60, 70, 80, 3).build();
		columnValues[10] = new MembershipModel.MembershipModelBuilder(60, 70, 80, 3).build();
		columnValues[11] = new MembershipModel.MembershipModelBuilder(60, 70, 80, 3).build();
		columnValues[12] = new MembershipModel.MembershipModelBuilder(75, 80, Double.MAX_VALUE, 0).build();

		rowValues[0] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 10, 15).build();
		rowValues[1] = new MembershipModel.MembershipModelBuilder(10, 20, 30, 1).build();
		rowValues[2] = new MembershipModel.MembershipModelBuilder(10, 20, 30, 1).build();
		rowValues[3] = new MembershipModel.MembershipModelBuilder(10, 20, 30, 1).build();
		rowValues[4] = new MembershipModel.MembershipModelBuilder(25, 30, 35, 0).build();
		rowValues[5] = new MembershipModel.MembershipModelBuilder(30, 37.5, 45, 2).build();
		rowValues[6] = new MembershipModel.MembershipModelBuilder(30, 37.5, 45, 2).build();
		rowValues[7] = new MembershipModel.MembershipModelBuilder(40, 45, 50).build();
		rowValues[8] = new MembershipModel.MembershipModelBuilder(45, 50, Double.MAX_VALUE).build();

		this.service.desitionTable = desitionTable;
		this.service.columnValues = columnValues;
		this.service.rowValues = rowValues;

		this.service.mergeAdjacentColumsForOperation2();

		assertTrue(this.service.columnValues[0].getGoupingNumber() == this.service.columnValues[1].getGoupingNumber());
		assertTrue(this.service.columnValues[0].getGoupingNumber() == 1);
		assertTrue(this.service.columnValues[1].getGoupingNumber() == 1);

		assertTrue(this.service.columnValues[0].getGoupingNumber() == this.service.columnValues[2].getGoupingNumber());
		assertTrue(this.service.columnValues[2].getGoupingNumber() == 1);

		assertTrue(this.service.columnValues[3].getGoupingNumber() == this.service.columnValues[4].getGoupingNumber());
		assertTrue(this.service.columnValues[3].getGoupingNumber() == 2);
		assertTrue(this.service.columnValues[4].getGoupingNumber() == 2);

		assertTrue(this.service.columnValues[5].getGoupingNumber() == this.service.columnValues[6].getGoupingNumber());
		assertTrue(this.service.columnValues[5].getGoupingNumber() == 4);
		assertTrue(this.service.columnValues[6].getGoupingNumber() == 4);

		assertTrue(this.service.columnValues[7].getGoupingNumber() == 0);
		assertTrue(this.service.columnValues[8].getGoupingNumber() == 0);

		assertTrue(this.service.columnValues[9].getGoupingNumber() == this.service.columnValues[10].getGoupingNumber());
		assertTrue(this.service.columnValues[9].getGoupingNumber() == 3);
		assertTrue(this.service.columnValues[10].getGoupingNumber() == 3);
		assertTrue(this.service.columnValues[9].getGoupingNumber() == this.service.columnValues[11].getGoupingNumber());
		assertTrue(this.service.columnValues[11].getGoupingNumber() == 3);

		assertTrue(this.service.columnValues[12].getGoupingNumber() == 0);

	}

	private int[] getInitialColumns() {
		return new int[13];
	}

	private int[] getInitialRows() {
		return new int[9];
	}

	private DecisionTableElement[][] getInitialDecicionTable() {
		DecisionTableElement[][] desitionTable = new DecisionTableElement[13][9];
		for(int i = 0; i < 13; i++){
			for(int j = 0; j < 9; j++){
				desitionTable[i][j] = new DecisionTableElement.DecisionTableElementBuilder(0).build();
			}
		}
		return desitionTable;
	}

	@Test
	public void mergeAdjacentRowsIfTheyAreSame_NoParam_MergesSameRows() {

		DecisionTableElement[][] desitionTable = getInitialDecicionTable();

		desitionTable[0][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][0] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[5][8] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][4] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[8][0] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[12][4] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[12][7] = new DecisionTableElement.DecisionTableElementBuilder(3).build();

		this.service.desitionTable = desitionTable;
		MembershipModel[] columnValues = new MembershipModel[13];
		MembershipModel[] rowValues = new MembershipModel[9];

		columnValues[0] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 20, 25).build();
		columnValues[1] = new MembershipModel.MembershipModelBuilder(20, 25, 30).build();
		columnValues[2] = new MembershipModel.MembershipModelBuilder(25, 30, 35).build();
		columnValues[3] = new MembershipModel.MembershipModelBuilder(30, 35, 40).build();
		columnValues[4] = new MembershipModel.MembershipModelBuilder(35, 40, 45).build();
		columnValues[5] = new MembershipModel.MembershipModelBuilder(40, 45, 50).build();
		columnValues[6] = new MembershipModel.MembershipModelBuilder(45, 50, 55).build();
		columnValues[7] = new MembershipModel.MembershipModelBuilder(50, 55, 60).build();
		columnValues[8] = new MembershipModel.MembershipModelBuilder(55, 60, 65).build();
		columnValues[9] = new MembershipModel.MembershipModelBuilder(60, 65, 70).build();
		columnValues[10] = new MembershipModel.MembershipModelBuilder(65, 70, 75).build();
		columnValues[11] = new MembershipModel.MembershipModelBuilder(70, 75, 80).build();
		columnValues[12] = new MembershipModel.MembershipModelBuilder(75, 80, Double.MAX_VALUE).build();

		rowValues[0] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 10, 15).build();
		rowValues[1] = new MembershipModel.MembershipModelBuilder(10, 15, 20).build();
		rowValues[2] = new MembershipModel.MembershipModelBuilder(15, 20, 25).build();
		rowValues[3] = new MembershipModel.MembershipModelBuilder(20, 25, 30).build();
		rowValues[4] = new MembershipModel.MembershipModelBuilder(25, 30, 35).build();
		rowValues[5] = new MembershipModel.MembershipModelBuilder(30, 35, 40).build();
		rowValues[6] = new MembershipModel.MembershipModelBuilder(35, 40, 45).build();
		rowValues[7] = new MembershipModel.MembershipModelBuilder(40, 45, 50).build();
		rowValues[8] = new MembershipModel.MembershipModelBuilder(45, 50, Double.MAX_VALUE).build();

		this.service.desitionTable = desitionTable;
		this.service.columnValues = columnValues;
		this.service.rowValues = rowValues;

		this.service.mergeAdjacentRowsIfTheyAreSame();

		assertTrue(this.service.rowValues[0].getGoupingNumber() == 0);

		assertTrue(this.service.rowValues[1].getGoupingNumber() == this.service.rowValues[2].getGoupingNumber());
		assertTrue(this.service.rowValues[1].getGoupingNumber() == 1);
		assertTrue(this.service.rowValues[2].getGoupingNumber() == 1);
		assertTrue(this.service.rowValues[1].getGoupingNumber() == this.service.rowValues[3].getGoupingNumber());
		assertTrue(this.service.rowValues[3].getGoupingNumber() == 1);

		assertTrue(this.service.rowValues[4].getGoupingNumber() == 0);
		assertTrue(this.service.rowValues[5].getGoupingNumber() == 2);
		assertTrue(this.service.rowValues[6].getGoupingNumber() == 2);
		assertTrue(this.service.rowValues[7].getGoupingNumber() == 0);
		assertTrue(this.service.rowValues[8].getGoupingNumber() == 0);
	}

	@Test
	public void mergeAdjacentColumnsIfTheyAreSame_NoParam_MergesSameColumns() {

		DecisionTableElement[][] desitionTable = getInitialDecicionTable();

		desitionTable[0][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[1][4] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[2][0] = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		desitionTable[5][8] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[6][4] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[8][0] = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		desitionTable[12][4] = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		desitionTable[12][7] = new DecisionTableElement.DecisionTableElementBuilder(3).build();

		MembershipModel[] columnValues = new MembershipModel[13];
		MembershipModel[] rowValues = new MembershipModel[9];

		columnValues[0] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 20, 25).build();
		columnValues[1] = new MembershipModel.MembershipModelBuilder(20, 25, 30).build();
		columnValues[2] = new MembershipModel.MembershipModelBuilder(25, 30, 35).build();
		columnValues[3] = new MembershipModel.MembershipModelBuilder(30, 35, 40).build();
		columnValues[4] = new MembershipModel.MembershipModelBuilder(35, 40, 45).build();
		columnValues[5] = new MembershipModel.MembershipModelBuilder(40, 45, 50).build();
		columnValues[6] = new MembershipModel.MembershipModelBuilder(45, 50, 55).build();
		columnValues[7] = new MembershipModel.MembershipModelBuilder(50, 55, 60).build();
		columnValues[8] = new MembershipModel.MembershipModelBuilder(55, 60, 65).build();
		columnValues[9] = new MembershipModel.MembershipModelBuilder(60, 65, 70).build();
		columnValues[10] = new MembershipModel.MembershipModelBuilder(65, 70, 75).build();
		columnValues[11] = new MembershipModel.MembershipModelBuilder(70, 75, 80).build();
		columnValues[12] = new MembershipModel.MembershipModelBuilder(75, 80, Double.MAX_VALUE).build();

		rowValues[0] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 10, 15).build();
		rowValues[1] = new MembershipModel.MembershipModelBuilder(10, 15, 20).build();
		rowValues[2] = new MembershipModel.MembershipModelBuilder(15, 20, 25).build();
		rowValues[3] = new MembershipModel.MembershipModelBuilder(20, 25, 30).build();
		rowValues[4] = new MembershipModel.MembershipModelBuilder(25, 30, 35).build();
		rowValues[5] = new MembershipModel.MembershipModelBuilder(30, 35, 40).build();
		rowValues[6] = new MembershipModel.MembershipModelBuilder(35, 40, 45).build();
		rowValues[7] = new MembershipModel.MembershipModelBuilder(40, 45, 50).build();
		rowValues[8] = new MembershipModel.MembershipModelBuilder(45, 50, Double.MAX_VALUE).build();

		this.service.desitionTable = desitionTable;
		this.service.columnValues = columnValues;
		this.service.rowValues = rowValues;

		this.service.mergeAdjacentColumnsIfTheyAreSame();

		assertTrue(this.service.columnValues[0].getGoupingNumber() == this.service.columnValues[1].getGoupingNumber());
		assertTrue(this.service.columnValues[0].getGoupingNumber() == 1);
		assertTrue(this.service.columnValues[1].getGoupingNumber() == 1);

		assertTrue(this.service.columnValues[2].getGoupingNumber() == 0);

		assertTrue(this.service.columnValues[3].getGoupingNumber() == this.service.columnValues[4].getGoupingNumber());
		assertTrue(this.service.columnValues[3].getGoupingNumber() == 2);
		assertTrue(this.service.columnValues[4].getGoupingNumber() == 2);

		assertTrue(this.service.columnValues[5].getGoupingNumber() == 0);
		assertTrue(this.service.columnValues[6].getGoupingNumber() == 0);
		assertTrue(this.service.columnValues[7].getGoupingNumber() == 0);
		assertTrue(this.service.columnValues[8].getGoupingNumber() == 0);

		assertTrue(this.service.columnValues[9].getGoupingNumber() == this.service.columnValues[10].getGoupingNumber());
		assertTrue(this.service.columnValues[9].getGoupingNumber() == 3);
		assertTrue(this.service.columnValues[10].getGoupingNumber() == 3);
		assertTrue(this.service.columnValues[9].getGoupingNumber() == this.service.columnValues[11].getGoupingNumber());
		assertTrue(this.service.columnValues[11].getGoupingNumber() == 3);

		assertTrue(this.service.columnValues[12].getGoupingNumber() == 0);

	}

	@Test
	public void findSmallestPredefinedUnitForAge_NoParam_GetSmallestPreDefinedUnit() {
		double smallestPredefinedUnit = service.findAndSetSmallestPredefinedUnitForAge();
		assertTrue(smallestPredefinedUnit != Double.MAX_VALUE);
		assertTrue(smallestPredefinedUnit > 0);
		assertTrue(smallestPredefinedUnit == service.smallesetPredefinedUnitForAge);
	}

	@Test
	public void findSmallestPredefinedUnitForProperty_NoParam_GetSmallestPreDefinedUnit() {
		double smallestPredefinedUnit = service.findAndSetSmallestPredefinedUnitForProperty();
		assertTrue(smallestPredefinedUnit != Double.MAX_VALUE);
		assertTrue(smallestPredefinedUnit > 0);
		assertTrue(smallestPredefinedUnit == service.smallesetPredefinedUnitForProperty);
	}

	@Test
	public void constructInitialDecisionTable_NoParam_ConstructsInitialDecisionTable() {
		service.findAndSetSmallestPredefinedUnitForAge();
		service.findAndSetSmallestPredefinedUnitForProperty();

		this.service.constructInitialDecisionTable();
		assertTrue(service.desitionTable != null);
		assertTrue(service.desitionTable[0].length == 5);
		assertTrue(service.desitionTable.length == 13);

		assertTrue(service.desitionTable[0][4] != null);
		assertTrue(service.desitionTable[1][4] != null);
		assertTrue(service.desitionTable[2][0] != null);

		assertTrue(service.desitionTable[2][0].getGroup() == 1);

		assertTrue(service.desitionTable[8][0] != null);

		assertTrue(service.desitionTable[8][0].getGroup() == 2);


	}

}
