package net.youzule.java.dubbo.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Unit test for simple App.
 */
public class DubboHttpApplicationTest {
	private static final Logger logger = LoggerFactory.getLogger(DubboHttpApplicationTest.class);

	@Test
	public void test1() {
		Resource resource = new ClassPathResource("/files/txt.txt");
		File txtFile = null;// 文件
		FileInputStream fileInputStream = null;// 文件流

		try {
			txtFile = resource.getFile();
			fileInputStream = new FileInputStream(txtFile);
			StringBuffer stringBuffer = new StringBuffer();

			byte[] bytes = new byte[512];// 每次从流中读取的大小
			// 读取文件内容
			while (fileInputStream.read(bytes) != -1) {
				String string = new String(bytes, "utf-8");
				stringBuffer.append(string);
			}
			logger.info("文件内容为:" + stringBuffer.toString());
			int end = fileInputStream.available();
			logger.info("流读取完毕:" + end);
			logger.info("fileInputStream.channel.size: " + fileInputStream.getChannel().size());
			
			logger.info("第二次=======================================");
			fileInputStream.reset();
			StringBuffer stringBuffer1 = new StringBuffer();

			byte[] bytes1 = new byte[512];// 每次从流中读取的大小
			
			// 读取文件内容
			while (fileInputStream.read(bytes1) != -1) {
				String string = new String(bytes1, "utf-8");
				stringBuffer1.append(string);
			}
			logger.info("文件内容为:" + stringBuffer1.toString());
			int end1 = fileInputStream.available();
			logger.info("流读取完毕:" + end1);
			logger.info("fileInputStream.channel.size: " + fileInputStream.getChannel().size());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
