package com.example.demo.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.Validate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
public class ApplicationContextHolder implements ApplicationContextAware, DisposableBean {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextHolder.class);
    private static ApplicationContext applicationContext;
    private static void assertContextInjected(){
        Validate.validState(applicationContext!=null,"applicationContext属性未注入, 请在applicationContext.xml中定义ApplicationContextHolder.");
    }
    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }
    public static <T> T getBean(String name){
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }
    public static <T> T getBean(Class<T> requiredType){
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    @Override
    public void destroy() throws Exception {
        logger.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
        applicationContext = null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;
    }
}
