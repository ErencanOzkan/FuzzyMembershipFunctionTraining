package com.fuzzy.stocks.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fuzzy.stocks.enums.FuzzyMembershipCalculationStatusEnum;
import com.fuzzy.stocks.model.FuzzyData;

public class FuzzyMembershipServiceImplTest {

	private FuzzyMembershipServiceImpl service;

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
		data.add(fuzzyData1);
		data.add(fuzzyData2);
		data.add(fuzzyData3);
		data.add(fuzzyData4);
		data.add(fuzzyData5);
		data.add(fuzzyData6);
		data.add(fuzzyData7);
		data.add(fuzzyData8);

		service = (FuzzyMembershipServiceImpl) new FuzzyMembershipServiceImpl.FuzzyMembershipServiceBuilder(data).build();
		assertTrue(service.data != null);
	}

	@Test
	public void calculateSimilarities_NoParams_StatusReturn() {

		boolean methodStatus = service.sortRawData();
		assertTrue(methodStatus);

		methodStatus = service.prepareDifferenceSequence();
		assertTrue(methodStatus);

		methodStatus = service.calculateStandartDerivationOfFees();
		assertTrue(methodStatus);

		methodStatus = service.calculateSimilarities();
		assertTrue(methodStatus);

	}

	@Test
	public void calculateSimilarities_WrongStatus_StatusReturn() {
		service.calculationStatus = FuzzyMembershipCalculationStatusEnum.NONE;
		boolean methodStatus = service.calculateSimilarities();
		assertFalse(methodStatus);

	}

	@Test
	public void calculateSimilarities_AllreadySorted_StatusReturn() {
		service.calculationStatus = FuzzyMembershipCalculationStatusEnum.SIMILARITIES_SEQUENCE_PREPARED;
		boolean methodStatus = service.calculateSimilarities();
		assertTrue(methodStatus);
	}

	@Test
	public void sortRawData_WrongStatus_StatusReturn() {
		service.calculationStatus = FuzzyMembershipCalculationStatusEnum.NONE;
		boolean methodStatus = service.sortRawData();
		assertFalse(methodStatus);

	}

	@Test
	public void sortRawData_AllreadySorted_StatusReturn() {
		service.calculationStatus = FuzzyMembershipCalculationStatusEnum.DATA_IS_SET;
		boolean methodStatus = service.sortRawData();
		assertTrue(methodStatus);

		methodStatus = service.sortRawData();
		assertTrue(methodStatus);
	}

	@Test
	public void sortRawData_NoParams_StatusReturn() {

		service.calculationStatus = FuzzyMembershipCalculationStatusEnum.DATA_IS_SET;
		boolean methodStatus = service.sortRawData();
		assertTrue(methodStatus);
		for(int i = 1; i < service.data.size(); i++){
			double previousInsuranceFee = service.data.get(i - 1).getInsuranceFee();
			double currentInsuranceFee = service.data.get(i).getInsuranceFee();
			assertTrue(currentInsuranceFee >= previousInsuranceFee);
		}

	}

	@Test
	public void prepareDifferenceSequence_WrongStatus_StatusReturn() {
		service.calculationStatus = FuzzyMembershipCalculationStatusEnum.NONE;
		boolean methodStatus = service.prepareDifferenceSequence();
		assertFalse(methodStatus);
	}

	@Test
	public void prepareDifferenceSequence_AllreadyCalculated_StatusReturn() {
		service.calculationStatus = FuzzyMembershipCalculationStatusEnum.DATA_IS_SORTED;
		boolean methodStatus = service.prepareDifferenceSequence();
		assertTrue(methodStatus);

		methodStatus = service.prepareDifferenceSequence();
		assertTrue(methodStatus);
	}

	@Test
	public void prepareDifferenceSequence_NoParams_StatusReturn() {
		service.calculationStatus = FuzzyMembershipCalculationStatusEnum.DATA_IS_SORTED;
		boolean methodStatus = service.prepareDifferenceSequence();
		assertTrue(methodStatus);

		for(int i = 1; i < service.data.size(); i++){
			double previousInsuranceFee = service.data.get(i - 1).getInsuranceFee();
			double currentInsuranceFee = service.data.get(i).getInsuranceFee();
			assertTrue((currentInsuranceFee - previousInsuranceFee) == service.differenceSequence.get(i - 1));
		}
	}

	@Test
	public void calculateStandartDerivationOfFees_WrongStatus_StatusReturn() {
		service.calculationStatus = FuzzyMembershipCalculationStatusEnum.NONE;
		boolean methodStatus = service.calculateStandartDerivationOfFees();
		assertFalse(methodStatus);
	}

	@Test
	public void calculateStandartDerivationOfFees_AllreadyCalculated_StatusReturn() {
		service.calculationStatus = FuzzyMembershipCalculationStatusEnum.STANDART_DERIVATION_IS_CALCULATED;
		boolean methodStatus = service.calculateStandartDerivationOfFees();
		assertTrue(methodStatus);
	}

	@Test
	public void calculateStandartDerivationOfFees_NoParams_StatusReturn() {
		service.calculationStatus = FuzzyMembershipCalculationStatusEnum.DATA_IS_SORTED;
		boolean methodStatus = service.prepareDifferenceSequence();
		assertTrue(methodStatus);
		methodStatus = service.calculateStandartDerivationOfFees();
		assertTrue(methodStatus);
	}

}
