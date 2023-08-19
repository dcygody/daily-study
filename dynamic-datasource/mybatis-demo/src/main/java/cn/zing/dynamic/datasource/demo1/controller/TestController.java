package cn.zing.dynamic.datasource.demo1.controller;

import cn.zing.dynamic.datasource.demo1.bean.Student;
import cn.zing.dynamic.datasource.demo1.bean.TTest;
import cn.zing.dynamic.datasource.demo1.service.StudentService;
import cn.zing.dynamic.datasource.demo1.service.TTestService;
import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-19 12:20
 */
@RestController
public class TestController {

    @Autowired
    StudentService studentService;

    @Autowired
    TTestService tTestService;

    @GetMapping("/mysql")
    public Object mysql() {
        Student student = studentService.getStudent(1);

        System.out.println("----------->" + JSON.toJSONString(student));
        return student;
    }
    @GetMapping("/pg")
    public Object pg() {
        TTest tTest = tTestService.getTTest("d45202ee-bb68-4438-b540-af30704fddad");

        System.out.println("----------->" + JSON.toJSONString(tTest));

        return tTest;
    }
}


