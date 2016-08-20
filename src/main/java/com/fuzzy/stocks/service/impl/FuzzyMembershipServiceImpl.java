package com.fuzzy.stocks.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fuzzy.stocks.comparators.FuzzyDataFeesComparator;
import com.fuzzy.stocks.enums.FuzzyMembershipCalculationStatusEnum;
import com.fuzzy.stocks.model.FuzzyData;
import com.fuzzy.stocks.service.FuzzyMembershipService;
import com.fuzzy.stocks.util.MathUtil;

public class FuzzyMembershipServiceImpl implements FuzzyMembershipService {

	FuzzyMembershipCalculationStatusEnum calculationStatus = FuzzyMembershipCalculationStatusEnum.NONE;;

	final double controlParameter = 4;
	final double cutOfSimilarity = 0.81d;
	int numberOfGroups = 0;

	double standartDerivation;
	List<Double> diffSequence = new ArrayList<Double>();
	List<Double> similarities = new ArrayList<Double>();
	List<FuzzyData> data;
	List<Double> differenceSequence;

	List<Double> centralPoints = new ArrayList<Double>();
	List<Double> leftCornerPoints = new ArrayList<Double>();
	List<Double> rigthCornerPoints = new ArrayList<Double>();

	private FuzzyMembershipServiceImpl() {

	}

	private FuzzyMembershipServiceImpl(List<FuzzyData> data) {
		this.data = data;
		calculationStatus = FuzzyMembershipCalculationStatusEnum.DATA_IS_SET;
	}

	public static class FuzzyMembershipServiceBuilder {

		private List<FuzzyData> data;
		double standartDerivation;

		public FuzzyMembershipServiceBuilder(List<FuzzyData> data) {
			this.data = data;
		}

		public FuzzyMembershipService build() {

			FuzzyMembershipService service = new FuzzyMembershipServiceImpl(data);
			return service;
		}
	}

	@Override
	public boolean sortRawData() {
		if(FuzzyMembershipCalculationStatusEnum.DATA_IS_SORTED.equals(calculationStatus)){
			return true;
		} else if(data != null && FuzzyMembershipCalculationStatusEnum.DATA_IS_SET.equals(calculationStatus)){
			Collections.sort(data, new FuzzyDataFeesComparator());
			calculationStatus = FuzzyMembershipCalculationStatusEnum.DATA_IS_SORTED;
			return true;
		} else{
			return false;
		}
	}

	@Override
	public boolean calculateStandartDerivationOfFees() {
		if(FuzzyMembershipCalculationStatusEnum.STANDART_DERIVATION_IS_CALCULATED.equals(calculationStatus)){
			return true;
		} else if(data != null && differenceSequence != null && FuzzyMembershipCalculationStatusEnum.DIFFERENCES_SEQUENCE_PREPARED.equals(calculationStatus)){
			this.standartDerivation = MathUtil.calculateStandardDerivation(differenceSequence);
			calculationStatus = FuzzyMembershipCalculationStatusEnum.STANDART_DERIVATION_IS_CALCULATED;
			return true;
		} else{
			return false;
		}
	}

	@Override
	public boolean prepareDifferenceSequence() {
		if(FuzzyMembershipCalculationStatusEnum.DIFFERENCES_SEQUENCE_PREPARED.equals(calculationStatus)){
			return true;
		} else if(data != null && FuzzyMembershipCalculationStatusEnum.DATA_IS_SORTED.equals(calculationStatus)){
			differenceSequence = new ArrayList<Double>();
			for(int i = 1; i < data.size(); i++){
				FuzzyData firstItem = data.get(i - 1);
				FuzzyData secondItem = data.get(i);
				double difference = secondItem.getInsuranceFee() - firstItem.getInsuranceFee();
				differenceSequence.add(difference);
				calculationStatus = FuzzyMembershipCalculationStatusEnum.DIFFERENCES_SEQUENCE_PREPARED;
			}
			return true;
		} else{
			return false;
		}
	}

