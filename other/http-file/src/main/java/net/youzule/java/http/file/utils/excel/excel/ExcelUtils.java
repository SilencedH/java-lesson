package net.youzule.java.http.file.utils.excel.excel;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @Title: ExcelUtils.java
 * @Description:
 * @author：zhaodahai
 * @date 2018年9月20日 下午5:51:14
 */

public class ExcelUtils {

	/**
	 * @description 判断是否03excel文件，是返回true,不是返回false
	 * @param filePath
	 * @return
	 */
	public static boolean isExcel2003(final String filePath) {
		return filePath.matches("^.+\\.(?i)(xls)$");
	}

	/**
	 * @description 判断是否07excel文件，是返回true,不是返回false
	 * @param filePath
	 * @return
	 */
	public static boolean isExcel2007(final String filePath) {
		return filePath.matches("^.+\\.(?i)(xlsx)$");
	}

	/**
	 * @description 验证是否是excel文件
	 * @param filePath
	 * @return
	 */
	public static boolean validateExcel(final String filePath) {
		if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
			return false;
		}
		return true;
	}

	/**
	 * @description 返回cellstyle,要区分head、body、title
	 * @param workbook
	 * @return
	 */
	public static CellStyle getCellStyle(Workbook workbook, String type) {
		// 创建单元格样式
		final CellStyle cellStyle = workbook.createCellStyle();
		// 设置单元格字体样式
		final Font font = workbook.createFont();
		cellStyle.setFont(font);

		// 设置单元格水平居中
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		// 设置单元格垂直居中
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		// 设置单元格内容显示不下自动换行
		cellStyle.setWrapText(true);

		// 字体
		font.setFontName("宋体");

		// 设置单元格边框为细线条
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);

		// 根据类别设置不同style
		if ("head".equals(type)) {
			font.setFontHeight((short) 200);
			// 设置字体加粗
			font.setBold(true);
			// 设置单元格背景颜色为淡蓝色
			cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.PALE_BLUE.getIndex());
			cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		} else if ("title".equals(type)) {
			font.setFontHeightInPoints((short) 18);
			// 设置字体加粗
			font.setBold(true);
		} else if ("body".equals(type)) {
			font.setFontHeightInPoints((short) 11);
		}

		return cellStyle;
	}
	
	

}
