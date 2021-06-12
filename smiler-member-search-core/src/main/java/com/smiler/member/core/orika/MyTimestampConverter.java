package com.smiler.member.core.orika;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/25
 */
@Component
public class MyTimestampConverter extends CustomConverter<Date, Long> {
    @Override
    public Long convert(Date date, Type<? extends Long> type, MappingContext mappingContext) {
        return date.getTime();
    }
}
