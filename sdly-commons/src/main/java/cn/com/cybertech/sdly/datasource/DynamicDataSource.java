package cn.com.cybertech.sdly.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by huangkd on 2019/1/21.
 */
public class DynamicDataSource  extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSourceKey();
    }
}
