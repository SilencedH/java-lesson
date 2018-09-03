package springboot.myabtis.multidatasource.module.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.myabtis.multidatasource.module.dao.admin.AdminDao;
import springboot.myabtis.multidatasource.module.dao.blog.BlogDao;

import java.util.Map;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/8/30 15:23
 **/

@RestController
public class IndexController {

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private AdminDao adminDao;


    @GetMapping("/blog")
    public Map<String,Object> blog(){
        System.out.println("blog");
        System.out.println(blogDao.blog());
        return blogDao.blog();
    }

    @GetMapping("/admin")
    public Map<String,Object> admin(){
        System.out.println(adminDao.admin());
        return adminDao.admin();
    }
}
