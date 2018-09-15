package net.youzule.java.http.file.module.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.youzule.java.http.file.module.service.TxtService;

/**  
* @Title: TxtController.java
* @Description:  
* @author：zhaodahai  
* @date 2018年9月15日  下午4:03:21
*/

@RestController
@RequestMapping("/txt")
public class TxtController {
	private static final Logger logger = LoggerFactory.getLogger(TxtController.class);
	
	@Autowired
	private TxtService txtService;
	
	@GetMapping("/download")
	public void downloadTxt1(HttpServletResponse response) {
		OutputStream outputStream = null;
		FileInputStream fileInputStream = null;
		try {
			outputStream = response.getOutputStream();
			response.reset();
			fileInputStream = txtService.getFileInputStream();
			IOUtils.copy(fileInputStream, outputStream);
			response.setHeader("Content-Disposition", "attachment;fileName=txt.txt");
			response.setContentType("text/plain;charset=utf-8");
		} catch (IOException e) {
			logger.error("io异常",e);
		}finally {
			IOUtils.closeQuietly(fileInputStream);
			IOUtils.closeQuietly(outputStream);
		}
	}
}
