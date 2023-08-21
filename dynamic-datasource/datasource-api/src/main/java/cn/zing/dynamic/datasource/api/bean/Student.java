package cn.zing.dynamic.datasource.api.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@TableName(value = "student")
@ToString
public class Student {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    @TableField(value = "stu_name")
    private String stuName;

    /**
     * 学号
     */
    @TableField(value = "stu_no")
    private String stuNo;

    /**
     * 性别
     */
    @TableField(value = "stu_sex")
    private Integer stuSex;

    /**
     * 年龄
     */
    @TableField(value = "stu_age")
    private Integer stuAge;
}