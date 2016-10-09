package com.fuzzy.stocks.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fuzzy.stocks.model.MembershipModel;

public class FuzzyInferenceServiceTest {

	private FuzzyInferenceServiceImpl service;

	@Before
	public void initializeTest() {

		MembershipModel model1ForInput1 = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35,1).build();
		MembershipModel model2ForInput1 = new MembershipModel.MembershipModelBuilder(30, 54.166666666666664, 80,2).build();
		MembershipModel model3ForInput1 = new MembershipModel.MembershipModelBuilder(60, 80, Double.MAX_VALUE,3).build();

		MembershipModel model1ForInput2 = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE,1).build();

		List<MembershipModel> input1 = new ArrayList<MembershipModel>();
		List<MembershipModel> input2 = new ArrayList<MembershipModel>();

		input1.add(model1ForInput1);
		input1.add(model2ForInput1);
		input1.add(model3ForInput1);
		input2.add(model1ForInput2);

		service = (FuzzyInferenceServiceImpl) new FuzzyInferenceServiceImpl.FuzzyInferenceServiceBuilder(input1, input2).build();
	}

	@Test
	public void calculateInteferenceValue_Rules_InfereceValue() {
		double inferenceValue = service.calculateInteferenceValue(20,30);
	}

}
