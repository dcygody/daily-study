package cn.zing.dynamic.datasource.demo1;

import cn.zing.dynamic.datasource.api.bean.Student;
import cn.zing.dynamic.datasource.api.service.StudentService;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-19 11:09
 */
@SpringBootTest
public class DyDemo1 {


    @Qualifier("StuService1")
    @Autowired
    StudentService studentService1;


    @Qualifier("StuService2")
    @Autowired
    StudentService studentService2;

    @Test
    void mysql1() {
        Student student = studentService1.getStudent(1);

        System.out.println("----------->" + JSON.toJSONString(student));
    }

    @Test
    void mysql2() {
        Student student = studentService2.getStudent(9);

        System.out.println("----------->" + JSON.toJSONString(student));
    }


}


