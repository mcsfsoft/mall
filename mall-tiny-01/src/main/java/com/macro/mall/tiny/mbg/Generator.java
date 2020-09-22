package com.macro.mall.tiny.mbg;

import lombok.extern.slf4j.Slf4j;
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
 * 需要去查看下mybatis的相关文件
 * 用于产生mbg文件的java代码
 *
 * @author mc
 * @date 2020/9/21
 */
@Slf4j
public class Generator {
    public static void main(String[] args) {
        //警告信息收集
        List<String> warnings = new ArrayList<String>();
        //定义代码重复时,是否覆盖原代码
        boolean overwrite = true;
        //读取mgb文件
        InputStream is = Generator.class.getResourceAsStream("/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        try {
            Configuration config = cp.parseConfiguration(is);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            //创建MGB  参数为 读取的配置config 是否重写参数callback  警告文件的集合
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            //执行生成文件
            myBatisGenerator.generate(null);
            //输出警告信息
            for (String warning : warnings) {
                log.info(warning);
            }
        } catch (IOException e) {
            //日志输出相关异常信息
            log.info("IO: " + e.toString());
        } catch (XMLParserException e) {
            //日志输出相关异常信息
            log.info("XML: " + e.toString());
        } catch (InterruptedException e) {
            //日志输出相关异常信息
            log.info("generate: " + e.toString());
        } catch (SQLException throwables) {
            //日志输出相关异常信息
            log.info("generate" + throwables);
        } catch (InvalidConfigurationException e) {
            //日志输出相关异常信息
            log.info("gen run " + e.toString());
        } finally {
            //日志输出相关异常信息,最终关闭
            try {
                is.close();
            } catch (Exception e) {
                log.info("close is: " + e.toString());
            }
        }
    }

}
