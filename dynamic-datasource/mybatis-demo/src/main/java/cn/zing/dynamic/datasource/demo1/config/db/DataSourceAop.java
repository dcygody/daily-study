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

    @Pointcut("@annotation(cn.zing.dynamic.datasource.demo1.config.db.DSM)")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void cut(JoinPoint joinPoint) throws Throwable {
        System.out.println("------------> 进行AOP");

        //1.1获取目标对象对应的字节码对象
//        Class<?> targetCls = joinPoint.getTarget().getClass();
        // 找的是实现类
//        System.out.println("--------------->" + targetCls.getCanonicalName());
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DSM dsm = method.getAnnotation(DSM.class);
        if (null != dsm) {
            DBContextHolder.set(dsm.value());
        }
    }
}



