package com.fuzzy.stocks.enums;

public enum FuzzyMembershipCalculationStatusEnum {
	NONE(0l),
	DATA_IS_SET(1l),
	DATA_IS_SORTED(2l),
	STANDART_DERIVATION_IS_CALCULATED(3l),
	DIFFERENCES_SEQUENCE_PREPARED(4l),
	SIMILARITIES_SEQUENCE_PREPARED(5l),
	DATA_IS_GROUPED_BASED_ON_SIMILARITY(6l),
	CENTER_POINT_B_IS_CALCULATED(7l);

	private long statusStepOrder;

	FuzzyMembershipCalculationStatusEnum(long statusStepOrder) {
		this.statusStepOrder = statusStepOrder;
	}

}
