package net.youzule.spring.chapter05.app4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.print.Doc;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/6 18:37
 **/

@Configuration
public class DogConfig {

    @Bean
    public Dog dog(){
        return new Dog("dog",11);
    }
}
