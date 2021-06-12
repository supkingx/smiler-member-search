package com.smiler.member.service;

import com.smiler.member.model.vo.UserVo;

import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/16
 */
public interface UserQueryApiService {

    List<UserVo> queryUsersByName(String name);

}
