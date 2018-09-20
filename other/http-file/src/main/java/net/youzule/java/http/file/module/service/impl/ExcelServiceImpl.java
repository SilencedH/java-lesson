package net.youzule.java.http.file.module.service.impl;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.junit.Assert.assertArrayEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hpsf.Array;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.youzule.java.http.file.module.service.ExcelService;
import net.youzule.java.http.file.utils.excel.ExcelDataCell;
import net.youzule.java.http.file.utils.excel.ExcelEntity;
import net.youzule.java.http.file.utils.excel.ExcelTitleCell;
import net.youzule.java.http.file.utils.excel.ExcelUtils;

/**
 * @Title: ExcelServiceImpl.java
 * @Description:
 * @author：zhaodahai
 * @date 2018年9月15日 下午4:50:08
 */

@Service
public class ExcelServiceImpl implements ExcelService {
	private static final Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);

	@Override
	public void doResponse(HttpServletResponse response) {
		response.reset();

		response.setHeader("Content-Disposition", "attachment;fileName=aa.xlsx");
		response.setContentType("application/ms-excel;charset=utf-8");
		Resource resource = new ClassPathResource("files/张三.xlsx");
		FileInputStream fileInputStream = null;
		OutputStream outputStream = null;
		File file = null;

		try {
			file = resource.getFile();
			fileInputStream = new FileInputStream(file);
			outputStream = response.getOutputStream();
			IOUtils.copy(fileInputStream, outputStream);
		} catch (IOException e) {
			logger.error("io异常", e);
			;
		} finally {
			IOUtils.closeQuietly(outputStream);
			IOUtils.closeQuietly(outputStream);
		}

	}

	// HSSFWork 后缀.xls XSSFWorkbook 后缀.xlsx
	@Override
	public void exportExcel(HttpServletResponse response) {
		// excel title头
		String[] titles = { "姓名", "年龄" };
		String fileName = "学生.xlsx";
		String sheetName = "学生信息表";

		// 定义一个excel文件
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		// 定义一个sheet
		XSSFSheet xssfSheet = xssfWorkbook.createSheet(sheetName);

		XSSFRow xssfRow = xssfSheet.createRow(0);
		XSSFCell xssfCell = xssfRow.createCell(0);

		xssfCell.setCellValue("sean");

		OutputStream outputStream = null;

		response.reset();
		response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
		response.setContentType("application/ms-excel;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			outputStream = response.getOutputStream();
			xssfWorkbook.write(outputStream);

		} catch (IOException e) {
			logger.info("io异常", e);
		} finally {
			IOUtils.closeQuietly(outputStream);
			try {
				xssfWorkbook.close();
			} catch (IOException e) {
				logger.info("io异常", e);
			}
		}

	}

	@Override
	public void exportExcel1(HttpServletResponse response) {
		OutputStream outputStream = null;
		String fileName = "a.xlsx";

		response.reset();
		response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
		response.setContentType("application/ms-excel;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		XSSFWorkbook xssfWorkbook = null;
		try {
			outputStream = response.getOutputStream();
			String[] titles = { "姓名", "年龄" };
			String sheetName = "学生信息表";
			List<String[]> data = new ArrayList<>();
			String[] data1 = { "张三", "22" };
			String[] data2 = { "李四", "23" };
			data.add(data1);
			data.add(data2);

			xssfWorkbook = ExcelUtils.createExcel(titles, sheetName, data);

			xssfWorkbook.write(outputStream);
			outputStream.flush();
		} catch (IOException e) {
			logger.info("io异常", e);
		} finally {
			IOUtils.closeQuietly(outputStream);
			if (xssfWorkbook != null) {
				try {
					xssfWorkbook.close();
				} catch (IOException e) {
					logger.info("io异常", e);
				}

			}
		}

	}

	@Override
	public String importExcel1(MultipartFile file) {

		return null;
	}

	@Override
	public String exportExcel2(HttpServletResponse response) {
		ExcelEntity excelEntity = new ExcelEntity();
		
		//添加表头
		List<ExcelTitleCell> excelTitles = new ArrayList<>();
		
		excelEntity.setTitles(excelTitles);
		
		ExcelTitleCell titleCell1= new ExcelTitleCell("String");
		titleCell1.setValue("姓名");
		titleCell1.setColumn("name");
		titleCell1.setWidth(100);
		excelTitles.add(titleCell1);
		
		ExcelTitleCell titleCell2= new ExcelTitleCell("int");
		titleCell2.setValue("年龄");
		titleCell2.setColumn("age");
		titleCell2.setWidth(100);
		excelTitles.add(titleCell2);
		
		ExcelTitleCell titleCell3= new ExcelTitleCell("String");
		titleCell3.setValue("性别");
		titleCell3.setColumn("sex");
		titleCell3.setWidth(100);
		excelTitles.add(titleCell3);
		
		
		
		//添加表数据
		List<Map<String, Object>> sourceData = new ArrayList<>();
		for (int i = 0; i <= 20; i ++) {
			Map<String, Object> map = new HashMap<>();
			map.put("name", "sean" + i);
			map.put("age", 11 + i);
			map.put("sex", "M");
			sourceData.add(map);
		}
		
		List<List<Object>> excelData = new ArrayList<>();
		excelEntity.setBodyData(excelData);
		
		for(Map<String, Object> item:sourceData) {
			List<Object> excelDataCells = new ArrayList<>();
			for (int j = 0; j < excelTitles.size(); j ++) {
				Object value = item.get(excelTitles.get(j).getColumn());
				excelDataCells.add(value);
			}
			excelData.add(excelDataCells);
		}
		
		XSSFWorkbook xssfWorkbook = ExcelUtils.exportExcel(excelEntity);

		OutputStream outputStream = null;
		response.reset();
		response.setHeader("Content-Disposition", "attachment;fileName=a.xlsx");
		response.setContentType("application/ms-excel;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		try {
			outputStream = response.getOutputStream();
			xssfWorkbook.write(outputStream);
			outputStream.flush();
		} catch (IOException e) {
			logger.info("io异常", e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
				}
			}
		}

		return "SUCCESS";
	}

}
