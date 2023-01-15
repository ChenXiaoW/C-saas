package com.chenw.base.common.datasource.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MybatisConfig
 * @Description: MybatisConfig
 * @Author ChenXiaoW
 * @Date 2023/01/06 - 22:29
 */
@Configuration
@MapperScan(basePackages = {"com.chenw.**.dao"})
public class MybatisConfig {

    @Resource
    @Qualifier(DataSources.MASTER)
    private DataSource masterDataSource;

    @Bean(name = "dynamicDataSource")
    protected DataSource dynamicDataSource(){
        Map<Object,Object> dsMap = new HashMap<>(3);
        dsMap.put(DataSources.MASTER,masterDataSource);
        DynamicDataSource dynamicDataSource = new DynamicDataSource(masterDataSource,dsMap);
        return dynamicDataSource;
    }

    /**
     * sqlSessionFactory
     * @param logEnable - 日志打印
     * @param dynamicDataSource 多数据源
     * @param mybatisPlusInterceptor 分页拦截
     * @return
     * @throws IOException
     * @throws SQLException
     */
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(@Value("${mybatis-log.enable:false}")Boolean logEnable,
                                                              DataSource   dynamicDataSource,
                                                              MybatisPlusInterceptor mybatisPlusInterceptor
    ) throws IOException, SQLException {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        //装载数据源
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        if (logEnable){
            //SQL日志打印
            configuration.setLogImpl(StdOutImpl.class);
        }
        //加入分页拦截
        configuration.addInterceptor(mybatisPlusInterceptor);
        sqlSessionFactoryBean.setConfiguration(configuration);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mapper/**/*.xml"));
        return sqlSessionFactoryBean;
    }

    /**
     * 分页拦截
     * @return
     */
    @Bean(name = "mybatisPlusInterceptor")
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        // 设置最大单页限制数量，-1（不受限制）
        paginationInnerInterceptor.setMaxLimit(-1L);
        //设置请求的页面大于最大页面后的操作：true(回调到第一页)，false(继续请求)
        paginationInnerInterceptor.setOverflow(false);
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        return interceptor;
    }

    /**
     * 事务管理器
     *
     * @param dynamicDataSource 数据源
     * @return
     * @throws SQLException
     */
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(DataSource   dynamicDataSource) throws SQLException {
        return new DataSourceTransactionManager(dynamicDataSource);
    }

}
