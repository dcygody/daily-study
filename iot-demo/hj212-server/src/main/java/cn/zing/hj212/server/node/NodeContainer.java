package cn.zing.hj212.server.node;

import cn.zing.hj212.api.node.Node;
import cn.zing.hj212.server.processor.ProcessorBean;
import com.alibaba.fastjson2.JSONObject;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-27 14:14
 */
public class NodeContainer implements Node {

    public ProcessorBean bean;

    public boolean stop;

    public JSONObject data;

    public NodeContainer(ProcessorBean bean) {
        this.bean = bean;
    }

    @Override
    public void execute(Object data) throws Exception {
        //这一步解析出了212报文 HJ212ResolvingProcessor
        this.pre(data);
        this.processor(data);
        this.post(data);
    }

    @Override
    public void pre(Object data) throws Exception {
        if (this.bean.getPreProcessor() != null) {
            this.bean.getPreProcessor().execute(data);
        }
    }

    @Override
    public void post(Object data) throws Exception {
        if (this.bean.getPostProcessor() != null) {
            this.bean.getPostProcessor().execute(data);
        }
    }

    @Override
    public void processor(Object data) throws Exception {
        if (this.bean.getProcessor() != null) {
            this.bean.getProcessor().execute(data);
        }
    }
}


