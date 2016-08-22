package com.fuzzy.stocks.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fuzzy.stocks.enums.FuzzyMembershipCalculationStatusEnum;
import com.fuzzy.stocks.model.FuzzyData;

public class FuzzyDataUtilTest {

	List<FuzzyData> data = new ArrayList<FuzzyData>();

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
	}

	@Test
	public void setFuzzyDataStatus_Data_NumberOfStatusSet() {
		boolean success = FuzzyDataUtil.setFuzzyDataStatus(data, FuzzyMembershipCalculationStatusEnum.STANDART_DERIVATION_IS_CALCULATED);
		assertTrue(success);

		if(data != null && data.size() > 0){
			for(FuzzyData d : data){
				assertTrue(d.getStatus().equals(FuzzyMembershipCalculationStatusEnum.STANDART_DERIVATION_IS_CALCULATED));
			}
		}

	}
	
	@Test
	public void setFuzzyDataStatus_Null_NumberOfStatusSet() {
		boolean success = FuzzyDataUtil.setFuzzyDataStatus(null, FuzzyMembershipCalculationStatusEnum.STANDART_DERIVATION_IS_CALCULATED);
		assertFalse(success);

	}
	@Test
	public void setFuzzyDataStatus_EmptyData_NumberOfStatusSet() {
		List<FuzzyData> emptyData = new ArrayList<FuzzyData>();
		boolean success = FuzzyDataUtil.setFuzzyDataStatus(emptyData, FuzzyMembershipCalculationStatusEnum.STANDART_DERIVATION_IS_CALCULATED);
		assertFalse(success);

	}

}
