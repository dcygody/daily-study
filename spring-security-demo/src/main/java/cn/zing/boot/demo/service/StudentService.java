package cn.zing.boot.demo.service;

import cn.zing.boot.demo.bean.Student;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface StudentService extends IService<Student>{



    List<Student> selectList(Student student);

    int insertSelective(Student student);



}
