package com.smiler.member.search.model.vo;

import com.smiler.member.search.common.Vo;

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

    private Integer type;

    public List<BigInteger> getIds() {
        return ids;
    }

    public void setIds(List<BigInteger> ids) {
        this.ids = ids;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}