	@Override
	public boolean calculateSimilarities() {
		if(FuzzyMembershipCalculationStatusEnum.SIMILARITIES_SEQUENCE_PREPARED.equals(calculationStatus)){
			return true;
		} else if(data != null && differenceSequence != null && FuzzyMembershipCalculationStatusEnum.STANDART_DERIVATION_IS_CALCULATED.equals(calculationStatus)){
			for(Double diff : differenceSequence){
				Double similarty = 1 - (diff / (this.standartDerivation * this.controlParameter));
				this.similarities.add(similarty);
			}
			calculationStatus = FuzzyMembershipCalculationStatusEnum.SIMILARITIES_SEQUENCE_PREPARED;
			return true;
		}
		return false;
	}

	@Override
	public double calculateCentralPoint(List<FuzzyData> sameGroupData, List<Double> sameGroupSimilarity) {
		double centralPoint = 0d;
		if(sameGroupData != null && sameGroupSimilarity != null && sameGroupData.size() == sameGroupSimilarity.size()){
			double centralPointNumerator = 0;
			double centralPointDenominator = 0;
			for(int j = 0; j < sameGroupData.size(); j++){
				if(j == 0){
					centralPointNumerator += sameGroupData.get(j).getInsuranceFee() * sameGroupSimilarity.get(j);
				} else if(j > 0 && j < (sameGroupData.size() - 1)){
					centralPointNumerator += sameGroupData.get(j).getInsuranceFee() * (((sameGroupSimilarity.get(j) + sameGroupSimilarity.get(j - 1)) / 2));
				} else if(j == (sameGroupData.size() - 1)){
					centralPointNumerator += sameGroupData.get(j).getInsuranceFee() * sameGroupSimilarity.get(j - 1);
				}
			}
			for(int j = 0; j < sameGroupSimilarity.size(); j++){
				if(j == 0){
					centralPointDenominator += sameGroupSimilarity.get(j);
				} else if(j > 0 && j < (sameGroupData.size() - 1)){
					centralPointDenominator += ((sameGroupSimilarity.get(j - 1) + sameGroupSimilarity.get(j)) / 2);
				} else if(j == (sameGroupData.size() - 1)){
					centralPointDenominator += sameGroupSimilarity.get(j - 1);
				}
			}
			centralPoint = centralPointNumerator / centralPointDenominator;
		}

		return centralPoint;
	}

	@Override
	public boolean calculateRigthCornerPointC() {
		if(FuzzyMembershipCalculationStatusEnum.RIGTH_CORNER_C_IS_CALCULATED.equals(calculationStatus)){
			return true;
		} else if(data != null && similarities != null && FuzzyMembershipCalculationStatusEnum.LEFT_CORNER_A_IS_CALCULATED.equals(calculationStatus)){

			int groupingNumber = 1;
			List<FuzzyData> sameGroupData = new ArrayList<FuzzyData>();
			List<Double> sameGroupSimilarity = new ArrayList<Double>();
			for(int i = 0; i < this.data.size(); i++){
				FuzzyData datum = this.data.get(i);
				Double similarity = Double.MAX_VALUE;
				if(i < similarities.size()){
					similarity = this.similarities.get(i);
				}
				if(groupingNumber == datum.getGroup()){
					sameGroupData.add(datum);
					sameGroupSimilarity.add(similarity);
				} else{
					sameGroupSimilarity.remove(sameGroupSimilarity.size() - 1);
					double bj = centralPoints.get(groupingNumber - 1);
					double yi = findMaximumData(sameGroupData);
					double myi = findMinimumSimilarity(sameGroupSimilarity);
					double rigthCornerPointC = bj + ((yi - bj) / (1 - myi));
					this.rigthCornerPoints.add(rigthCornerPointC);

					sameGroupData.clear();
					sameGroupSimilarity.clear();
					sameGroupData.add(datum);
					sameGroupSimilarity.add(similarity);
					groupingNumber++;
				}
			}

			sameGroupSimilarity.remove(sameGroupSimilarity.size() - 1);
			double bj = centralPoints.get(groupingNumber - 1);
			double yi = findMaximumData(sameGroupData);
			double myi = findMinimumSimilarity(sameGroupSimilarity);
			double rigthCornerPointC = bj + ((yi - bj) / (1 - myi));
			this.rigthCornerPoints.add(rigthCornerPointC);

			calculationStatus = FuzzyMembershipCalculationStatusEnum.RIGTH_CORNER_C_IS_CALCULATED;

			return true;
		}
		return false;
	}

