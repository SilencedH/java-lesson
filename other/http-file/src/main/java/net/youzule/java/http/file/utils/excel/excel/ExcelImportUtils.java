package net.youzule.java.http.file.utils.excel.excel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**  
* @Title: ExcelImportUtils.java
* @Description:  
* @author：zhaodahai  
* @date 2018年9月20日  下午3:53:06
*/

public class ExcelImportUtils {
	
	/**
	 *@description excel转list,excel第一行不算
	 *@param input
	 *@param fieldName excel头对应map中Key，比如:姓名->name
	 *@return
	 */
	public static List<Map<String, String>> excelFileToListResult(final InputStream input, final String[] fieldName) {
		final List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			final XSSFWorkbook workBook = new XSSFWorkbook(input);
			final XSSFSheet sheet = workBook.getSheetAt(0);
			if (sheet != null) {
				for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
					final Map<String, String> rowData = new HashMap<String, String>();
					final XSSFRow row = sheet.getRow(i);
					for (int j = 0; j < fieldName.length; j++) {
						final XSSFCell cell = row.getCell(j);
						if (cell == null) {
							continue;
						}
						final String cellStr = cell.toString();
						if (fieldName[j] == null) {
							break;
						}
						rowData.put(fieldName[j], cellStr);
					}
					list.add(rowData);
				}
			}
			workBook.close();
		} catch ( Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
