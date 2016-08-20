package com.fuzzy.stocks.service;

import java.util.List;

import com.fuzzy.stocks.model.FuzzyData;

public interface FuzzyMembershipService {

	boolean sortRawData();

	boolean calculateStandartDerivationOfFees();

	boolean prepareDifferenceSequence();

	boolean groupDataBasedOnSimilarities();

	boolean calculateCenterPointB();

	boolean calculateSimilarities();

	boolean findTheMembershipValues();

	boolean calculateLeftCornerPointA();

	boolean calculateRigthCornerPointC();

	double calculateCentralPoint(List<FuzzyData> sameGroupData, List<Double> sameGroupSimilarity);

}
