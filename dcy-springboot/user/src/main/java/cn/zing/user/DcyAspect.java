package cn.zing.user;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-24 22:09
 */
@Aspect
@Component
public class DcyAspect {

    @Before("execution(public * cn.zing.user.service.UserService.getUser())")
    public void dcyBefore(JoinPoint joinPoint) {
        System.out.println("dcyBefore");
    }
}


