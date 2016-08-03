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

	double standartDerivation;
	List<Double> diffSequence = new ArrayList<Double>();
	List<Double> similarities = new ArrayList<Double>();
	List<FuzzyData> data;
	List<Double> differenceSequence;

	private FuzzyMembershipServiceImpl() {

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

	public boolean calculateCenterPointB() {
		if(FuzzyMembershipCalculationStatusEnum.CENTER_POINT_B_IS_CALCULATED.equals(calculationStatus)){
			return true;
		} else if(data != null && similarities != null && FuzzyMembershipCalculationStatusEnum.STANDART_DERIVATION_IS_CALCULATED.equals(calculationStatus)){

			calculationStatus = FuzzyMembershipCalculationStatusEnum.CENTER_POINT_B_IS_CALCULATED;

			return true;
		}
		return false;
	}

	public boolean groupDataBasedOnSimilarities() {
		if(FuzzyMembershipCalculationStatusEnum.DATA_IS_GROUPED_BASED_ON_SIMILARITY.equals(calculationStatus)){
			return true;
		} else if(data != null && similarities != null && FuzzyMembershipCalculationStatusEnum.SIMILARITIES_SEQUENCE_PREPARED.equals(calculationStatus)){
			int groupNumber = 1;
			int itemCounter = 0;
			for(Double similarity : this.similarities){
				if(similarity < this.cutOfSimilarity){
					FuzzyData datum = this.data.get(itemCounter);
					datum.setGroup(groupNumber);
					groupNumber++;

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
}
