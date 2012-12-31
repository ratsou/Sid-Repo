/**
 * 
 */
package com.test.mahout;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVParser;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.model.MemoryIDMigrator;
import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
//import javax.ejb.Singleton;

/**
 * @author Siddharth
 *
 */

public class HelloRecommender {

	private Recommender recommender = null;
	private MemoryIDMigrator thing2long = new MemoryIDMigrator();
	private static String DATA_FILE_NAME = "E:\\Dataset.csv";
	private static DataModel dataModel;

	@PostConstruct
	public void initRecommender() {

		try {
			Map<Long,List<Preference>> preferecesOfUsers = new HashMap<Long,List<Preference>>();

			CSVParser parser = new CSVParser(new InputStreamReader(new FileInputStream(DATA_FILE_NAME), "UTF-8"));

			String[] header = parser.getLine();

			String[] line;

			while((line = parser.getLine()) != null) {

				String person = line[0];
				String likeName = line[1];

				long userLong = thing2long.toLongID(person);
				thing2long.storeMapping(userLong, person);

				long itemLong = thing2long.toLongID(likeName);
				thing2long.storeMapping(itemLong, likeName);

				List<Preference> userPrefList;

				if((userPrefList = preferecesOfUsers.get(userLong)) == null) {
					userPrefList = new ArrayList<Preference>();
					preferecesOfUsers.put(userLong, userPrefList);
				}

				userPrefList.add(new GenericPreference(userLong, itemLong, 1));
			}

			FastByIDMap<PreferenceArray> preferecesOfUsersFastMap = new FastByIDMap<PreferenceArray>();
			for(Entry<Long, List<Preference>> entry : preferecesOfUsers.entrySet()) {
				preferecesOfUsersFastMap.put(entry.getKey(), new GenericUserPreferenceArray(entry.getValue()));
			}

			dataModel = new GenericDataModel(preferecesOfUsersFastMap);

			recommender = new GenericBooleanPrefItemBasedRecommender(dataModel, new LogLikelihoodSimilarity(dataModel));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String[] recommendThings(String personName) throws TasteException {
		List<String> recommendations = new ArrayList<String>();
		try {
			List<RecommendedItem> items = recommender.recommend(thing2long.toLongID(personName), 10);
			for(RecommendedItem item : items) {
				recommendations.add(thing2long.toStringID(item.getItemID()));
			}
		} catch (TasteException e) {
			throw e;
		}
		return recommendations.toArray(new String[recommendations.size()]);
	}

	public static void main(String[] args) {
		System.out.println("------recommendation------\n\n");
		HelloRecommender hlReco = new HelloRecommender();
		hlReco.initRecommender();
		try {
			for (String result : hlReco.recommendThings("Manuel Blechschmidt")) {
				System.out.println(result);
			}
		} catch (TasteException e) {
			e.printStackTrace();
		}
	}
}
