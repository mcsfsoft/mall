package com.macro.mall.tiny.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author mc
 * @date 2020/9/21
 */

@Configuration
@MapperScan({"com.marco.mall.tiny.mapper"})
public class MybatisConfig {
}
