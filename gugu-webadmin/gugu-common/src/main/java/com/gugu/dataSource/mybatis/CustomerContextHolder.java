package com.gugu.dataSource.mybatis;

/**
 * <b>function:</b> 
 * @author qinhc
 * @createDate 2015-4-20 11:36:57
 * @file CustomerContextHolder.java
 * @project gugu
 * @version 1.0
 */
public abstract class CustomerContextHolder {
 
    public final static String DATA_SOURCE_MYSQL_READER = "reader0";
    public final static String DATA_SOURCE_MYSQL_MASTER = "master";
    
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
    
    public static void setCustomerType(String customerType) {  
        contextHolder.set(customerType);  
    }  
      
    public static String getCustomerType() {  
        return contextHolder.get();  
    }  
      
    public static void clearCustomerType() {  
        contextHolder.remove();  
    }  
}
