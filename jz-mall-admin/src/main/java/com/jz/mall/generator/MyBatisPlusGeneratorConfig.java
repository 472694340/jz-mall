package com.jz.mall.generator;

import cn.hutool.setting.dialect.Props;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import freemarker.template.Template;

import java.util.Collections;

public class MyBatisPlusGeneratorConfig {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");// 获取当前用户的目录
        // 6、整合配置
        AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfig());// 构建代码生自动成器对象,// 将数据源配置放到代码生成器对象中
        autoGenerator
                .global(globalConfig(projectPath))// 将全局配置放到代码生成器对象中
                .packageInfo(packageConfig(projectPath))// 将包配置放到代码生成器对象中
                .strategy(initStrategyConfig())// 将策略配置放到代码生成器对象中
                .template(initTemplateConfig())
                .injection(initInjectionConfig())
                .execute(new FreemarkerTemplateEngine());// 执行！
    }

    /**
     * 1、全局配置
     */
    private static GlobalConfig globalConfig(String projectPath) {
        return new GlobalConfig.Builder()
                .outputDir(projectPath + "/jz-mall-admin" + "/src/main/java" )
                .author("ShenLiang")
                .dateType(DateType.ONLY_DATE)
                .fileOverride()
                .disableOpenDir()
                .enableSwagger()
                .build();
    }

    /**
     *2、数据源配置
     */
    private static DataSourceConfig dataSourceConfig() {
        Props props = new Props("application.yml");
        String url = props.getStr("url");
        String username = props.getStr("username");
        String password = props.getStr("password");
        return new DataSourceConfig.Builder(url,username,password)
                .dbQuery(new MySqlQuery())
                .build();
    }

    private static PackageConfig packageConfig(String projectPath){
        Props props = new Props("application.yml");
        return new PackageConfig.Builder()
                .parent(props.getStr("base"))
                .entity("model")
                .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "/src/main/resources/mapper/"))
                .build();
    }

    /**
     * 初始化包配置
     */
//    private static PackageConfig packageConfig(String projectPath) {
//        Props props = new Props(" .yml");
//        return new PackageConfig.Builder()
//                .parent(props.getStr("base"))
//                .moduleName("/jz-mall-admin")
//                .entity("model")
//                .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "/src/main/resources/mapper/"))
//                .build();
//    }

    /**
     * 初始化模板配置
     */
    private static TemplateConfig initTemplateConfig() {
        //可以对controller、service、entity模板进行配置
        return new TemplateConfig.Builder().build();
    }

    /**
     * 4.初始化策略配置
     */
    private static StrategyConfig initStrategyConfig() {
        StrategyConfig.Builder builder = new StrategyConfig.Builder();
        builder.entityBuilder()
                .naming(NamingStrategy.underline_to_camel)
                .columnNaming(NamingStrategy.underline_to_camel)
                .enableLombok()
                .formatFileName("%s")
                .mapperBuilder()
                .enableBaseResultMap()
                .formatMapperFileName("%sMapper")
                .formatXmlFileName("%sMapper")
                .serviceBuilder()
                .formatServiceFileName("%sService")
                .formatServiceImplFileName("%sServiceImpl")
                .controllerBuilder()
                .enableRestStyle()
                .formatFileName("%sController");
        return builder.build();
    }

    /**
     * 初始化自定义配置
     */
    private static InjectionConfig initInjectionConfig( ) {
        // 自定义配置
        return new InjectionConfig.Builder().build();
    }

}
