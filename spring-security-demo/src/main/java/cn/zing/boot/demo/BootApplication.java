package cn.zing.boot.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-04 22:40
 */
@SpringBootApplication
@MapperScan("cn.zing.boot.demo.mapper")
//@EnableWebMvc //不加这个注解会报错 Failed to start bean 'documentationPluginsBootstrapper' boot2.7.13;
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }
}


