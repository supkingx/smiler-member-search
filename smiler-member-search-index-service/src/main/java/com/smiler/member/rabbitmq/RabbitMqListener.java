package com.smiler.member.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.smiler.member.search.constant.RabbitMqConstant;
import com.smiler.member.search.model.enums.MessageTypeEnum;
import com.smiler.member.search.model.vo.UserIndexMessageVo;
import com.smiler.member.service.UserIndexService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/27
 */
@Service
public class RabbitMqListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqListener.class);

    @Autowired
    private UserIndexService userIndexService;

    @RabbitListener(queues = RabbitMqConstant.SMILER_USER_INDEX)
    public void receiveUserIndexMessage(String massageContent) {
        LOGGER.info("RabbitMqListener.receiveUserIndexMessage.massageContent:{}", massageContent);
        if (StringUtils.isBlank(massageContent)) {
            return;
        }
        UserIndexMessageVo userIndexMessageVo = JSON.parseObject(massageContent, UserIndexMessageVo.class);
        if (MessageTypeEnum.USER_ADD.getType().equals(userIndexMessageVo.getType())) {
            userIndexService.addUserByIds(userIndexMessageVo.getIds());
        }
    }
}
