package com.fuzzy.stocks.util;

import java.util.List;

import com.fuzzy.stocks.enums.FuzzyMembershipCalculationStatusEnum;
import com.fuzzy.stocks.model.FuzzyData;

public class FuzzyDataUtil {

	public static boolean setFuzzyDataStatus(List<FuzzyData> data, FuzzyMembershipCalculationStatusEnum status) {
		int numberOfNodesWhoseStatusSet = 0;
		if(data != null && data.size() > 0){
			for(FuzzyData d : data){
				d.setStatus(status);
				numberOfNodesWhoseStatusSet++;
			}
		}

		return numberOfNodesWhoseStatusSet > 0 ? true : false;
	}

}