	private double findMaximumData(List<FuzzyData> sameGroupData) {
		Double maximumFee = Double.MAX_VALUE * (-1);
		for(FuzzyData fee : sameGroupData){
			if(maximumFee < fee.getInsuranceFee()){
				maximumFee = fee.getInsuranceFee();
			}
		}
		return maximumFee;
	}

	@Override
	public boolean calculateLeftCornerPointA() {
		if(FuzzyMembershipCalculationStatusEnum.LEFT_CORNER_A_IS_CALCULATED.equals(calculationStatus)){
			return true;
		} else if(data != null && similarities != null && FuzzyMembershipCalculationStatusEnum.CENTER_POINT_B_IS_CALCULATED.equals(calculationStatus)){

			int groupingNumber = 1;
			List<FuzzyData> sameGroupData = new ArrayList<FuzzyData>();
			List<Double> sameGroupSimilarity = new ArrayList<Double>();
			int smallestSimilarityIndex = 0;
			for(int i = 0; i < this.data.size(); i++){
				FuzzyData datum = this.data.get(i);
				Double similarity = Double.MAX_VALUE;
				if(i < similarities.size()){
					similarity = this.similarities.get(i);
				}
				if(groupingNumber == datum.getGroup()){
					sameGroupData.add(datum);
					sameGroupSimilarity.add(similarity);
				} else{
					sameGroupSimilarity.remove(sameGroupSimilarity.size() - 1);
					double bj = centralPoints.get(groupingNumber - 1);
					double yi = findMinimumData(sameGroupData);
					double myi = findMinimumSimilarity(sameGroupSimilarity);
					double leftCornerPointA = bj - ((bj - yi) / (1 - myi));
					this.leftCornerPoints.add(leftCornerPointA);

					sameGroupData.clear();
					sameGroupSimilarity.clear();
					sameGroupData.add(datum);
					sameGroupSimilarity.add(similarity);
					groupingNumber++;
				}
			}

			sameGroupSimilarity.remove(sameGroupSimilarity.size() - 1);
			double bj = centralPoints.get(groupingNumber - 1);
			double yi = findMinimumData(sameGroupData);
			double myi = findMinimumSimilarity(sameGroupSimilarity);
			double leftCornerPointA = bj - ((bj - yi) / (1 - myi));
			this.leftCornerPoints.add(leftCornerPointA);

			calculationStatus = FuzzyMembershipCalculationStatusEnum.LEFT_CORNER_A_IS_CALCULATED;

			return true;
		}
		return false;
	}

	private double findMinimumData(List<FuzzyData> sameGroupData) {
		Double smallestFee = Double.MAX_VALUE;
		for(FuzzyData fee : sameGroupData){
			if(smallestFee > fee.getInsuranceFee()){
				smallestFee = fee.getInsuranceFee();
			}
		}
		return smallestFee;
	}

	private double findMinimumSimilarity(List<Double> sameGroupSimilarities) {
		Double smallestSimilarity = Double.MAX_VALUE;
		for(Double similarity : sameGroupSimilarities){
			if(smallestSimilarity > similarity){
				smallestSimilarity = similarity;
			}
		}
		return smallestSimilarity;
	}

