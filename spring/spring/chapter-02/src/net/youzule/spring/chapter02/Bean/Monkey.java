package net.youzule.spring.chapter02.Bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/2 19:31
 **/

public class Monkey {

    private String name;

    public Monkey() {
        System.out.println("monkey constructor method...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void init(){
        System.out.println("init method...");
    }

    public void destroy(){
        System.out.println("destroy method...");
    }

    @Override
    public String toString() {
        return "Monkey{" +
                "name='" + name + '\'' +
                '}';
    }


}
