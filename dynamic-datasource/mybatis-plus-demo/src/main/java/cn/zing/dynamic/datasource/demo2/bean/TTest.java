package cn.zing.dynamic.datasource.demo2.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@TableName(value = "idoc_zero_code.t_test")
@ToString
public class TTest {
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @TableField(value = "source_id")
    private String sourceId;

    @TableField(value = "dc_name")
    private String dcName;

    @TableField(value = "province_code")
    private String provinceCode;

    @TableField(value = "city_code")
    private String cityCode;

    @TableField(value = "mark_delete")
    private String markDelete;
}