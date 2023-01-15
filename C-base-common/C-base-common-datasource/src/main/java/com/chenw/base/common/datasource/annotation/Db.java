package com.chenw.base.common.datasource.annotation;

import com.chenw.base.common.datasource.config.DataSources;

import java.lang.annotation.*;

/**
 * @author chenw
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Db {

    /**
     * 数据库名称，默认主库
     * @return
     */
    String value() default DataSources.MASTER;
}
