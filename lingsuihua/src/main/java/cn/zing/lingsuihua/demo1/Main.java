package cn.zing.lingsuihua.demo1;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-28 10:23
 */
public class Main {

    public static void main(String[] args) {


        NodeDTO nodeDTO = new NodeDTO();

        NodeProcessorImpl pre = new KafkaNodePreProcessor();
        NodeProcessorImpl processor = new KafkaNodeProcessor();
        NodeProcessorImpl post = new KafkaNodePostProcessor();

        nodeDTO.setPreProcessor(pre);
        nodeDTO.setProcessor(processor);
        nodeDTO.setPostProcessor(post);

        NodeService nodeService = new NodeService(nodeDTO);
        Node node = nodeService.execute();
        System.out.println(node.name);
    }
}


