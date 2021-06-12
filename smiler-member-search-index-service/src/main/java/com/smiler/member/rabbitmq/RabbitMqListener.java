package com.smiler.member.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.smiler.member.constant.CommonConstant;
import com.smiler.member.constant.RabbitMqConstant;
import com.smiler.member.core.orika.OrikaFacade;
import com.smiler.member.model.po.UserPo;
import com.smiler.member.model.po.UserSearchPo;
import com.smiler.member.model.vo.UserIndexMessageVo;
import com.smiler.member.service.UserBaseService;
import com.smiler.member.service.UserIndexService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/27
 */
@Service
public class RabbitMqListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqListener.class);

    @Autowired
    private UserBaseService userBaseService;

    @Autowired
    private UserIndexService userIndexService;

    @Autowired
    private OrikaFacade orikaFacade;

    @RabbitListener(queues = RabbitMqConstant.SMILER_USER_INDEX)
    public void receiveUserIndexMessage(String massageContent) {
        LOGGER.info("RabbitMqListener.receiveUserIndexMessage.massageContent:{}", massageContent);
        if (StringUtils.isBlank(massageContent)) {
            return;
        }
        UserIndexMessageVo userIndexMessageVo = JSON.parseObject(massageContent, UserIndexMessageVo.class);
        for (List<BigInteger> ids : Lists.partition(userIndexMessageVo.getIds(), CommonConstant.QUERY_SIZE)) {
            List<UserPo> userPos = userBaseService.queryUserByIds(ids);
            List<UserSearchPo> userSearchPos = orikaFacade.mapAsList(userPos, UserSearchPo.class);
            userIndexService.indexUserForClient(userSearchPos);
        }
    }
}
