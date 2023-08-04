package cn.zing.boot.demo.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "student")
public class Student{
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    @ExcelProperty("姓名")
    @TableField(value = "stu_name")
    private String stuName;

    /**
     * 学号
     */
    @ExcelProperty("学号")
    @TableField(value = "stu_no")
    private String stuNo;

    /**
     * 性别
     */
    @ExcelProperty("性别")
    @TableField(value = "stu_sex")
    private Integer stuSex;

    /**
     * 年龄
     */
    @ExcelProperty("年龄")
    @TableField(value = "stu_age")
    private Integer stuAge;

    public static final String COL_ID = "id";

    public static final String COL_STU_NAME = "stu_name";

    public static final String COL_STU_NO = "stu_no";

    public static final String COL_STU_SEX = "stu_sex";

    public static final String COL_STU_AGE = "stu_age";
}