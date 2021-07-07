package com.smiler.member.search.core.orika;

import ma.glasnost.orika.MapperFactory;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/25
 */
public interface OrikaRegistry {

    void register(MapperFactory factory);
}
