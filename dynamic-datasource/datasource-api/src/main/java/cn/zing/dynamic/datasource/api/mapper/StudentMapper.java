package cn.zing.dynamic.datasource.api.mapper;

import cn.zing.dynamic.datasource.api.bean.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface StudentMapper extends BaseMapper<Student> {

    Student getStudent(Integer id);
}