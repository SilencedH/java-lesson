package springboot.myabtis.multidatasource.module.dao.blog;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/8/30 15:04
 **/
@Component
@Mapper
public interface BlogDao {
    Map<String,Object> blog();
}
