package com.chenw.base.common.datasource.aspectj;

import com.chenw.base.common.datasource.annotation.Db;
import com.chenw.base.common.datasource.config.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @ClassName: DbAspectj
 * @Description: @Db 注解拦截
 * @Author ChenXiaoW
 * @Date 2023/01/06 - 23:23
 */
@Slf4j
@Aspect
@Order(-1)
@Component
public class DbAspectj {

    /**
     * 类切面
     *
     * @param db
     */
    @Pointcut(value = "@within(db)")
    public void classPointCut(Db db) {
    }

    /**
     * 方法切面
     *
     * @param db
     */
    @Pointcut(value = "@annotation(db)")
    public void methodPointCut(Db db) {
    }

    @Around(value = "classPointCut(db) || methodPointCut(db)")
    public Object dataSourceSwitcher(ProceedingJoinPoint point, Db db) throws Throwable {
        try {
            DynamicDataSourceContextHolder.setDataSourceKey(db.value());
            // 执行业务方法
            return point.proceed();
        } finally {
            DynamicDataSourceContextHolder.clearDataSourceKey();
        }
    }
}
