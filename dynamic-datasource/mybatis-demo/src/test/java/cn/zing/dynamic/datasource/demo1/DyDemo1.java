package cn.zing.dynamic.datasource.demo1;

import cn.zing.dynamic.datasource.api.bean.DcyAvatar;
import cn.zing.dynamic.datasource.api.bean.Student;
import cn.zing.dynamic.datasource.api.service.DcyAvatarService;
import cn.zing.dynamic.datasource.api.service.StudentService;
import cn.zing.dynamic.datasource.api.util.FileUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-19 11:09
 */
@SpringBootTest
public class DyDemo1 {


    @Qualifier("StuService1")
    @Autowired
    StudentService studentService1;


    @Qualifier("StuService2")
    @Autowired
    StudentService studentService2;

    @Autowired
    DcyAvatarService avatarService;

    @Test
    void mysql1() {
        Student student = studentService1.getStudent(1);

        System.out.println("----------->" + JSON.toJSONString(student));
    }

    @Test
    void mysql2() {
        Student student = studentService2.getStudent(9);

        System.out.println("----------->" + JSON.toJSONString(student));
    }

    @Test
    void insert() {

        String base64 = FileUtil.file2Base64("C:\\Users\\dcy\\Desktop\\avatar.png");
        List<DcyAvatar> list = new ArrayList<>();
        for (int i = 51; i <= 100; i++) {
            DcyAvatar avatar = new DcyAvatar();
            avatar.setUserAvatar(base64);
            avatar.setUserName("张三" + i);
            avatarService.addAvatar(avatar);
            System.out.println("添加: " + avatar.toString());
            list.add(avatar);
        }

//        avatarService.saveBatch(list);
    }

    @Test
    void getAvatar() {
        long start = System.currentTimeMillis();
        List<DcyAvatar> list = avatarService.avatarList();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }


}


