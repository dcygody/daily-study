package cn.zing.dynamic.datasource.demo2;

import cn.zing.dynamic.datasource.demo2.bean.Student;
import cn.zing.dynamic.datasource.demo2.bean.TTest;
import cn.zing.dynamic.datasource.demo2.service.StudentService;
import cn.zing.dynamic.datasource.demo2.service.TTestService;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-19 10:54
 */
@SpringBootTest
public class DyDemo2Test {

    @Autowired
    StudentService studentService;

    @Autowired
    TTestService tTestService;

    @Test
    void mysql() {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getId, 1);
        Student student = studentService.getOne(queryWrapper);

        System.out.println("----------->" + JSON.toJSONString(student));
    }

    @Test
    void pg() {
        LambdaQueryWrapper<TTest> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TTest::getId, "d45202ee-bb68-4438-b540-af30704fddad");
        TTest tTest = tTestService.getOne(queryWrapper);

        System.out.println("----------->" + JSON.toJSONString(tTest));
    }
}


