package com.jz.mall.elasticsearch.service;

import com.jz.mall.elasticsearch.esModel.EsProduct;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 *  商品管理:定义一些增删改查的方法,供实现
 *
 *
 */
public interface EsProductService {
    /**
     * 添加商品(从数据库导入商品到ES)
     */
    int importAll();

    /**
     * 根据id删除商品
     * @param id
     */
    void deleteProduct(Long id);

    /**
     * 根据id创建商品 (这里的id是包含什么?)
     */
    EsProduct createProduct(Long id);

    /**
     * 批量删除商品
     */
    void delete(List<Long> ids);

    /**
     * 根据关键字搜索名称或者副标题
     */
    Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);


}
