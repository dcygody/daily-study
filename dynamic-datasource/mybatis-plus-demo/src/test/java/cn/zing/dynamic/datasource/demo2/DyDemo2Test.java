package cn.zing.dynamic.datasource.demo2;

import cn.zing.dynamic.datasource.api.bean.Student;
import cn.zing.dynamic.datasource.api.service.StudentService;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-19 10:54
 */
@SpringBootTest
public class DyDemo2Test {

    @Qualifier("StuService1")
    @Autowired
    StudentService studentService1;


    @Qualifier("StuService2")
    @Autowired
    StudentService studentService2;

    @Test
    void mysql1() {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getId, 1);
        Student student = studentService1.getOne(queryWrapper);

        System.out.println("----------->" + JSON.toJSONString(student));
    }

    @Test
    void mysql2() {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getId, 9);
        Student student = studentService2.getOne(queryWrapper);

        System.out.println("----------->" + JSON.toJSONString(student));
    }

}


