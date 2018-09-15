package net.youzule.java.http.file.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
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
		//行数
		int rows = 0;
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		// 定义一个sheet
		XSSFSheet xssfSheet = xssfWorkbook.createSheet(sheetName);

		// 制作表头
		XSSFRow xssfRowHeader = xssfSheet.createRow(rows);
		rows ++;
		for (int i = 0; i < titles.length; i++) {
			XSSFCell xssfCell = xssfRowHeader.createCell(i);
			//表头赋值
			xssfCell.setCellValue(titles[i]);
		}
		
		//excel体赋值
		for(String[] item:data) {
			//生成一行
			XSSFRow xssfRow = xssfSheet.createRow(rows);
			rows ++;
			for(int i = 0; i < titles.length; i ++) {
				//遍历生成一个cell，并赋值
				XSSFCell xssfCell = xssfRow.createCell(i);
				xssfCell.setCellValue(item[i]);
			}
		}
		return xssfWorkbook;
	}
}
