package cn.zing.netty.client.mock;

import cn.zing.netty.client.repo.DataRepo;
import cn.zing.netty.client.util.FileUtil;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.util.Objects;

@Component
@Slf4j
public class DataTimer {

    JSONObject sendJson = null;
    JSONObject respJson = null;

    public DataTimer() throws Exception {
        String path = Objects.requireNonNull(Objects.requireNonNull(ClassUtils.getDefaultClassLoader()).getResource("")).getPath();
        File file = new File(path + "protocol/设备发.json");
        this.sendJson = JSONObject.parseObject(FileUtil.read(file));

        File file2 = new File(path + "protocol/设备回复.json");
        this.respJson = JSONObject.parseObject(FileUtil.read(file2));

//        String pretty = JSON.toJSONString(this.json, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
//                SerializerFeature.WriteDateUseDateFormat);
        log.info("读取配置1: {}", this.sendJson);
        log.info("读取配置2: {}", this.respJson);
    }

    /**
     * 心跳
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void sendHeart(){
        DataRepo.DEVICE_CHANNEL.forEach((mn, channel) -> {
            if (null != channel && channel.isActive()) {
                JSONObject heart = sendJson.getJSONObject("heart");
                heart.put("mn", mn);
                channel.writeAndFlush(heart.toJSONString().getBytes());
                log.info("{}-{}-4G心跳-{}", channel.id().toString(), mn, heart.toJSONString());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * 心跳
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void sendNetHeart(){
        DataRepo.DEVICE_CHANNEL.forEach((mn, channel) -> {
            if (null != channel && channel.isActive()) {
                JSONObject heart = sendJson.getJSONObject("netheart");
                heart.put("mn", mn);
                channel.writeAndFlush(heart.toJSONString().getBytes());
                log.info("{}-{}-NET心跳-{}", channel.id().toString(), mn, heart.toJSONString());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Scheduled(cron = "0 0/1 * * * ?")
    public void sendData(){
        DataRepo.DEVICE_CHANNEL.forEach((mn, channel) -> {
            if (null != channel && channel.isActive()) {
                JSONObject data = sendJson.getJSONObject("data");
                data.put("mn", mn);
                channel.writeAndFlush(data.toJSONString().getBytes());
                log.info("{}-{}-定时数据-{}", channel.id().toString(), mn, data.toJSONString());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
