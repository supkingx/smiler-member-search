package com.smiler.member.service;

import com.smiler.member.model.po.UserPo;

import java.math.BigInteger;
import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/17
 */
public interface UserBaseService {

    List<UserPo> queryAllUsers();

    List<UserPo> queryUserByIds(List<BigInteger> ids);

}
