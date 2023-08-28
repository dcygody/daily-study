package cn.zing.lingsuihua.demo1;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-28 11:04
 */
public class KafkaNodePreProcessor extends NodeProcessor{


    @Override
    public Node execute(Object data) {
        NodeDTO nodeDTO = (NodeDTO) data;
        nodeDTO.setName("kafka");
        System.out.println("Kafka前置处理器");
        return null;
    }
}


