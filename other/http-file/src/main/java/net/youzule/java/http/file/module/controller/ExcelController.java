package net.youzule.java.http.file.module.controller;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.util.FileUtil;
import net.youzule.java.http.file.module.service.ExcelService;

/**  
* @Title: ExcelController.java
* @Description:  
* @author：zhaodahai  
* @date 2018年9月15日  下午4:03:34
*/

@RestController
@RequestMapping("/excel")
public class ExcelController {

	private static final Logger logger = LoggerFactory.getLogger(ExcelController.class);
	
	@Autowired
	private ExcelService excelService;
	
	@GetMapping("/download1")
	public void downloadExcel1(HttpServletResponse response) {
		logger.info("下载excel开始");
		excelService.doResponse(response);
	}
	
	@GetMapping("/export1")
	public void exportExcel1(HttpServletResponse response) {
		logger.info("导出excel开始");
		excelService.exportExcel(response);
	}
	
	@GetMapping("/export2")
	public void exportExcel2(HttpServletResponse response) {
		logger.info("导出excel开始");
		excelService.exportExcel1(response);
	}
}
