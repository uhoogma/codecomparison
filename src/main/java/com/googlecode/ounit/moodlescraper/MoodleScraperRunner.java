package com.googlecode.ounit.moodlescraper;

import java.util.List;

import com.googlecode.ounit.codecomparison.model.Attempt;
import com.googlecode.ounit.codecomparison.model.Round;
import com.googlecode.ounit.codecomparison.model.Student;

public class MoodleScraperRunner {

	public static void main(String[] args) {
		MoodleScraper ms;
		// ms = new
		// MoodleScraper("C:\\Users\\Urmas
		// Hoogma\\Desktop\\treenode-2\\students");
		// ms.downloadTest(780917, "TreeNode.java"); // 5.

		ms = new MoodleScraper("C:\\Users\\Urmas Hoogma\\Desktop\\GraphTask\\students");
		ms.downloadTest(789951, "GraphTask.java"); // 6.

		// ms= new
		// MoodleScraper("C:\\Users\\Urmas Hoogma\\Desktop\\Puzzle\\students");
		// ms.downloadTest(798475, "Puzzle.java"); // 7.
	}

	
	public List<Student> getNewStudents(Round round, Integer taskId, String user, String pass, List<Long> uniqueIdsFromDB ) {
		MoodleScraper2 ms = new MoodleScraper2(user, pass);
		return ms.downloadStudents(round.getUrl(), "TreeNode.java", uniqueIdsFromDB);
	}
	public List<Attempt> getNewAttempts(Round round, Integer taskId, String user, String pass, List<Long> uniqueIdsFromDB ) {
		MoodleScraper2 ms = new MoodleScraper2(user, pass);
		return ms.downloadAttempts(round, "TreeNode.java", uniqueIdsFromDB);
	}
	public List<Attempt> downloadAttempts(Round round, List<Attempt> attempts, String user, String pass ) {
		MoodleScraper2 ms = new MoodleScraper2(user, pass);
		return ms.bulkDownload2(round, "TreeNode.java", attempts);
	}
}
