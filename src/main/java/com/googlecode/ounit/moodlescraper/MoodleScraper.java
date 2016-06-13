package com.googlecode.ounit.moodlescraper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.ui.Model;

import com.googlecode.ounit.codecomparison.util.Message;
import com.googlecode.ounit.codecomparison.model.Attempt;
import com.googlecode.ounit.codecomparison.model.Round;
import com.googlecode.ounit.codecomparison.model.Student;

public class MoodleScraper {

	private String user;
	private String pass;
	private WebDriver driver = new HtmlUnitDriver();
	private Map<String, Exception> exceptions = new HashMap<String, Exception>();
	private Map<Integer, Student> pairs = new HashMap<Integer, Student>();
	private Model model;
	private Message message;
	private Long taskId;

	public MoodleScraper(Long taskId, String userName, String password, Model model, Message message) {
		user = userName;
		pass = password;
		this.model = model;
		this.message = message;
		this.taskId = taskId;
	}

	@After
	private void closeDriver() {
		driver.close();
	}

	public String download(Integer attemptId, Integer studentId) {
		driver.get("https://moodle.hitsa.ee/mod/quiz/review.php?attempt=" + attemptId);
		List<WebElement> elements = driver.findElements(By.cssSelector("textarea[class='ou-codeeditor']"));
		WebElement elem = elements.isEmpty() ? null : elements.get(0);
		String code = elem == null ? "" : elem.getAttribute("value");
		return code;
	}

	@Test
	public List<Attempt> downloadAttempts(Round round, String fileName, List<Long> uniqueIdsFromDB) {
		getReport(round.getUrl());
		List<Attempt> newAttempts = new ArrayList<>();
		for (Entry<Integer, Student> attempt : pairs.entrySet()) {
			Attempt newAttempt = new Attempt(round.getTask_id(), round.getId(), attempt.getKey(),
					attempt.getValue().getMoodleId());
			if (uniqueIdsFromDB == null) {
				newAttempts.add(newAttempt);
			}
			if (uniqueIdsFromDB != null && !uniqueIdsFromDB.contains(attempt.getKey())) {
				newAttempts.add(newAttempt);
			}
		}
		return newAttempts;
	}

	@Test
	public List<Student> downloadStudents(Round round, String fileName, List<Integer> uniqueIdsFromDB) {
		getReport(round.getUrl());
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
		return newStudents;
	}

	private WebElement elementByCSS(String id) {
		List<WebElement> elements = driver.findElements(By.cssSelector("a[href*='" + id + "']"));
		return elements.isEmpty() ? null : elements.get(0);
	}

	private WebElement elementById(String id) {
		List<WebElement> elements = driver.findElements(By.id(id));
		return elements.isEmpty() ? null : elements.get(0);
	}

	private WebElement elementByXPath(String id) {
		List<WebElement> elements = driver.findElements(By.xpath("//*[@type='submit']"));
		return elements.isEmpty() ? null : elements.get(0);
	}

	public Model getModel() {
		return model;
	}

	public Map<Integer, Student> getPairs() {
		return pairs;
	}

	private void getReport(Integer roundId) {
		getRound(roundId);
		pairs = getReport("mod-quiz-report-overview-report");
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

	private void getRound(Integer roundId) {
		driver.get("https://moodle.hitsa.ee/mod/quiz/report.php?id=" + roundId + "&mode=overview");
		System.out.println(driver.getPageSource());
		WebElement submitButton = elementById("id_submitbutton");
		WebElement pageSize = elementById("id_pagesize");
		if (submitButton != null && pageSize!= null) {
			pageSize.clear();
			pageSize.sendKeys("1000");
			submitButton.click();
		} else {
			message.storeMessage(taskId, model, "Ei leia \"Esita\" nuppu või ei saa raporti suurust määrata");
		}
	}
	
	private void login() {
		driver.get("https://moodle.hitsa.ee/");
		elementById("login_username").sendKeys(user);
		elementById("login_password").sendKeys(pass);
		elementByXPath("submit").click();
		if (elementById("loginerrormessage") != null) {
			message.storeMessage(taskId, model, "Vigane sisenemine, palun proovi uuesti.");
		} else {
			message.storeMessage(taskId, model, "Kasutaja \"" + user + "\" on sisse loginud.");
		}
	}

	public void logout() {
		elementByCSS("https://moodle.hitsa.ee/login/logout.php?").click();
	}

	public void prepare(Integer roundId) {
		login();
	}

	public void setPairs(Map<Integer, Student> pairs) {
		this.pairs = pairs;
	}

	public String getRoundName(Integer url) {
		login();
		getRound(url);
		return elementById("region-main").findElements(By.tagName("h2")).get(0).getText();
	}
}
