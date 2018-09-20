package net.youzule.java.http.file.module.service.impl;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.youzule.java.http.file.module.service.FileService;

/**  
* @Title: FileServiceImpl.java
* @Description:  
* @author：zhaodahai  
* @date 2018年9月19日  上午11:32:16
*/

@Service
public class FileServiceImpl implements FileService {

	private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
	
	@Override
	public String upload(MultipartFile file) {
		String fileOriginalName ="C:\\Users\\13673\\Documents\\files\\youzule\\test\\" +  file.getOriginalFilename();
		File fileDest = new File(fileOriginalName);
		try {
			file.transferTo(fileDest);
			return "SUCCESS";
		} catch (IllegalStateException | IOException e) {
			logger.error("异常",e);
			return "FAIL";
		}
	}

}
