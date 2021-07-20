package com.smiler.member.config;

import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @description: beetl模板引擎配置
 * @Author: wangchao
 * @Date: 2021/5/25
 */
@Configuration
public class GroupTemplateConfig {

    @Bean
    public GroupTemplate groupTemplate() {
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader("template");
        org.beetl.core.Configuration cfg = null;
        try {
            cfg = org.beetl.core.Configuration.defaultConfiguration();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new GroupTemplate(resourceLoader, cfg);
    }
}
