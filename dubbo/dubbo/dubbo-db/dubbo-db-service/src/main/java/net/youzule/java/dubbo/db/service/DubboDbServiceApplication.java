package net.youzule.java.dubbo.db.service;

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
@ImportResource("classpath:dubbo-provider.xml")
public class DubboDbServiceApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(DubboDbServiceApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DubboDbServiceApplication.class, args);
		LOGGER.info("dubbo-db-service启动成功!");
	}
}
