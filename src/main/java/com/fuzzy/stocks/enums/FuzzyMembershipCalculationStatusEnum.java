package com.fuzzy.stocks.enums;

public enum FuzzyMembershipCalculationStatusEnum {
	NONE(0l),
	DATA_IS_SET(1l),
	DATA_IS_SORTED(2l),
	STANDART_DERIVATION_IS_CALCULATED(3l),
	DIFFERENCES_SEQUENCE_PREPARED(4l),
	SIMILARITIES_SEQUENCE_PREPARED(5l);

	private long statusStepOrder;

	FuzzyMembershipCalculationStatusEnum(long statusStepOrder) {
		this.statusStepOrder = statusStepOrder;
	}

}
