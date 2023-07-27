package cn.zing.hj212.server.netty.session;

import com.alibaba.fastjson2.JSONObject;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-01 23:01
 */
@Slf4j
public class SessionMgr {

    /**
     * 设备号
     */
    private static final AttributeKey<String> DEVICE_ID = AttributeKey.valueOf("DEVICE_ID");

    /**
     * 4G Channel
     */
    private static final ConcurrentHashMap<String, Channel> CHANNEL_MAP = new ConcurrentHashMap<>();


    /**
     * 获取设备号
     */
    public static String getDeviceId(ChannelHandlerContext ctx) {
        return ctx.channel().attr(DEVICE_ID).get();
    }

    /**
     * 设置设备号
     */
    public static void setDeviceId(ChannelHandlerContext ctx, String mn) {
        ctx.channel().attr(DEVICE_ID).setIfAbsent(mn);
    }


    public static void putSession(ChannelHandlerContext ctx, String mn) {
        setDeviceId(ctx, mn);
        CHANNEL_MAP.put(mn, ctx.channel());

    }

    /**
     * 获取4G的channel
     *
     * @param mn 设备号
     */
    public static Channel getSession4G(String mn) {
        if (!CHANNEL_MAP.containsKey(mn)) {
            log.error("4G连接中不存在该设备[{}]的信道, 无法获取", mn);
            return null;
        }
        return CHANNEL_MAP.get(mn);
    }

    /**
     * 移除channel
     */
    public static void removeSession(ChannelHandlerContext ctx) {

        Channel channel = ctx.channel();

        String mn = channel.attr(DEVICE_ID).get();

        if (!CHANNEL_MAP.containsKey(mn)) {
            log.error("4G连接中不存在该设备[{}]的信道, 无需移除", mn);
            ctx.close();
            return;
        }
        CHANNEL_MAP.remove(mn);
        log.info("设备[{}]4G连接成功移除", mn);
        // 优雅关闭
        ctx.close();

    }
}


