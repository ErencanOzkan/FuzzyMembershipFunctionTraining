package com.fuzzy.stocks.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;

public class MembershipModelTest {

	Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MembershipModelTest.class);

	@Test
	public void getMembershipRulesTest_ColumnValues_MembershipRules() {
		MembershipModel[] columnValues = new MembershipModel[13];

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

		List<MembershipModel> rules = MembershipModel.getMembershipRules(columnValues);
		assertTrue(rules != null);
		assertTrue(rules.size() == 3);

		logRules(rules);

	}

	@Test
	public void isValueMatches_Value_isMatches() {

		MembershipModel model = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		boolean matches = model.isValueMatches(20);
		assertTrue(matches);

		matches = model.isValueMatches(40);
		assertFalse(matches);
	}

	@Test
	public void calculateMembershipValue_Value_MembershipValue() {
		MembershipModel model = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		double membershipValue = model.calculateMembershipValue(20);
		assertTrue(membershipValue == 0.7619047619047619d);
		
		model = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		membershipValue = model.calculateMembershipValue(30);
		assertTrue(membershipValue == 0.5714285714285714d);

		membershipValue = model.calculateMembershipValue(30);
		assertTrue(membershipValue == 0.5714285714285714d);

		model = new MembershipModel.MembershipModelBuilder(60, 80, Double.MAX_VALUE, 5).build();
		membershipValue = model.calculateMembershipValue(70);
		assertTrue(membershipValue == 0.5d);

		membershipValue = model.calculateMembershipValue(90);
		assertTrue(membershipValue == 1d);
	}

	@Test
	public void getMembershipRulesTest_RowValues_MembershipRules() {
		MembershipModel[] rowValues = new MembershipModel[9];

		rowValues[0] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();
		rowValues[1] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();
		rowValues[2] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();
		rowValues[3] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();
		rowValues[4] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();
		rowValues[5] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();
		rowValues[6] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();
		rowValues[7] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();
		rowValues[8] = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 3).build();

		List<MembershipModel> rules = MembershipModel.getMembershipRules(rowValues);

		assertTrue(rules != null);
		assertTrue(rules.size() == 1);

		logRules(rules);

	}

	@Test
	public void getMatchingRules_ListOfRules_MatchingRules() {

		MembershipModel model1ForInput1 = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25, 35, 1).build();
		MembershipModel model2ForInput1 = new MembershipModel.MembershipModelBuilder(30, 54.166666666666664, 80, 2).build();
		MembershipModel model3ForInput1 = new MembershipModel.MembershipModelBuilder(60, 80, Double.MAX_VALUE, 3).build();
		List<MembershipModel> input1 = new ArrayList<MembershipModel>();
		input1.add(model1ForInput1);
		input1.add(model2ForInput1);
		input1.add(model3ForInput1);

		List<MembershipModel> matchingRules = MembershipModel.getMatchingRules(input1, 25);
		assertTrue(matchingRules != null);
		assertTrue(matchingRules.size() == 1);
	}

	@Test
	public void getRuleWhichHasMinimumMembershipValue_ListOfMembershipValues_MinMembershipValue() {
		MembershipModel model1ForInput1 = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25d, 35d, 1, 0.55d).build();
		MembershipModel model2ForInput1 = new MembershipModel.MembershipModelBuilder(30d, 54.166666666666664d, 80d, 2, 0.3d).build();
		List<MembershipModel> input1 = new ArrayList<MembershipModel>();
		input1.add(model1ForInput1);
		input1.add(model2ForInput1);
		
		List<MembershipModel> input2 = new ArrayList<MembershipModel>();
		MembershipModel model1ForInput2 = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 30, Double.MAX_VALUE, 1,1d).build();
		input2.add(model1ForInput2);

		List<MembershipModel> results = MembershipModel.getRuleWhichHasMinimumMembershipValue(input1, input2);
		assertTrue(results != null);
		assertTrue(results.size() > 0);
		assertTrue(results.get(0).membershipValue == 0.55d);
		assertTrue(results.get(1).membershipValue == 0.3d);
	}
	
	@Test
	public void caculateResultingValue_ListOfMembershipValues_ResultingValue()
	{
		
		MembershipModel model1ForInput1 = new MembershipModel.MembershipModelBuilder(Double.MIN_VALUE, 26.25d, 35d, 1, 0.55d).build();
		MembershipModel model2ForInput1 = new MembershipModel.MembershipModelBuilder(30d, 54.166666666666664d, 80d, 2, 0.3d).build();
		model1ForInput1.matchingRuleIndex =0;
		model2ForInput1.matchingRuleIndex =1;
		
		List<MembershipModel> results = new ArrayList<MembershipModel>();
		results.add( model1ForInput1 );
		results.add( model2ForInput1 );
		
		List<Double> centralPoints = new ArrayList<Double>();
		centralPoints.add(2100d);
		centralPoints.add(2600d);
		centralPoints.add(3250d);
		
		double result = MembershipModel.caculateResultingValue(results,centralPoints);
		assertTrue(result == 2276.4705882352937d);
	}

	private void logRules(List<MembershipModel> rules) {
		this.LOGGER.debug("Rules -> ");

		for(MembershipModel rule : rules){
			this.LOGGER.debug(rule.toString());
		}
	}

}
