package com.smiler.member.search.model.vo;

import com.smiler.member.search.common.Vo;

import java.math.BigInteger;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/16
 */
public class UserVo extends Vo {

    private static final long serialVersionUID = -2239870254191299407L;

    private BigInteger id;

    private String username;
    private String birthday;
    private long birthdayTimestamp;
    private Byte gender;
    private String address;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public long getBirthdayTimestamp() {
        return birthdayTimestamp;
    }

    public void setBirthdayTimestamp(long birthdayTimestamp) {
        this.birthdayTimestamp = birthdayTimestamp;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", birthday='" + birthday + '\'' +
                ", birthdayTimestamp=" + birthdayTimestamp +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                '}';
    }
}
