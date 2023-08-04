package cn.zing.boot.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-04 22:59
 */
@SpringBootTest
public class BootTest {

    @Autowired
    PasswordEncoder passwordEncoder;


    @Test
    void createPwd() {
        String pwd = passwordEncoder.encode("123456");
        System.out.println(pwd);
    }
}


