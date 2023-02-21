package com.jz.mall.generator.service.impl;

import com.jz.mall.generator.model.PmsProductCategoryAttributeRelation;
import com.jz.mall.generator.mapper.PmsProductCategoryAttributeRelationMapper;
import com.jz.mall.generator.service.PmsProductCategoryAttributeRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类） 服务实现类
 * </p>
 *
 * @author ShenLiang
 * @since 2023-02-21
 */
@Service
public class PmsProductCategoryAttributeRelationServiceImpl extends ServiceImpl<PmsProductCategoryAttributeRelationMapper, PmsProductCategoryAttributeRelation> implements PmsProductCategoryAttributeRelationService {

}
