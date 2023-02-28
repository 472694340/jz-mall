package com.jz.mall.generator.dto;

import com.jz.mall.generator.model.UmsAdmin;
import com.jz.mall.generator.model.UmsPermission;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 在初次登录时,会将信息存入这里
 */
@Data
public class AdminUserDetails implements UserDetails {
    private UmsAdmin umsAdmin;
    private List<UmsPermission> permissions;

    public AdminUserDetails(UmsAdmin umsAdmin, List<UmsPermission> permissions) {
        this.umsAdmin = umsAdmin;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (UmsPermission permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission.getValue()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return umsAdmin.getStatus() == 1 ? true:false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return umsAdmin.getStatus() == 1 ? true:false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return umsAdmin.getStatus() == 1 ? true:false;
    }

    @Override
    public boolean isEnabled() {
        return umsAdmin.getStatus() == 1 ? true:false;
    }

    public AdminUserDetails() {
    }

}
