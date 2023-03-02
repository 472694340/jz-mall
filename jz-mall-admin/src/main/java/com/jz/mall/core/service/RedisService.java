package com.jz.mall.core.service;

/**
 * 自定义Redis常用方法
 *  对象和数组都以json形式存储
 *
 *  接口的定义:提供方法供实现类去实现功能
 *  抽象类的方法是,提供模板方法,继承模板功能方法(抽象类中里面可以有实现)
 *
 */
public interface RedisService {

    /**存放数据*/
    void set(String key ,String value);

    /**获取数据*/
    String get(String key);

    /**设置过期时间*/
    boolean setExpire(String key,Long expire);

    /**删除数据*/
    void remove(String key);

    /**自增操作*/
    Long increment(String key,long delta);
}
