package com.jz.mall.mybatisplus.service.impl;

import com.jz.mall.mybatisplus.model.PmsProductCategoryAttributeRelation;
import com.jz.mall.mybatisplus.mapper.PmsProductCategoryAttributeRelationMapper;
import com.jz.mall.mybatisplus.service.PmsProductCategoryAttributeRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类） 服务实现类
 * </p>
 *
 * @author ShenLiang
 * @since 2023-02-18
 */
@Service
public class PmsProductCategoryAttributeRelationServiceImpl extends ServiceImpl<PmsProductCategoryAttributeRelationMapper, PmsProductCategoryAttributeRelation> implements PmsProductCategoryAttributeRelationService {

}
