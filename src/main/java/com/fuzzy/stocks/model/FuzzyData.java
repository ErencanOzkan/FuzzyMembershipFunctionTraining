package com.fuzzy.stocks.model;

import com.fuzzy.stocks.enums.FuzzyMembershipCalculationStatusEnum;

public class FuzzyData {

	double age;
	double property;
	double insuranceFee;

	int group;
	double membershipValue;
	FuzzyMembershipCalculationStatusEnum status;

	private FuzzyData() {
	}

	public static class FuzzyDataBuilder {

		private double age;
		private double property;
		private double insuranceFee;

		public FuzzyDataBuilder(double age, double property, double insuranceFee) {
			this.age = age;
			this.property = property;
			this.insuranceFee = insuranceFee;

		}

		public FuzzyData build() {
			FuzzyData fuzzyData = new FuzzyData();
			fuzzyData.setAge(this.age);
			fuzzyData.setInsuranceFee(this.insuranceFee);
			fuzzyData.setProperty(this.property);
			return fuzzyData;

		}
	}

	public double getAge() {
		return age;
	}

	public void setAge(double age) {
		this.age = age;
	}

	public double getProperty() {
		return property;
	}

	public void setProperty(double property) {
		this.property = property;
	}

	public double getInsuranceFee() {
		return insuranceFee;
	}

	public void setInsuranceFee(double insuranceFee) {
		this.insuranceFee = insuranceFee;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public double getMembershipValue() {
		return membershipValue;
	}

	public void setMembershipValue(double membershipValue) {
		this.membershipValue = membershipValue;
	}

	
	public FuzzyMembershipCalculationStatusEnum getStatus() {
		return status;
	}

	
	public void setStatus(FuzzyMembershipCalculationStatusEnum status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "FuzzyData [age=" + age + ", property=" + property + ", insuranceFee=" + insuranceFee + ", group=" + group + ", membershipValue=" + membershipValue + ", "
				+ (status != null ? "status=" + status : "") + "]";
	}


}
