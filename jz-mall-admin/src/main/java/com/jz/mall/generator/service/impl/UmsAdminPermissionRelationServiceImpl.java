package com.jz.mall.generator.service.impl;

import com.jz.mall.generator.model.UmsAdminPermissionRelation;
import com.jz.mall.generator.mapper.UmsAdminPermissionRelationMapper;
import com.jz.mall.generator.service.UmsAdminPermissionRelationService;
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
