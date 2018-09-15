package net.youzule.java.dubbo.http.module.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

/**
 * @Title: FileDownloadService.java
 * @Description:
 * @author：zhaodahai
 * @date 2018年9月12日 下午6:31:15
 */

@Service
public class FileDownloadService {

	private static final Logger logger = LoggerFactory.getLogger(FileDownloadService.class);

	/**
	 * @description 返回文件字节数组
	 * @param filePath
	 * @return byte[]
	 */
	public byte[] getFileBytes(String filePath) {
		logger.info("获取文件流servcie入参 filePath:" + filePath);
		FileInputStream fileInputStream = null;
		Resource resource = new ClassPathResource(filePath);
		try {
			File file = resource.getFile();
			fileInputStream = new FileInputStream(file);
			byte[] bytes = new byte[fileInputStream.available()];
			fileInputStream.read(bytes);
			return bytes;
		} catch (IOException e) {
			logger.error("io异常", e);
			return null;
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					logger.error("关闭fileInputStream异常", e);
				}
			}
		}
	}

	/**
	 *@param response
	 *@param filePath
	 *@param fileType
	 *@param fileName
	 *@param fileSuffix
	 */
	public void writeHttpServletResponse(HttpServletResponse response, String filePath, String fileType, String fileName,
			String fileSuffix) {
		logger.info("根据路径将文件写入输出流,入参:" + filePath);
		Resource resource = new ClassPathResource(filePath);

		OutputStream outputStream = null;
		File file;
		FileInputStream fileInputStream = null;
		try {
			outputStream = response.getOutputStream();
			response.reset();
			response.setHeader("Content-Disposition", "attachment;fileName=" + fileName + "." + fileSuffix);
			response.setContentType(fileType + "charset=utf-8");

			file = resource.getFile();
			fileInputStream = new FileInputStream(file);


			byte[] bytes = new byte[512];
			while (fileInputStream.read(bytes) != -1) {
				outputStream.write(bytes);
			}
			outputStream.flush();
			logger.info("response处理完成");
		} catch (IOException e) {
			logger.error("io异常", e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					logger.error("关闭outputStream异常", e);
				}
			}

			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					logger.error("关闭fileinputStream异常", e);
				}
			}
		}
	}
	
	
	
	/**
	 *从dubbo中获取outputStream
	 */
	public void getBytes(HttpServletResponse response,String fileName,String suffix) {

		logger.info("调用dubbo-db-service开始");
		byte[] result = null;
		OutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			
			response.reset();
			response.setHeader("Content-Disposition", "attachment;fileName=" + fileName + suffix);
			response.setContentType("Content-Type=text/plain;charset=utf-8");
			
			outputStream.write(result);
			outputStream.flush();
			logger.info("response处理完成");
		} catch (IOException e) {
			logger.info("io异常",e);
		}finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					logger.info("关闭outputStream异常",e);
				}
				
			}
		}
	}
}
