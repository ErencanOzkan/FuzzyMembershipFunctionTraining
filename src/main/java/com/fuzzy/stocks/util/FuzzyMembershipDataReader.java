package com.fuzzy.stocks.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;

import com.fuzzy.stocks.model.FuzzyData;

public class FuzzyMembershipDataReader {

	private static Logger LOGGER = org.slf4j.LoggerFactory.getLogger(FuzzyMembershipDataReader.class);

	private final static String fileName = "data.json";

	public static  List<FuzzyData> readFuzzyData() {
		File fuzzyDataFile = getCustomerFileReader.apply(fileName);

		List<FuzzyData> fuzzyData = new ArrayList<FuzzyData>();;

		JSONParser parser = new JSONParser();
		try(Reader is = new FileReader(fuzzyDataFile) ){
			JSONObject jsonObject = (JSONObject) parser.parse(is);
			for(Object key : jsonObject.keySet()){

				JSONArray jsonArray = (JSONArray) jsonObject.get(key);
				fuzzyData.addAll((List<FuzzyData>) jsonArray.stream().collect(Collectors.toList()));
			}

		} catch (IOException e){
			FuzzyMembershipDataReader.LOGGER.error(e.getMessage(), e);
		} catch (org.json.simple.parser.ParseException e){
			FuzzyMembershipDataReader.LOGGER.error(e.getMessage(), e);
		}
		return fuzzyData;
	}

	private static Function<String, File> getCustomerFileReader = filename -> {
		ClassLoader cl = FuzzyMembershipDataReader.class.getClassLoader();
		File fuzzyData = new File(cl.getResource(filename).getFile());
		return fuzzyData;
	};

}
