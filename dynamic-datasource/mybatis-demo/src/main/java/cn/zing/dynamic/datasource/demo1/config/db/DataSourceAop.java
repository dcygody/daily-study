package cn.zing.dynamic.datasource.demo1.config.db;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-17 17:47
 */
@Aspect
@Component
public class DataSourceAop {

    @Pointcut("@within(cn.zing.dynamic.datasource.demo1.config.db.DSM)")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void cut(JoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 方法优先级高
        if (method.isAnnotationPresent(DSM.class)) {
            DSM dsm = method.getAnnotation(DSM.class);
            DBContextHolder.set(DBTypeEnum.valueOf(dsm.value().toUpperCase()));
            return;
        }
        Class<?> targetCls = joinPoint.getTarget().getClass();
//        找的是实现类
//        System.out.println("--------------->" + targetCls.getCanonicalName());
        if (targetCls.isAnnotationPresent(DSM.class)) {
            DSM dsm = targetCls.getAnnotation(DSM.class);
            DBContextHolder.set(DBTypeEnum.valueOf(dsm.value().toUpperCase()));
        }
    }
}



