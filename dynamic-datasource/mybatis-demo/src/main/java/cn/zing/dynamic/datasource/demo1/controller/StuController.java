package cn.zing.dynamic.datasource.demo1.controller;

import cn.zing.dynamic.datasource.api.log.DcyLog;
import cn.zing.dynamic.datasource.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-21 11:56
 */
@RestController
public class StuController {

    @Autowired
    @Qualifier("StuService2")
    private StudentService studentService2;

    @GetMapping("/dy/getStu")
    public Object getStudent(Integer id) {

        return studentService2.getStudent(id);
    }
}


