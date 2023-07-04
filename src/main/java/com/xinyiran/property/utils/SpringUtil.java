package com.xinyiran.property.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil {
    private static ApplicationContext appContext;

    public static void setAppContext(ApplicationContext appContext) {
        SpringUtil.appContext = appContext;
    }

    public static ApplicationContext getAppContext() {
        return appContext;
    }

    public static Object getBean(String beanId) throws BeansException {
        return appContext.getBean(beanId);
    }
}
