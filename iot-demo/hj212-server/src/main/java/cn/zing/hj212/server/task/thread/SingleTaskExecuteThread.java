package cn.zing.hj212.server.task.thread;

import cn.zing.hj212.api.processer.ProcessorImpl;
import cn.zing.hj212.api.util.StringUtil;
import cn.zing.hj212.server.node.NodeContainer;
import cn.zing.hj212.server.processor.ProcessorBean;
import cn.zing.hj212.server.stater.AbstractStarter;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.LinkedList;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-27 14:20
 */
public class SingleTaskExecuteThread extends Thread {

    private final static Logger log = LoggerFactory.getLogger(SingleTaskExecuteThread.class);

    private final JSONObject config;

    public SingleTaskExecuteThread(JSONObject config) {
        this.config = config;
    }

    LinkedList<JSONObject> taskConfig = new LinkedList<>();
    /**
     * 单个任务的所有节点
     */
    LinkedList<NodeContainer> tasks = new LinkedList<>();

    // 任务节点排序
    // 任务节点依次放入 taskConfig
    private void getNext(String parent) {
        JSONObject task = config.getJSONObject("task");
        JSONArray next = new JSONArray();
        JSONObject node = new JSONObject();
        for (String key : task.keySet()) {
            if (key.equals(parent)) {
                node = task.getJSONObject(key);
                node.put("name", key);
                taskConfig.add(node);
                next = task.getJSONObject(key).getJSONArray("next");
                if (next != null && next.size() > 0) {
                    for (Object object : next) {
                        getNext(String.valueOf(object));
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            // JSON里链式调用的方法, 都被放进了taskConfig
            getNext(config.getString("start"));

            ProcessorBean bean = new ProcessorBean();
            ProcessorImpl step = null;

            Class<?> z = null;
            for (JSONObject obj : taskConfig) {
                if (obj.containsKey("start")) {
                    continue;
                }
                bean = new ProcessorBean();
                bean.setAsync(obj.getBooleanValue("async"));
                // 例如: name=extract
                bean.setName(obj.getString("name"));

                // 前置处理器
                if (StringUtil.isNotBlank(obj.getString("preProcessor"))) {
                    z = Class.forName(obj.getString("preProcessor"));
                    step = (ProcessorImpl) z.getConstructor(JSONObject.class).newInstance(obj);
                    bean.setPreProcessor(step);
                }

                //只有当前一个处理器
                if (StringUtil.isNotBlank(obj.getString("processor"))) {
                    z = Class.forName(obj.getString("processor"));
                    // 将当前配置传到构造器中
                    step = (ProcessorImpl) z.getConstructor(JSONObject.class).newInstance(obj);
                    // set
                    bean.setProcessor(step);
                }

                // 后置处理器
                if (StringUtil.isNotBlank(obj.getString("postProcessor"))) {
                    z = Class.forName(obj.getString("postProcessor"));
                    step = (ProcessorImpl) z.getConstructor(JSONObject.class).newInstance(obj);
                    bean.setPostProcessor(step);
                }

                // 节点任务保存
                tasks.add(new NodeContainer(bean));
            }

            /*下面是找到第一个需要执行的任务, 并且启动, 就是启动Netty*/
            // 这个得到起始节点的任务, 就是netty
            JSONObject startConfig = config.getJSONObject("task").getJSONObject(config.getString("start"));
            String startClassName = startConfig.getString("start");

            Class<?> startClazz = Class.forName(startClassName);
            Constructor<?> c = startClazz.getConstructor(JSONObject.class);
            // 把这个JSON的配置传入, 整个JSON文件
            AbstractStarter start = (AbstractStarter) c.newInstance(config);
            // 得到nodes属性
            Field field = start.getClass().getField("nodes");
            field.setAccessible(true);
            // 将链式的任务节点set进去
            field.set(start, tasks);
            // 启动netty
            start.execute(startConfig.getJSONObject("params"));
        } catch (Exception e) {
            log.error("任务节点初始化失败 任务名称：" + config.getString("name"), e);
            e.printStackTrace();
            System.exit(0);
        }
    }
}


