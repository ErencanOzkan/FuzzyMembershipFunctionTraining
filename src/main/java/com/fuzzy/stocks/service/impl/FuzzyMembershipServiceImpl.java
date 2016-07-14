package com.fuzzy.stocks.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.fuzzy.stocks.model.FuzzyData;
import com.fuzzy.stocks.service.FuzzyMembershipService;

public class FuzzyMembershipServiceImpl implements FuzzyMembershipService {

	final double controlParameter = 4;
	double standartDerivation;
	List<Double> diffSequence = new ArrayList<Double>();
	List<Double> similarities = new ArrayList<Double>();
	List<FuzzyData> data;

	private FuzzyMembershipServiceImpl() {

	}
	
	public class FuzzyMembershipServiceBuilder{
		
		private List<FuzzyData> data;
		
		public FuzzyMembershipServiceBuilder(List<FuzzyData> data)
		{
			this.data = data;
		}
		public FuzzyMembershipService build()
		{
			FuzzyMembershipService service = new FuzzyMembershipServiceImpl();
			
			return service;
		}
	}
}
