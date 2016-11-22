package com.fuzzy.stocks.service;

import java.util.List;

import com.fuzzy.stocks.model.MembershipModel;

public interface FuzzyMembershipConstructionService {

	void constructInitialDecisionTable();

	void mergeAdjacentColumnsIfTheyAreSame();

	void mergeAdjacentRowsIfTheyAreSame();

	void mergeAdjacentColumsForOperation2();

	void mergeAdjacentRowsForOperation2();

	void mergeRowsForOperation3();

	void mergeColumnsForOperation3();

	void mergeColumnsForOperation4();

	void mergeRowsForOperation4();

	void mergeRowsForOperation5();

	void mergeColumnsForOperation5();

	List<MembershipModel> getAgeMembershipRules();

	List<MembershipModel> getPropertyMembershipRules();

}
