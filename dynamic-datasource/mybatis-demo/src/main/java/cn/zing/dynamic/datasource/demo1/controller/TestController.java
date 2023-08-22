package cn.zing.dynamic.datasource.demo1.controller;

import cn.zing.dynamic.datasource.api.bean.DcyAvatar;
import cn.zing.dynamic.datasource.api.log.DcyLog;
import cn.zing.dynamic.datasource.api.service.DcyAvatarService;
import cn.zing.dynamic.datasource.api.service.StudentService;
import cn.zing.dynamic.datasource.api.util.FileUtil;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-21 11:56
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    @Qualifier("StuService2")
    private StudentService studentService2;

    @Autowired
    private DcyAvatarService avatarService;

    @GetMapping("/dy/getStu")
    public Object getStudent(Integer id) {

        return studentService2.getStudent(id);
    }

    /**
     * 不压缩
     * @return
     * @throws IOException
     */
    @GetMapping("/avatar/list")
    public Object avatarList() throws IOException {
        long start = System.currentTimeMillis();
        log.info("请求到达: " + start);
        List<DcyAvatar> list = avatarService.avatarList();

//        long end = System.currentTimeMillis();
//        System.out.println("用时: " + (end -start));

        return list;
    }

    /**
     * 压缩
     * @return
     * @throws IOException
     */
    @GetMapping("/avatar/list2")
    public Object avatarList2() throws IOException {
        long start = System.currentTimeMillis();
        log.info("请求到达: " + start);
        List<DcyAvatar> list = avatarService.avatarList();

//        long end = System.currentTimeMillis();
//        System.out.println("用时: " + (end -start));

        return FileUtil.compressData(JSON.toJSONString(list));
    }
}


