package com.googlecode.ounit.codesimilarity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.googlecode.ounit.codecomparison.model.SavedComparison;

public class SimilarityRunnerAdvanced {

	private static final String FS = System.getProperty("file.separator");
	private static final String INTEGER_REGEX = "\\d+";
	private static final String STUDENTS = "students";
	private static final String TEACHER = "teacher";

	private String filename;
	private int ngramSize;
	private int windowSize;
	private double similarityThreshold;
	private int modulus;

	private Map<Pair, String> studentSubmissions = new HashMap<Pair, String>();
	private Map<Integer, List<Integer>> studentHashes = new HashMap<Integer, List<Integer>>();
	private Map<Integer, int[]> convertedHashes = new HashMap<Integer, int[]>();
	private Map<Pair, ValueObject> comparisonResults = new HashMap<Pair, ValueObject>();

	public SimilarityRunnerAdvanced(int ngramsize, int windowsize, double similarityThreshold, int modulus) {
		this.ngramSize = ngramsize;
		this.windowSize = windowsize;
		this.similarityThreshold = similarityThreshold;
		this.modulus = modulus;
	}

	private List<Integer> generateBoilerPlateHashes(String boilerplate) {
		return Similarity.generateHashes(boilerplate, ngramSize, modulus);
	}

	public List<SavedComparison> run(String boilerplate, Map<Pair, String> studentSubmissions) {
		// listf(path + STUDENTS);
		for (Map.Entry<Pair, String> entry : studentSubmissions.entrySet()) {
			List<Integer> hashes = Similarity.generateHashes(studentSubmissions.get(entry.getKey()), ngramSize,
					modulus);
			studentHashes.put(entry.getKey().getSecond(), hashes);
		}

		List<Integer> attemptsList = new ArrayList<Integer>();
		for (Pair pair : studentSubmissions.keySet()) {
			attemptsList.add(pair.getSecond());
		}
		Integer[] attemptsArray = attemptsList.toArray(new Integer[1]);

		List<Integer> hashesBoilerPlate = generateBoilerPlateHashes(boilerplate);

		convertHashes(hashesBoilerPlate);
		System.out.println(convertedHashes.toString());

		for (int i = 0; i < attemptsArray.length; i++) {
			int firstAttempt = attemptsArray[i];
			for (int j = 0; j < attemptsArray.length; j++) {
				int secondAttempt = attemptsArray[j];
				if (i < j) {
					double firstToSecondComparison = Similarity.JaccardCoefficientFromHashes(
							convertedHashes.get(firstAttempt), convertedHashes.get(secondAttempt), windowSize);
					double secondToFirstComparison = Similarity.JaccardCoefficientFromHashes(
							convertedHashes.get(secondAttempt), convertedHashes.get(firstAttempt), windowSize);
					comparisonResults.put(new Pair(firstAttempt, secondAttempt), new ValueObject(firstAttempt,
							secondAttempt, firstToSecondComparison, secondToFirstComparison));
				}
			}
		}
		comparisonResults = MapUtil.sortByValue(comparisonResults);

		// creating a lookup map where key is attempt and value is student
		Map<Integer, Integer> attemptToStudent = new HashMap<Integer, Integer>();
		for (Pair entry : studentSubmissions.keySet()) {
			attemptToStudent.put(entry.getSecond(), entry.getFirst());
		}

		return makeComparisons(attemptToStudent);
	}

