package com.smiler.member.model.vo;

import com.smiler.member.common.Vo;

import java.math.BigInteger;
import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/16
 */
public class UserIndexMessageVo extends Vo {

    private static final long serialVersionUID = -145246809464189743L;

    private List<BigInteger> ids;

    public List<BigInteger> getIds() {
        return ids;
    }

    public void setIds(List<BigInteger> ids) {
        this.ids = ids;
    }
}