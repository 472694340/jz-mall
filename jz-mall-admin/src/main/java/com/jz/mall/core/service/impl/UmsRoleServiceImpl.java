package com.jz.mall.core.service.impl;

import com.jz.mall.core.domain.model.UmsMenu;
import com.jz.mall.core.domain.model.UmsRole;
import com.jz.mall.core.mapper.UmsRoleMapper;
import com.jz.mall.core.service.UmsRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务实现类
 * </p>
 *
 * @author ShenLiang
 * @since 2023-02-21
 */
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements UmsRoleService {

    @Autowired
    UmsRoleMapper umsRoleMapper;

    @Override
    public List<UmsMenu> getMenusList(Long id) {
        List<UmsMenu> umsRoles = umsRoleMapper.getMenusList(id);
        return umsRoles;
    }
}
