package net.youzule.java.dubbo.db.service.module.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.youzule.java.dubbo.db.api.DubboDbService;

/**  
* @Title: DubboDbServiceImpl.java
* @Description:  
* @author：zhaodahai  
* @date 2018年9月12日  下午12:03:10
*/


@Service("dubboDbService")
public class DubboDbServiceImpl implements DubboDbService{

	private static final Logger logger = LoggerFactory.getLogger(DubboDbServiceImpl.class);
	
	@Override
	public String sayHello(String name) {
		logger.info("dubboDbService入参:" + name);
		return "Hello!" + name;
	}


	@Override
	public String info(String name, int age) {
		
		return "Your name is: " + name + "/t" + ",and your age is: " + age;
	}

}
