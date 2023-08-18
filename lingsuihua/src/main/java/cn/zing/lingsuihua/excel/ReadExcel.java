package cn.zing.lingsuihua.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-18 14:36
 */
@Slf4j
public class ReadExcel {

    public static void simpleRead(String sheetName) {

        // 写法4
        String fileName = "";



        // 一个文件一个reader
        try (ExcelReader excelReader = EasyExcel.read(fileName, ExcelBean.class, new DemoDataListener()).build()) {
            ReadSheet readSheet = EasyExcel.readSheet(sheetName).build();
            // 读取一个sheet
            excelReader.read(readSheet);

        }

    }

    public static void main(String[] args) {
        String fileName = "";

        ExcelReader excelReader1 = EasyExcel.read(fileName).build();
        List<ReadSheet> sheets = excelReader1.excelExecutor().sheetList();
        List<String> collect = sheets.stream().map(ReadSheet::getSheetName).collect(Collectors.toList());
        for (int i = 3; i < collect.size(); i++) {
            simpleRead(collect.get(i));

        }

    }
}


