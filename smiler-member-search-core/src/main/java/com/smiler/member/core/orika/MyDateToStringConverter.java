package com.smiler.member.core.orika;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:e
 * @Author: wangchao
 * @Date: 2021/5/25
 */
@Component
public class MyDateToStringConverter extends CustomConverter<Date, String> {

    @Override
    public String convert(Date date, Type<? extends String> type, MappingContext mappingContext) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
