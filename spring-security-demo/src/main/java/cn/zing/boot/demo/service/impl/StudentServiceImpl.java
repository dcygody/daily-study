package cn.zing.boot.demo.service.impl;

import cn.zing.boot.demo.bean.Student;
import cn.zing.boot.demo.mapper.StudentMapper;
import cn.zing.boot.demo.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Resource
    StudentMapper studentMapper;

    @Override
    public List<Student> selectList(Student student) {
        return studentMapper.selectStuList(student);
    }

    @Override
    public int insertSelective(Student student) {
        return studentMapper.insertSelective(student);
    }
}
