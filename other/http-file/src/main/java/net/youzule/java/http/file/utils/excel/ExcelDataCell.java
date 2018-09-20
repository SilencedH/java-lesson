package net.youzule.java.http.file.utils.excel;

/**  
* @param <E>
 * @Title: ExcelDataCell.java
* @Description:  
* @author：zhaodahai  
* @date 2018年9月19日  下午4:20:40
*/

public class ExcelDataCell extends ExcelCell {
	private Object value;//数据的值

	public ExcelDataCell (String type) {
		this.setType(type);
	}
	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	
	
	
}
