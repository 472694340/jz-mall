package com.jz.mall.generator.service;

import com.jz.mall.generator.model.UmsAdmin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jz.mall.generator.model.UmsPermission;

import java.util.List;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author ShenLiang
 * @since 2023-02-21
 */
public interface UmsAdminService extends IService<UmsAdmin> {

    UmsAdmin getUserByName(String username);

    List<UmsPermission> getPermissionList(Long adminId);

    String login(String username, String password);

    UmsAdmin register(UmsAdmin umsAdminParam);
}
