package net.youzule.java.http.file.module.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.youzule.java.http.file.module.service.TxtService;

/**
 * @Title: TxtServiceImpl.java
 * @Description:
 * @author：zhaodahai
 * @date 2018年9月15日 下午4:07:13
 */

@Service
public class TxtServiceImpl implements TxtService {

	private static final Logger logger = LoggerFactory.getLogger(TxtServiceImpl.class);

	@Override
	public FileInputStream getFileInputStream() {
		Resource resource = new ClassPathResource("/files/hello.txt");
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(resource.getFile());
			logger.info("文件路径为:" + resource.getFile().getPath());
			logger.info("文件转为输出流成功");
		} catch (IOException e) {
			logger.error("io异常", e);
		}

		return fileInputStream;
	}

	@Override
	public String upload(HttpServletRequest request) {
		logger.info("处理请求流开始");
		ServletInputStream servletInputStream = null;
		Resource resource = new ClassPathResource("/");
		URI uri = null;
		String uriString = "";
		try {
			uri = resource.getURI();
			uriString = uri.getPath();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		FileOutputStream fileOutputStream = null;
		try {
			String path = uriString.substring(1, uriString.length()) + "files/upload.txt";
			logger.info("文件路径为:" + path);
			fileOutputStream = new FileOutputStream(path);

			servletInputStream = request.getInputStream();
			int a = servletInputStream.available();
			logger.info("available:" + a);
			int length = 0;
			byte[] bytes = new byte[512];
			while ((length = servletInputStream.read(bytes)) != -1) {
				logger.info("length:" + length);
				fileOutputStream.write(bytes);
			}
			fileOutputStream.flush();

			return "SUCCESS";
		} catch (IOException e) {
			logger.info("io异常", e);
			return "FAIL";
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					logger.info("io异常", e);
				}
			}
			if (servletInputStream != null) {
				try {
					servletInputStream.close();
				} catch (IOException e) {
					logger.info("io异常", e);
				}

			}
		}
	}

	@Override
	public String upload(MultipartFile file) {
		if (null == file) {
			return "文件为空";
		}
		if (file.getSize() <= 0) {
			return "文件为空";
		}

		String contentType = file.getContentType();
		logger.info("content-type:" + contentType);
		String fileName = file.getName();
		logger.info("fileName:" + fileName);

		String originalFileName = file.getOriginalFilename();
		logger.info("originalFileName:" + originalFileName);

		boolean isEmpty = file.isEmpty();
		logger.info("isEmpty: " + isEmpty);
		long size = file.getSize();
		logger.info("size: " + size);
		byte[] bytes = null;
		try {
			bytes = file.getBytes();
			String byteString = new String(bytes, "utf-8");
			logger.info("bytesString:" + byteString);
		} catch (IOException e) {
			logger.error("io异常", e);
		}

		FileOutputStream fileOutputStream = null;

		try {
			String classPath = new ClassPathResource("/").getURI().getPath().substring(1);
			String helloPath = classPath + "/files/hello.txt";
			String uploadPath = classPath + "/files/upload.txt";
			File hello = new File(helloPath);
			logger.info("transfer to hello.txt");
			file.transferTo(hello);

			logger.info("上传:");
			File upload = new File(uploadPath);

			fileOutputStream = new FileOutputStream(upload);
			fileOutputStream.write(bytes);
			fileOutputStream.flush();
			return "SUCCESS";
		} catch (IOException e) {
			logger.error("异常:", e);
			return "FAIL";
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
				}

			}
		}
	}

}
