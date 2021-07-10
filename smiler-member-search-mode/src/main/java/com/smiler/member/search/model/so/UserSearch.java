package com.smiler.member.search.model.so;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/7
 */
@Getter
@Setter
public class UserSearch implements Serializable {

    private static final long serialVersionUID = -1141429777591020971L;

    private String username;

    private String address;

    private Byte gender;

    private String birthdayStart;

    private String birthdayEnd;
}
