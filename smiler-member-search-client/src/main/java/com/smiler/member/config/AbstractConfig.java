package com.smiler.member.config;

import com.google.common.base.Preconditions;
import com.smiler.member.search.constant.CommonConstant;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/16
 */
public class AbstractConfig {

    @Value("${smiler.elasticsearch.servers}")
    private String servers;

    /**
     * 设置获取连接池超时时间
     */
    private static final int CONNECTION_REQUEST_TIMEOUT = 5000;

    /**
     * 同时间正在使用的最多的连接数
     */
    private static final int MAX_CONN_TOTAL = 40;

    /**
     * 针对一个域名同时间正在使用的最多的连接数
     *
     * @return
     */
    private static final int MAX_CONN_PER_ROUTE = 40;

    protected RestHighLevelClient initRestHighLevelClient() {
        List<HttpHost> httpHosts = buildHttpHosts();
        RestClientBuilder restClientBuilder = RestClient.builder(httpHosts.toArray(new HttpHost[]{}));
        return new RestHighLevelClient(restClientBuilder);
    }

    /**
     * setSocketTimeout:设置请求超时时间
     * setConnectionRequestTimeout:设置获取连接池超时时间
     * maxConnTotal:是同时间正在使用的最多的连接数
     * maxConnPerRoute:是针对一个域名同时间正在使用的最多的连接数
     *
     * @param timeout 请求超时时间
     * @return
     */
    protected RestClient initRestClient(int timeout) {
        List<HttpHost> httpHosts = buildHttpHosts();
        RestClientBuilder builder = RestClient.builder(httpHosts.toArray(new HttpHost[]{}));
        builder.setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder.setSocketTimeout(timeout).setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT));
        builder.setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setMaxConnTotal(MAX_CONN_TOTAL).setMaxConnPerRoute(MAX_CONN_PER_ROUTE));
        return builder.build();
    }

    private List<HttpHost> buildHttpHosts() {
        Preconditions.checkArgument(Objects.nonNull(servers), "Elasticsearch server cluster address is empty!");
        String[] serverAddresses = servers.split(CommonConstant.COMMA);
        List<HttpHost> httpHosts = new ArrayList<>();
        for (String serverAddress : serverAddresses) {
            String[] ipAndPort = serverAddress.split(CommonConstant.COLON);
            HttpHost httpHost = new HttpHost(ipAndPort[0], Integer.parseInt(ipAndPort[1]), CommonConstant.HTTP);
            httpHosts.add(httpHost);
        }
        return httpHosts;
    }

}
