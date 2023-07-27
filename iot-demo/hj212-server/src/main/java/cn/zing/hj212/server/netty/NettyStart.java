package cn.zing.hj212.server.netty;

import cn.zing.hj212.api.util.DateUtil;
import cn.zing.hj212.server.netty.handler.ConfigHandler;
import cn.zing.hj212.server.netty.handler.HeartBeatHandler;
import cn.zing.hj212.server.stater.AbstractStarter;
import com.alibaba.fastjson2.JSONObject;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * 这个是212的Netty
 */
@Slf4j
public class NettyStart extends AbstractStarter {

    public NettyStart(JSONObject config) {
        super(config);
    }

    @Override
    public void execute(JSONObject data) {
        int port = data.getInteger("port");

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline()
                                    .addLast("http-aggregator", new HttpObjectAggregator(10000))
                                    .addLast("server-logging-handler", new LoggingHandler(LogLevel.DEBUG))
                                    .addLast(new LineBasedFrameDecoder(1024))
                                    .addLast(new ConfigHandler())
                                    .addLast(new StringEncoder())
                                    .addLast(new StringDecoder())
                                    .addLast(new ChannelInboundHandlerAdapter() {
                                        @Override
                                        public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                            JSONObject data = new JSONObject();
                                            InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
                                            String message = (String) msg;
                                            log.info("收到: {}", message);
                                            data.put("ip", insocket.getAddress().getHostAddress());
                                            data.put("port", insocket.getPort());
                                            data.put("receiveTime", DateUtil.getCurrentMilliTime());
                                            // 这是接收到数据的处理
                                            JSONObject nodedata = new JSONObject();
                                            nodedata.put("ctx", ctx);
                                            nodedata.put("message", message);
                                            data.put("nodedata", nodedata);
                                            if (message.contains("QN") && message.startsWith("##")) {
                                                // 处理212数据
                                                data.put("protocol", "212-2017");
                                                dispatch(data);
                                            }
                                        }
                                    })
                                    .addLast(new IdleStateHandler(60, 0, 0, TimeUnit.SECONDS)) // 心跳监测, 如果1min内没有发送消息就断开连接
                                    .addLast(new HeartBeatHandler());
                        }
                    });
            ChannelFuture f = b.bind(port).sync();
            log.info("========================Netty启动=======================端口：" + port);
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("链接异常：===" + e.getMessage());
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


}
