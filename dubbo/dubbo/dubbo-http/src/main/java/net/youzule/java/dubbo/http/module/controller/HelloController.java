package net.youzule.java.dubbo.http.module.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.youzule.java.dubbo.http.module.service.HelloService;

/**  
* @Title: HelloController.java
* @Description:  
* @author：zhaodahai  
* @date 2018年9月11日  上午10:14:33
*/

@RestController
public class HelloController {

	private static final Logger  logger = LoggerFactory.getLogger(HelloController.class);
	
	@Autowired
	private HelloService helloService;
	
	@GetMapping("/hello/{name}")
	public String hello(@PathVariable String name) {
		logger.info("controller入参:" + name);
		return helloService.hello(name);
	}
	
	@GetMapping("/hello/info")
	public String info(@RequestParam String name,@RequestParam Integer age) {
		return helloService.info(name, age);
	}
}
