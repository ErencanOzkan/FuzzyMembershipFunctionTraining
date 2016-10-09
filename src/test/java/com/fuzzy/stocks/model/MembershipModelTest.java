package com.fuzzy.stocks.model;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;

import com.fuzzy.stocks.model.MembershipModel;
import com.fuzzy.stocks.service.impl.FuzzyInferenceServiceImpl;

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

		List<MembershipModel> rules = columnValues[0].getMembershipRules(columnValues);
		assertTrue(rules != null);
		assertTrue(rules.size() == 3);

		logRules(rules);

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

		List<MembershipModel> rules = rowValues[0].getMembershipRules(rowValues);

		assertTrue(rules != null);
		assertTrue(rules.size() == 1);

		logRules(rules);

	}

	private void logRules(List<MembershipModel> rules) {
		this.LOGGER.debug("Rules -> ");

		for(MembershipModel rule : rules){
			this.LOGGER.debug(rule.toString());
		}
	}

}
