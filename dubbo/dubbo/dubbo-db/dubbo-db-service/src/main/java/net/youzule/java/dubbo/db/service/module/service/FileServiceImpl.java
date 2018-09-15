package net.youzule.java.dubbo.db.service.module.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import net.youzule.java.dubbo.db.api.FileService;

/**  
* @Title: OutStreamServiceImpl.java
* @Description:  
* @author：zhaodahai  
* @date 2018年9月13日  上午10:50:49
*/

@Service("fileService")
public class FileServiceImpl implements FileService {

	private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

	
	@Override
	public byte[] downloadFile() {
		logger.info("dubbo-service下载文件开始");
		Resource resource = new ClassPathResource("/files/txt.txt");
		FileInputStream fileInputStream = null;
		try {
			File file = resource.getFile();
			logger.info("获取文件路径为:" + file.getPath());

			fileInputStream = new FileInputStream(file);
			byte[] bytes = new byte[fileInputStream.available()];
			fileInputStream.read(bytes);
			return bytes;
		} catch (IOException e) {
			logger.error("io异常",e);
			return null;
		}finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					logger.error("关闭fileInputStream异常",e);
				}
			}
		}
		
	}

}
