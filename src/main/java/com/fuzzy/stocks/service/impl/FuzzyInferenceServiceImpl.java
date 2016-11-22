package com.fuzzy.stocks.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.fuzzy.stocks.model.MembershipModel;
import com.fuzzy.stocks.service.FuzzyInferenceService;

public class FuzzyInferenceServiceImpl implements FuzzyInferenceService {

	Logger LOGGER = org.slf4j.LoggerFactory.getLogger(FuzzyInferenceServiceImpl.class);

	List<MembershipModel> membershipRulesForInput1;
	List<MembershipModel> membershipRulesForInput2;
	List<Double> centralPoints;

	private FuzzyInferenceServiceImpl(List<MembershipModel> input1, List<MembershipModel> input2, List<Double> centralPoints) {
		this.membershipRulesForInput1 = input1;
		this.membershipRulesForInput2 = input2;
		this.centralPoints = centralPoints;
	}

	public static class FuzzyInferenceServiceBuilder {

		List<MembershipModel> membershipRulesForInput1;
		List<MembershipModel> membershipRulesForInput2;
		List<Double> centralPoints;

		public FuzzyInferenceServiceBuilder(List<MembershipModel> input1, List<MembershipModel> input2, List<Double> centralPoints) {
			if(input1 == null){
				this.membershipRulesForInput1 = new ArrayList<MembershipModel>();
			}
			if(input2 == null){
				this.membershipRulesForInput2 = new ArrayList<MembershipModel>();
			}
			if(centralPoints == null){
				this.centralPoints = new ArrayList<Double>();
			}
			this.membershipRulesForInput1 = input1;
			this.membershipRulesForInput2 = input2;
			this.centralPoints = centralPoints;
		}

		public FuzzyInferenceService build() {
			FuzzyInferenceService service = new FuzzyInferenceServiceImpl(this.membershipRulesForInput1, this.membershipRulesForInput2,centralPoints);
			return service;

		}
	}

	@Override
	public double calculateInteferenceValue(double value1, double value2) {

		double result = 0;
		List<MembershipModel> membershipCaluesForInput1 = MembershipModel.calculateMembershipValues(membershipRulesForInput1, value1);
		List<MembershipModel> membershipCaluesForInput2 = MembershipModel.calculateMembershipValues(membershipRulesForInput2, value2);

		List<MembershipModel> results = MembershipModel.getRuleWhichHasMinimumMembershipValue(membershipCaluesForInput1, membershipCaluesForInput2);

		result = MembershipModel.caculateResultingValue(results,centralPoints);

		return result;

	}

}
