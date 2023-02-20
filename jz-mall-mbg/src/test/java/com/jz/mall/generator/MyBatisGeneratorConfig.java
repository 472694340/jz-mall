package com.jz.mall.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * MyBatis逆向工程工具类
 */
public class MyBatisGeneratorConfig {
//    public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
//        //思路:读取配置文件,创建日志,是否覆盖原文件 ,创建执行器,执行
//        InputStream is = MyBatisGenerator.class.getResourceAsStream("/generatorConfiguration.xml");
//        List<String> warnning = new ArrayList<>();
//        boolean overwrite = true;
//
//        //配置解析器,指定日志输出
//        ConfigurationParser configurationParser = new ConfigurationParser(warnning);
//        Configuration configuration = configurationParser.parseConfiguration(is);
//
//
//        //创建回调函数
//        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
//        //public MyBatisGenerator(Configuration configuration, ShellCallback shellCallback, List<String> warnings)
//        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration,callback,warnning);
//        myBatisGenerator.generate(null);
//        is.close();
//
//        for (String s : warnning) {
//            System.out.println(s);
//        }
//    }
}
