package cn.zing.boot.demo.mapper;

import cn.zing.boot.demo.bean.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface StudentMapper extends BaseMapper<Student> {

    int insertSelective(Student student);
    List<Student> selectStuList(Student student);
}