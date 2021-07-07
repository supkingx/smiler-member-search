package com.smiler.member.dao.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smiler.member.search.common.Hits;
import com.smiler.member.search.common.SearchResponse;
import com.smiler.member.search.constant.CommonConstant;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.x/java-rest-low-usage-requests.html
 * @Author: wangchao
 * @Date: 2021/5/19
 */
@Component
public class EsRequestHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(EsRequestHelper.class);

    private static final String POST = "POST";
    private static final String AUTH_HEAD_KEY = "Authorization";

    @Autowired
    @Qualifier(CommonConstant.INDEX_REST_CLIENT)
    private RestClient indexRestClient;

    @Autowired
    @Qualifier(CommonConstant.QUERY_REST_CLIENT)
    private RestClient queryRestClient;

    public Response indexRestClient(String requestParam, String url) {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
//        builder.addHeader(AUTH_HEAD_KEY, "Basic " + "");
        Request request = new Request(POST, url);
        HttpEntity entity = new NStringEntity(requestParam, ContentType.APPLICATION_JSON);
        request.setEntity(entity);
//        request.setJsonEntity(requestParam);
        request.setOptions(builder);
        try {
            return indexRestClient.performRequest(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> SearchResponse<T> queryRestClient(String requestParam, String url, Class<T> returnClazz) {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        Request request = new Request(POST, url);
        HttpEntity entity = new NStringEntity(requestParam, ContentType.APPLICATION_JSON);
        request.setEntity(entity);
        request.setOptions(builder);
        try {
            Response response = queryRestClient.performRequest(request);
            return buildSearchResponse(response, returnClazz);
        } catch (IOException e) {
            LOGGER.error("queryRestClient.performRequest error,", e);
            throw new RuntimeException("queryRestClient.performRequest error");
        }
    }

    private <T> SearchResponse<T> buildSearchResponse(Response response, Class<T> clazz) throws IOException {
        JSONObject result = JSON.parseObject(EntityUtils.toString(response.getEntity()));
        JSONObject hitsObject = result.getJSONObject("hits");
        JSONObject totalObject = hitsObject.getJSONObject("total");
        long total = totalObject.getLongValue("value");
        JSONArray hitsResponse = hitsObject.getJSONArray("hits");

        SearchResponse<T> searchResponse = new SearchResponse<>();
        List<Hits<T>> hitsList = new ArrayList<>();
        List<T> data = new ArrayList<>();
        for (Object hitObject : hitsResponse) {
            Hits<T> hit = new Hits<>();
            JSONObject jsonObject = JSON.parseObject(hitObject.toString());
            JSONArray sort = jsonObject.getJSONArray("sort");
            Double score = jsonObject.getDoubleValue("_score");
            JSONObject source = jsonObject.getJSONObject("_source");
            T object = JSON.parseObject(String.valueOf(source), clazz);
            hit.setSource(object);
            hit.setScore(score);
            if (CollectionUtils.isNotEmpty(sort)) {
                hit.setSort(sort.toString());
            }
            hitsList.add(hit);
            data.add(object);
        }
        searchResponse.setHits(hitsList);
        searchResponse.setTotal(total);
        searchResponse.setData(data);
        return searchResponse;
    }
}
