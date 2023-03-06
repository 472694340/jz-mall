package com.jz.mall.elasticsearch.esModel.description;

/**
 * 相当于MySQL数据中 对象元素里的属性
 */
public @interface Field {
    //文档中字段的类型
    FieldType type() default FieldType.Auto;
    //是否建立倒排索引
    boolean index() default true;
    //是否进行存储
    boolean store() default false;
    //分词器名次
    String analyzer() default "";
}
