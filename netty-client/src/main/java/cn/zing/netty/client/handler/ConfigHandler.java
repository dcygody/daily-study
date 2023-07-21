package cn.zing.netty.client.handler;

import cn.zing.netty.client.ClientStart;
import cn.zing.netty.client.repo.DataRepo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConfigHandler extends ChannelDuplexHandler {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.warn("{} 断开连接", ctx.channel().id().asShortText());
        Channel channel = ctx.channel();
        DataRepo.DEVICE_CHANNEL.forEach((mn, ch) -> {
            if (ch.equals(channel)) {
                ClientStart clientStart = new ClientStart();
                try {
                    clientStart.start(mn);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
//        ctx.close();
    }
}
