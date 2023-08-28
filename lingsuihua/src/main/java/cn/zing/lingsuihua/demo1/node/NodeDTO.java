package cn.zing.lingsuihua.demo1.node;

import cn.zing.lingsuihua.demo1.processor.NodeProcessorImpl;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-28 10:26
 */
public class NodeDTO {

    private String name;

    private NodeProcessorImpl preProcessor;
    private NodeProcessorImpl processor;
    private NodeProcessorImpl postProcessor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NodeProcessorImpl getPreProcessor() {
        return preProcessor;
    }

    public void setPreProcessor(NodeProcessorImpl preProcessor) {
        this.preProcessor = preProcessor;
    }

    public NodeProcessorImpl getProcessor() {
        return processor;
    }

    public void setProcessor(NodeProcessorImpl processor) {
        this.processor = processor;
    }

    public NodeProcessorImpl getPostProcessor() {
        return postProcessor;
    }

    public void setPostProcessor(NodeProcessorImpl postProcessor) {
        this.postProcessor = postProcessor;
    }
}


