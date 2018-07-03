package net.youzule.spring.chapter02.Bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.Order;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/2 19:52
 **/

public class InitializeDuck implements BeanPostProcessor {
    @Order(value = 1)
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("duck".equals(beanName)){
            System.out.println("bean duckPostProcessBeforeInitialization-beanName:" + beanName);
        }
        return bean;
    }
    @Order(value = 1)
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if("duck".equals(beanName)){
            System.out.println("bean duckPostProcessAfterInitialization:" + beanName);
        }
        return bean;
    }
}
