package com.smiler.member.config;

import com.smiler.member.search.constant.CommonConstant;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: ES 配置类
 * @Author: wangchao
 * @Date: 2021/5/15
 */
@Configuration
public class ElasticsearchConfig extends AbstractConfig {

    private static final int READ_TIMEOUT = 5000;
    private static final int WRITE_TIMEOUT = 10000;

    @Bean(CommonConstant.QUERY_REST_HIGH_LEVEL_CLIENT)
    public RestHighLevelClient queryRestHighLevelClient() {
        return initRestHighLevelClient();
    }

    @Bean(CommonConstant.INDEX_REST_HIGH_LEVEL_CLIENT)
    public RestHighLevelClient indexRestHighLevelClient() {
        return initRestHighLevelClient();
    }

    @Bean(CommonConstant.QUERY_REST_CLIENT)
    public RestClient queryRestClient() {
        return initRestClient(READ_TIMEOUT);
    }

    @Bean(CommonConstant.INDEX_REST_CLIENT)
    public RestClient indexRestClient() {
        return initRestClient(WRITE_TIMEOUT);
    }

}
