package com.googlecode.ounit.moodlescraper;

public class MoodleScraperRunner {

	public static void main(String[] args) {
		MoodleScraper ms;
		// ms = new
		// MoodleScraper("C:\\Users\\Urmas Hoogma\\Desktop\\treenode-2\\students");
		// ms.downloadTest(780917, "TreeNode.java"); // 5.

		ms = new MoodleScraper(
				"C:\\Users\\Urmas Hoogma\\Desktop\\GraphTask\\students");
		ms.downloadTest(789951, "GraphTask.java"); // 6.

		// ms= new
		// MoodleScraper("C:\\Users\\Urmas Hoogma\\Desktop\\Puzzle\\students");
		// ms.downloadTest(798475, "Puzzle.java"); // 7.
	}
}
