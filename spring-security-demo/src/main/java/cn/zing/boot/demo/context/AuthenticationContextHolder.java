package cn.zing.boot.demo.context;

import org.springframework.security.core.Authentication;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-09 22:59
 */
public class AuthenticationContextHolder {

    private static final ThreadLocal<Authentication> contextHolder = new ThreadLocal<>();

    public static Authentication getContext() {
        return contextHolder.get();
    }

    public static void setContext(Authentication context) {
        contextHolder.set(context);
    }

    public static void clearContext() {
        contextHolder.remove();
    }
}


