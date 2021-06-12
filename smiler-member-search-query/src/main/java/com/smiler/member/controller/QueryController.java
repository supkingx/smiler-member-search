package com.smiler.member.controller;

import com.smiler.member.model.so.UserSo;
import com.smiler.member.model.vo.UserVo;
import com.smiler.member.service.UserQueryApiService;
import com.smiler.member.service.UserQueryTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/15
 */
@RestController
@RequestMapping("/member/query")
public class QueryController {

    @Autowired
    private UserQueryApiService userQueryApiService;
    @Autowired
    private UserQueryTemplateService userQueryTemplateService;

    @GetMapping("/queryUsersByName")
    public List<UserVo> queryUsersByName(String username) {
        List<UserVo> userVos = userQueryApiService.queryUsersByName(username);
        return userVos;
    }

    @PostMapping("/queryUsersComprehensive")
    public List<UserVo> queryUsersComprehensive(@RequestBody UserSo userSo) {
        List<UserVo> userVos = userQueryTemplateService.queryUsersComprehensive(userSo);
        return userVos;
    }
}
