package cn.zing.dynamic.datasource.demo2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-18 17:15
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.zing.dynamic.datasource.api.mapper")
public class DynamicDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicDatasourceApplication.class, args);
    }
}


