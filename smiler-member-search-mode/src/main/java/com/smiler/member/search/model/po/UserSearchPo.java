package com.smiler.member.search.model.po;

import com.smiler.member.search.common.Po;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/16
 */
@Getter
@Setter
public class UserSearchPo extends Po {

    private static final long serialVersionUID = -7733932915768524372L;

    private String id;

    private String username;

    private String birthday;
    private Long birthdayTimestamp;
    private String gender;
    private String address;


}
