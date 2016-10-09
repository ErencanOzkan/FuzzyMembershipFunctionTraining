package com.fuzzy.stocks.util;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.fuzzy.stocks.model.FuzzyData;

public class FuzzyMembershipDataReaderTest {

	@Test
	public void readFuzzyDataTest_Json_ListOfFuzzyData() {
		FuzzyMembershipDataReader reader = new FuzzyMembershipDataReader();
		List<FuzzyData> fuzzyData = reader.readFuzzyData();
		
		assertTrue(fuzzyData != null);
		assertTrue(fuzzyData.size() > 0);
	}
}
