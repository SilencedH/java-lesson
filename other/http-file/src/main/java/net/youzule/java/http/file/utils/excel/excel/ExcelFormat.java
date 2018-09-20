package net.youzule.java.http.file.utils.excel.excel;

import java.util.List;
import java.util.Map;

/**
 * @Title: ExcelFormat.java
 * @Description:
 * @author：zhaodahai
 * @date 2018年9月20日 下午3:44:20
 */

public class ExcelFormat {
	private String fileTitle;//文件title
	private List<Map<String, String>> headData;//表头
	private List<Map<String, String>> bodyData;//表数据内容
	public String getFileTitle() {
		return fileTitle;
	}
	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}
	
	public List<Map<String, String>> getHeadData() {
		return headData;
	}
	public void setHeadData(List<Map<String, String>> headData) {
		this.headData = headData;
	}
	public List<Map<String, String>> getBodyData() {
		return bodyData;
	}
	public void setBodyData(List<Map<String, String>> bodyData) {
		this.bodyData = bodyData;
	}
}
