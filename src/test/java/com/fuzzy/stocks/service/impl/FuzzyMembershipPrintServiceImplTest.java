package com.fuzzy.stocks.service.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fuzzy.stocks.model.DecisionTableElement;
import com.fuzzy.stocks.model.FuzzyData;

public class FuzzyMembershipPrintServiceImplTest {

	List<FuzzyData> data = new ArrayList<FuzzyData>();

	DecisionTableElement[][] desitionTable;

	@Before
	public void initializeTest() {
		FuzzyData fuzzyData1 = new FuzzyData.FuzzyDataBuilder(20d, 30d, 2000d).build();
		FuzzyData fuzzyData2 = new FuzzyData.FuzzyDataBuilder(25d, 30d, 2100d).build();
		FuzzyData fuzzyData3 = new FuzzyData.FuzzyDataBuilder(30d, 10d, 2200d).build();
		FuzzyData fuzzyData4 = new FuzzyData.FuzzyDataBuilder(45d, 50d, 2500d).build();
		FuzzyData fuzzyData5 = new FuzzyData.FuzzyDataBuilder(50d, 30d, 2600d).build();
		FuzzyData fuzzyData6 = new FuzzyData.FuzzyDataBuilder(60d, 10d, 2700d).build();
		FuzzyData fuzzyData7 = new FuzzyData.FuzzyDataBuilder(80d, 30d, 3200d).build();
		FuzzyData fuzzyData8 = new FuzzyData.FuzzyDataBuilder(80d, 40d, 3300d).build();
		data.add(fuzzyData1);
		data.add(fuzzyData2);
		data.add(fuzzyData3);
		data.add(fuzzyData4);
		data.add(fuzzyData5);
		data.add(fuzzyData6);
		data.add(fuzzyData7);
		data.add(fuzzyData8);

		assertTrue(this.data != null);
		assertTrue(this.data.size() > 0);

		desitionTable = new DecisionTableElement[2][2];
		DecisionTableElement element1 = new DecisionTableElement.DecisionTableElementBuilder(1).build();
		DecisionTableElement element2 = new DecisionTableElement.DecisionTableElementBuilder(2).build();
		DecisionTableElement element3 = new DecisionTableElement.DecisionTableElementBuilder(3).build();
		DecisionTableElement element4 = new DecisionTableElement.DecisionTableElementBuilder(4).build();
		desitionTable[0][0] = element1;
		desitionTable[0][1] = element2;
		desitionTable[1][0] = element3;
		desitionTable[1][1] = element4;
	}

	@Test
	public void printFuzzyData_Data_Prints() {
		FuzzyMembershipPrintServiceImpl.printFuzzyData(data);
	}
	
	@Test
	public void printFuzzyData_Null_Prints() {
		FuzzyMembershipPrintServiceImpl.printFuzzyData(null);
	}
	
	@Test
	public void printFuzzyData_EmptyData_Prints() {
		List<FuzzyData> data2 = new ArrayList<FuzzyData>();
		FuzzyMembershipPrintServiceImpl.printFuzzyData(data2);
	}

	@Test
	public void printDecitionTable_Data_Prints() {
		FuzzyMembershipPrintServiceImpl.printDecitionTable(desitionTable);
	}
	@Test
	public void printDecitionTable_Null_Prints() {
		FuzzyMembershipPrintServiceImpl.printDecitionTable(null);
	}
	@Test
	public void printDecitionTable_EmptyData_Prints() {
		DecisionTableElement[][]  desitionTable2 = new DecisionTableElement[2][2];
		FuzzyMembershipPrintServiceImpl.printDecitionTable(desitionTable2);
	}
	
	
	@Test
	public void printDecitionTableCalculatedValues_Data_Prints() {
		FuzzyMembershipPrintServiceImpl.printDecitionTableCalculatedValues(desitionTable);
	}
	@Test
	public void printDecitionTableCalculatedValues_Null_Prints() {
		FuzzyMembershipPrintServiceImpl.printDecitionTableCalculatedValues(null);
	}
	@Test
	public void printDecitionTableCalculatedValues_EmptyData_Prints() {
		DecisionTableElement[][]  desitionTable2 = new DecisionTableElement[2][2];
		FuzzyMembershipPrintServiceImpl.printDecitionTableCalculatedValues(desitionTable2);
	}

}
