package cn.zing.lingsuihua.demo1.processor;

public interface Step<T> {

    T execute(Object data);
}
