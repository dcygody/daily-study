package cn.zing.hj212.api.node;

/**
 * @description: 抽取业务逻辑公共节点
 * @author: dcy
 * @create: 2023-07-27 13:39
 */
public interface Node {

    void execute(Object data) throws Exception;

    void pre(Object data) throws Exception;

    void post(Object data) throws Exception;

    void processor(Object data) throws Exception;
}


