package cn.zing.lingsuihua.demo1.processor;

import cn.zing.lingsuihua.demo1.node.Node;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-28 11:02
 */
public abstract class NodeProcessor extends NodeProcessorImpl {

    @Override
    public abstract Node execute(Object data);
}


