package com.jz.mall.elasticsearch.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jz.mall.elasticsearch.esModel.EsProduct;

import java.util.List;

public interface EsProductDao extends BaseMapper<EsProduct> {
    List<EsProduct> selectFormDataBase();
}
