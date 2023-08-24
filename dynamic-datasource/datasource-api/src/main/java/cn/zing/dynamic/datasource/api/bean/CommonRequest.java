package cn.zing.dynamic.datasource.api.bean;

import lombok.Data;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @description: 封装的公共请求
 * @author: dcy
 * @create: 2023-08-24 09:30
 */
@Data
public class CommonRequest {

    /**
     * 线程id
     */
    private String threadId;

    /**
     * 查询参数
     */
    private Map<String, Object> params;

    /**
     * 返回结果
     */
    private CompletableFuture<Map<String, Object>> resultFuture;
}


