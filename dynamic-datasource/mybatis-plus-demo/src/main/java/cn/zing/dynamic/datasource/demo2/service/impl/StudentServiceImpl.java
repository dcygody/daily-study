package cn.zing.dynamic.datasource.demo2.service.impl;

import cn.zing.dynamic.datasource.demo2.bean.Student;
import cn.zing.dynamic.datasource.demo2.mapper.StudentMapper;
import cn.zing.dynamic.datasource.demo2.service.StudentService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@DS("mysql")
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService{

}
