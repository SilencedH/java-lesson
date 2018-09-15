package net.youzule.java.dubbo.http.module.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.youzule.java.dubbo.db.api.DubboDbService;

/**  
* @Title: HelloService.java
* @Description:  
* @author：zhaodahai  
* @date 2018年9月12日  下午1:59:37
*/

@Service
public class HelloService {
	@Autowired
	private DubboDbService dubboDbService;
	
	public String hello(String name) {
		return dubboDbService.sayHello(name);
	}
	
	public String info(String name,int age) {
		return dubboDbService.info(name, age);
	}
}
