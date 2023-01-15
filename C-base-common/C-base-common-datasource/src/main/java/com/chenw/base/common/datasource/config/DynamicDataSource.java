package com.chenw.base.common.datasource.config;

import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.util.Map;

/**
 * @ClassName: DynamicDataSource
 * @Description: DynamicDataSource
 * @Author ChenXiaoW
 * @Date 2023/01/06 - 22:50
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 数据源列表
     */
    private Map<Object, Object> dynamicTargetDataSources;

    DynamicDataSource(DataSource defaultTargetDataSource,Map<Object,Object> targetDataSources){
        // 设置默认数据源
        setDefaultTargetDataSource(defaultTargetDataSource);
        // 设置多数据源
        setTargetDataSources(targetDataSources);
        this.dynamicTargetDataSources = targetDataSources;
        super.afterPropertiesSet();
    }

    /**
     * determineTargetDataSource中可以看到最终使用的DataSource对象是从 dynamicTargetDataSources 中获取的
     * @return
     */
    @Override
    protected DataSource determineTargetDataSource() {
        return super.determineTargetDataSource();
    }

    /**
     * 该方法决定了使用哪个数据源
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceKey = DynamicDataSourceContextHolder.getDataSourceKey();
        if (!StrUtil.isEmpty(dataSourceKey) ){
            if (dynamicTargetDataSources.containsKey(dataSourceKey)){
                log.debug("当前数据源为：{}",dataSourceKey);
            }else {
                log.debug("不存在数据源：{}",dataSourceKey);
            }
        }else {
            log.debug("当前数据源为默认数据源");
        }
        return dataSourceKey;
    }

    /**
     * 指定默认数据源
     * @param defaultTargetDataSource
     */
    @Override
    public void setDefaultTargetDataSource(Object defaultTargetDataSource) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
    }

    /**
     * 指定多数据源
     * @param targetDataSources
     */
    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        this.dynamicTargetDataSources = targetDataSources;
        DynamicDataSourceContextHolder.addDataSourceKeys(targetDataSources.keySet());
    }

}
