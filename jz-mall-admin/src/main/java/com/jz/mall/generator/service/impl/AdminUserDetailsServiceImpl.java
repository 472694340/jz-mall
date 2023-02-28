package com.jz.mall.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jz.mall.generator.dto.AdminUserDetails;
import com.jz.mall.generator.mapper.UmsAdminMapper;
import com.jz.mall.generator.mapper.UmsPermissionMapper;
import com.jz.mall.generator.model.UmsAdmin;
import com.jz.mall.generator.model.UmsPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 江南
 */
//@Service
// public class AdminUserDetailsServiceImpl implements UserDetailsService
public class AdminUserDetailsServiceImpl {

//    @Autowired
//    UmsAdminMapper umsAdminMapper;
//
//    @Autowired
//    UmsPermissionMapper umsPermissionMapper;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        List<UmsAdmin> list = umsAdminMapper.selectList(new QueryWrapper<UmsAdmin>().eq("username", username));
//        AdminUserDetails userAdminDetails = new AdminUserDetails();
////        if (!list.isEmpty()){
////            UmsAdmin admin = list.get(0);
////            List<UmsPermission> permissionList = umsAdminMapper.getPermissionList(admin.getId());
////            userAdminDetails.setUmsAdmin(admin);
////            userAdminDetails.setPermissions(permissionList);
////        }
//        return userAdminDetails;
//    }
}
