package com.jz.mall.core.domain.dto;

import com.jz.mall.core.domain.model.UmsAdmin;
import com.jz.mall.core.domain.model.UmsPermission;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 会将信息存入这里
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
        permissions.stream().filter(o->o.getValue()!=null)
                .forEach(k->authorities.add(new SimpleGrantedAuthority(k.getValue())));
//        for (UmsPermission permission : permissions) {
//            if (permission.getValue() !=null){
//                authorities.add(new SimpleGrantedAuthority(permission.getValue()));
//            }
//        }
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
