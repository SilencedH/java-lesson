package net.youzule.springboot.dynamicdatsource.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/8/31 14:00
 **/

public class DataSourceContextHolder {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceContextHolder.class);

    public static final String DEFAULT_DATASOURCE = "dataSourceMaster";

    public static ThreadLocal<String> contextHolder = new ThreadLocal<>();

    //设置数据源名
    public static void setDataSource(String dataSourceName){
        logger.info("切换到数据源:" + dataSourceName);
        contextHolder.set(dataSourceName);
    }

    //获取数据源
    public static String getDataSource(){
        return contextHolder.get();
    }

    //清除数据源
    public static void removeDataSource(){
        contextHolder.remove();
    }
}
