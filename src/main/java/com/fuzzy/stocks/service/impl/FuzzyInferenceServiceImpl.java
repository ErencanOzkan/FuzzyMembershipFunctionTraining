package com.fuzzy.stocks.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.fuzzy.stocks.model.MembershipModel;
import com.fuzzy.stocks.service.FuzzyInferenceService;

public class FuzzyInferenceServiceImpl implements FuzzyInferenceService {

	Logger LOGGER = org.slf4j.LoggerFactory.getLogger(FuzzyInferenceServiceImpl.class);

	List<MembershipModel> membershipRules;

	private FuzzyInferenceServiceImpl(List<MembershipModel> data) {
		this.membershipRules = data;
	}

	public static class FuzzyInferenceServiceBuilder {

		List<MembershipModel> membershipRules;

		public FuzzyInferenceServiceBuilder(List<MembershipModel> data) {
			if(data == null){
				this.membershipRules = new ArrayList<MembershipModel>();
			}
			this.membershipRules = data;
		}

		public FuzzyInferenceService build() {
			FuzzyInferenceService service = new FuzzyInferenceServiceImpl(this.membershipRules);
			return service;

		}
	}
}
