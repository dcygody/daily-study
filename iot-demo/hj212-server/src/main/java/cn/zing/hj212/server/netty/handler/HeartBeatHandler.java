package cn.zing.hj212.server.netty.handler;

import cn.zing.hj212.server.netty.session.SessionMgr;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 心跳检测
 */
@Slf4j
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
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
                break;
            case ALL_IDLE:
                break;
            default:
                break;
        }
    }
}
