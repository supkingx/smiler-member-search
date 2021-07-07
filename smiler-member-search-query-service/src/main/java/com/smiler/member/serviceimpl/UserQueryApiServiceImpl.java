package com.smiler.member.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.smiler.member.search.constant.CommonConstant;
import com.smiler.member.search.model.vo.UserVo;
import com.smiler.member.service.UserQueryApiService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/16
 */
@Service
public class UserQueryApiServiceImpl implements UserQueryApiService {

    @Autowired
    @Qualifier(CommonConstant.QUERY_REST_HIGH_LEVEL_CLIENT)
    private RestHighLevelClient restHighLevelClient;

    @Override
    public List<UserVo> queryUsersByName(String name) {

        // 1、创建检索请求
        SearchRequest searchRequest = new SearchRequest();
        // 指定索引
        searchRequest.indices("user_alias");

        // 指定DSL，检索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("username", name));
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(searchResponse.toString());
        SearchHits hits = searchResponse.getHits();
        List<UserVo> userVos = new ArrayList<>();
        for (SearchHit hit : hits.getHits()) {
            String sourceAsString = hit.getSourceAsString();
            if (Objects.isNull(sourceAsString)) {
                continue;
            }
            UserVo userVo = JSON.parseObject(sourceAsString, UserVo.class);
            userVos.add(userVo);
        }
        return userVos;
    }
}
