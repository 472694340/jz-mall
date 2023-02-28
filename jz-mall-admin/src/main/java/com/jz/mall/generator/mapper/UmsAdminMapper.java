package com.jz.mall.generator.mapper;

import com.jz.mall.generator.model.UmsAdmin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jz.mall.generator.model.UmsPermission;

import java.util.List;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author ShenLiang
 * @since 2023-02-21
 */
public interface UmsAdminMapper extends BaseMapper<UmsAdmin> {

    List<UmsPermission> getPermissionList(Long adminId);
}
