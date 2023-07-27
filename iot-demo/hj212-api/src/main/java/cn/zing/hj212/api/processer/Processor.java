package cn.zing.hj212.api.processer;

import com.alibaba.fastjson2.JSONObject;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-27 13:47
 */
public abstract class Processor extends ProcessorImpl{

    public Processor(JSONObject config) {
        this.config = config;
    }

    @Override
    public abstract void execute(Object data) throws Exception;
}


