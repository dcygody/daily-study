package cn.zing.dubbos.framework;

/**
 * 定义协议
 */
public interface Protocol {

    void export(URL url);
    Invoker refer(URL url);
}
