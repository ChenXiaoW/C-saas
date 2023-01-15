package com.chenw.base.common.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @ClassName: DataSourcesConfig
 * @Description: 数据源配置
 * @Author ChenXiaoW
 * @Date 2023/01/06 - 22:30
 */
@Configuration
public class DataSourcesConfig {

    /**
     * 主数据源
     * @return
     */
    @Bean(name = DataSources.MASTER)
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

}
