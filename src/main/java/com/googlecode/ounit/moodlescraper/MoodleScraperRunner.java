package com.googlecode.ounit.moodlescraper;

import org.springframework.ui.Model;

import com.googlecode.ounit.codecomparison.model.Login;
import com.googlecode.ounit.codecomparison.model.Round;
import com.googlecode.ounit.codecomparison.util.Message;

public class MoodleScraperRunner {
	
	public MoodleScraper login(Long taskId, Round round, Login login, Model model, Message message) {
		MoodleScraper ms = new MoodleScraper(taskId, login.getUser(), login.getPass(), model, message);
		ms.prepare(round.getUrl());
		return ms;
	}
	
	public String getRoundName(Long taskId, Login login, Model model, Round round, Message message) {
		MoodleScraper ms = new MoodleScraper(taskId, login.getUser(), login.getPass(), model, message);
		return ms.getRoundName(round.getUrl());
	}
}
