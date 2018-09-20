package net.youzule.java.http.file.module.service;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/**  
* @Title: TxtService.java
* @Description:  
* @author：zhaodahai  
* @date 2018年9月15日  下午4:06:42
*/

public interface TxtService {
	FileInputStream getFileInputStream();
	
	String upload(HttpServletRequest request);
	
	String upload(MultipartFile file);
}
