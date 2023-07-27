package cn.zing.hj212.server.config;

import cn.zing.hj212.api.util.FileUtil;
import cn.zing.hj212.server.task.TaskThreadManager;
import com.alibaba.fastjson2.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-27 14:18
 */
@Configuration
public class TaskConfiguration {

    private final static Logger log = LoggerFactory.getLogger(TaskConfiguration.class);

    /**
     * 这个pips可能放一些重要的配置
     */
    public static List<JSONObject> pips = new ArrayList<>();

    @Bean(name = "taskConfig")
    public void readConfig() throws Exception {
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        File configDir = new File(path + "config" + File.separator);
        if (configDir.listFiles() != null) {
            JSONObject o = new JSONObject();
            // 获取所有配置文件 json类型的
            List<File> configs = recursion(configDir);
            for (File config : configs) {
                try {
                    o = JSONObject.parseObject(FileUtil.read(config));
                    o.put("ConfigFile", config.getName());
                } catch (Exception e) {
                    log.error(":::配置文件：" + config.getName() + "配置出错");
                    log.error(":::错误信息：", e);
                }
                if (o.getBoolean("enable")) {
                    log.info("加载任务配置:::" + o);
                    pips.add(o);
                }
            }

            TaskThreadManager ttm = new TaskThreadManager();
            // 启动所有JSON配置文件下配置的netty
            ttm.dispatch();
        }
    }

    /**
     * 递归获取目录下的所有文件
     */
    private static List<File> recursion(File file) {
        List<File> files = new ArrayList<>();
        for (File f : Objects.requireNonNull(file.listFiles())) {
            if (f.isDirectory()) {
                files.addAll(recursion(f));
            } else {
                files.add(f);
            }
        }

        return files;
    }
}


