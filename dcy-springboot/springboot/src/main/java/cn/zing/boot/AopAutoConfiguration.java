package cn.zing.boot;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-24 21:00
 */
@Configuration
public class AopAutoConfiguration implements AutoConfiguration{

    @Configuration
    @EnableAspectJAutoProxy
    @DcyConditionOnClass("org.aspectj.weaver.Advice")
    static class AspectjConfiguration {

    }


}


