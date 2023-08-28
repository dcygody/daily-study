package cn.zing.lingsuihua.demo1;

import com.alibaba.fastjson2.JSONObject;

/**
 * 抽象节点方法, T为需要创建的节点类型
 * @param <T>
 */
public interface Processor<T> {

    T execute();

    void pre();

    T processor();

    void post(T t);
}
