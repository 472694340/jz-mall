package com.jz.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * 如果想启动项目排查数据源检查,那么
 * 1.注释掉pom文件里druid数据源连接 注释掉MySQL 和MyBatis依赖注释掉
 * 2.需要加上 (exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
 *
 */
@SpringBootApplication
public class JzMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(JzMallApplication.class, args);
    }

}
