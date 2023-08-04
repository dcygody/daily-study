package cn.zing.boot.demo.bean;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description: 用户登录信息
 * @author: dcy
 * @create: 2023-07-07 17:12
 */
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    /**
     * 用户标识
     */
    private String uuid;

    /**
     * 过期时间
     */
    private Long expireTime;


    private SysUser userInfo;

    private Set<String> permissions;

    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    public LoginUser(String uuid, SysUser userInfo, Set<String> permissions) {
        this.uuid = uuid;
        this.userInfo = userInfo;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (Objects.nonNull(authorities)) {
            return authorities;
        }
        if (Objects.nonNull(permissions)) {
            authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return userInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return userInfo.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


