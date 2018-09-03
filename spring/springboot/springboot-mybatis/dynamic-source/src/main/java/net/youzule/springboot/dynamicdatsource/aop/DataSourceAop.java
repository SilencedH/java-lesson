package net.youzule.springboot.dynamicdatsource.aop;

import net.youzule.springboot.dynamicdatsource.annotation.DS;
import net.youzule.springboot.dynamicdatsource.config.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Method;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/9/3 10:30
 **/

@Aspect
@Component
public class DataSourceAop {

	private static final Logger logger = LoggerFactory.getLogger(DataSourceAop.class);
	
    @Before(value = "@annotation(DS)")
    public void beforeSwitchDataSource(JoinPoint joinPoint,DS DS){
    	logger.info("aop处理开始");
        //获得类名
        Class<?> className = joinPoint.getTarget().getClass();
        logger.info("className:"+className);
        //获得方法名
        String methodName = joinPoint.getSignature().getName();
        logger.info("methodName:" + methodName);
        //获得参数类型
        Class[] argClass = ((MethodSignature)(joinPoint.getSignature())).getParameterTypes();
        logger.info("argClass:{}",JSON.toJSONString(argClass));
        String dataSourceName = DataSourceContextHolder.DEFAULT_DATASOURCE;
        logger.info("默认dataSourceName:" + dataSourceName);

        try {
            Method method = className.getMethod(methodName,argClass);
            logger.info("判断是否有@DS注解");
            //判断是否有@DS注解存在
            if(method.isAnnotationPresent(DS.class)){
            	logger.info("有@DS注解");
                DS ds = method.getAnnotation(DS.class);
                //取出注解中的数据源名
                dataSourceName = ds.value();
                logger.info("注解中dataSourceName:" + dataSourceName);
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //切换数据源
        logger.info("切换到数据源:" + dataSourceName);
        DataSourceContextHolder.setDataSource(dataSourceName);
        logger.info("当前线程数据源是:" + DataSourceContextHolder.getDataSource());
    }

    @After("@annotation(DS)")
    public void afterSwitchDataSource(JoinPoint joinPoint,DS DS){
    	logger.info("移除当前数据源" + DataSourceContextHolder.getDataSource());
        DataSourceContextHolder.removeDataSource();
    }
}
