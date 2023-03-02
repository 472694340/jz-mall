package com.jz.mall.core.mapper;

import com.jz.mall.core.domain.model.UmsAdmin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jz.mall.core.domain.model.UmsPermission;
import com.jz.mall.core.domain.model.UmsRole;
import org.apache.ibatis.annotations.Param;

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

    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);

    List<UmsRole> getRoleList(@Param("id") Long id);
}
