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

    protected RestHighLevelClient initRestHighLevelClient() {
        List<HttpHost> httpHosts = buildHttpHosts();
        RestClientBuilder restClientBuilder = RestClient.builder(httpHosts.toArray(new HttpHost[]{}));
        return new RestHighLevelClient(restClientBuilder);
    }

    protected RestClient initRestClient() {
        List<HttpHost> httpHosts = buildHttpHosts();
        RestClientBuilder builder = RestClient.builder(httpHosts.toArray(new HttpHost[]{}));
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
