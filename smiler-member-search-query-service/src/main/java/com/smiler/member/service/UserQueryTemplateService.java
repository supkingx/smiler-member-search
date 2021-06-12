package com.smiler.member.service;

import com.smiler.member.model.so.UserSo;
import com.smiler.member.model.vo.UserVo;

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
     * @param userSo
     * @return
     */
    List<UserVo> queryUsersComprehensive(UserSo userSo);
}
