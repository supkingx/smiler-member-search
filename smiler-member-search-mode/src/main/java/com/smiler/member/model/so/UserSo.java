package com.smiler.member.model.so;

import com.smiler.member.common.So;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/25
 */
@Getter
@Setter
public class UserSo extends So {

    private static final long serialVersionUID = -8447863356826039076L;

    private String username;

    private String address;

    private Byte gender;

    private String birthdayStart;

    private String birthdayEnd;
}
