package com.chenw.base.common.security.dto;

import com.chenw.base.common.security.utils.JwtUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: UserSecurityDTO
 * @Description: UserSecurityDTO
 * @Author ChenXiaoW
 * @Date 2023/02/06 - 23:06
 */
public class UserSecurityDTO implements UserDetails {

    private String id;

    private String userName;

    private String password;

    private Boolean state;

    private Set<String> permissionCodes;

    private UserSecurityDTO(){};

    public UserSecurityDTO(String id ,String userName,Boolean state,Set<String> permissionCodes){
        this.id = id;
        this.userName = userName;
        this.state = state;
        this.permissionCodes = permissionCodes;
    };

    /**
     * 设置权限
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        for (String permissionCode : permissionCodes) {
            authorities.add(new SimpleGrantedAuthority(permissionCode));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * 账户是否过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * 账户是否锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     * 凭证是否过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * 是否启用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return state;
    }
}
