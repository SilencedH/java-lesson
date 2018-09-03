package net.youzule.springboot.dynamicdatsource.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserDao {
	Map<String,Object> queryUser();
}
