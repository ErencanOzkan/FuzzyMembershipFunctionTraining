package com.fuzzy.stocks.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fuzzy.stocks.model.DecisionTableElement;
import com.fuzzy.stocks.model.FuzzyData;
import com.fuzzy.stocks.model.MembershipModel;
import com.fuzzy.stocks.service.FuzzyMembershipPrintService;

public class FuzzyMembershipPrintServiceImpl implements FuzzyMembershipPrintService {

	public static void printFuzzyData(List<FuzzyData> data) {
		if(data != null){
			for(FuzzyData d : data){
				System.out.println(d);
			}
		}
	}

	public static void printDecitionTable(DecisionTableElement[][] desitionTable) {
		if(desitionTable != null){
			int x = desitionTable.length;
			int y = desitionTable[0].length;
			for(int j = 0; j < y; j++){
				for(int i = 0; i < x; i++){
					int group = desitionTable[i][j] != null ? desitionTable[i][j].getGroup() : -1;
					System.out.print("[ " + group + " ]");
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	public static void printDecitionTableMergedColumns(int[] columns) {
		System.out.print("columns -> ");
		for(int i = 0; i < columns.length; i++){
			System.out.format("( [%d] : %d) ", i, columns[i]);
		}
		System.out.println("");
	}

	public static void printDecitionTableMergedRows(int[] rows) {
		System.out.print("rows -> ");
		for(int i = 0; i < rows.length; i++){
			System.out.format("( [%d] : %d) ", i, rows[i]);
		}
		System.out.println("");
	}

	public static void printMembershipModels(MembershipModel[] columnValues) {
		System.out.println("Printing groups");

		List<MembershipModel> uniqueGroups = getUniqueGroups(columnValues);
		for(MembershipModel model : uniqueGroups){
			System.out.println(model);

		}

	}

	public static List<MembershipModel> getUniqueGroups(MembershipModel[] columnValues) {
		List<MembershipModel> uniqueGroups = new ArrayList<MembershipModel>();
		Set<Integer> printedGroups = new HashSet<Integer>();
		for(MembershipModel model : columnValues){
			if((model.getGoupingNumber() == 0) || !printedGroups.contains(model.getGoupingNumber())){
				uniqueGroups.add(model);
				if(model.getGoupingNumber() != 0){
					printedGroups.add(model.getGoupingNumber());
				}
			}
		}
		return uniqueGroups;
	}

	public static void printRigthCornerPoints(List<Double> rigthCornerPoints) {
		System.out.println("-----------Printing rigth corner points-------------");
		printCornerPoints(rigthCornerPoints,'c');
		System.out.println("---------------------------------------------------");

	}
	
	public static void printCenterCornerPoints(List<Double> centerCornerPoints) {
		System.out.println("-----------Printing center corner points-------------");
		printCornerPoints(centerCornerPoints,'b');
		System.out.println("---------------------------------------------------");

	}
	
	public static void printLeftCornerPoints(List<Double> leftCornerPoints) {
		System.out.println("-----------Printing left corner points-------------");
		printCornerPoints(leftCornerPoints,'a');
		System.out.println("---------------------------------------------------");

	}

	private static void printCornerPoints(List<Double> cornerPoints, char pointName) {
		int counter = 1;
		for(Double point : cornerPoints){
			System.out.format("( [%c%d] : %f) \n", pointName, counter, point);
			counter++;
		}
	}
}
