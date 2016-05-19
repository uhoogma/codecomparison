package com.googlecode.ounit.codecomparison.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class Paging {
	// TODO
	public Map<Integer, Integer> getPages(Double size, Integer pageSize) {
		Map<Integer, Integer> pages = new LinkedHashMap<>();

		int sizeInt = size.intValue() + 1;
		if (sizeInt == 1) {
			pages.put(0, pageSize);
		}
		if (sizeInt == 2) {
			pages.put(0, pageSize);
			pages.put(1, 2 * pageSize);
		} else {
			for (int i = 0; i < 4 && i <= sizeInt; i++) {
				pages.put(i, i * pageSize);
			}
			pages.put(sizeInt, sizeInt * pageSize);
		}
		return pages;
	}
}
