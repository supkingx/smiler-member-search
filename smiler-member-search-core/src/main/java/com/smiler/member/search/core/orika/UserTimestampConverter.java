package com.smiler.member.search.core.orika;

import com.smiler.member.search.model.po.UserPo;
import com.smiler.member.search.model.po.UserSearchPo;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/25
 */
@Component
public class UserTimestampConverter implements OrikaRegistry {
    @Override
    public void register(MapperFactory factory) {
        factory.getConverterFactory().registerConverter("MyTimestampConverter", new MyTimestampConverter());
        factory.getConverterFactory().registerConverter("MyDateConverter", new MyDateToStringConverter());
        factory.classMap(UserPo.class, UserSearchPo.class)
                .fieldMap("birthday", "birthdayTimestamp").converter("MyTimestampConverter").add()
                .fieldMap("birthday", "birthday").converter("MyDateConverter").add()
                .byDefault()
                .register();
    }
}
