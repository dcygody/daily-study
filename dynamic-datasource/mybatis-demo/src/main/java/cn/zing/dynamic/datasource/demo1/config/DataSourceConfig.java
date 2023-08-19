package cn.zing.dynamic.datasource.demo1.config;

import cn.zing.dynamic.datasource.demo1.config.db.DBTypeEnum;
import cn.zing.dynamic.datasource.demo1.config.db.MyRoutingDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-19 10:46
 */
@Configuration
@Slf4j
public class DataSourceConfig {


    @Value("${spring.datasource.primary}")
    private String defaultDataSource;

    @Bean(name = "pg")
    @ConfigurationProperties("spring.datasource.pg")
    public DataSource pgDataSource() {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return dataSource;
    }

    @Primary
    @Bean(name = "mysql")
    @ConfigurationProperties("spring.datasource.mysql")
    public DataSource mysqlDataSource() {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return dataSource;
    }


    @Bean
    public DataSource myRoutingDataSource(
            @Qualifier("pg") DataSource pg,
            @Qualifier("mysql") DataSource mysql
            ) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DBTypeEnum.PG, pg);
        targetDataSources.put(DBTypeEnum.MYSQL, mysql);
        MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();

        DBTypeEnum dbTypeEnum = DBTypeEnum.valueOf(defaultDataSource.toUpperCase());
        log.info("默认数据源配置--------" + dbTypeEnum.name());
        DataSource dfs = (DataSource) targetDataSources.get(dbTypeEnum);

        myRoutingDataSource.setDefaultTargetDataSource(dfs);
        myRoutingDataSource.setTargetDataSources(targetDataSources);
        return myRoutingDataSource;
    }
}


