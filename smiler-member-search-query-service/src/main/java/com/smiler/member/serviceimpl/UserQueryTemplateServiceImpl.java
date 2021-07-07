package com.smiler.member.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.smiler.member.search.common.SearchResponse;
import com.smiler.member.dao.utils.EsRequestHelper;
import com.smiler.member.search.model.so.UserSearch;
import com.smiler.member.search.model.so.UserSo;
import com.smiler.member.search.model.vo.UserVo;
import com.smiler.member.service.UserQueryTemplateService;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/24
 */
@Service
public class UserQueryTemplateServiceImpl implements UserQueryTemplateService {

    private static final Logger logger = LoggerFactory.getLogger(UserQueryTemplateServiceImpl.class);

    @Autowired
    private GroupTemplate groupTemplate;

    @Autowired
    private EsRequestHelper esRequestHelper;

    @Value("${elasticsearch.user.search.url}")
    private String userSearchUrl;

    @Override
    public List<UserVo> queryUsersComprehensive(UserSearch userSearch) {
        JSONObject soParam = JSON.parseObject(JSON.toJSONString(userSearch, SerializerFeature.WriteMapNullValue));
        String DSL = generateDSL(soParam);
        logger.info("queryUsersComprehensive.{}", DSL);
        SearchResponse<UserVo> searchResponse = esRequestHelper.queryRestClient(DSL, userSearchUrl, UserVo.class);
        System.out.println(searchResponse.getData());
        return searchResponse.getData();
    }

    private String generateDSL(JSONObject soParam) {
        Template template = groupTemplate.getTemplate("user_query_common_template.beetl");
        template.binding(soParam);
        String render = template.render();
        return render;
    }
}
