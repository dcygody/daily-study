package cn.zing.lingsuihua.demo1.processor;

import cn.zing.lingsuihua.demo1.node.KafkaNode;
import cn.zing.lingsuihua.demo1.node.Node;
import cn.zing.lingsuihua.demo1.node.NodeDTO;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-28 11:04
 */
public class KafkaNodeProcessor extends NodeProcessor{


    @Override
    public Node execute(Object data) {
        NodeDTO nodeDTO = (NodeDTO) data;
        System.out.println("Kafka执行器");

        return new KafkaNode(nodeDTO.getName());
    }
}


