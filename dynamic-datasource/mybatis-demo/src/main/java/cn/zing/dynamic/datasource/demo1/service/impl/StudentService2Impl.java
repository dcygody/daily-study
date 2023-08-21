package cn.zing.dynamic.datasource.demo1.service.impl;

import cn.zing.dynamic.datasource.api.bean.Student;
import cn.zing.dynamic.datasource.api.log.DcyLog;
import cn.zing.dynamic.datasource.demo1.config.db.DSM;
import cn.zing.dynamic.datasource.api.mapper.StudentMapper;
import cn.zing.dynamic.datasource.api.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("StuService2")
@DSM("mysql2")
public class StudentService2Impl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Resource
    StudentMapper studentMapper;

    @Override
    @DcyLog(desc = "查询学生信息")
    public Student getStudent(Integer id) {
        //        System.out.println("----------->" + JSON.toJSONString(student));
        return studentMapper.getStudent(id);
    }
}
