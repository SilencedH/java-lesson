package net.youzule.java.http.file.utils.excel;

import static org.mockito.Mockito.verifyZeroInteractions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hpsf.Array;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @Title: ExcelUtils.java
 * @Description:
 * @author：zhaodahai
 * @date 2018年9月15日 下午6:05:26
 */

public class ExcelUtils {
	public static XSSFWorkbook createExcel(String[] titles, String sheetName, List<String[]> data) {
		// 行数
		int rows = 0;
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		// 定义一个sheet
		XSSFSheet xssfSheet = xssfWorkbook.createSheet(sheetName);

		// 制作表头
		XSSFRow xssfRowHeader = xssfSheet.createRow(rows);
		rows++;
		for (int i = 0; i < titles.length; i++) {
			XSSFCell xssfCell = xssfRowHeader.createCell(i);
			// 表头赋值
			xssfCell.setCellValue(titles[i]);
		}

		// excel体赋值
		for (String[] item : data) {
			// 生成一行
			XSSFRow xssfRow = xssfSheet.createRow(rows);
			rows++;
			for (int i = 0; i < titles.length; i++) {
				// 遍历生成一个cell，并赋值
				XSSFCell xssfCell = xssfRow.createCell(i);
				xssfCell.setCellValue(item[i]);
			}
		}
		return xssfWorkbook;
	}

	// 导出一个excel，包含一个sheet
	public static XSSFWorkbook exportExcel(ExcelEntity excelEntity) {
		// 创建一个excel对象
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		// sheet对象
		XSSFSheet xssfSheet = xssfWorkbook.createSheet();

		// 创建excel表头
		XSSFRow xssfRowHeader = xssfSheet.createRow(0);
		List<ExcelTitleCell> excelTitleCells = excelEntity.getTitles();
		for (int i = 0; i < excelTitleCells.size(); i++) {
			ExcelTitleCell excelTitleCell = excelTitleCells.get(i);
			//设置这一列的宽度
			xssfSheet.setColumnWidth(i, excelTitleCell.getWidth());
			//创建xshell，设置值
			XSSFCell xssfCell =  xssfRowHeader.createCell(i);
			xssfCell.setCellValue(excelTitleCell.getValue());
		}
		
		//填充数据body
		List<List<Object>> excelData = excelEntity.getBodyData();
		for (int i = 0; i < excelData.size(); i ++) {
			List<Object> excelDataCells = excelData.get(i);
			//创建一个row
			XSSFRow xssfRow = xssfSheet.createRow(i + 1);//之所以是 i +1，是去掉header之后开始每增加一行就是i+1
			//遍历headers添加cell数据
			for(int j = 0; j < excelTitleCells.size(); j ++) {
				XSSFCell xssfCell = xssfRow.createCell(j);
				//设置值
				Object excelDataCell = excelDataCells.get(j);
				/*if("int".equals(excelTitleCells.get(j).getType())) {
					xssfCell.setCellValue(Integer.parseInt(excelDataCell.toString()));
				}else if ("String".equals(excelTitleCells.get(j).getType())) {
					xssfCell.setCellValue(excelDataCell.toString());
				}*/
				xssfCell.setCellValue(String.valueOf(excelDataCell));
			}
		}
		return xssfWorkbook;
	}

}
