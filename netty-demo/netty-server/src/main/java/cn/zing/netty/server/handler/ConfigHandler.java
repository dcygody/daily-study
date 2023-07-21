package cn.zing.netty.server.handler;

import cn.zing.netty.server.session.SessionMgr;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-01 12:33
 */
@Slf4j
public class ConfigHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("Channel[{}]接入", ctx.channel().id().toString());
        super.channelActive(ctx);
        SessionMgr.initSession(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.warn("Channel[{}]断开连接", ctx.channel().id().toString());
        // 移除channel
        super.channelInactive(ctx);
        SessionMgr.removeSession(ctx);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

}


