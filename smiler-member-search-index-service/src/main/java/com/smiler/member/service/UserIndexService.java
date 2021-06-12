package com.smiler.member.service;

import com.smiler.member.model.po.UserSearchPo;
import com.smiler.member.model.vo.UserVo;

import java.text.ParseException;
import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/16
 */
public interface UserIndexService {

    int indexUsersTest(List<UserVo> userVos);

    int indexUsersForApi(List<UserVo> userVos) throws ParseException;

    void indexUserForClient(List<UserSearchPo> userSearchPos);

    void indexAllUser();
}
