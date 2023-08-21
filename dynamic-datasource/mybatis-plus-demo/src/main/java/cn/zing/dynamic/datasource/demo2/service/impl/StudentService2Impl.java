package cn.zing.dynamic.datasource.demo2.service.impl;

import cn.zing.dynamic.datasource.api.bean.Student;
import cn.zing.dynamic.datasource.api.mapper.StudentMapper;
import cn.zing.dynamic.datasource.api.service.StudentService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@DS("mysql2")
@Service("StuService2")
public class StudentService2Impl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Override
    public Student getStudent(Integer id) {
        return null;
    }
}
