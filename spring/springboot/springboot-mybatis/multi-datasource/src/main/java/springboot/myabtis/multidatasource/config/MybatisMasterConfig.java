package springboot.myabtis.multidatasource.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/8/30 15:03
 **/

@Configuration
@MapperScan(basePackages = {"springboot.myabtis.multidatasource.module.dao.blog"},sqlSessionFactoryRef = "sqlSessionFactoryMaster",sqlSessionTemplateRef = "sqlSessionTemplateMaster")
public class MybatisMasterConfig {

    @Autowired
    @Qualifier(value = "dataSourceMaster")
    private DataSource dataSourceMaster;


    @Bean("sqlSessionFactoryMaster")
    public SqlSessionFactory sqlSessionFactoryMaster() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceMaster);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/blog/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean("sqlSessionTemplateMaster")
    public SqlSessionTemplate sqlSessionTemplateMaster() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactoryMaster());
        return sqlSessionTemplate;
    }
}
