package net.youzule.java.http.file.module.service;

import org.springframework.web.multipart.MultipartFile;

/**  
* @Title: FileService.java
* @Description:  
* @author：zhaodahai  
* @date 2018年9月19日  上午11:31:49
*/

public interface FileService {
	String upload(MultipartFile file);
}
