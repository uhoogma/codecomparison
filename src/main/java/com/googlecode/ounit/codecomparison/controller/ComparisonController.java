package com.googlecode.ounit.codecomparison.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ComparisonController {

	@RequestMapping("/comparison")
	public String comparison() {
		return "comparison";
	}

}
