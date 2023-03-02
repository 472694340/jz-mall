package com.jz.mall.core.service.impl;

import com.jz.mall.core.domain.model.UmsAdminPermissionRelation;
import com.jz.mall.core.mapper.UmsAdminPermissionRelationMapper;
import com.jz.mall.core.service.UmsAdminPermissionRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限) 服务实现类
 * </p>
 *
 * @author ShenLiang
 * @since 2023-02-21
 */
@Service
public class UmsAdminPermissionRelationServiceImpl extends ServiceImpl<UmsAdminPermissionRelationMapper, UmsAdminPermissionRelation> implements UmsAdminPermissionRelationService {

}
