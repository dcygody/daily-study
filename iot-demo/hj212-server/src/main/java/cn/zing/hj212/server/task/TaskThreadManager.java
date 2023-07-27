package cn.zing.hj212.server.task;

import cn.zing.hj212.api.task.Task;
import cn.zing.hj212.server.config.TaskConfiguration;
import cn.zing.hj212.server.task.thread.SingleTaskExecuteThread;
import com.alibaba.fastjson2.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-27 14:17
 */
public class TaskThreadManager implements Task {

    private final static Logger log = LoggerFactory.getLogger(TaskThreadManager.class);

    /**
     * 这里存放的是线程任务
     */
    public static Map<String, Thread> ts = new HashMap<>();

    @Override
    public void start(String name) {
        try {
            ts.get(name).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pause(String name) {
        try {
            ts.get(name).wait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void interrupt(String name) {
        try {
            ts.get(name).interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sleep(String name, long millis) {
        try {
            ts.get(name).sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 这里可以启动一个任务, 包括JSON配置
     */
    @Override
    public void dispatch() {
        try {
            Thread.sleep(500);
            for (JSONObject config : TaskConfiguration.pips) {
                // 启动enable=true的任务
                if (config.getBooleanValue("enable")) {
                    String name = config.getString("name");
                    SingleTaskExecuteThread t = new SingleTaskExecuteThread(config);
                    t.setName(name);
                    t.start();
                    // 保存这个任务
                    ts.put(name, t);
                }
            }
        } catch (Exception e) {
            log.error("启动任务失败:::：" + e);
            e.printStackTrace();
        }
    }
}


