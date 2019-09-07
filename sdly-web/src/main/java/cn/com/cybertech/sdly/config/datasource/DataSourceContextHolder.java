package cn.com.cybertech.sdly.config.datasource;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangkd on 2019/1/21.
 */
@Slf4j
public class DataSourceContextHolder {
    private static ThreadLocal<String> dataSourceKey = new ThreadLocal<>();


    public static void setDataSourceKey(String key) {
        dataSourceKey.set(key);

    }


    public static String getDataSourceKey() {
        return dataSourceKey.get();
    }

    public static void remove() {
        dataSourceKey.remove();
    }
}
