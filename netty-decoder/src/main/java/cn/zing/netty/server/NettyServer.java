package cn.zing.netty.server;

import cn.zing.netty.server.handler.ConfigHandler;
import cn.zing.netty.server.handler.DataDecoder;
import cn.zing.netty.server.handler.DataInboundHandler;
import cn.zing.netty.server.handler.DataFirstDecoder;
import cn.zing.netty.server.util.FileUtil;
import com.alibaba.fastjson2.JSONObject;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: dcy
 * @create: 2023-06-22 23:20
 */
@Slf4j
@Service
public class NettyServer {

    public static JSONObject sendJson = null;
    public static JSONObject respJson = null;

    @PostConstruct
    public void startNetty() throws Exception {
        String path = Objects.requireNonNull(Objects.requireNonNull(ClassUtils.getDefaultClassLoader()).getResource("")).getPath();
        File file = new File(path + "protocol/服务器发.json");
        sendJson = JSONObject.parseObject(FileUtil.read(file));

        File file2 = new File(path + "protocol/服务器回复.json");
        respJson = JSONObject.parseObject(FileUtil.read(file2));

//        String pretty = JSON.toJSONString(this.json, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
//                SerializerFeature.WriteDateUseDateFormat);
        log.info("读取配置1: {}", sendJson);
        log.info("读取配置2: {}", respJson);
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                startServer10704();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void startServer10704() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new IdleStateHandler(70, 0, 0, TimeUnit.SECONDS)); // 心跳监测, 如果5min内没有发送消息就断开连接
                            p.addLast(new ConfigHandler());
                            p.addLast(new DataDecoder());
                            p.addLast(new ByteArrayDecoder());
                            p.addLast(new ByteArrayEncoder());
                            p.addLast(new DataInboundHandler());
//                            p.addLast(new HeartBeatHandler());
                        }
                    });
            // Start the server.
            ChannelFuture f = b.bind(10704).sync();
            log.info("Netty启动成功, 监听端口: " + 10704);
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

