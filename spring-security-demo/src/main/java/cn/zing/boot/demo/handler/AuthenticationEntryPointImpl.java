package cn.zing.boot.demo.handler;

import cn.zing.boot.demo.constant.HttpStatus;
import cn.zing.boot.demo.exception.ServiceException;
import cn.zing.boot.demo.util.WebUtils;
import cn.zing.boot.demo.vo.AjaxResult;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-08 15:07
 */
@Component
@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException ex) throws IOException, ServletException {
        // 给前端AjaxResult 的json
        AjaxResult result = null;
        if (ex instanceof InternalAuthenticationServiceException) {
            InternalAuthenticationServiceException exception = (InternalAuthenticationServiceException) ex;
            if (exception.getCause() != null) {
                //判断抛出的是否是自己定义的异常
                if (exception.getCause() instanceof ServiceException) {
                    //自定义异常
                    ServiceException serviceException = (ServiceException) exception.getCause();
                    result = AjaxResult.error(HttpStatus.ERROR_PASS, serviceException.getMessage());
                } else {
                    result = AjaxResult.error(HttpStatus.ERROR, "系统内部错误！");
                }
            }
        } else {
            result = AjaxResult.error(HttpStatus.TOKEN_EXP, "无权访问！");
        }
        WebUtils.renderString(response, JSON.toJSONString(result));


    }
}


