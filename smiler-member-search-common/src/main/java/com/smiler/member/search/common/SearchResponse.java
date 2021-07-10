package com.smiler.member.search.common;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 搜索结果集
 * @Author: wangchao
 * @Date: 2021/5/25
 */
public class SearchResponse<T> implements Serializable {

    private static final long serialVersionUID = -4908630136074724861L;

    long total;

    private List<Hits<T>> hits;

    private List<T> data;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Hits<T>> getHits() {
        return hits;
    }

    public void setHits(List<Hits<T>> hits) {
        this.hits = hits;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "total=" + total +
                ", hits=" + hits +
                ", data=" + data +
                '}';
    }
}
