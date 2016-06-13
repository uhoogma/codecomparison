package com.googlecode.ounit.codecomparison.util;

import java.util.Map;

import org.springframework.ui.Model;

import com.googlecode.ounit.codecomparison.controller.CircularBuffer;

public class Message {

	public void storeMessage(Model model, String message) {
		Map<String, Object> modelMap = model.asMap();
		CircularBuffer modelValue = (CircularBuffer) modelMap.get("messages");
		if (modelValue != null) {
			
		
		modelValue.store(message);
		}
	}
}
