package com.smiler.member.search.core.orika;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/28
 */
@Component
public class SpringHelper implements BeanFactoryPostProcessor {

    private static ConfigurableListableBeanFactory beanFactory;

    public static ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringHelper.beanFactory = beanFactory;
    }

    public static Object getBean(String beanName) {
        return beanFactory.getBean(beanName);
    }

    public static <T> T getBean(Class<T> clazz) {
        return beanFactory.getBean(clazz);
    }

}
