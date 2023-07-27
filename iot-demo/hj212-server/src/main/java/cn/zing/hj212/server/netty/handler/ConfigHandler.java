package cn.zing.hj212.server.netty.handler;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConfigHandler extends ChannelDuplexHandler {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("新的channel接入: {}, {}", ctx.channel().remoteAddress().toString(), ctx.channel().id().asShortText());
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.warn("channel断开连接: {}, {}", ctx.channel().remoteAddress().toString(), ctx.channel().id().asShortText());
        super.channelInactive(ctx);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("channel异常: {}, {}", ctx.channel().remoteAddress().toString(), ctx.channel().id().asShortText());

    }
}