	@Override
	public boolean calculateCenterPointB() {
		if(FuzzyMembershipCalculationStatusEnum.CENTER_POINT_B_IS_CALCULATED.equals(calculationStatus)){
			return true;
		} else if(data != null && similarities != null && FuzzyMembershipCalculationStatusEnum.DATA_IS_GROUPED_BASED_ON_SIMILARITY.equals(calculationStatus)){

			int groupingNumber = 1;
			List<FuzzyData> sameGroupData = new ArrayList<FuzzyData>();
			List<Double> sameGroupSimilarity = new ArrayList<Double>();
			for(int i = 0; i < this.data.size(); i++){
				FuzzyData datum = this.data.get(i);
				Double similarity = 0d;
				if(i < this.similarities.size()){
					similarity = this.similarities.get(i);
				}

				if(groupingNumber == datum.getGroup()){
					sameGroupData.add(datum);
					sameGroupSimilarity.add(similarity);
				} else{
					double centralPoint = calculateCentralPoint(sameGroupData, sameGroupSimilarity);
					this.centralPoints.add(centralPoint);

					sameGroupData.clear();
					sameGroupSimilarity.clear();
					sameGroupData.add(datum);
					sameGroupSimilarity.add(similarity);
					groupingNumber++;
				}
			}

			double centralPoint = calculateCentralPoint(sameGroupData, sameGroupSimilarity);
			this.centralPoints.add(centralPoint);

			calculationStatus = FuzzyMembershipCalculationStatusEnum.CENTER_POINT_B_IS_CALCULATED;

			return true;
		}
		return false;
	}

	@Override
	public boolean groupDataBasedOnSimilarities() {
		if(FuzzyMembershipCalculationStatusEnum.DATA_IS_GROUPED_BASED_ON_SIMILARITY.equals(calculationStatus)){
			return true;
		} else if(data != null && similarities != null && FuzzyMembershipCalculationStatusEnum.SIMILARITIES_SEQUENCE_PREPARED.equals(calculationStatus)){
			int groupNumber = 1;
			numberOfGroups = 1;
			int itemCounter = 0;
			for(Double similarity : this.similarities){
				if(similarity < this.cutOfSimilarity){
					FuzzyData datum = this.data.get(itemCounter);
					datum.setGroup(groupNumber);
					groupNumber++;
					numberOfGroups++;

				} else{

					FuzzyData datum = this.data.get(itemCounter);
					datum.setGroup(groupNumber);
				}
				itemCounter++;
			}
			FuzzyData datum = this.data.get(itemCounter);
			datum.setGroup(groupNumber);

			calculationStatus = FuzzyMembershipCalculationStatusEnum.DATA_IS_GROUPED_BASED_ON_SIMILARITY;
			return true;
		}
		return false;
	}

	@Override
	public boolean findTheMembershipValues() {
		if(FuzzyMembershipCalculationStatusEnum.MEMBERSHIP_VALUES_ARE_CALCULATED.equals(calculationStatus)){
			return true;
		} else if(data != null && similarities != null && FuzzyMembershipCalculationStatusEnum.RIGTH_CORNER_C_IS_CALCULATED.equals(calculationStatus)){
			for(int i = 1; i <= this.numberOfGroups; i++){
				List<FuzzyData> sameGroupData = getSameGroupData(i);
				double b = this.centralPoints.get(i-1);
				for(FuzzyData d : sameGroupData){
					if(d.getInsuranceFee() == b){
						d.setMembershipValue(1);
					} else if(d.getInsuranceFee() > b){
						double c = this.rigthCornerPoints.get(i-1);
						double diffA = c - b;
						double diffB = c - d.getInsuranceFee();

						double result = (diffB / diffA);
						d.setMembershipValue(result);
					} else if(d.getInsuranceFee() < b){
						double a = this.leftCornerPoints.get(i-1);
						double diffA = b - a;
						double diffB = d.getInsuranceFee() - a;

						double result = (diffB / diffA);
						d.setMembershipValue(result);
					}
				}
			}
			calculationStatus = FuzzyMembershipCalculationStatusEnum.MEMBERSHIP_VALUES_ARE_CALCULATED;
			return true;
		}
		return false;
	}

	List<FuzzyData> getSameGroupData(final int groupingNumber) {
		List<FuzzyData> sameGroupData = new ArrayList<FuzzyData>();

		if(groupingNumber > 0){
			for(FuzzyData d : this.data){
				if(d.getGroup() == groupingNumber){
					sameGroupData.add(d);
				}
			}
		}

		return sameGroupData;
	}

}
