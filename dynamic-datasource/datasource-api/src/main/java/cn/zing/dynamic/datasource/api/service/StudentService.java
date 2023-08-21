package cn.zing.dynamic.datasource.api.service;


import cn.zing.dynamic.datasource.api.bean.Student;
import com.baomidou.mybatisplus.extension.service.IService;

public interface StudentService extends IService<Student> {

    Student getStudent(Integer id);

}
