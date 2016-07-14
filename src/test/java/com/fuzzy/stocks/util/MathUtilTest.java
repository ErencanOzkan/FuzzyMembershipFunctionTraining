package com.fuzzy.stocks.util;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MathUtilTest {

	@Test
	public void calculateStandardDerivation_ListOfNumbers_Null_NaN() {
		double standartDevation= MathUtil.calculateStandardDerivation(null);
		assertTrue(Double.isNaN(standartDevation));
	}
	
	@Test
	public void calculateStandardDerivation_ListOfNumbers_StandartDerivation() {
		List<Double> data = new ArrayList<Double>();
		double standartDevation = MathUtil.calculateStandardDerivation(data);
		assertTrue(Double.isNaN(standartDevation));
		data.add(2d);
		standartDevation = MathUtil.calculateStandardDerivation(data);
		assertTrue(standartDevation == 0d);
		data.add(5d);
		standartDevation = MathUtil.calculateStandardDerivation(data);
		assertTrue(standartDevation == 1.5d);
	}

	@Test
	public void calculateMean_Null_NaN() {
		double mean = MathUtil.calculateMean(null);
		assertTrue(Double.isNaN(mean));
	}

	@Test
	public void calculateMeanValue_ListOfNumbers_Mean() {
		List<Double> data = new ArrayList<Double>();
		double mean = MathUtil.calculateMean(data);
		assertTrue(Double.isNaN(mean));
		data.add(2d);
		mean = MathUtil.calculateMean(data);
		assertTrue(mean == 2d);
		data.add(5d);
		mean = MathUtil.calculateMean(data);
		assertTrue(mean == 3.5d);
	}

	@Test
	public void calculateVariance_Null_NaN() {
		double variance = MathUtil.calculateVariance(null);
		assertTrue(Double.isNaN(variance));
	}

	@Test
	public void calculateVariance_ListOfNumbers_Variance() {
		List<Double> data = new ArrayList<Double>();
		double variance = MathUtil.calculateVariance(data);
		assertTrue(Double.isNaN(variance));
		data.add(2d);
		variance = MathUtil.calculateVariance(data);
		assertTrue(variance == 0d);
		data.add(5d);
		variance = MathUtil.calculateVariance(data);
		assertTrue(variance == 2.25d);
	}

}
