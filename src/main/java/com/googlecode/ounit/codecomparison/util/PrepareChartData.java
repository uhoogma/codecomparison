package com.googlecode.ounit.codecomparison.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PrepareChartData {

	public Map<String, Double> groupAndCalculateMedium(List<Double> input) {
		Map<String, Double> kvp = new LinkedHashMap<>();
		double i;
		double powerOfTwo = 1;
		double correlatedSum;
		int countOfInfiniteValues = 0;
		double sum = 0;
		double endOfLastGroup = 0;
		double currentInput;
		for (i = 0; i < input.size(); i++) {
			if (i == Math.pow(2, powerOfTwo)) {
				// TODO correlate infinite values
				correlatedSum = i == countOfInfiniteValues ? 0.0 : (i * sum) / (i - countOfInfiniteValues);
				kvp.put(String.valueOf((int) i), correlatedSum / (i - endOfLastGroup));
				powerOfTwo++;
				countOfInfiniteValues = 0;
				sum = 0;
				endOfLastGroup = i;
			}

			currentInput = input.get((int) i);
			if (currentInput == Double.MAX_VALUE) {
				countOfInfiniteValues++;
			} else {
				sum += currentInput;
			}
		}
		// correlate infinite values
		correlatedSum = i == countOfInfiniteValues ? 0.0 : (i * sum) / (i - countOfInfiniteValues);
		kvp.put(String.valueOf((int) i), correlatedSum / (i - endOfLastGroup));
		return kvp;
	}
}
