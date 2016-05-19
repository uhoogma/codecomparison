package com.googlecode.ounit.codecomparison.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.googlecode.ounit.codecomparison.dao.AttemptDao;
import com.googlecode.ounit.codecomparison.dao.RoundDao;
import com.googlecode.ounit.codecomparison.dao.SavedComparisonDao;
import com.googlecode.ounit.codecomparison.dao.StudentDao;
import com.googlecode.ounit.codecomparison.model.Attempt;
import com.googlecode.ounit.codecomparison.model.Round;
import com.googlecode.ounit.codecomparison.model.SavedComparison;
import com.googlecode.ounit.codecomparison.model.Student;
import com.googlecode.ounit.codecomparison.view.ComparisonForm;

@Controller
public class ComparisonController {

	@Resource
	private SavedComparisonDao savedComparisonDao = new SavedComparisonDao();
	@Resource
	private AttemptDao attemptDao = new AttemptDao();
	@Resource
	private StudentDao studentDao = new StudentDao();
	@Resource
	private RoundDao roundDao = new RoundDao();

	@RequestMapping("/comparison/{comparisonId}")
	public String comparison(@PathVariable("comparisonId") String comparisonId, Model model) {
		ComparisonForm comparisonForm = new ComparisonForm();
		SavedComparison comparison = setComparison(comparisonId, comparisonForm);
		setAttempts(comparisonForm, comparison);
		setStudents(comparisonForm, comparison);
		setRounds(comparisonForm, comparison);
		model.addAttribute("comparisonForm", comparisonForm);
		return "comparison";
	}
	
	private SavedComparison setComparison(String comparisonId, ComparisonForm comparisonForm) {
		SavedComparison comparison = savedComparisonDao.findComparisonForId(Long.parseLong(comparisonId));
		comparisonForm.setSavedComparison(comparison);
		return comparison;
	}
	
	private void setAttempts(ComparisonForm comparisonForm, SavedComparison comparison) {
		Attempt firstAttempt = attemptDao.findAttemptForId(comparison.getFirstAttemptId());
		Attempt secondAttempt = attemptDao.findAttemptForId(comparison.getSecondAttemptId());
		comparisonForm.setFirstCode(firstAttempt.getCode());
		comparisonForm.setSecondCode(secondAttempt.getCode());
	}

	private void setStudents(ComparisonForm comparisonForm, SavedComparison comparison) {
		Student firstStudent = studentDao.findStudentForMoodleId(comparison.getFirstStudentId());
		Student secondStudent = studentDao.findStudentForMoodleId(comparison.getSecondStudentId());
		comparisonForm.setFirstStudent(firstStudent);
		comparisonForm.setSecondStudent(secondStudent);
	}

	private void setRounds(ComparisonForm comparisonForm, SavedComparison comparison) {
		Round firstRound = roundDao.findRoundForAttemptId(comparison.getFirstAttemptId());
		Round secondRound = roundDao.findRoundForAttemptId(comparison.getSecondAttemptId());
		comparisonForm.setFirstRound(firstRound);
		comparisonForm.setSecondRound(secondRound);
		setReturnLink(comparisonForm, firstRound);
	}

	private void setReturnLink(ComparisonForm comparisonForm, Round firstRound) {
		comparisonForm.setReturnLink("/task/" + firstRound.getTask_id());
	}

}
