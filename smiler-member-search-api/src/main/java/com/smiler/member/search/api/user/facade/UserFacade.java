package com.smiler.member.search.api.user.facade;

import com.smiler.member.model.so.UserSo;
import com.smiler.member.model.vo.UserVo;
import com.smiler.member.search.api.user.model.UserResponse;

import java.io.IOException;
import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/6/5
 */
public interface UserFacade {

    /**
     * 综合搜索
     *
     * @param userSo
     * @return
     * @throws IOException
     */
    List<UserResponse> queryUsersComprehensive(UserSo userSo);
}
