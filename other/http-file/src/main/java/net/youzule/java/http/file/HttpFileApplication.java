package net.youzule.java.http.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class HttpFileApplication {
	public static void main(String[] args) {
		SpringApplication.run(HttpFileApplication.class, args);
		System.out.println("HttpFile启动成功!");
	}
}
