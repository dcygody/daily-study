package cn.zing.hj212.server.netty.protocol.hj212;


import cn.zing.hj212.api.processer.Processor;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 * 入库
 */
@Slf4j
public class LoadStructure extends Processor {


    public LoadStructure(JSONObject config) {
        super(config);
    }


    @Override
    public void execute(Object dat) throws Exception {
        JSONObject nodedata = (JSONObject) dat;
        // 数据
        JSONObject node = nodedata.getJSONObject("data");
        String MN = nodedata.getString("code");
        String msg = nodedata.getString("message");


        JSONObject data = new JSONObject();
        data.put("type", "212");
        data.put("mn", MN);
        data.put("value", node);

        log.info("入库::::{}", data.toString());

    }
}
