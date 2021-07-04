package com.smiler.member.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.smiler.member.constant.CommonConstant;
import com.smiler.member.core.orika.OrikaFacade;
import com.smiler.member.dao.utils.EsRequestHelper;
import com.smiler.member.model.po.UserPo;
import com.smiler.member.model.po.UserSearchPo;
import com.smiler.member.model.vo.UserVo;
import com.smiler.member.service.UserBaseService;
import com.smiler.member.service.UserIndexService;
import com.smiler.member.service.UserShardingBaseService;
import org.apache.commons.collections4.CollectionUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/16
 */
@Service
public class UserIndexServiceImpl implements UserIndexService {

    private static final String UER_ID = "userId";
    private static final String INDEX = "{\"index\":{\"_id\":userId,\"retry_on_conflict\":3}}\n";
    private static final String LINE_BREAK = "\n";
    @Value("${elasticsearch.user.bulk.url}")
    private String bulkUrl;

    @Autowired
    @Qualifier(CommonConstant.INDEX_REST_HIGH_LEVEL_CLIENT)
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private EsRequestHelper esRequestHelper;

    @Autowired
    private UserBaseService userBaseService;

    @Autowired
    private OrikaFacade orikaFacade;

    @Autowired
    private UserShardingBaseService userShardingBaseService;

    @Override
    public int indexUsersTest(List<UserVo> userVos) {
        // 1、封装请求对象
        BulkRequest bulkRequest = new BulkRequest();
        IndexRequest indexRequest;

        final Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDate = simpleDateFormat.format(now);
        for (int i = 0; i < 3; i++) {
            indexRequest = new IndexRequest("user");
            Map map = new HashMap();
            map.put("id", 1001);
            map.put("username", "小小王");
            map.put("birthday", nowDate);
            map.put("birthdayTimestamp", now.getTime());
            map.put("gender", 1);
            map.put("address", "南京");
            indexRequest.source(map);
            bulkRequest.add(indexRequest);
        }

        try {
            BulkResponse response = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            return response.status().getStatus();
        } catch (IOException e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public int indexUsersForApi(List<UserVo> userVos) throws ParseException {
        BulkRequest bulkRequest = new BulkRequest();
        for (UserVo userVo : userVos) {
            IndexRequest indexRequest = new IndexRequest("user");
            indexRequest.id(userVo.getId().toString());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parse = simpleDateFormat.parse(userVo.getBirthday());
            userVo.setBirthdayTimestamp(parse.getTime());
            String userVoJson = JSON.toJSONString(userVo);
            indexRequest.source(userVoJson, XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        try {
            BulkResponse response = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            System.out.println(response.hasFailures());
            System.out.println(response.buildFailureMessage());
            return response.status().getStatus();
        } catch (IOException e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public void indexUserForClient(List<UserSearchPo> userSearchPos) {
        if (CollectionUtils.isEmpty(userSearchPos)) {
            return;
        }
        StringBuilder bulkBuilder = new StringBuilder();
        for (UserSearchPo userVo : userSearchPos) {
            bulkBuilder.append(INDEX.replace(UER_ID, userVo.getId()))
                    .append(JSON.toJSONString(userVo)).append(LINE_BREAK);
        }
        Response response = esRequestHelper.indexRestClient(bulkBuilder.toString(), bulkUrl);
        System.out.println(JSON.toJSONString(response));
    }

    @Override
    public void indexAllUser() {
        List<UserPo> userPos = userBaseService.queryAllUsers();
        List<UserSearchPo> userVos = orikaFacade.mapAsList(userPos, UserSearchPo.class);
        indexUserForClient(userVos);
    }

    @Override
    public void addUserByIds(List<BigInteger> idList) {
        for (List<BigInteger> ids : Lists.partition(idList, CommonConstant.QUERY_SIZE)) {
            List<UserPo> userPos = userBaseService.queryUserByIds(ids);
            List<UserSearchPo> userSearchPos = orikaFacade.mapAsList(userPos, UserSearchPo.class);
            this.indexUserForClient(userSearchPos);
        }
    }

    @Override
    public void addUserBatch(List<BigInteger> idList) {
        for (List<BigInteger> ids : Lists.partition(idList, CommonConstant.QUERY_SIZE)) {
            List<UserVo> userPos = userShardingBaseService.queryUserById(ids);
            List<UserSearchPo> userSearchPos = orikaFacade.mapAsList(userPos, UserSearchPo.class);
            this.indexUserForClient(userSearchPos);
        }
    }
}
