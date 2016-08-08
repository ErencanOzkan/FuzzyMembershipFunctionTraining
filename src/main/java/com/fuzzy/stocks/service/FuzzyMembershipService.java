package com.fuzzy.stocks.service;

public interface FuzzyMembershipService {

	boolean sortRawData();

	boolean calculateStandartDerivationOfFees();

	boolean prepareDifferenceSequence();

	boolean groupDataBasedOnSimilarities();

	boolean calculateCenterPointB();

	boolean calculateSimilarities();

}
