package springboot.myabtis.multidatasource.module.dao.admin;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/8/30 15:05
 **/

@Component
@Mapper
public interface AdminDao {

    Map<String,Object> admin();
}
