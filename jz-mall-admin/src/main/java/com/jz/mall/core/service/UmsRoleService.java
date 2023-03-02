package com.jz.mall.core.service;

import com.jz.mall.core.domain.model.UmsMenu;
import com.jz.mall.core.domain.model.UmsRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务类
 * </p>
 *
 * @author ShenLiang
 * @since 2023-02-21
 */
public interface UmsRoleService extends IService<UmsRole> {

    List<UmsMenu> getMenusList(Long id);
}
