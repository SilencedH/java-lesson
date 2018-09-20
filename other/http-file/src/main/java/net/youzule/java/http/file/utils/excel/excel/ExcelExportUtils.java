package net.youzule.java.http.file.utils.excel.excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @Title: ExcelExportUtils.java
 * @Description:
 * @author：zhaodahai
 * @date 2018年9月20日 下午3:53:34
 */

public class ExcelExportUtils {

	/**
	 * @description 生成07(.xlsx)
	 * @param excelFormat
	 * @return
	 */
	public static SXSSFWorkbook initWorkbook(final ExcelFormat excelFormat) {
		// 创建一个workbook，对应一个excel文件，使用SXSSFWorkbook防止内存溢出
		final SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(5000);

		// 在sxssfWorkbook中添加一个sheet，对应excel文件中的sheet
		final SXSSFSheet sxssfSheet = sxssfWorkbook.createSheet("sheet1");

		// 获取cellStyle
		final CellStyle headCellStyle = ExcelUtils.getCellStyle(sxssfWorkbook, "head");
		final CellStyle bodyCellStyle = ExcelUtils.getCellStyle(sxssfWorkbook, "body");

		int row = 0;

		// 表头head
		final SXSSFRow headRow = sxssfSheet.createRow(row);
		headRow.setHeight((short) 360);
		final List<Map<String, String>> heads = excelFormat.getHeadData();
		for (int i = 0; i < heads.size(); i++) {
			final SXSSFCell headCell = headRow.createCell(i);
			headCell.setCellStyle(headCellStyle);
			headCell.setCellValue(heads.get(i).get("headName"));// 表头值
			// 默认10个字符宽度
			final int colWidth = StringUtils.isBlank(heads.get(i).get("colWidth")) ? 10
					: Integer.parseInt(heads.get(i).get("colWidth"));
			sxssfSheet.setColumnWidth(i, colWidth * 256);
		}

		// 表体数据
		final List<Map<String, String>> body = excelFormat.getBodyData();
		if (!body.isEmpty()) {
			for (int j = 0; j < body.size(); j++) {
				row++;
				final SXSSFRow bodyRow = sxssfSheet.createRow(row);
				bodyRow.setHeight((short) 360);
				for (int k = 0; k < heads.size(); k++) {
					final Cell bodyCell = bodyRow.createCell(k);
					bodyCell.setCellStyle(bodyCellStyle);
					String key = heads.get(k).get("headName");
					String value = body.get(j).get(key);
					bodyCell.setCellValue(value);
				}
				// 刷新到硬盘
				if ((row % 5000) == 0) {
					try {
						sxssfSheet.flushRows();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return sxssfWorkbook;
	}

	
}
