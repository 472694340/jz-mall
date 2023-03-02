package com.jz.mall.core.mapper;

import com.jz.mall.core.domain.model.UmsMenu;
import com.jz.mall.core.domain.model.UmsRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 Mapper 接口
 * </p>
 *
 * @author ShenLiang
 * @since 2023-02-21
 */
public interface UmsRoleMapper extends BaseMapper<UmsRole> {

    List<UmsMenu> getMenusList(@Param("id") Long id);
}
