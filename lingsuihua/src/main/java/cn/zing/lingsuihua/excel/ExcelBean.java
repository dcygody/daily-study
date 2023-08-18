package cn.zing.lingsuihua.excel;

import lombok.Data;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-18 14:38
 */
@Data
public class ExcelBean {
    //表名	中文名称	字段代码（小写）	数据类型	导入模板数据类型	导入模板数据长度

    private String tbl;
    private String comment;
    private String code;
    private String dataType;
    private String tmpType;
    private String tmpLen;
}


