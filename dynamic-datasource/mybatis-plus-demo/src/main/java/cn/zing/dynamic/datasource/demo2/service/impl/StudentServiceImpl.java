package cn.zing.dynamic.datasource.demo2.service.impl;

import cn.zing.dynamic.datasource.api.bean.Student;
import cn.zing.dynamic.datasource.api.mapper.StudentMapper;
import cn.zing.dynamic.datasource.api.service.StudentService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@DS("mysql1")
@Service("StuService1")
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService{

    @Override
    public Student getStudent(Integer id) {
        return null;
    }
}
