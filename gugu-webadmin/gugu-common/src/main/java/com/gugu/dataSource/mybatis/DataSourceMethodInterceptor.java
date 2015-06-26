package com.gugu.dataSource.mybatis;

import java.lang.reflect.Proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.ClassUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

/**
 * <b>function:</b> 
 * @author qinhc
 * @createDate 2015-4-20 02:00:13
 * @file DataSourceMethodInterceptor.java
 * @project gugu
 * @version 1.0
 */
public class DataSourceMethodInterceptor implements MethodInterceptor, InitializingBean {
 
	Logger logger = Logger.getLogger(this.getClass());
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Class<?> clazz = invocation.getThis().getClass();
        String className = clazz.getName();
        if (ClassUtils.isAssignable(clazz, Proxy.class)) {
            className = invocation.getMethod().getDeclaringClass().getName();
        }
        String methodName = invocation.getMethod().getName();
        Object[] arguments = invocation.getArguments();
        logger.info("execute {}.{}({}) className:"+className+"-methodName:"+methodName+"-arguments:"+arguments);
        
        if (className.contains("Master")) {
            CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_MYSQL_MASTER);
        } else if (className.contains("Reader")) {
            CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_MYSQL_READER);
        }  else {
            CustomerContextHolder.clearCustomerType();
        }
        
        Object result = invocation.proceed();
        return result;
    }
 
    public void afterPropertiesSet() throws Exception {
    	logger.trace("afterPropertiesSet---");
    }
}

