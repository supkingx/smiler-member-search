package com.smiler.member.search.api.user.facade;

import com.smiler.member.search.model.so.UserSearch;
import com.smiler.member.search.model.so.UserSo;
import com.smiler.member.search.api.user.model.UserResponse;

import java.io.IOException;
import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/6/5
 */
public interface UserSearchFacade {

    /**
     * 综合搜索
     *
     * @param userSearch
     * @return
     * @throws IOException
     */
    List<UserResponse> queryUsersComprehensive(UserSearch userSearch);
}
