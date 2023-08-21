package cn.zing.dynamic.datasource.demo1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-19 10:39
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.zing.dynamic.datasource.api.mapper")
public class DynamicDatasourceDemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(DynamicDatasourceDemo1Application.class, args);
    }
}


