package com.smiler.member.serviceimpl;

import com.smiler.member.constant.CommonConstant;
import com.smiler.member.core.orika.OrikaFacade;
import com.smiler.member.dao2.user.UserMapper;
import com.smiler.member.model.po.UserPo;
import com.smiler.member.model.vo.UserVo;
import com.smiler.member.service.UserShardingBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/3
 */
@Service
@Transactional(rollbackFor = Exception.class, timeout = 3, transactionManager = CommonConstant.SHARDING_DATA_SOURCE_TRANSACTION_MANAGER)
public class UserShardingBaseServiceImpl implements UserShardingBaseService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrikaFacade orikaFacade;

    @Override
    public List<UserVo> queryAllUsers() {
        return orikaFacade.mapAsList(userMapper.queryAllUsers(), UserVo.class);
    }

    @Override
    public List<UserVo> queryUserById(List<BigInteger> idList) {
        List<UserPo> userPo = userMapper.queryUserById(idList);
        return orikaFacade.mapAsList(userPo, UserVo.class);
    }
}
