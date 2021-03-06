package com.fuzzy.stocks.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.fuzzy.stocks.enums.FuzzyMembershipCalculationStatusEnum;
import com.fuzzy.stocks.model.FuzzyData;

@RunWith(MockitoJUnitRunner.class)
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
	public void findTheMembershipValues_NoParams_StatusReturn() {
		boolean methodStatus = service.sortRawData();
		assertTrue(methodStatus);

		methodStatus = service.prepareDifferenceSequence();
		assertTrue(methodStatus);

		methodStatus = service.calculateStandartDerivationOfFees();
		assertTrue(methodStatus);

		methodStatus = service.calculateSimilarities();
		assertTrue(methodStatus);

		methodStatus = service.groupDataBasedOnSimilarities();
		assertTrue(methodStatus);

		methodStatus = service.calculateCenterPointB();
		assertTrue(methodStatus);

		methodStatus = service.calculateLeftCornerPointA();
		assertTrue(methodStatus);

		//Mock above methods
		methodStatus = service.calculateRigthCornerPointC();
		assertTrue(methodStatus);

		//Mock above methods
		methodStatus = service.findTheMembershipValues();
		assertTrue(methodStatus);

	}

	@Test
	public void findTheMembershipValues_WrongStatus_StatusReturn() {
		// use mockito here to mock calculateSimilarities

		service.calculationStatus = FuzzyMembershipCalculationStatusEnum.NONE;
		boolean methodStatus = service.findTheMembershipValues();
		assertFalse(methodStatus);

	}

	@Test
	public void findTheMembershipValues_AllreadyCalculated_StatusReturn() {
		// use mockito here to mock calculateSimilarities

		service.calculationStatus = FuzzyMembershipCalculationStatusEnum.MEMBERSHIP_VALUES_ARE_CALCULATED;
		boolean methodStatus = service.findTheMembershipValues();
		assertTrue(methodStatus);
	}

	@Test
	public void getSameGroupData_FuzzyData_GroupedData() {

		boolean methodStatus = service.sortRawData();
		assertTrue(methodStatus);

		methodStatus = service.prepareDifferenceSequence();
		assertTrue(methodStatus);

		methodStatus = service.calculateStandartDerivationOfFees();
		assertTrue(methodStatus);

		methodStatus = service.calculateSimilarities();
		assertTrue(methodStatus);

		methodStatus = service.groupDataBasedOnSimilarities();
		assertTrue(methodStatus);

		List<FuzzyData> groupedData = service.getSameGroupData(1);
		assertTrue(groupedData != null);
		assertTrue(groupedData.size() > 0);
		checkAllDataHaveSamaGroup(groupedData, 1);

		groupedData = service.getSameGroupData(2);
		assertTrue(groupedData != null);
		assertTrue(groupedData.size() > 0);
		checkAllDataHaveSamaGroup(groupedData, 2);

		groupedData = service.getSameGroupData(3);
		assertTrue(groupedData != null);
		assertTrue(groupedData.size() > 0);
		checkAllDataHaveSamaGroup(groupedData, 3);
	}

	private void checkAllDataHaveSamaGroup(List<FuzzyData> groupedData, int group) {
		for(FuzzyData d : groupedData){
			assertTrue(d.getGroup() == group);
		}
	}

	@Test
	public void calculateRigthCornerPointC_NoParams_StatusReturn() {
		boolean methodStatus = service.sortRawData();
		assertTrue(methodStatus);

		methodStatus = service.prepareDifferenceSequence();
		assertTrue(methodStatus);

		methodStatus = service.calculateStandartDerivationOfFees();
		assertTrue(methodStatus);

		methodStatus = service.calculateSimilarities();
		assertTrue(methodStatus);

		methodStatus = service.groupDataBasedOnSimilarities();
		assertTrue(methodStatus);

		methodStatus = service.calculateCenterPointB();
		assertTrue(methodStatus);

		methodStatus = service.calculateLeftCornerPointA();
		assertTrue(methodStatus);

		//Mock above methods
		methodStatus = service.calculateRigthCornerPointC();
		assertTrue(methodStatus);

	}

	@Test
	public void calculateRigthCornerPointC_WrongStatus_StatusReturn() {
		// use mockito here to mock calculateSimilarities

		service.calculationStatus = FuzzyMembershipCalculationStatusEnum.NONE;
		boolean methodStatus = service.calculateRigthCornerPointC();
		assertFalse(methodStatus);

	}

	@Test
	public void calculateRigthCornerPointC_AllreadyCalculated_StatusReturn() {
		// use mockito here to mock calculateSimilarities

		service.calculationStatus = FuzzyMembershipCalculationStatusEnum.RIGTH_CORNER_C_IS_CALCULATED;
		boolean methodStatus = service.calculateRigthCornerPointC();
		assertTrue(methodStatus);
	}

	@Test
	public void calculateLeftCornerPointA_NoParams_StatusReturn() {
		boolean methodStatus = service.sortRawData();
		assertTrue(methodStatus);

		methodStatus = service.prepareDifferenceSequence();
		assertTrue(methodStatus);

		methodStatus = service.calculateStandartDerivationOfFees();
		assertTrue(methodStatus);

		methodStatus = service.calculateSimilarities();
		assertTrue(methodStatus);

		methodStatus = service.groupDataBasedOnSimilarities();
		assertTrue(methodStatus);

		methodStatus = service.calculateCenterPointB();
		assertTrue(methodStatus);

		//Mock above methods
		methodStatus = service.calculateLeftCornerPointA();
		assertTrue(methodStatus);
	}

	@Test
	public void calculateLeftCornerPointA_WrongStatus_StatusReturn() {
		// use mockito here to mock calculateSimilarities

		service.calculationStatus = FuzzyMembershipCalculationStatusEnum.NONE;
		boolean methodStatus = service.calculateLeftCornerPointA();
		assertFalse(methodStatus);

	}

	@Test
	public void calculateLeftCornerPointA_AllreadyCalculated_StatusReturn() {
		// use mockito here to mock calculateSimilarities

		service.calculationStatus = FuzzyMembershipCalculationStatusEnum.LEFT_CORNER_A_IS_CALCULATED;
		boolean methodStatus = service.calculateLeftCornerPointA();
		assertTrue(methodStatus);
	}

	@Test
	public void calculateCentralPointB_InitialParams_CentralPoint() {
		List<FuzzyData> data = new ArrayList<FuzzyData>();
		FuzzyData fuzzyData1 = new FuzzyData.FuzzyDataBuilder(20d, 30d, 2000d).build();
		FuzzyData fuzzyData2 = new FuzzyData.FuzzyDataBuilder(25d, 30d, 2100d).build();
		FuzzyData fuzzyData3 = new FuzzyData.FuzzyDataBuilder(30d, 10d, 2200d).build();

		data.add(fuzzyData1);
		data.add(fuzzyData2);
		data.add(fuzzyData3);

		List<Double> similarities = new ArrayList<Double>();
		similarities.add(0.83);
		similarities.add(0.83);
		similarities.add(0.14);

		double centralPoint = service.calculateCentralPoint(data, similarities);
		assertTrue(centralPoint == 2100d);

	}

	@Test
	public void calculateCentralPointB_NullParams_Zero() {
		double centralPoint = service.calculateCentralPoint(null, null);
		assertTrue(centralPoint == 0d);
	}

	@Test
	public void calculateCenterPointB_NoParams_StatusReturn() {

		boolean methodStatus = service.sortRawData();
		assertTrue(methodStatus);

		methodStatus = service.prepareDifferenceSequence();
		assertTrue(methodStatus);

		methodStatus = service.calculateStandartDerivationOfFees();
		assertTrue(methodStatus);

		methodStatus = service.calculateSimilarities();
		assertTrue(methodStatus);

		methodStatus = service.groupDataBasedOnSimilarities();
		assertTrue(methodStatus);

		//Mock above methods

		methodStatus = service.calculateCenterPointB();
		assertTrue(methodStatus);

	}

	@Test
	public void calculateCenterPointB_WrongStatus_StatusReturn() {
		// use mockito here to mock calculateSimilarities

		service.calculationStatus = FuzzyMembershipCalculationStatusEnum.NONE;
		boolean methodStatus = service.calculateCenterPointB();
		assertFalse(methodStatus);

	}

	@Test
	public void calculateCenterPointB_AllreadyCalculated_StatusReturn() {
		// use mockito here to mock calculateSimilarities

		service.calculationStatus = FuzzyMembershipCalculationStatusEnum.CENTER_POINT_B_IS_CALCULATED;
		boolean methodStatus = service.calculateCenterPointB();
		assertTrue(methodStatus);
	}

	@Test
	public void groupDataBasedOnSimilarities_NoParams_StatusReturn() {

		boolean methodStatus = service.sortRawData();
		assertTrue(methodStatus);

		methodStatus = service.prepareDifferenceSequence();
		assertTrue(methodStatus);

		methodStatus = service.calculateStandartDerivationOfFees();
		assertTrue(methodStatus);

		methodStatus = service.calculateSimilarities();
		assertTrue(methodStatus);

		methodStatus = service.groupDataBasedOnSimilarities();
		assertTrue(methodStatus);

	}

	@Test
	public void groupDataBasedOnSimilarities_WrongStatus_StatusReturn() {
		// use mockito here to mock calculateSimilarities

		service.calculationStatus = FuzzyMembershipCalculationStatusEnum.NONE;
		boolean methodStatus = service.groupDataBasedOnSimilarities();
		assertFalse(methodStatus);

	}

	@Test
	public void groupDataBasedOnSimilarities_AllreadySorted_StatusReturn() {
		// use mockito here to mock calculateSimilarities

		service.calculationStatus = FuzzyMembershipCalculationStatusEnum.DATA_IS_GROUPED_BASED_ON_SIMILARITY;
		boolean methodStatus = service.groupDataBasedOnSimilarities();
		assertTrue(methodStatus);
	}

	@Test
	public void calculateSimilarities_NoParams_StatusReturn() {

		// use mockito here to mock calculateSimilarities

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
		// use mockito here to mock calculateSimilarities

		service.calculationStatus = FuzzyMembershipCalculationStatusEnum.NONE;
		boolean methodStatus = service.calculateSimilarities();
		assertFalse(methodStatus);

	}

	@Test
	public void calculateSimilarities_AllreadySorted_StatusReturn() {
		// use mockito here to mock calculateSimilarities

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
