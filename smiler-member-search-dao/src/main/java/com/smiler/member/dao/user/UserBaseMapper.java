package com.smiler.member.dao.user;


import com.smiler.member.search.model.po.UserPo;

import java.math.BigInteger;
import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/8
 */
public interface UserBaseMapper {

    List<UserPo> queryAllUsers();

    List<UserPo> queryUserByIds(List<BigInteger> ids);
}
