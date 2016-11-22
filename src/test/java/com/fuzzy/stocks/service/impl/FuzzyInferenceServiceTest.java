package com.fuzzy.stocks.service.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fuzzy.stocks.model.MembershipModel;

public class FuzzyInferenceServiceTest {

	private FuzzyInferenceServiceImpl service;

	@Before
	public void initializeTest() {

		MembershipModel model1ForInput1 = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		MembershipModel model2ForInput1 = new MembershipModel.MembershipModelBuilder(30, 54.166666666666664, 80, 2).build();
		MembershipModel model3ForInput1 = new MembershipModel.MembershipModelBuilder(60, 80, Double.MAX_VALUE, 3).build();

		MembershipModel model1ForInput2 = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 1).build();

		List<MembershipModel> input1 = new ArrayList<MembershipModel>();
		List<MembershipModel> input2 = new ArrayList<MembershipModel>();

		input1.add(model1ForInput1);
		input1.add(model2ForInput1);
		input1.add(model3ForInput1);
		input2.add(model1ForInput2);
		
		
		List<Double> centralPoints = new ArrayList<Double>();
		centralPoints.add(2100d);
		centralPoints.add(2600d);
		centralPoints.add(3250d);

		service = (FuzzyInferenceServiceImpl) new FuzzyInferenceServiceImpl.FuzzyInferenceServiceBuilder(input1, input2,centralPoints).build();
	}

	@Test
	public void calculateInteferenceValue_Rules_InfereceValue() {
		double inferenceValue = service.calculateInteferenceValue(20, 30);
		assertTrue(inferenceValue == 2100);
		
		inferenceValue = service.calculateInteferenceValue(25, 30);
		assertTrue(inferenceValue == 2100);
		
		inferenceValue = service.calculateInteferenceValue(30, 10);
//		assertTrue(inferenceValue == 2185.704);
		
		inferenceValue = service.calculateInteferenceValue(45, 50);
//		assertTrue(inferenceValue == 2442.818d);
		
		inferenceValue = service.calculateInteferenceValue(50, 30);
//		assertTrue(inferenceValue == 2528.522d);
		
		inferenceValue = service.calculateInteferenceValue(60, 10);
//		assertTrue(inferenceValue == 2746.709d);
		
		inferenceValue = service.calculateInteferenceValue(80, 30);
//		assertTrue(inferenceValue == 3250d);
		
		inferenceValue = service.calculateInteferenceValue(80, 30);
//		assertTrue(inferenceValue == 3250d);
		
	}

	

}
