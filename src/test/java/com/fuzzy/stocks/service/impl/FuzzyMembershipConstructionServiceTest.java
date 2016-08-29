package com.fuzzy.stocks.service.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fuzzy.stocks.enums.FuzzyMembershipCalculationStatusEnum;
import com.fuzzy.stocks.model.FuzzyData;

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
	public void mergeAdjacentColumnsIfTheyAreSame_NoParam_MergesSameColumns()
	{
		this.service.findAndSetSmallestPredefinedUnitForAge();
		this.service.findAndSetSmallestPredefinedUnitForProperty();
		this.service.constructInitialDecisionTable();
		
		this.service.mergeAdjacentColumnsIfTheyAreSame();
		
		assertTrue(this.service.desitionTable[0][0].getCalculatedGroup() == this.service.desitionTable[1][0].getCalculatedGroup());
		assertTrue(this.service.desitionTable[0][1].getCalculatedGroup() == this.service.desitionTable[1][1].getCalculatedGroup());
		assertTrue(this.service.desitionTable[0][2].getCalculatedGroup() == this.service.desitionTable[1][2].getCalculatedGroup());
		assertTrue(this.service.desitionTable[0][3].getCalculatedGroup() == this.service.desitionTable[1][3].getCalculatedGroup());
		assertTrue(this.service.desitionTable[0][4].getCalculatedGroup() == this.service.desitionTable[1][4].getCalculatedGroup());
		assertTrue(this.service.desitionTable[0][5].getCalculatedGroup() == this.service.desitionTable[1][5].getCalculatedGroup());
		assertTrue(this.service.desitionTable[0][6].getCalculatedGroup() == this.service.desitionTable[1][6].getCalculatedGroup());
		assertTrue(this.service.desitionTable[0][7].getCalculatedGroup() == this.service.desitionTable[1][7].getCalculatedGroup());
		assertTrue(this.service.desitionTable[0][8].getCalculatedGroup() == this.service.desitionTable[1][8].getCalculatedGroup());
		
		
		assertTrue(this.service.desitionTable[3][0].getCalculatedGroup() == this.service.desitionTable[4][0].getCalculatedGroup());
		assertTrue(this.service.desitionTable[3][1].getCalculatedGroup() == this.service.desitionTable[4][1].getCalculatedGroup());
		assertTrue(this.service.desitionTable[3][2].getCalculatedGroup() == this.service.desitionTable[4][2].getCalculatedGroup());
		assertTrue(this.service.desitionTable[3][3].getCalculatedGroup() == this.service.desitionTable[4][3].getCalculatedGroup());
		assertTrue(this.service.desitionTable[3][4].getCalculatedGroup() == this.service.desitionTable[4][4].getCalculatedGroup());
		assertTrue(this.service.desitionTable[3][5].getCalculatedGroup() == this.service.desitionTable[4][5].getCalculatedGroup());
		assertTrue(this.service.desitionTable[3][6].getCalculatedGroup() == this.service.desitionTable[4][6].getCalculatedGroup());
		assertTrue(this.service.desitionTable[3][7].getCalculatedGroup() == this.service.desitionTable[4][7].getCalculatedGroup());
		assertTrue(this.service.desitionTable[3][8].getCalculatedGroup() == this.service.desitionTable[4][8].getCalculatedGroup());
		
		
		assertTrue(this.service.desitionTable[9][0].getCalculatedGroup() == this.service.desitionTable[10][0].getCalculatedGroup());
		assertTrue(this.service.desitionTable[9][1].getCalculatedGroup() == this.service.desitionTable[10][1].getCalculatedGroup());
		assertTrue(this.service.desitionTable[9][2].getCalculatedGroup() == this.service.desitionTable[10][2].getCalculatedGroup());
		assertTrue(this.service.desitionTable[9][3].getCalculatedGroup() == this.service.desitionTable[10][3].getCalculatedGroup());
		assertTrue(this.service.desitionTable[9][4].getCalculatedGroup() == this.service.desitionTable[10][4].getCalculatedGroup());
		assertTrue(this.service.desitionTable[9][5].getCalculatedGroup() == this.service.desitionTable[10][5].getCalculatedGroup());
		assertTrue(this.service.desitionTable[9][6].getCalculatedGroup() == this.service.desitionTable[10][6].getCalculatedGroup());
		assertTrue(this.service.desitionTable[9][7].getCalculatedGroup() == this.service.desitionTable[10][7].getCalculatedGroup());
		assertTrue(this.service.desitionTable[9][8].getCalculatedGroup() == this.service.desitionTable[10][8].getCalculatedGroup());
		
		assertTrue(this.service.desitionTable[9][0].getCalculatedGroup() == this.service.desitionTable[11][0].getCalculatedGroup());
		assertTrue(this.service.desitionTable[9][1].getCalculatedGroup() == this.service.desitionTable[11][1].getCalculatedGroup());
		assertTrue(this.service.desitionTable[9][2].getCalculatedGroup() == this.service.desitionTable[11][2].getCalculatedGroup());
		assertTrue(this.service.desitionTable[9][3].getCalculatedGroup() == this.service.desitionTable[11][3].getCalculatedGroup());
		assertTrue(this.service.desitionTable[9][4].getCalculatedGroup() == this.service.desitionTable[11][4].getCalculatedGroup());
		assertTrue(this.service.desitionTable[9][5].getCalculatedGroup() == this.service.desitionTable[11][5].getCalculatedGroup());
		assertTrue(this.service.desitionTable[9][6].getCalculatedGroup() == this.service.desitionTable[11][6].getCalculatedGroup());
		assertTrue(this.service.desitionTable[9][7].getCalculatedGroup() == this.service.desitionTable[11][7].getCalculatedGroup());
		assertTrue(this.service.desitionTable[9][8].getCalculatedGroup() == this.service.desitionTable[11][8].getCalculatedGroup());
		
		
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
		assertTrue(service.desitionTable[0].length == 9);
		assertTrue(service.desitionTable.length == 13);
		
		assertTrue(service.desitionTable[0][4] != null);
		assertTrue(service.desitionTable[1][4] != null);
		assertTrue(service.desitionTable[2][0] != null);
		
		assertTrue(service.desitionTable[0][4].getGroup() == 1);
		assertTrue(service.desitionTable[1][4].getGroup() == 1);
		assertTrue(service.desitionTable[2][0].getGroup() == 1);
		
		assertTrue(service.desitionTable[5][8] != null);
		assertTrue(service.desitionTable[6][4] != null);
		assertTrue(service.desitionTable[8][0] != null);
		
		assertTrue(service.desitionTable[5][8].getGroup() == 2);
		assertTrue(service.desitionTable[6][4].getGroup() == 2);
		assertTrue(service.desitionTable[8][0].getGroup() == 2);
		
		assertTrue(service.desitionTable[12][4] != null);		
		assertTrue(service.desitionTable[12][6] != null);
		
		assertTrue(service.desitionTable[12][4].getGroup() == 3);		
		assertTrue(service.desitionTable[12][6].getGroup() == 3);
		
	}
}
