package net.youzule.java.dubbo.http.module.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.youzule.java.dubbo.http.module.service.FileDownloadService;

/**
 * @Title: ExportExcelController.java
 * @Description:
 * @author：zhaodahai
 * @date 2018年9月12日 下午3:09:09
 */
@RestController
@RequestMapping("/download/file")
public class DownloadFileController {

	private static final Logger logger = LoggerFactory.getLogger(DownloadFileController.class);

	@Autowired
	private FileDownloadService fileDownLoadService;

	/**
	 * @description: 下载txt
	 * @param: request
	 * @param: response
	 */
	@GetMapping("/txt/{fileName}")
	public void downloadTxt(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) {
		try {
			OutputStream outputStream = response.getOutputStream();
			response.reset();
			response.setHeader("Content-Disposition", "attachment;fileName:" + fileName + ".txt");
			response.setContentType("text/plain;charset=utf-8");
			outputStream.write(fileDownLoadService.getFileBytes("/files/txt.txt"));
			outputStream.flush();
		} catch (IOException e) {
			logger.error("io异常:", e);
		}

	}

	@GetMapping("/txt1/{fileName}")
	public void downloadTxt1(@PathVariable String fileName, HttpServletResponse response) {
		fileDownLoadService.writeHttpServletResponse(response, "/files/txt.txt", "txt/plain", fileName, "txt");
	}
	
	@GetMapping("/dubbo/txt/{fileName}")
	public void downloadDubboTxt(@PathVariable String fileName,HttpServletResponse response) {
		fileDownLoadService.getBytes(response, fileName, ".txt");
	}
	
	
	
	@GetMapping("/download/excel")
	public void downloadExcel(HttpServletRequest request, HttpServletResponse response) {
		// 获取excel路径
		Resource resource = new ClassPathResource("/files/张三.xlsx");
		FileInputStream fileInputStream = null;
		OutputStream outputStream = null;
		try {
			File file = resource.getFile();
			fileInputStream = new FileInputStream(file);

		} catch (IOException e) {
			logger.info("io异常",e);
		}finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					logger.info("关闭fileInputStream异常",e);
				}
			}
			
			if(outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					logger.info("关闭outputStream异常",e);
				}
			}
		}
	}

}
