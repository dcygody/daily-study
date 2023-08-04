package cn.zing.boot.demo.handler;

import cn.zing.boot.demo.constant.HttpStatus;
import cn.zing.boot.demo.util.WebUtils;
import cn.zing.boot.demo.vo.AjaxResult;
import com.alibaba.fastjson2.JSON;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-08 15:11
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 给前端AjaxResult 的json
        AjaxResult result = AjaxResult.error(HttpStatus.FORBIDDEN, "未授权!");
        WebUtils.renderString(response, JSON.toJSONString(result));

    }
}


