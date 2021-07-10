package com.smiler.member.kafka;

import com.alibaba.fastjson.JSON;
import com.smiler.member.search.model.enums.MessageTypeEnum;
import com.smiler.member.search.model.vo.UserIndexMessageVo;
import com.smiler.member.service.UserIndexService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/6/27
 */
@Configuration
public class KafkaListenerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaListenerService.class);

    private static final String GROUP_ID = "smiler.user";

    @Autowired
    private UserIndexService userIndexService;

    @KafkaListener(id = "1", topics = "${smiler.user.index.topic}", groupId = GROUP_ID)
    public void receiveUserIndexMessage(String msg) {
        LOGGER.info("KafkaListenerService.receiveUserIndexMessage.msg:{}", msg);
        if (StringUtils.isBlank(msg)) {
            return;
        }
        UserIndexMessageVo userIndexMessageVo = JSON.parseObject(msg, UserIndexMessageVo.class);
        if (MessageTypeEnum.USER_ADD.getType().equals(userIndexMessageVo.getType())) {
            userIndexService.addUserByIds(userIndexMessageVo.getIds());
        }
    }

    @KafkaListener(id = "2", topics = "${smiler.king.user.index.topic}", groupId = GROUP_ID)
    public void receiveKingUserIndexMessage(String msg) {
        LOGGER.info("KafkaListenerService.receiveKingUserIndexMessage.msg:{}", msg);
        if (StringUtils.isBlank(msg)) {
            return;
        }
        UserIndexMessageVo userIndexMessageVo = JSON.parseObject(msg, UserIndexMessageVo.class);
        if (MessageTypeEnum.USER_ADD.getType().equals(userIndexMessageVo.getType())) {
            userIndexService.addUserBatch(userIndexMessageVo.getIds());
        }
    }
}
