package com.googlecode.ounit.moodlescraper;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Util {

	// http://stackoverflow.com/questions/13592236/parse-a-uri-string-into-name-value-collection
	public static Integer splitQuery(String url, String parameter) {
		final Map<String, List<String>> query_pairs = new LinkedHashMap<String, List<String>>();
		String[] pairs;
		try {
			pairs = (new URL(url)).getQuery().split("&");
			for (String pair : pairs) {
				final int idx = pair.indexOf("=");
				final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
				if (!query_pairs.containsKey(key)) {
					query_pairs.put(key, new LinkedList<String>());
				}
				final String value = idx > 0 && pair.length() > idx + 1
						? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
				query_pairs.get(key).add(value);
			}
			return new Integer(query_pairs.get(parameter).get(0));

		} catch (MalformedURLException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new Integer(null);
	}
}
