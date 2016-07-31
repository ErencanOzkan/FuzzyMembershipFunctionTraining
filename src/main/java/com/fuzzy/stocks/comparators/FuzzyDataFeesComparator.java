package com.fuzzy.stocks.comparators;

import java.util.Comparator;

import com.fuzzy.stocks.model.FuzzyData;

public class FuzzyDataFeesComparator implements Comparator<FuzzyData> {

	@Override
	public int compare(FuzzyData fuzzyData1, FuzzyData fuzzyData2) {

		return (int) (fuzzyData1.getInsuranceFee() - fuzzyData2.getInsuranceFee());
	}

}
