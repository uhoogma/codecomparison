package com.googlecode.ounit.moodlescraper;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.googlecode.ounit.codecomparison.model.Attempt;
import com.googlecode.ounit.codecomparison.model.Round;
import com.googlecode.ounit.codecomparison.model.Student;

public class MoodleScraper2 {

	private WebDriver driver = new HtmlUnitDriver();
	private static String FS = System.getProperty("file.separator");
	private Map<String, Exception> exceptions = new HashMap<String, Exception>();
	private static String user;
	private static String pass;

	public MoodleScraper2(String user1, String pass1) {
		user = user1;
		pass = pass1;
	}

	@Test
	public List<Student> downloadStudents(Integer roundId, String fileName, List<Long> uniqueIdsFromDB) {
		long start = System.currentTimeMillis();
		login();
		getTest(roundId);
		Map<Integer, Student> pairs = getReport("mod-quiz-report-overview-report");
		List<Student> newStudents = getNewStudents(pairs, uniqueIdsFromDB, fileName);
		logout();
		long elapsedTimeMillis = System.currentTimeMillis() - start;
		System.out.println("downloadTest runtime: " + elapsedTimeMillis / 1000F + " sec");
		System.out.println("With exceptions: " + exceptions.toString());
		return newStudents;
	}

	@Test
	public List<Attempt> downloadAttempts(Round round, String fileName, List<Long> uniqueIdsFromDB) {
		long start = System.currentTimeMillis();
		login();
		getTest(round.getUrl());
		Map<Integer, Student> pairs = getReport("mod-quiz-report-overview-report");
		List<Attempt> newStudents = getNewAttempts(round, pairs, uniqueIdsFromDB, fileName);
		logout();
		long elapsedTimeMillis = System.currentTimeMillis() - start;
		System.out.println("downloadTest runtime: " + elapsedTimeMillis / 1000F + " sec");
		System.out.println("With exceptions: " + exceptions.toString());
		return newStudents;
	}

	private void login() {
		driver.get("https://moodle.hitsa.ee/");
		elementById("login_username").sendKeys(user);
		elementById("login_password").sendKeys(pass);
		elementByXPath("submit").click();
	}

	private void getTest(Integer testId) {
		driver.get("https://moodle.hitsa.ee/mod/quiz/report.php?id=" + testId + "&mode=overview");
		System.out.println(driver.getPageSource());
		// set params for example:
		// elementById("id_pagesize").sendKeys("1000");
		elementById("id_submitbutton").click();
	}

	public List<Attempt> getNewAttempts(Round round, Map<Integer, Student> pairs, List<Long> uniqueIdsFromDB,
			String fileName) {
		List<Attempt> newStudents = new ArrayList<>();
		for (Entry<Integer, Student> attempt : pairs.entrySet()) {
			Attempt a = new Attempt();
			a.setMoodleId(new Long(attempt.getKey()));
			a.setRound_id(round.getId());
			a.setTask_id(round.getTask_id());
			a.setStudentId(attempt.getValue().getMoodleId());
			if (uniqueIdsFromDB == null) {
				newStudents.add(a);
			}
			if (uniqueIdsFromDB != null && !uniqueIdsFromDB.contains(attempt)) {
				newStudents.add(a);
			}
		}
		System.out.println(pairs.toString());
		bulkDownload(pairs, fileName);
		return newStudents;
	}

	public List<Student> getNewStudents(Map<Integer, Student> pairs, List<Long> uniqueIdsFromDB, String fileName) {

		Set<Student> uniqueStudentsFromMoodle = (pairs.values()).stream().collect(Collectors
				.toCollection(() -> new TreeSet<Student>((p1, p2) -> p1.getMoodleId().compareTo(p2.getMoodleId()))));
		List<Student> newStudents = new ArrayList<>();
		for (Student student : uniqueStudentsFromMoodle) {
			if (uniqueIdsFromDB == null) {
				newStudents.add(student);
			}
			if (uniqueIdsFromDB != null && !uniqueIdsFromDB.contains(student.getMoodleId())) {
				newStudents.add(student);
			}
		}
		// salvesta attempt kui pole
		// save only new pairs attempt student not fetched
		System.out.println(pairs.toString());
		bulkDownload(pairs, fileName);
		return newStudents;
	}

