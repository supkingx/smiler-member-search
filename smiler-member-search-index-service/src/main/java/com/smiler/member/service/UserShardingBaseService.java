package com.smiler.member.service;

import com.smiler.member.model.vo.UserVo;

import java.math.BigInteger;
import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/3
 */
public interface UserShardingBaseService {

    List<UserVo> queryAllUsers();

    List<UserVo> queryUserById(List<BigInteger> idList);

}
