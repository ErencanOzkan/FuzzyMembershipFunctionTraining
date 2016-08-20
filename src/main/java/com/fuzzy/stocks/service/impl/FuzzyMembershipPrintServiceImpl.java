package com.fuzzy.stocks.service.impl;

import java.util.List;

import com.fuzzy.stocks.model.FuzzyData;
import com.fuzzy.stocks.service.FuzzyMembershipPrintService;

public class FuzzyMembershipPrintServiceImpl implements FuzzyMembershipPrintService {

	public static void printFuzzyData(List<FuzzyData> data) {
		if(data != null){
			for(FuzzyData d : data){
				System.out.println(d);
			}
		}
	}
}
