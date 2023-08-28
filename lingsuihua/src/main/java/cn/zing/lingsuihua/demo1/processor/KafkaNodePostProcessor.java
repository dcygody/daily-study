package cn.zing.lingsuihua.demo1.processor;

import cn.zing.lingsuihua.demo1.node.Node;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-28 11:04
 */
public class KafkaNodePostProcessor extends NodeProcessor{


    @Override
    public Node execute(Object data) {
        Node node = (Node)data;
        System.out.println("Kafka后置处理器");
        return null;
    }
}


