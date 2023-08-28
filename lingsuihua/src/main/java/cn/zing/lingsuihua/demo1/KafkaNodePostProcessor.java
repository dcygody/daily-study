package cn.zing.lingsuihua.demo1;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-28 11:04
 */
public class KafkaNodePostProcessor extends NodeProcessor{


    @Override
    public Node execute(Object data) {

        System.out.println("Kafka后置处理器");
        return null;
    }
}


