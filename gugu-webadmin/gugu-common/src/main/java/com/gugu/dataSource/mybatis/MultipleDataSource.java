package com.gugu.dataSource.mybatis;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * <b>function:</b> Spring
 * @author qinhc
 * @createDate 2015-4-20 11:24:53
 * @file MultipleDataSource.java
 * @project gugu
 * @version 1.0
 */
public class MultipleDataSource extends AbstractRoutingDataSource {
 
    @Override
    protected Object determineCurrentLookupKey() {
        return CustomerContextHolder.getCustomerType();
    }
}
