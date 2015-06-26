package com.gugu.dataSource.mybatis;

import java.lang.reflect.Method;
import java.util.Random;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * <b>function:</b> 
 * 
 * @author qinhc
 * @createDate 2015-4-20 11:35:54
 * @file MultipleDataSourceInterceptor.java
 * @project gugu
 * @version 1.0
 */
@Component
@Aspect
public class MultipleDataSourceInterceptor {

	final private static String master = "master";
	final private static String reader = "reader";
	final private static int size = 2;
	/*final private static int size = Integer
			.parseInt((String) CustomizedPropertyConfigurer
					.getContextProperty("mysql.db.count"));*/

	/**
	 * <b>function:</b>
	 * 
	 * @author hoojo
	 * @createDate 2013-10-10 11:38:45
	 * @throws Exception
	 */
	@Before("execution(* com.gugu.service..*ServiceImpl.*(..)) || execution(* com.gugu.dataAccess..*Mapper.*(..))")
	public void dynamicSetDataSoruce(JoinPoint joinPoint) throws Exception {

		Logger logger = Logger.getLogger(this.getClass());

		Object target = joinPoint.getTarget();
		String method = joinPoint.getSignature().getName();
		Class<?>[] classz = target.getClass().getInterfaces();
		Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature())
				.getMethod().getParameterTypes();
		logger.info("execute {}.{}({}) className:"
				+ target.getClass().getName() + "-methodName:" + method
				+ "-arguments:" + parameterTypes);

		try {
			Method m = classz[0].getMethod(method, parameterTypes);
			if (m != null && m.isAnnotationPresent(DataSource.class)) {
				DataSource data = m.getAnnotation(DataSource.class);
				if (data == null || data.value().equals(master)) {
					CustomerContextHolder
							.setCustomerType(CustomerContextHolder.DATA_SOURCE_MYSQL_MASTER);
				} else if (data.value().equals(reader)) {
					Random random = new Random();
					int index = random.nextInt(size);
					CustomerContextHolder
							.setCustomerType(CustomerContextHolder.DATA_SOURCE_MYSQL_READER);

				} else {
					CustomerContextHolder.clearCustomerType();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
