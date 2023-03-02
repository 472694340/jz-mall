package com.jz.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//basePackages数据结构是一个字符数组,需要用{},不用好像也没事?暂时先这样认为
@MapperScan(basePackages = {"com.jz.mall.core.mapper"})//这里的注解等共同于主启动类中的@MapperScan ,指定的是要变成实现类的mapper接口所在的包
public class MyBatisConfig {
}
