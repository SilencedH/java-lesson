package net.youzule.spring.chapter05.app4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/6 18:42
 **/

/*CatConfig类*/
@Configuration
public class CatConfig {

    @Bean(name = "cat1")
    public Cat cat(){
        Cat cat = new Cat();
        cat.setHome("jy");
        cat.setType("ju");
        return cat;
    }
}
