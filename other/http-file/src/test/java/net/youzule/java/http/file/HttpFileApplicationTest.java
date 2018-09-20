package net.youzule.java.http.file;


import java.io.IOException;
import java.net.URI;
import java.net.URL;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class HttpFileApplicationTest extends TestCase {

	private static final Logger logger = LoggerFactory.getLogger(HttpFileApplicationTest.class);
	@Test
	public void test1() throws IOException {
		String url = HttpFileApplicationTest.class.getResource("/").toString();
		Resource resource = new ClassPathResource("/");
		System.out.println(url);
		System.out.println(resource.getURL().toString());
	}
	
	@Test
	public void test2() {
		Resource resource = new ClassPathResource("/");
		try {
			URI uri = resource.getURI();
			URL url = resource.getURL();
			String uriPath = uri.getPath();
			logger.info("uriPath:" + uriPath);
			String uriString = uri.toString();
			logger.info("uriString" + uriString);
			String urlString = url.toString();
			logger.info("urlString:" + urlString);
			
			String urlPath = url.getPath();
			logger.info("urlPath:" + urlPath);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
