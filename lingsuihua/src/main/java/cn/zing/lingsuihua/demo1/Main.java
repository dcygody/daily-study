package cn.zing.lingsuihua.demo1;

import cn.zing.lingsuihua.demo1.node.Node;
import cn.zing.lingsuihua.demo1.node.NodeDTO;
import cn.zing.lingsuihua.demo1.processor.KafkaNodePostProcessor;
import cn.zing.lingsuihua.demo1.processor.KafkaNodePreProcessor;
import cn.zing.lingsuihua.demo1.processor.KafkaNodeProcessor;
import cn.zing.lingsuihua.demo1.processor.NodeProcessorImpl;
import cn.zing.lingsuihua.demo1.service.NodeService;

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


