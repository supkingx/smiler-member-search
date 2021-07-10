package com.smiler.member.search.api.impl.user.facade;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.smiler.member.search.core.orika.OrikaFacade;
import com.smiler.member.search.model.so.UserSearch;
import com.smiler.member.search.model.vo.UserVo;
import com.smiler.member.search.api.user.facade.UserSearchFacade;
import com.smiler.member.search.api.user.model.UserResponse;
import com.smiler.member.service.UserQueryTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/6/5
 */
@Service
public class UserSearchFacadeImpl implements UserSearchFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserSearchFacadeImpl.class);

    @Autowired
    private UserQueryTemplateService userQueryTemplateService;

    @Autowired
    private OrikaFacade orikaFacade;

    @Override
    public List<UserResponse> queryUsersComprehensive(UserSearch userSearch) {
        LOGGER.info("UserFacade.queryUsersComprehensive.param:{}", JSON.toJSONString(userSearch));
        List<UserVo> userVos = userQueryTemplateService.queryUsersComprehensive(userSearch);
        List<UserResponse> userResponses = orikaFacade.mapAsList(userVos, UserResponse.class);
        LOGGER.info("UserFacade.queryUsersComprehensive.response:{}", JSON.toJSONString(userResponses));
        return userResponses;
    }
}
