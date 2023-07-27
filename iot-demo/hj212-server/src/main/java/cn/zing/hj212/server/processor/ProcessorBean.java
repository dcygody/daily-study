package cn.zing.hj212.server.processor;

import cn.zing.hj212.api.processer.ProcessorImpl;
import cn.zing.hj212.api.step.Step;

/**
 * @description: 执行器bean
 * @author: dcy
 * @create: 2023-07-27 14:10
 */
public class ProcessorBean {

    private String name;

    private boolean async;

    private ProcessorImpl processor;

    private ProcessorImpl preProcessor;

    private ProcessorImpl postProcessor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProcessorImpl getProcessor() {
        return processor;
    }

    public void setProcessor(ProcessorImpl processor) {
        this.processor = processor;
    }

    public Step getPreProcessor() {
        return preProcessor;
    }

    public void setPreProcessor(ProcessorImpl preProcessor) {
        this.preProcessor = preProcessor;
    }

    public ProcessorImpl getPostProcessor() {
        return postProcessor;
    }

    public void setPostProcessor(ProcessorImpl postProcessor) {
        this.postProcessor = postProcessor;
    }

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

}


