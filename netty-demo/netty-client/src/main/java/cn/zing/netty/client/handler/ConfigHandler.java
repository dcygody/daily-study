package cn.zing.netty.client.handler;

import cn.zing.netty.client.ClientStart;
import cn.zing.netty.client.config.ClientSetting;
import cn.zing.netty.client.repo.DataRepo;
import cn.zing.netty.client.util.SpringUtil;
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
        ClientStart clientStart = SpringUtil.getBean(ClientStart.class);
        DataRepo.DEVICE_CHANNEL.forEach((mn, ch) -> {
            if (ch.equals(ctx.channel())) {
                try {
                    clientStart.startClient(ClientSetting.SERVER_ADDR, ClientSetting.SERVER_PORT, mn);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
//        ctx.close();
    }
}