	private List<SavedComparison> makeComparisons(Map<Integer, Integer> attemptToStudent) {
		List<SavedComparison> scl = new ArrayList<>();
		for (Map.Entry<Pair, ValueObject> entry : comparisonResults.entrySet()) {
			SavedComparison sc = new SavedComparison();
			int attempt1 = entry.getKey().getFirst();
			int attempt2 = entry.getKey().getSecond();
			int student1 = attemptToStudent.get(attempt1);
			int student2 = attemptToStudent.get(attempt2);
			if (student1 != student2 && entry.getValue().getLargestSimilarityResult() > similarityThreshold) {

				sc.setFirstStudentId(student1);
				sc.setSecondStudentId(student2);

				sc.setFirstAttemptId( attempt1);
				sc.setSecondAttemptId( attempt2);

				Double fts = entry.getValue().getFirstToSecondComparison();
				if (fts.isInfinite() // || fts.isNaN()
				) {
					sc.setFirstToSecondResult(Double.MAX_VALUE);
					sc.setFirstToSecondIsInfinite(true);
				} else {
					sc.setFirstToSecondResult(fts);
					sc.setFirstToSecondIsInfinite(false);
				}

				Double stf = entry.getValue().getSecondToFirstComparison();
				if (stf.isInfinite() // || fts.isNaN()
				) {
					sc.setSecondToFirstResult(Double.MAX_VALUE);
					sc.setSecondToFirstIsInfinite(true);
				} else {
					sc.setSecondToFirstResult(stf);
					sc.setSecondToFirstIsInfinite(false);
				}
				scl.add(sc);
			}
		}
		return scl;
	}

	private String composeCsv(Map<Integer, Integer> attemptToStudent) {
		StringBuffer sb = new StringBuffer();
		sb.append("Comparison_number\tStudent1\tAttempt1\tStudent2\tAttempt2\tResult\n");
		int comparisonNumber = 1;
		for (Map.Entry<Pair, ValueObject> entry : comparisonResults.entrySet()) {
			int attempt1 = entry.getKey().getFirst();
			int attempt2 = entry.getKey().getSecond();
			int student1 = attemptToStudent.get(attempt1);
			int student2 = attemptToStudent.get(attempt2);
			if (student1 != student2 && entry.getValue().getLargestSimilarityResult() > similarityThreshold) {
				sb.append(comparisonNumber);
				sb.append("\t");
				sb.append(student1);
				sb.append("\t");
				sb.append(entry.getKey().getFirst());
				sb.append("\t");
				sb.append(student2);
				sb.append("\t");
				sb.append(entry.getKey().getSecond());
				sb.append("\t");
				sb.append(entry.getValue().getLargestSimilarityResult());
				sb.append("\n");

				sb.append(comparisonNumber);
				sb.append("\t");
				sb.append(student2);
				sb.append("\t");
				sb.append(entry.getKey().getSecond());
				sb.append("\t");
				sb.append(student1);
				sb.append("\t");
				sb.append(entry.getKey().getFirst());
				sb.append("\t");
				sb.append(entry.getValue().getLargestSimilarityResult());
				sb.append("\n\n");

				comparisonNumber++;
			}
		}
		return sb.toString();
	}

	/**
	 * removing hashes that are common in attempt code and boilerplate code
	 */
	private void convertHashes(List<Integer> boilerPlateHashes) {
		for (Integer key : studentHashes.keySet()) {
			int[] converted = null;
			if (boilerPlateHashes.size() == 0) {
				converted = studentHashes.get(key).stream().mapToInt(h -> h).toArray();
			} else {
				converted = Similarity.removeBoilerplate(studentHashes.get(key), boilerPlateHashes).stream()
						.mapToInt(h -> h).toArray();
			}
			convertedHashes.put(key, converted);
		}
	}

	public List<File> listf(String directoryName) {
		File directory = new File(directoryName);
		List<File> resultList = new ArrayList<File>();

		File[] fList = directory.listFiles();
		resultList.addAll(Arrays.asList(fList));
		String[] splitted = directoryName.split("\\\\");// escaping slashes
		for (File file : fList) {
			if (file.isFile()) {
				String studentId = splitted[splitted.length - 2];
				String attemptId = splitted[splitted.length - 1];
				String submission;
				if (studentId.matches(INTEGER_REGEX) && attemptId.matches(INTEGER_REGEX)) {
					try {
						submission = new String(Files.readAllBytes(Paths.get(directoryName + FS + filename)));
						studentSubmissions.put(new Pair(Integer.parseInt(studentId), Integer.parseInt(attemptId)),
								submission);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else if (file.isDirectory()) {
				resultList.addAll(listf(file.getAbsolutePath()));
			}
		}
		return resultList;
	}
}