	private void logout() {
		elementByCSS("https://moodle.hitsa.ee/login/logout.php?").click();
		System.out.println("Done");
	}

	private Map<Integer, Student> getReport(String id) {
		WebElement table = driver.findElements(By.id("attempts")).get(0);
		Map<Integer, Student> pairs = new HashMap<Integer, Student>();
		for (WebElement elem : table
				.findElements(By.cssSelector("tr[id*='" + "mod-quiz-report-overview-report_r" + "']"))) {
			System.out.println(elem.toString());
			try {
				List<WebElement> tableData = elem.findElements(By.cssSelector("td.c2"));
				if (!tableData.isEmpty()) {
					WebElement data = tableData.get(0);
					if (data != null) {
						String student = data
								.findElement(By
										.cssSelector("a[href*='" + "https://moodle.hitsa.ee/user/view.php?id=" + "']"))
								.getAttribute("href");
						String fullName = data
								.findElement(By
										.cssSelector("a[href*='" + "https://moodle.hitsa.ee/user/view.php?id=" + "']"))
								.getText().trim();
						String attempt = data
								.findElement(By.cssSelector(
										"a[href*='" + "https://moodle.hitsa.ee/mod/quiz/review.php?attempt=" + "']"))
								.getAttribute("href");
						pairs.put(Util.splitQuery(attempt, "attempt"),
								new Student(Util.splitQuery(student, "id"), fullName));
					}
				}
			} catch (NoSuchElementException e) {
				exceptions.put(Long.toString(System.currentTimeMillis()), e);
				break; // break on summary row
			}
		}
		return pairs;
	}

	private void bulkDownload(Map<Integer, Student> pairs, String fileName) {
		// boolean sentinel = true;
		for (Map.Entry<Integer, Student> pair : pairs.entrySet()) {
			// if (sentinel) {
			// sentinel =
			// download(pair.getKey(), pair.getValue(), fileName);
			// sentinel = false;
			// }
		}
	}

	public List<Attempt> bulkDownload2(Round round, String fileName, List<Attempt> attempts) {
		// boolean sentinel = true;
		List<Attempt> ret = new ArrayList<>();
		for (Attempt a : attempts) {
			// if (sentinel) {
			// sentinel =
			String code = download(a.getMoodleId(), a.getStudentId(), fileName);
			System.out.println("code is "+ code);
			a.setCode(code);
			ret.add(a);
			break;
			// sentinel = false;
			// }
		}
		return ret;
	}

	private String download(Long attempt, Integer studentId, String fileName) {
		driver.get("https://moodle.hitsa.ee/mod/quiz/review.php?attempt=" + attempt);
		List<WebElement> elements = driver.findElements(By.cssSelector("textarea[class='ou-codeeditor']"));
		WebElement elem = elements.isEmpty() ? null : elements.get(0);
		if (elem == null) {
			throw new RuntimeException("elem is null");
		}
		String code = elem == null ? "" : elem.getAttribute("value");
		// save(attempt, studentId, code, fileName);
		return code;
	}

	private void save(int attempt, Long studentId, String code, String fileName) {
		// System.out.println(attempt + " " + student + " " + code + " " +
		// fileName);
	}

	private WebElement elementById(String id) {
		List<WebElement> elements = driver.findElements(By.id(id));
		return elements.isEmpty() ? null : elements.get(0);
	}

	private WebElement elementByXPath(String id) {
		List<WebElement> elements = driver.findElements(By.xpath("//*[@type='submit']"));
		return elements.isEmpty() ? null : elements.get(0);
	}

	private WebElement elementByCSS(String id) {
		List<WebElement> elements = driver.findElements(By.cssSelector("a[href*='" + id + "']"));
		return elements.isEmpty() ? null : elements.get(0);
	}

	@After
	private void closeDriver() {
		driver.close();
	}
}
