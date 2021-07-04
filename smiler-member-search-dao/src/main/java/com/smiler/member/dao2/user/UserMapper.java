package com.smiler.member.dao2.user;

import com.smiler.member.model.po.UserPo;

import java.math.BigInteger;
import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/1
 */
public interface UserMapper {

    List<UserPo> queryAllUsers();

    List<UserPo> queryUserById(List<BigInteger> idList);
}
