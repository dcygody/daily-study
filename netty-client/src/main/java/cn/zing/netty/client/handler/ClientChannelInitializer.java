package cn.zing.netty.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientChannelInitializer extends ChannelInitializer<NioSocketChannel> {

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ConfigHandler());
        ch.pipeline().addLast(new ByteArrayEncoder()); // 1019
        ch.pipeline().addLast(new ByteArrayDecoder()); // 1019
        ch.pipeline().addLast(new DataInboundHandler()); // 1019

    }
}
