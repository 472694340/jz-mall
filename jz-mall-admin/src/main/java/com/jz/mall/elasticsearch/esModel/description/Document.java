package com.jz.mall.elasticsearch.esModel.description;

/**
 * 这@Document @Field @Id enum FieldType 几个文档都是只用作说明,说明用处,不做引用
 * 在Elasticsearch中,存储数据是以文档的形式存储,类似于java中的对象
 *  对象中有属性 属性就是一个索引(表)
 *   索引中的元素 就是存储的具体对象
 */
public @interface Document {
    //索引库名次，mysql中数据库的概念
    String indexName();
    //文档类型，mysql中表的概念
    String type() default "";
    //默认分片数
    short shards() default 5;
    //默认副本数量
    short replicas() default 1;
}
