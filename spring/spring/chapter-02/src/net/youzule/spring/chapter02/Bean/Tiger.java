package net.youzule.spring.chapter02.Bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/6/30 18:14
 **/

public class Tiger implements InitializingBean,DisposableBean {

    private String legs;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("initialize call back");
    }

    public Tiger() {
        System.out.println("initializing a tiger...");
    }

    public String getLegs() {
        return legs;
    }

    public void setLegs(String legs) {
        this.legs = legs;
    }

    @Override
    public String toString() {
        return "Tiger{" +
                "legs='" + legs + '\'' +
                '}';
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destory call back");
    }
}
