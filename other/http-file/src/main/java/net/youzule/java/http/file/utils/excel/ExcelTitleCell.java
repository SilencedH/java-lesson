package net.youzule.java.http.file.utils.excel;

/**
 * @Title: ExcelTitleRow.java
 * @Description:
 * @author：zhaodahai
 * @date 2018年9月19日 下午4:23:37
 */

/**
 * 
 */
public class ExcelTitleCell {
	private int width;// title的宽度
	private String value;// 数据的值
	private String column;// 字段值
	private String type;//单元格数据类型

	public ExcelTitleCell(String type) {
		this.type = type;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
