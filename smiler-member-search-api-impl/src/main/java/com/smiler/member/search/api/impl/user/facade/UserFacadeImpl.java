package com.smiler.member.search.api.impl.user.facade;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.smiler.member.core.orika.OrikaFacade;
import com.smiler.member.model.so.UserSo;
import com.smiler.member.model.vo.UserVo;
import com.smiler.member.search.api.user.facade.UserFacade;
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
public class UserFacadeImpl implements UserFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserFacadeImpl.class);

    @Autowired
    private UserQueryTemplateService userQueryTemplateService;

    @Autowired
    private OrikaFacade orikaFacade;

    @Override
    public List<UserResponse> queryUsersComprehensive(UserSo userSo) {
        LOGGER.info("UserFacade.queryUsersComprehensive.param:{}", JSON.toJSONString(userSo));
        List<UserVo> userVos = userQueryTemplateService.queryUsersComprehensive(userSo);
        List<UserResponse> userResponses = orikaFacade.mapAsList(userVos, UserResponse.class);
        LOGGER.info("UserFacade.queryUsersComprehensive.response:{}", JSON.toJSONString(userResponses));
        return userResponses;
    }
}
