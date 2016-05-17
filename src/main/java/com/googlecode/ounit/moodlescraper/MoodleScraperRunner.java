package com.googlecode.ounit.moodlescraper;

import com.googlecode.ounit.codecomparison.model.Login;
import com.googlecode.ounit.codecomparison.model.Round;

public class MoodleScraperRunner {
	
	public MoodleScraper login(Round round, Login login) {
		MoodleScraper ms = new MoodleScraper(login.getUser(), login.getPass());
		ms.prepare(round.getUrl());
		return ms;
	}
}
