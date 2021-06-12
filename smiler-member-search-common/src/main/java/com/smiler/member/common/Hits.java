package com.smiler.member.common;

import java.io.Serializable;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/25
 */
public class Hits<T> implements Serializable {

    private static final long serialVersionUID = 8504581878709695431L;

    private Double score;

    private T source;

    private String sort;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public T getSource() {
        return source;
    }

    public void setSource(T source) {
        this.source = source;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "Hits{" +
                "score=" + score +
                ", source=" + source +
                ", sort='" + sort + '\'' +
                '}';
    }
}
