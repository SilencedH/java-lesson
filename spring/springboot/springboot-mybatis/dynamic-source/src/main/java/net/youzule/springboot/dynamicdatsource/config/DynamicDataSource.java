package net.youzule.springboot.dynamicdatsource.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/8/31 14:07
 **/

public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
    	logger.info("动态数据源为:" + DataSourceContextHolder.getDataSource());
        return DataSourceContextHolder.getDataSource();
    }



}
