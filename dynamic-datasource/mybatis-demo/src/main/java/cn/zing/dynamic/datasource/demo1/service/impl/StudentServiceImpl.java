package cn.zing.dynamic.datasource.demo1.service.impl;

import cn.zing.dynamic.datasource.demo1.bean.Student;
import cn.zing.dynamic.datasource.demo1.config.db.DBTypeEnum;
import cn.zing.dynamic.datasource.demo1.config.db.DSM;
import cn.zing.dynamic.datasource.demo1.mapper.StudentMapper;
import cn.zing.dynamic.datasource.demo1.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    StudentMapper studentMapper;

    @DSM(DBTypeEnum.MYSQL)
    @Override
    public Student getStudent(Integer id) {
        //        System.out.println("----------->" + JSON.toJSONString(student));
        return studentMapper.getStudent(id);
    }
}
