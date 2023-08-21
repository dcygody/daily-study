package cn.zing.dynamic.datasource.demo1;

import cn.zing.dynamic.datasource.api.log.LogAspect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-19 10:39
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.zing.dynamic.datasource.api.mapper")
// 因为LogAspect 不在springboot扫描路径下,需要手动引入
@Import(LogAspect.class)
public class DynamicDatasourceDemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(DynamicDatasourceDemo1Application.class, args);
    }
}


