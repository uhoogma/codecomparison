package com.googlecode.ounit.codecomparison.util;

import java.util.Map;

import org.springframework.ui.Model;

import com.googlecode.ounit.codecomparison.controller.CircularBuffer;

public class Message {

	public void storeMessage(Long id, Model model, String message) {
		Map<String, Object> modelMap = model.asMap();
		@SuppressWarnings("unchecked")
		Map<Long, CircularBuffer> modelMapInner = (Map<Long, CircularBuffer>) modelMap.get("messages");
		if (modelMapInner != null) {
			CircularBuffer modelValue = modelMapInner.get(id);
			if (modelValue != null) {
				modelValue.store(message);
			}
		}
	}
}
