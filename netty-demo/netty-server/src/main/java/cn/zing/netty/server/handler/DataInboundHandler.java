package cn.zing.netty.server.handler;

import cn.zing.netty.server.NettyServer;
import cn.zing.netty.server.bean.DataFrame;
import cn.zing.netty.server.bean.DataTypeConst;
import cn.zing.netty.server.session.SessionMgr;
import cn.zing.netty.server.util.JSONUtil;
import com.alibaba.fastjson2.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @description:
 * @author: dcy
 * @create: 2023-06-22 23:21
 */
@Slf4j
public class DataInboundHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        byte[] bytes = (byte[]) msg;

        String data = new String(bytes);
//        System.out.println();
//        System.out.println();
        log.info("业务逻辑收到: {}", data);

        JSONObject json = JSONUtil.parseJSON(data);
        String type = DataTypeConst.UK;
        if (null == json) { // 这是升级包
            if (data.startsWith(DataTypeConst.A0A0) || data.startsWith(DataTypeConst.A1A1)) {
                type = data.substring(0, 4);
            }
        } else {
            // 设备每次重新连接服务器都会发送
            if (json.containsKey("csq")) {
                type = DataTypeConst.ONLINE;
            } else {
                type = json.getString("type");
            }
        }
        DataFrame dataFrame = new DataFrame(bytes, data, json, ctx, ctx.channel(), type, new Date());

        dealDataFrame(dataFrame);

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            // 接收到心跳超时事件
            IdleStateEvent event = (IdleStateEvent) evt;
            switch (event.state()) {
                case READER_IDLE:
                    String mn = SessionMgr.getDeviceId(ctx);
                    if (null != mn) {
                        log.error("设备[{}] 5分钟没有发送心跳, 即将断开连接", mn);
                    } else {
                        log.error("Channel[{}] 5分钟没有发送心跳, 即将断开连接", ctx.channel().id());
                    }
                    SessionMgr.removeSession(ctx);
                    break;
                case WRITER_IDLE:
                case ALL_IDLE:
                    break;
            }

        }
    }

    // {"ver":"1.9.4","csq":0,"imei":"863488052912717","iccid":"898604811920C0630316"}
    private void dealDataFrame(DataFrame dataFrame) {

        String dataType = dataFrame.getDataType();
        String mn = null;
        if (null == dataType) {
            dataType = "UK";
        }
        String type;
        String res = "UKUKUKUK";
        switch (dataType) {
            case DataTypeConst.ONLINE:
                type = "设备连接平台";
                res = NettyServer.respJson.getString(DataTypeConst.HEART);
                break;
            case DataTypeConst.HEART:
                type = "4G心跳";
                res = NettyServer.respJson.getString(DataTypeConst.HEART);
                // 给连接设置属性mn和数据来源
                mn = dataFrame.getJsonData().getString("mn");
                SessionMgr.setDeviceId(dataFrame.getCtx(),  mn);
                SessionMgr.setDataSource(dataFrame.getCtx(),  DataTypeConst.FOUR_G);
                SessionMgr.putSession(dataFrame);
                break;
            case DataTypeConst.NETHEART:
                type = "NET心跳";
                res = NettyServer.respJson.getString(DataTypeConst.HEART);
                // 给连接设置属性mn和数据来源
                mn = dataFrame.getJsonData().getString("mn");
                SessionMgr.setDeviceId(dataFrame.getCtx(),  mn);
                SessionMgr.setDataSource(dataFrame.getCtx(),  DataTypeConst.NET);
                SessionMgr.putSession(dataFrame);
                break;
            case DataTypeConst.DATA:
                type = "设备数据";
                res = NettyServer.respJson.getString(DataTypeConst.DATA);
                break;
            case DataTypeConst.A0A0:
                type = "设备获取升级包";
                res = "";
                break;
            default:
                type = "unknown_data";
                break;
        }
        log.info("{}-{}: {}", dataFrame.getChannel().id().toString(), type, dataFrame.getStringData());
        log.info("{}-{}回复: {}", dataFrame.getChannel().id(), type, res);
        dataFrame.getChannel().writeAndFlush(res.getBytes());
    }
}


