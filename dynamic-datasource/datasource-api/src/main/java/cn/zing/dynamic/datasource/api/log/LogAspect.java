package cn.zing.dynamic.datasource.api.log;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-21 11:46
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(cn.zing.dynamic.datasource.api.log.DcyLog)")
    private void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DcyLog logAnnotation = method.getAnnotation(DcyLog.class);

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        //请求的方法名
        methodName = className + "." + methodName;
        // 请求参数
        Object[] args = joinPoint.getArgs();
        String logDesc = logAnnotation.value().equals("") ? "未知方法" : logAnnotation.value();
        // 响应参数
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        LogInfo logInfo = new LogInfo( methodName, JSON.toJSONString(args), logDesc, JSON.toJSONString(result), startTime, endTime);
        log.info("【记录日志】: {}", logInfo.toString());

        return result;
    }

    /**
     * 转换request参数
     */
    private String getRequestParams(Object[] paramsArray) throws Exception {
        StringBuilder params = new StringBuilder();
        if (paramsArray != null && paramsArray.length > 0) {
            for (Object o : paramsArray) {
                String jsonObj = JSON.toJSONString(o);
                params.append(jsonObj);
            }
        }
        return params.toString().trim();
    }
}


