package cn.zing.boot.demo.service.impl;

import cn.zing.boot.demo.bean.LoginUser;
import cn.zing.boot.demo.bean.SysUser;
import cn.zing.boot.demo.context.AuthenticationContextHolder;
import cn.zing.boot.demo.service.LoginService;
import cn.zing.boot.demo.service.SysRoleService;
import cn.zing.boot.demo.util.SecurityUtils;
import cn.zing.boot.demo.vo.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-08 11:05
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private TokenService tokenService;

    @Override
    public AjaxResult login(SysUser user) {

        //3使用ProviderManager auth方法进行验证
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        AuthenticationContextHolder.setContext(usernamePasswordAuthenticationToken);
        String token = null;
        try {
            // 调用UserDetailImpl.loadUserByUsername
            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            //4自己生成jwt给前端
            LoginUser loginUser = (LoginUser) (authenticate.getPrincipal());
            token = tokenService.createToken(loginUser);
        } finally {
            AuthenticationContextHolder.clearContext();
        }

        Map<String, String> map = new HashMap<>();
        map.put("token", token);

        return AjaxResult.success(map);

    }

    @Override
    public AjaxResult logout() {

        LoginUser loginUser = SecurityUtils.getLoginUser();
        tokenService.delLoginUser(loginUser);
        return AjaxResult.success("退出成功");
    }

    /**
     * 用户信息
     *
     * @return
     */
    @Override
    public AjaxResult info() {
        LoginUser user = SecurityUtils.getLoginUser();
        HashMap<String, Object> responseData = new HashMap<>();
        responseData.put("name", user.getUserInfo().getUserName());
        Set<String> permissions = user.getPermissions();
        responseData.put("perms", permissions);
        if (user.getUserInfo().isAdmin()) {
            responseData.put("roles", "admin");
        } else {
            List<String> roles = roleService.selectRoleNameByUserId(user.getUserInfo().getId());
            responseData.put("roles", roles);
        }
        responseData.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return AjaxResult.success(responseData);
    }
}


