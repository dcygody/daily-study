package cn.zing.netty.client.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
public class DataInboundHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        try {
            byte[] bytes = (byte[])msg;
            String data = new String(bytes, StandardCharsets.UTF_8);
            log.info("收到服务器: " + data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
