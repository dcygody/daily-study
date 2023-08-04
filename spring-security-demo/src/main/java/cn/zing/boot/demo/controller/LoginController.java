package cn.zing.boot.demo.controller;

import cn.zing.boot.demo.bean.SysUser;
import cn.zing.boot.demo.service.LoginService;
import cn.zing.boot.demo.vo.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-08 10:29
 */
@RestController
@RequestMapping("/user")
public class LoginController extends BaseController{


    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public AjaxResult login(@RequestBody SysUser loginUser) {

        logger.info("登录: {}", loginUser.toString());
        return loginService.login(loginUser);

    }

    @GetMapping("/info")
    public AjaxResult info() {

        return loginService.info();
    }

    @PostMapping("/logout")
    public AjaxResult logout() {
        return loginService.logout();

    }
}


