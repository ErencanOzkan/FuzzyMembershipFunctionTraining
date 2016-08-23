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

	public static double findMinimumAge(List<FuzzyData> data) {
		double minimumAge = Double.MAX_VALUE;
		if(data != null && data.size() > 0){
			for(FuzzyData d : data){
				if(d.getAge() < minimumAge){
					minimumAge = d.getAge();
				}
			}
		}
		return minimumAge;
	}

	public static double findMaximumAge(List<FuzzyData> data) {
		double maximumAge = (-1)*Double.MAX_VALUE;
		if(data != null && data.size() > 0){
			for(FuzzyData d : data){
				if(d.getAge() > maximumAge){
					maximumAge = d.getAge();
				}
			}
		}
		return maximumAge;
	}
	
	
	public static double findMinimumProperty(List<FuzzyData> data) {
		double minimumProperty = Double.MAX_VALUE;
		if(data != null && data.size() > 0){
			for(FuzzyData d : data){
				if(d.getProperty() < minimumProperty){
					minimumProperty = d.getProperty();
				}
			}
		}
		return minimumProperty;
	}

	public static double findMaximumProperty(List<FuzzyData> data) {
		double maximumProperty = (-1)*Double.MAX_VALUE;
		if(data != null && data.size() > 0){
			for(FuzzyData d : data){
				if(d.getProperty() > maximumProperty){
					maximumProperty = d.getProperty();
				}
			}
		}
		return maximumProperty;
	}

}
