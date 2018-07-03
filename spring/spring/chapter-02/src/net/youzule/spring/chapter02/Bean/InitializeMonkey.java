package net.youzule.spring.chapter02.Bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.Order;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/2 19:44
 **/

public class InitializeMonkey implements BeanPostProcessor {
    @Order(2)
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("monkey".equals(beanName)){
            System.out.println("bean monkeyPostProcessBeforeInitialization-beanName:" + beanName);
        }
        return bean;
    }
    @Order(2)
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if("monkey".equals(beanName)){
            System.out.println("bean monkeyPostProcessAfterInitialization:" + beanName);
        }
        return bean;
    }
}
