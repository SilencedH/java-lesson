package net.youzule.java.http.file.module.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import net.youzule.java.http.file.module.service.TxtService;

/**  
* @Title: TxtServiceImpl.java
* @Description:  
* @author：zhaodahai  
* @date 2018年9月15日  下午4:07:13
*/

@Service
public class TxtServiceImpl implements TxtService{

	private static final Logger logger = LoggerFactory.getLogger(TxtServiceImpl.class);

	@Override
	public FileInputStream getFileInputStream() {
		Resource resource = new ClassPathResource("/files/hello.txt");
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(resource.getFile());
			logger.info("文件转为输出流成功");
		} catch (IOException e) {
			logger.error("io异常",e);
		}
		
		return fileInputStream;
	}

}
