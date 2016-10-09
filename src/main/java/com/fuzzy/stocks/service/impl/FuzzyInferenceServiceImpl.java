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

	private FuzzyInferenceServiceImpl(List<MembershipModel> input1, List<MembershipModel> input2) {
		this.membershipRulesForInput1 = input1;
		this.membershipRulesForInput2 = input2;
	}

	public static class FuzzyInferenceServiceBuilder {

		List<MembershipModel> membershipRulesForInput1;
		List<MembershipModel> membershipRulesForInput2;

		public FuzzyInferenceServiceBuilder(List<MembershipModel> input1, List<MembershipModel> input2) {
			if(input1 == null){
				this.membershipRulesForInput1 = new ArrayList<MembershipModel>();
			}
			if(input2 == null){
				this.membershipRulesForInput2 = new ArrayList<MembershipModel>();
			}
			this.membershipRulesForInput1 = input1;
			this.membershipRulesForInput2 = input2;
		}

		public FuzzyInferenceService build() {
			FuzzyInferenceService service = new FuzzyInferenceServiceImpl(this.membershipRulesForInput1, this.membershipRulesForInput2);
			return service;

		}
	}

	public double calculateInteferenceValue(double value1, double value2) {
		// TODO Auto-generated method stub
		return 0;
		
	}
}
