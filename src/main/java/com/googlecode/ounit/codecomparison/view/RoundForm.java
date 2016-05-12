package com.googlecode.ounit.codecomparison.view;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.ounit.codecomparison.model.Round;

public class RoundForm {

	private Round round;

	public Round getRound() {
		return round;
	}

	public void setRound(Round round) {
		this.round = round;
	}

	public List<String> validate(Round round) {
		List<String> errors = new ArrayList<>();
		if (round.getRoundName().length() > 100) {
			errors.add("Vooru nimi liiga pikk, ei tohi ületada 100 tähemärki");
		}
		if (round.getYear().length() > 9) {
			errors.add("Aasta tähis liiga pikk, ei tohi ületada 9 tähemärki");
		}
		if (round.getSemester().length() > 20) {
			errors.add("Semestri nimi liiga pikk, ei tohi ületada 20 tähemärki");
		}
		if (round.getSubject().length() > 6) {
			errors.add("Aine nimi liiga pikk, ei tohi ületada 6 tähemärki");
		}
		return errors;
	}

}
