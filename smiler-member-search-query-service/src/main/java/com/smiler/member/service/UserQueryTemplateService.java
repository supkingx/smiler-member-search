package com.smiler.member.service;

import com.smiler.member.search.model.so.UserSearch;
import com.smiler.member.search.model.so.UserSo;
import com.smiler.member.search.model.vo.UserVo;

import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/24
 */
public interface UserQueryTemplateService {

    /**
     * 综合搜索
     *
     * @param userSearch
     * @return
     */
    List<UserVo> queryUsersComprehensive(UserSearch userSearch);
}
