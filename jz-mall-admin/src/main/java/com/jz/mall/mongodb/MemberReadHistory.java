package com.jz.mall.mongodb;

import com.jz.mall.elasticsearch.esModel.description.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 *  集成MongoDB 商品浏览记录
 *   与ElasticSearch 的集成方式很像,一个中间件,那是不是也会有 一个将中间件数据和数据库数据同步的一个服务类
 * @Document
 *     @AliasFor("collection")
 *     String value() default "";
 *
 *     @AliasFor("value")
 *     String collection() default "";
 *
 *     String language() default "";
 *
 * @Document:标示映射到Mongodb文档上的领域对象
 * @Id:标示某个域为ID域
 * @Indexed:标示某个字段为Mongodb的索引字段
 */
@Document
@Data
public class MemberReadHistory {
    @Id
    private String id;
    @Indexed
    private Long memberId;
    private String memberNickname;
    private String memberIcon;
    @Indexed
    private Long productId;
    private String productName;
    private String productPic;
    private String productSubTitle;
    private String productPrice;
    private Date createTime;
}
