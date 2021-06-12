package com.smiler.member.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smiler.member.common.Po;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/8
 */
@Getter
@Setter
public class UserPo extends Po {

    private static final long serialVersionUID = -6768252023153362282L;

    private BigInteger id;

    private String username;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    private Byte gender;
    private String address;
}
