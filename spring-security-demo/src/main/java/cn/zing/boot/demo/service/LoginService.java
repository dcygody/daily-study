package cn.zing.boot.demo.service;

import cn.zing.boot.demo.bean.SysUser;
import cn.zing.boot.demo.vo.AjaxResult;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-08 10:27
 */
public interface LoginService {

    AjaxResult login(SysUser userInfo);

    AjaxResult logout();

    AjaxResult info();
}


