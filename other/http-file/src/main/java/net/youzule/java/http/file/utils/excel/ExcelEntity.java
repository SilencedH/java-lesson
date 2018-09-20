package net.youzule.java.http.file.utils.excel;

import java.util.List;

/**
 * @Title: ExcelEntity.java
 * @Description:
 * @author：zhaodahai
 * @date 2018年9月19日 下午12:03:37
 */

public class ExcelEntity {

	private List<ExcelTitleCell> titles;

	private List<List<Object>> bodyData;

	public List<ExcelTitleCell> getTitles() {
		return titles;
	}

	public void setTitles(List<ExcelTitleCell> titles) {
		this.titles = titles;
	}

	public List<List<Object>> getBodyData() {
		return bodyData;
	}

	public void setBodyData(List<List<Object>> bodyData) {
		this.bodyData = bodyData;
	}

}
