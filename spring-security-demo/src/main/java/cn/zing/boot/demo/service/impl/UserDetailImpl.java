package cn.zing.boot.demo.service.impl;

import cn.zing.boot.demo.bean.LoginUser;
import cn.zing.boot.demo.bean.SysUser;
import cn.zing.boot.demo.context.AuthenticationContextHolder;
import cn.zing.boot.demo.exception.ServiceException;
import cn.zing.boot.demo.mapper.SysUserMapper;
import cn.zing.boot.demo.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Set;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-08 10:00
 */
@Service
@Slf4j
public class UserDetailImpl implements UserDetailsService {

    @Resource
    SysUserMapper sysUserMapper;

    @Autowired
    SysPermissionService permissionService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // 1 根据用户名 获取用户
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUser::getUserName, username);
        SysUser userInfo = sysUserMapper.selectOne(lambdaQueryWrapper);
        if (Objects.isNull(userInfo)) {
            log.error("用户 {} 不存在", username);
            throw new ServiceException("账户不存在！");
        }
        Authentication authentication = AuthenticationContextHolder.getContext();
        String password = authentication.getCredentials().toString();
        // 密码校验
        if (!passwordEncoder.matches(password, userInfo.getPassword())) {
            log.error("用户 {} 密码错误！", username);
            throw new ServiceException("密码错误！");
        }
        // 2.  鉴权
        Set<String> permissions = permissionService.getMenuPermission(userInfo);
        // 3. 返回
        return new LoginUser(StringUtils.uuid(), userInfo, permissions);
    }
}


