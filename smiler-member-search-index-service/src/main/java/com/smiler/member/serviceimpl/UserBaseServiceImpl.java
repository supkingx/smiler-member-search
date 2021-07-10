package com.smiler.member.serviceimpl;

import com.smiler.member.search.constant.CommonConstant;
import com.smiler.member.dao.user.UserBaseMapper;
import com.smiler.member.search.model.po.UserPo;
import com.smiler.member.service.UserBaseService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/17
 */
@Service
public class UserBaseServiceImpl implements UserBaseService {

    @Autowired
    private UserBaseMapper userBaseMapper;


    @Override
    @Transactional(rollbackFor = Exception.class,timeout = 2,transactionManager = CommonConstant.PRIMARY_DATA_SOURCE_TRANSACTION_MANAGER)
    public List<UserPo> queryAllUsers() {
        return userBaseMapper.queryAllUsers();
    }

    @Override
    @Transactional(rollbackFor = Exception.class,timeout = 2,transactionManager = CommonConstant.PRIMARY_DATA_SOURCE_TRANSACTION_MANAGER)
    public List<UserPo> queryUserByIds(List<BigInteger> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return userBaseMapper.queryUserByIds(ids);
    }
}
