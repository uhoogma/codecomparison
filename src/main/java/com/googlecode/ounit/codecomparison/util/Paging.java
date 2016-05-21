package com.googlecode.ounit.codecomparison.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class Paging {

	public Map<Integer, Integer> paginate(Integer count, Integer pageSize, Integer startFromId) {
		Map<Integer, Integer> pagesMap = new LinkedHashMap<>();
		int pages = count % pageSize == 0 ? count / pageSize : (count / pageSize) + 1;
		int currentPage = startFromId / pageSize;
		if (pages > 4) {
			if (currentPage == 0 || currentPage == 1) {
				pagesMap.put(0, 0);
				pagesMap.put(1, 1 * pageSize);
				pagesMap.put(2, 2 * pageSize);
				pagesMap.put(3, 3 * pageSize);
				pagesMap.put(pages - 1, (pages - 1) * pageSize);
			} else if (currentPage == pages - 2 || currentPage == pages - 1) {
				pagesMap.put(0, 0);
				pagesMap.put(pages - 4, (pages - 4) * pageSize);
				pagesMap.put(pages - 3, (pages - 3) * pageSize);
				pagesMap.put(pages - 2, (pages - 2) * pageSize);
				pagesMap.put(pages - 1, (pages - 1) * pageSize);
			} else {
				pagesMap.put(0, 0);
				pagesMap.put(currentPage - 1, (currentPage - 1) * pageSize);
				pagesMap.put(currentPage, currentPage * pageSize);
				pagesMap.put(currentPage + 1, (currentPage + 1) * pageSize);
				pagesMap.put(pages - 1, (pages - 1) * pageSize);
			}
		} else {
			for (int i = 0; i < pages; i++) {
				pagesMap.put(i, i * pageSize);
			}
		}
		return pagesMap;
	}
}
