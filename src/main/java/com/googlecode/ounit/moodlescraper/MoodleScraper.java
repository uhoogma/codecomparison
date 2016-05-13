package com.googlecode.ounit.moodlescraper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class MoodleScraper {

	private WebDriver driver = new HtmlUnitDriver();
	private static String FS = System.getProperty("file.separator");
	private String studentSaveDirectory;
	private Map<String, Exception> exceptions = new HashMap<String, Exception>();

	public MoodleScraper(String studentSaveDirectory) {
		this.studentSaveDirectory = studentSaveDirectory;
	}

	@Test
	public void downloadTest(Integer testId, String fileName) {
		long start = System.currentTimeMillis();
		login();
		getTest(testId);
		downloadAttempts(fileName);
		logout();
		long elapsedTimeMillis = System.currentTimeMillis() - start;
		System.out.println("downloadTest runtime: " + elapsedTimeMillis / 1000F
				+ " sec");
		System.out.println("With exceptions: " + exceptions.toString());
	}

	private void login() {
		driver.get("https://moodle.hitsa.ee/");
		elementById("login_username").sendKeys("uhoogma");
		elementById("login_password").sendKeys("GetYourOwnPass");
		elementByXPath("submit").click();
	}

	private void getTest(Integer testId) {
		driver.get("https://moodle.hitsa.ee/mod/quiz/report.php?id=" + testId
				+ "&mode=overview");
		// set params for example:
		// elementById("id_pagesize").sendKeys("1000");
		elementById("id_submitbutton").click();
	}

	private void downloadAttempts(String fileName) {
		Map<Integer, Integer> pairs = getReport("mod-quiz-report-overview-report");
		System.out.println(pairs.toString());
		bulkDownload(pairs, fileName);
	}

	private void logout() {
		elementByCSS("https://moodle.hitsa.ee/login/logout.php?").click();
		System.out.println("Done");
	}

	private Map<Integer, Integer> getReport(String id) {
		WebElement table = driver.findElements(By.id("attempts")).get(0);
		Map<Integer, Integer> pairs = new HashMap<Integer, Integer>();
		for (WebElement elem : table.findElements(By.cssSelector("tr[id*='"
				+ "mod-quiz-report-overview-report_r" + "']"))) {
			System.out.println(elem.toString());
			try {
				List<WebElement> teines = elem.findElements(By.cssSelector("td.c2"));
				if (!teines.isEmpty()) {
					WebElement teine = teines.get(0);
					if (teine != null) {
						String tudeng = teine.findElement(
								By.cssSelector("a[href*='"
										+ "https://moodle.hitsa.ee/user/view.php?id="
										+ "']")).getAttribute("href");
						String attempt = teine
								.findElement(
										By.cssSelector("a[href*='"
												+ "https://moodle.hitsa.ee/mod/quiz/review.php?attempt="
												+ "']")).getAttribute("href");
						pairs.put(Util.splitQuery(attempt, "attempt"),
								Util.splitQuery(tudeng, "id"));
					}
				}
			} catch (NoSuchElementException e) {
				exceptions.put(Long.toString(System.currentTimeMillis()), e);
				break; // break on summary row
			}
		}
		return pairs;
	}

	private void bulkDownload(Map<Integer, Integer> pairs, String fileName) {
		boolean sentinel = true;
		for (Map.Entry<Integer, Integer> pair : pairs.entrySet()) {
			if (sentinel) {
				sentinel = download(pair.getKey(), pair.getValue(), fileName);
			}
		}
	}

	private boolean download(int attempt, int student, String fileName) {
		driver.get("https://moodle.hitsa.ee/mod/quiz/review.php?attempt="
				+ attempt);
		List<WebElement> elements = driver.findElements(By
				.cssSelector("textarea[class='ou-codeeditor']"));
		WebElement elem = elements.isEmpty() ? null : elements.get(0);
		if (elem == null) {
			System.out.println(driver.getPageSource());
			return true;
		}
		String code = elem == null ? "" : elem.getAttribute("value");
		save(attempt, student, code, fileName);
		return true;
	}

	private void save(int attempt, int student, String code, String fileName) {
		File f1 = new File(studentSaveDirectory + FS + student);
		if (!f1.exists()) {
			System.out.println("making a dir " + studentSaveDirectory + FS
					+ student);
			f1.mkdir();
		}
		File f2 = new File(studentSaveDirectory + FS + student + FS + attempt);
		f2.mkdir();
		try (PrintWriter out = new PrintWriter(studentSaveDirectory + FS
				+ student + FS + attempt + FS + fileName, "UTF-8"))
		// must be UTF-8
		{
			System.out.println("saving: " + studentSaveDirectory + FS + student
					+ FS + attempt + FS + fileName);
			out.println(code);
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			exceptions.put(Long.toString(System.currentTimeMillis()), e);
		}
	}

	private WebElement elementById(String id) {
		List<WebElement> elements = driver.findElements(By.id(id));
		return elements.isEmpty() ? null : elements.get(0);
	}

	private WebElement elementByXPath(String id) {
		List<WebElement> elements = driver.findElements(By
				.xpath("//*[@type='submit']"));
		return elements.isEmpty() ? null : elements.get(0);
	}

	private WebElement elementByCSS(String id) {
		List<WebElement> elements = driver.findElements(By
				.cssSelector("a[href*='" + id + "']"));
		return elements.isEmpty() ? null : elements.get(0);
	}

	@After
	private void closeDriver() {
		driver.close();
	}
}
