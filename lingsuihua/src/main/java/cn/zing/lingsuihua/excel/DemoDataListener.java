package cn.zing.lingsuihua.excel;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-18 14:45
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson2.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * 模板的读取类
 *
 * @author Jiaju Zhuang
 */
// 有个很重要的点 ExcelBeanListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
public class DemoDataListener implements ReadListener<ExcelBean> {

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据
     */
    private final List<ExcelBean> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);


    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. It is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(ExcelBean data, AnalysisContext context) {
        System.out.println("解析到一条数据: -> " + JSON.toJSONString(data));
        cachedDataList.add(data);

//        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
//        if (cachedDataList.size() >= BATCH_COUNT) {
//            saveData();
//            // 存储完成清理 list
//            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
//        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        String sheetName = context.readSheetHolder().getSheetName();
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
//
//        log.info("所有数据解析完成！");
        System.out.println("解析完了---" + cachedDataList.size());
        saveData(sheetName);


    }

    /**
     * 加上存储数据库
     */
    private void saveData(String sheetName) {
        StringBuilder builder = new StringBuilder();
        ExcelBean first = cachedDataList.get(0);
        String tbl = cachedDataList.get(0).getTbl();
        builder.append("create table pro.")
                .append(tbl)
                .append("\n(\n");
        if (first.getComment().contains("资源标识")) {

            builder.append("uuid varchar(255) not null constraint ")
                    .append(tbl)
                    .append("_pk primary key,")
                    .append("\n");
        }

        for (int i = 0; i < cachedDataList.size(); i++) {
            ExcelBean bean = cachedDataList.get(i);
            if (bean.getComment().contains("资源标识")) {
                continue;
            }

            builder.append(bean.getCode())
                    .append(" ")
                    .append(bean.getDataType());
            if (i != cachedDataList.size() - 1) {
                builder.append(",\n");
            } else {
                builder.append("\n");
            }

        }
        builder.append(");");
        System.out.println(builder.toString());

        StringBuilder comment = new StringBuilder();
        comment.append("comment on table pro.").append(tbl).append(" is '")
                .append(sheetName)
                .append("';\n");

        for (ExcelBean excelBean : cachedDataList) {
            comment.append("comment on column pro.").append(tbl)
                    .append(".").append(excelBean.getCode())
                    .append(" is '")
                    .append(excelBean.getComment())
                    .append("';\n");
        }
        comment.append("alter table pro.").append(tbl).append("  owner to postgres;");
        System.out.println(comment.toString());

        PrintWriter printWriter = null;
        String textToBeWritten = builder.toString() + "\n" + comment.toString();
        {
            try {
                printWriter = new PrintWriter("sql/" + sheetName + ".sql");
            } catch (FileNotFoundException e) {
                System.out.println("Unable to locate the fileName: " + e.getMessage());
            }
            Objects.requireNonNull(printWriter).println(textToBeWritten);
            printWriter.close();
        }

    }


}


