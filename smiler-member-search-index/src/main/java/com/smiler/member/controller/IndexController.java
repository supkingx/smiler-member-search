package com.smiler.member.controller;

import com.smiler.member.dao2.user.UserMapper;
import com.smiler.member.search.model.po.UserPo;
import com.smiler.member.search.model.po.UserSearchPo;
import com.smiler.member.search.model.vo.UserVo;
import com.smiler.member.service.UserBaseService;
import com.smiler.member.service.UserIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/16
 */
@RestController
@RequestMapping("member/index")
public class IndexController {

    @Autowired
    private UserIndexService userIndexService;
    @Autowired
    private UserBaseService userBaseService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/indexUsersTest")
    public int indexUsersTest() {
        return userIndexService.indexUsersTest(null);
    }

    @PostMapping("/indexUsersForApi")
    public int indexUsersForApi(@RequestBody List<UserVo> userVos) throws ParseException {
        return userIndexService.indexUsersForApi(userVos);
    }

    @PostMapping("/indexUserForClient")
    public void indexUserForClient(@RequestBody List<UserSearchPo> userSearchPos) {
        userIndexService.indexUserForClient(userSearchPos);
    }

    @GetMapping("/indexAllUser")
    public void indexAllUser() {
        userIndexService.indexAllUser();
    }

    @GetMapping("/queryAllUsers")
    public List<UserPo> queryAllUsers() {
        return userBaseService.queryAllUsers();
    }

    @GetMapping("/queryUserByIds")
    public List<UserPo> queryUserByIds(List<BigInteger> ids) {
        return userBaseService.queryUserByIds(ids);
    }

    @GetMapping("/list")
    public List<UserPo> list() {
        return userMapper.queryAllUsers();
    }
}
