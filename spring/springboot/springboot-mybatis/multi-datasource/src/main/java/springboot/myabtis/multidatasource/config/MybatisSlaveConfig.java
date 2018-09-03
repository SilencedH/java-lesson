package springboot.myabtis.multidatasource.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/8/30 15:03
 **/

@Configuration
@MapperScan(basePackages = {"springboot.myabtis.multidatasource.module.dao.admin"},sqlSessionFactoryRef = "sqlSessionFactorySlave")
public class MybatisSlaveConfig {

    @Autowired
    @Qualifier(value = "dataSourceSlave")
    private DataSource dataSourceSlave;


    @Bean
    public SqlSessionFactory sqlSessionFactorySlave() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceSlave);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/admin/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplateSlave() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactorySlave());
        return sqlSessionTemplate;
    }
}
