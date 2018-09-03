package net.youzule.springboot.dynamicdatsource.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/8/30 14:36
 **/

@Configuration
public class DataSourceConfig {

    @Bean(name = "dataSourceMaster")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource dataSourceMaster(){
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "dataSourceSlave")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource dataSourceSlave(){
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(value = "dynamicDataSource")
    public DataSource dynamicDataSource (){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(dataSourceMaster());

        Map<Object,Object> dsMap = new HashMap<>(5);

        dsMap.put("dataSourceMaster",dataSourceMaster());
        dsMap.put("dataSourceSlave",dataSourceSlave());
                
        dynamicDataSource.setTargetDataSources(dsMap);
        
       // dynamicDataSource.setDataSourceLookup(dataSourceLookup);
        
        return dynamicDataSource;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

}
