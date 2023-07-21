package cn.zing.netty.server.session;

import cn.zing.netty.server.bean.DataFrame;
import cn.zing.netty.server.bean.DataTypeConst;
import cn.zing.netty.server.handler.DataDecoder;
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
     * 记录半包时读索引的位置
     */
    private static final AttributeKey<Integer> READ_INDEX = AttributeKey.valueOf("READ_INDEX");
    /**
     * 判断是不是半包
     */
    private static final AttributeKey<Boolean> IS_HALF_PACK = AttributeKey.valueOf("IS_HALF_PACK");
    /**
     * 设备号
     */
    private static final AttributeKey<String> DEVICE_ID = AttributeKey.valueOf("DEVICE_ID");
    /**
     * 数据来源 是NET还是4G
     */
    private static final AttributeKey<String> DATA_SOURCE = AttributeKey.valueOf("DATA_SOURCE");
    /**
     * 4G Channel
     */
    private static final ConcurrentHashMap<String, Channel> CHANNEL_MAP = new ConcurrentHashMap<>();
    /**
     * NET Channel
     */
    private static final ConcurrentHashMap<String, Channel> NET_CHANNEL_MAP = new ConcurrentHashMap<>();

    /**
     * 初始化连接
     */
    public static void initSession(ChannelHandlerContext ctx) {
        ctx.channel().attr(READ_INDEX).set(0);
        ctx.channel().attr(IS_HALF_PACK).set(false);
    }

    /**
     * 是否发生半包
     */
    public static boolean getHalfPackStatus(ChannelHandlerContext ctx) {
        return ctx.channel().attr(IS_HALF_PACK).get();
    }

    /**
     * 设置半包状态
     */
    public static void setHalfPackStatus(ChannelHandlerContext ctx, boolean status) {
        ctx.channel().attr(IS_HALF_PACK).set(status);
    }

    /**
     * 得到半包索引位置
     */
    public static Integer getHalfPackIndex(ChannelHandlerContext ctx) {
        return ctx.channel().attr(READ_INDEX).get();
    }

    /**
     * 设置半包索引位置
     */
    public static void setHalfPackIndex(ChannelHandlerContext ctx, int index) {
        ctx.channel().attr(READ_INDEX).set(index);
    }

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

    /**
     * 获取数据来源
     */
    public static String getDataSource(ChannelHandlerContext ctx) {
        return ctx.channel().attr(DATA_SOURCE).get();
    }

    /**
     * 设置数据来源
     */
    public static void setDataSource(ChannelHandlerContext ctx, String dataSource) {
        ctx.channel().attr(DATA_SOURCE).setIfAbsent(dataSource);
    }


    public static void putSession(DataFrame dataFrame) {
        String dataSource = dataFrame.getChannel().attr(DATA_SOURCE).get();
        String mn = dataFrame.getChannel().attr(DEVICE_ID).get();
        if (DataTypeConst.FOUR_G.equals(dataSource)) {
            CHANNEL_MAP.put(mn, dataFrame.getChannel());
        } else if (DataTypeConst.NET.equals(dataSource)) {
            NET_CHANNEL_MAP.put(mn, dataFrame.getChannel());
        } else {
            log.error("未知来源Channel无法存储-{}", dataFrame.getChannel().id().toString());
        }
    }

    /**
     * 获取4G的channel
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
     * 获取NET的channel
     * @param mn 设备号
     */
    public static Channel getSessionNET(String mn) {
        if (!NET_CHANNEL_MAP.containsKey(mn)) {
            log.error("NET连接中不存在该设备[{}]的信道, 无法获取", mn);
            return null;
        }
        return NET_CHANNEL_MAP.get(mn);
    }

    /**
     * 移除channel
     */
    public static void removeSession(ChannelHandlerContext ctx) {

        Channel channel = ctx.channel();
        String dataSource = channel.attr(DATA_SOURCE).get();
        String mn = channel.attr(DEVICE_ID).get();
        if (DataTypeConst.FOUR_G.equals(dataSource)) {
            if (!CHANNEL_MAP.containsKey(mn)) {
                log.error("4G连接中不存在该设备[{}]的信道, 无需移除", mn);
                return;
            }
            CHANNEL_MAP.remove(mn);
            log.info("设备[{}]4G连接成功移除", mn);
        } else if (DataTypeConst.NET.equals(dataSource)) {
            if (!NET_CHANNEL_MAP.containsKey(mn)) {
                log.error("NET连接中不存在该设备[{}]的信道, 无需移除", mn);
                return;
            }
            NET_CHANNEL_MAP.remove(mn);
            log.info("设备[{}]NET连接成功移除", mn);
        } else {
            log.error("未知来源Channel无需移除-{}", channel.id().toString());
        }
        // 优雅关闭
        ctx.close();

    }
}


