package cn.zing.netty.client.repo;

import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class DataRepo {

    /**
     * 设备连接
     */
    public static ConcurrentHashMap<String, Channel> DEVICE_CHANNEL = new ConcurrentHashMap<>();

    /**
     * 设备列表
     */
    public static List<String> DEVICE_LIST = new ArrayList<>();
    public static Integer DEVICE_SIZE = 5;

}
