package com.smiler.member;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/15
 */
@SpringBootApplication
@EnableDubbo
public class MemberQueryApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberQueryApplication.class, args);
    }
}
