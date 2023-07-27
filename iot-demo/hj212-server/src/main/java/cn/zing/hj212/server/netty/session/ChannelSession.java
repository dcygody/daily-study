package cn.zing.hj212.server.netty.session;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

public class ChannelSession {

    /**
     * 设备连接
     */
    public static ConcurrentHashMap<String, Channel> DEVICE_CHANNEL = new ConcurrentHashMap<>();
    /**
     * Web的连接
     */
    public static ConcurrentHashMap<String, Channel> CLIENT_CHANNEL = new ConcurrentHashMap<>();

}
