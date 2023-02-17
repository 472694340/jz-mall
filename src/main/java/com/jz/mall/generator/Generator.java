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
 * 用于逆向生成代码工具类
 */
public class Generator {
    public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {

        //读取配置文件
        InputStream is = Generator.class.getResourceAsStream("/generatorConfiguration.xml");
        List<String> warnning = new ArrayList<>();//集合输出读取日志
        boolean overwriter = true;//下面的参数,表示是否覆盖重复的文件

        //分析配置文件
        ConfigurationParser configurationParser = new ConfigurationParser(warnning);

        Configuration configuration = configurationParser.parseConfiguration(is);
        is.close();

        /*
          public DefaultShellCallback(boolean overwrite) {
                this.overwrite = overwrite;
            }
         */
        //创建回调器?
        DefaultShellCallback callback = new DefaultShellCallback(overwriter);
        //执行器
//        public MyBatisGenerator(Configuration configuration, ShellCallback shellCallback, List<String> warnings) throws InvalidConfigurationException {
        MyBatisGenerator generator = new MyBatisGenerator(configuration, callback, warnning);
        generator.generate(null);//执行生成代码
        for (String s : warnning) {
            System.out.println(s);
        }
    }
}
