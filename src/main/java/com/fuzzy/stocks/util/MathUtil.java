package com.fuzzy.stocks.util;

import java.util.List;

public class MathUtil {

	public static double calculateStandardDerivation(List<Double> data) {
		if(data != null){
			return Math.sqrt(calculateVariance(data));
		} else{
			return Double.NaN;
		}
	}

	public static double calculateMean(List<Double> data) {
		if(data != null){
			double total = 0;

			for(int i = 0; i < data.size(); i++){
				total += data.get(i);
			}
			return total / data.size();
		}

		else{
			return Double.NaN;
		}
	}

	public static double calculateVariance(List<Double> data) {
		if(data != null){
			double mean = calculateMean(data);
			double temp = 0;
			for(double a : data)
				temp += (mean - a) * (mean - a);
			return temp / data.size();
		} else{
			return Double.NaN;
		}
	}
}
