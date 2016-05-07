package com.googlecode.ounit.codecomparison.view;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.ounit.codecomparison.model.Round;

public class RoundForm {

	private Round round;

	private String addRoundButton;

	
	public String getAddRoundButton() {
		return addRoundButton;
	}

	public void setAddRoundButton(String addRoundButton) {
		this.addRoundButton = addRoundButton;
	}

	public Round getRound() {
		return round;
	}

	public void setRound(Round round) {
		this.round = round;
	}
	private List<String> errors = new ArrayList<>();

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public void validate(Round round) {
		if (round.getRoundName().length() > 9) {
			errors.add("RoundName too long, must be no more then 9 characters");
		} else if (round.getYear().length() > 20) {
		errors.add("RoundYear too long, must be no more then 20 characters");
		} else if (round.getSemester().length() > 6) {
			errors.add("RoundSemester too long, must be no more then 6 characters");
		} else if (round.getSubject().length() > 100) {
			errors.add("RoundSubject too long, must be no more then 100 characters");
		}else if (positiveIntegerValue(round)) {
			errors.add("RoundUrl too long, must be no more then 100 characters");
		}
	}

	private boolean positiveIntegerValue(Round round) {
		
		return false;
	}

}
