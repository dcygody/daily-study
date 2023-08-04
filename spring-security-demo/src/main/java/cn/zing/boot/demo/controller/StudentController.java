package cn.zing.boot.demo.controller;

import cn.zing.boot.demo.bean.Student;
import cn.zing.boot.demo.service.StudentService;
import cn.zing.boot.demo.util.DateUtils;
import cn.zing.boot.demo.util.page.TableDataInfo;
import cn.zing.boot.demo.vo.AjaxResult;
import com.alibaba.excel.EasyExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @description: 测试类
 * @author: dcy
 * @create: 2023-07-04 14:31
 */
@RestController
@RequestMapping("/stu")
public class StudentController extends BaseController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/add")
    @PreAuthorize("@sg.hasPermi('sys:common:add')")
    public AjaxResult add(@RequestBody Student student) {

        logger.info("新增: {}", student.toString());

        studentService.insertSelective(student);
        return AjaxResult.success(student);

    }

    @GetMapping("/list")
    @PreAuthorize("@sg.hasPermi('sys:common:list')")
    public TableDataInfo list(Student student) {
        logger.info("查询: {}", student.toString());
        startPage();

        List<Student> list = studentService.selectList(student);

        return getDataTable(list);

    }

    @GetMapping("/get")
    @PreAuthorize("@sg.hasPermi('sys:common:list')")
    public AjaxResult get(@RequestParam Integer id) {

        return AjaxResult.success(studentService.getById(id));

    }

    @PutMapping("/update")
    @PreAuthorize("@sg.hasPermi('sys:common:edit')")
    public AjaxResult update(@RequestBody Student student) {

        logger.info("修改: {}", student.toString());

        studentService.updateById(student);
        return AjaxResult.success(student);

    }

    @DeleteMapping("/delete")
    @PreAuthorize("@sg.hasPermi('sys:common:delete')")
    public AjaxResult delete(Student student) {

        logger.info("删除: {}", student);

        studentService.removeById(student);
        return AjaxResult.success(student);

    }


    /**
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link Student}
     * <p>
     * 2. 设置返回的 参数
     * <p>
     * 3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
    @GetMapping("/download")
    public void download(HttpServletResponse response, Student student) throws IOException {
        logger.info("导出: {}", student.toString());
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        List<Student> list = studentService.selectList(student);
        // 根据用户传入字段 假设我们要忽略 date
        Set<String> excludeColumnFiledNames = new HashSet<>();
        excludeColumnFiledNames.add("id");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("学生列表-" + DateUtils.getNowDate().getTime(), "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), Student.class)
                .excludeColumnFieldNames(excludeColumnFiledNames)
                .sheet("学生列表").doWrite(list);
    }
}


