package cn.zing.hj212.server.stater;

import cn.zing.hj212.api.starter.Starter;
import cn.zing.hj212.api.util.DateUtil;
import cn.zing.hj212.server.node.NodeContainer;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.UUID;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-27 14:23
 */
@Slf4j
public abstract class AbstractStarter implements Starter {

    public LinkedList<NodeContainer> nodes;

    protected JSONObject config;

    public AbstractStarter() {
    }

    public AbstractStarter(JSONObject config) {
        this.config = config;
    }

    @Override
    public abstract void execute(JSONObject data);


    /**
     * 这个方法会解析212数据
     */
    @Override
    public void dispatch(Object data) {
        //type=netty
        String type = config.getString("type");

        String threadName = config.getString("name");
        Thread.currentThread().setName(threadName);
        //212数据
        JSONObject dta = (JSONObject) data;
        //nodedata.put("ctx", ctx);
        //nodedata.put("message", message);
        JSONObject nodedata = dta.getJSONObject("nodedata");
        // 这是报文
        Object oriData = nodedata.get("message");

        String _id = UUID.randomUUID().toString().replace("-", "").toUpperCase();

        log.info("开始任务，ID：" + _id + "，原始数据：" + oriData);

        JSONArray steps = new JSONArray();
        JSONObject step = new JSONObject();
        JSONObject exp = new JSONObject();
        // 在这个循环里, 一条数据会沿着链, 层级处理
        for (NodeContainer node : nodes) {
            try {
                step = new JSONObject();
                step.put("node", node.bean.getName());
                step.put("startTime", DateUtil.getCurrentMilliTime());
//                log.info("调试模式，ID：" + _id + "，节点数据：" + nodedata);
                if (log.isDebugEnabled()) {
                    log.debug("调试模式，ID：" + _id + "，节点数据：" + nodedata);
                }
                if (node.bean.isAsync()) {
                    new Thread(() -> {
                        try {
                            // 这里是处理节点数据
                            node.execute(nodedata);
                        } catch (Exception e) {
                            exceptionCatch(e, _id, nodedata);
                        }
                    }, threadName).start();
                } else {
                    //// 这里是处理节点数据
                    node.execute(nodedata);
                }
                step.put("nodedata", JSON.parse(nodedata.toJSONString()));
                step.put("endTime", DateUtil.getCurrentMilliTime());
                steps.add(step);
            } catch (Exception e) {
                exp = exceptionCatch(e, _id, nodedata);
                break;
            }
        }
        if (exp.isEmpty()) {
            log.info("执行完成，ID：" + _id);
        } else {
            log.info("执行异常，ID：" + _id);
        }
        /*下面都是日志处理*/
        // 只有配置了log2db：true才会保存日志到数据库
        JSONObject save = new JSONObject();
        try {
            if (config.containsKey("log2db") && config.getBoolean("log2db")) {
                JSONObject tags = new JSONObject();
                JSONObject metrics = new JSONObject();

                metrics.put("data", oriData);

                metrics.put("steps", steps);
                metrics.put("exception", exp);

                tags.put("code", nodedata.get("code"));
                tags.put("time", nodedata.get("time"));
                tags.put("ip", dta.get("ip"));
                tags.put("port", dta.get("port"));
                tags.put("protocol", dta.get("protocol"));
                tags.put("task", threadName);
                tags.put("type", type);
                tags.put("ConfigFile", config.get("ConfigFile"));

                save.put("_id", _id);
                save.put("receiveTime", dta.get("receiveTime"));
                save.put("completeTime", dta.get("endTime"));
                save.put("createTime", DateUtil.getCurrentMilliTime());
                save.put("tags", tags);
                save.put("metrics", metrics);
            }
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
            log.error("++++++记录日志异常：", exceptionCatch(e, _id, save));
        }
    }

    private JSONObject exceptionCatch(Exception e, String _id, JSONObject data) {
        JSONObject exp = new JSONObject();
        StackTraceElement[] sts = e.getStackTrace();

        String className = "";
        JSONArray arr = new JSONArray();
        JSONObject pos = new JSONObject();
        for (StackTraceElement stackTraceElement : sts) {
            className = stackTraceElement.getClassName();
            if (className.startsWith("com.hw.one")) {
                pos = new JSONObject();
                pos.put("class", stackTraceElement.getClassName());
                pos.put("method", stackTraceElement.getMethodName());
                pos.put("line", stackTraceElement.getLineNumber());
                arr.add(pos);
            }
        }
        exp.put("exp", arr);
        exp.put("msg", e);
        log.error("执行异常，ID：" + _id + "，错误信息：" + exp);
        log.error("执行异常，ID：" + _id + "，节点数据：" + data);

        return exp;
    }

}


