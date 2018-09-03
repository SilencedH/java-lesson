package net.youzule.springboot.dynamicdatsource.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.youzule.springboot.dynamicdatsource.annotation.DS;
import net.youzule.springboot.dynamicdatsource.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@DS
	@GetMapping("/admin")
	public Map<String,Object> queryBlogUser(){
		return userService.queryUser();
	}
	@DS(value = "dataSourceSlave")
	@GetMapping("/blog")
	public Map<String,Object> queryAdminUser(){
		return userService.queryUser();
	}
	
}
