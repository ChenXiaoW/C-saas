package com.chenw.base.common.datasource.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName DynamicDataSourceContextHolder
 * @Description:  动态数据源上下文
 * @Author chenw
 * @Date 2022/11/12
 * @Version
**/

public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<String> CONTEXT_HOLDER_EXE = new ThreadLocal<String>() {
        /**
         * 将 MASTER_DB 数据源的 key作为默认数据源的 key
         */
        @Override
        protected String initialValue() {
            return DataSources.MASTER;
        }
    };

    /**
     数据源的 key集合，用于切换时判断数据源是否存在
     */
    private static List<Object> dataSourceKeys = new ArrayList<>();

    /**
     切换数据源
     @param key String
     */
    public static void setDataSourceKey(String key) {
        CONTEXT_HOLDER_EXE.set(key);
    }

    /**
     获取数据源
     @return String
     */
    public static String getDataSourceKey() {
        return CONTEXT_HOLDER_EXE.get();
    }

    /**
     * 获取主数据源
     * @return
     */
    public static String getMainDataSourceKey() {
        return CONTEXT_HOLDER_EXE.get();
    }

    /**
     重置数据源
     */
    public static void clearDataSourceKey() {
        CONTEXT_HOLDER_EXE.remove();
    }

    /**
     判断是否包含数据源
     @param key 数据源key
     @return boolean
     */
    public static boolean containDataSourceKey(String key) {
        return dataSourceKeys.contains(key);
    }

    /**
     添加数据源keys
     @param keys Collection
     @return boolean
     */
    public static boolean addDataSourceKeys(Collection<?> keys) {
        return dataSourceKeys.addAll(keys);
    }
}
