package net.youzule.springboot.dynamicdatsource.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.youzule.springboot.dynamicdatsource.annotation.DS;
import net.youzule.springboot.dynamicdatsource.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	

	@Override
	public Map<String, Object> queryUser() {
		// TODO Auto-generated method stub
		return userDao.queryUser();
	}

}
