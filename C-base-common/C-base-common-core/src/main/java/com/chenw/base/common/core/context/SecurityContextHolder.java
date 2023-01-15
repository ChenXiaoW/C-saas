package com.chenw.base.common.core.context;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.chenw.base.common.core.constant.SecurityConstants;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: UserContextHolder
 * @Description: 获取当前线程变量中的 用户id、用户名称、Token等信息
 * @Author ChenXiaoW
 * @Date 2023/01/08 - 21:00
 */
public class SecurityContextHolder {

    private static final TransmittableThreadLocal<Map<String, Object>> THREAD_LOCAL = new TransmittableThreadLocal<>();

    public static void set(String key, Object value) {
        Map<String, Object> map = getLocalMap();
        map.put(key, value == null ? StrUtil.EMPTY : value);
    }

    public static String get(String key) {
        Map<String, Object> map = getLocalMap();
        return Convert.toStr(map.getOrDefault(key, StrUtil.EMPTY));
    }

    public static <T> T get(String key, Class<T> clazz) {
        Map<String, Object> map = getLocalMap();
        Object orDefault = map.getOrDefault(key, null);
        return (T) orDefault;
    }

    public static Map<String, Object> getLocalMap() {
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new ConcurrentHashMap<String, Object>();
            THREAD_LOCAL.set(map);
        }
        return map;
    }

    public static void setLocalMap(Map<String, Object> threadLocalMap) {
        THREAD_LOCAL.set(threadLocalMap);
    }

    public static String getUserId() {
        return Convert.toStr(get(SecurityConstants.DETAILS_USER_ID), StrUtil.EMPTY);
    }

    public static void setUserId(String userId) {
        set(SecurityConstants.DETAILS_USER_ID, userId);
    }

    public static String getUserName() {
        return get(SecurityConstants.DETAILS_USERNAME);
    }

    public static void setUserName(String username) {
        set(SecurityConstants.DETAILS_USERNAME, username);
    }

    /**
     * 移除threadLocal缓存
     */
    public static void remove() {
        THREAD_LOCAL.remove();
    }

}
