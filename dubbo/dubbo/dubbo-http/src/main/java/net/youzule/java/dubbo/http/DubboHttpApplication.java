package net.youzule.java.dubbo.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ImportResource("classpath:dubbo-consumer.xml")
public class DubboHttpApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(DubboHttpApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(DubboHttpApplication.class, args);
		logger.info("dubbo-http启动成功！");
	}
}
