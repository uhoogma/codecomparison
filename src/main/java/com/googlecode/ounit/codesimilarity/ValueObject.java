package com.googlecode.ounit.codesimilarity;

import java.util.Comparator;

public class ValueObject implements Comparable<ValueObject> {
	static class ValueObjectComparator implements Comparator<ValueObject> {
		public int compare(ValueObject c1, ValueObject c2) {
			Double a1 = c1.getLargestSimilarityResult();
			Double a2 = c2.getLargestSimilarityResult();
			return a1.compareTo(a2);
		}
	}

	private int firstattemptId;
	private int secondattemptId;
	private Double firstToSecondComparison;

	private Double secondToFirstComparison;

	public ValueObject(int firstattemptId, int secondattemptId, Double firstToSecondComparison,
			Double secondToFirstComparison) {
		super();
		this.firstattemptId = firstattemptId;
		this.secondattemptId = secondattemptId;
		this.firstToSecondComparison = firstToSecondComparison;
		this.secondToFirstComparison = secondToFirstComparison;
	}

	@Override
	public int compareTo(ValueObject vo) {
		return getLargestSimilarityResult().compareTo(vo.getLargestSimilarityResult());
	}

	public int getFirstattempt() {
		return firstattemptId;
	}

	public Double getFirstToSecondComparison() {
		return firstToSecondComparison;
	}

	public Double getLargestSimilarityResult() {
		return (secondToFirstComparison > firstToSecondComparison) ? secondToFirstComparison : firstToSecondComparison;
	}

	public int getSecondattemptId() {
		return secondattemptId;
	}

	public Double getSecondToFirstComparison() {
		return secondToFirstComparison;
	}

	public Double getSmallestSimilarityResult() {
		return (secondToFirstComparison < firstToSecondComparison) ? secondToFirstComparison : firstToSecondComparison;
	}

}
