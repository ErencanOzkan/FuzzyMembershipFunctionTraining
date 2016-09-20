package com.fuzzy.stocks.service.impl;

import java.util.List;

import com.fuzzy.stocks.model.DecisionTableElement;
import com.fuzzy.stocks.model.FuzzyData;
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
		for(int i = 0; i< columns.length; i++)
		{
			System.out.format("( [%d] : %d) ", i, columns[i]);
		}
		System.out.println("");
	}

	public static void printDecitionTableMergedRows(int[] rows) {
		System.out.print("rows -> ");
		for(int i = 0; i< rows.length; i++)
		{
			System.out.format("( [%d] : %d) ", i, rows[i]);
		}
		System.out.println("");		
	}
}
