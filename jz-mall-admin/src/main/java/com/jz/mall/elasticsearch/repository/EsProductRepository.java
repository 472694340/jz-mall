package com.jz.mall.elasticsearch.repository;

import com.jz.mall.elasticsearch.esModel.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 通过继承 ElasticsearchRepository 接口,可以通过Spring 提供的一种以Spring Data风格来操作 数据库存储的方式 里面包含大量的样板代码
 *  继承ElasticsearchRepository接口，这样就拥有了一些基本的Elasticsearch数据操作方法，同时定义了一个衍生查询方法。
 */
public interface EsProductRepository extends ElasticsearchRepository<EsProduct,Long> {

    /**
     *  可通过以下三个参数 从ElasticSearch中查询数据
     * @param name 商品名称
     * @param subTitle 商品标题
     * @param keywords 商品关键字
     * @return page 分页信息
     */
    Page<EsProduct> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords,Pageable page);




    /**
     *这种方式好像不常用
     * @param name 商品名称
     * @return
     */
//    @Query("{"bool" : {"must" : {"field" : {"name" : "?0"}}}}")  //这种方式好像不常用
//    Page<EsProduct> findByName(String name, Pageable pageable);
}
