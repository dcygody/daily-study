package cn.zing.netty.client;

import cn.zing.netty.client.config.ClientSetting;
import cn.zing.netty.client.handler.ClientChannelInitializer;
import cn.zing.netty.client.repo.DataRepo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class ClientStart {


    Bootstrap bootstrap = new Bootstrap();

    @Autowired
    public ClientStart() {

        EventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ClientChannelInitializer());
    }

    @PostConstruct
    public void start() {
        if (DataRepo.DEVICE_LIST.size() == 0) {
            for (int i = 1; i <= DataRepo.DEVICE_SIZE; i++) {
                DataRepo.DEVICE_LIST.add("LP00" + i);
            }
        }
        for (String mn : DataRepo.DEVICE_LIST) {
//            Executors.newSingleThreadExecutor().execute(() -> {
                try {
                    startClient(ClientSetting.SERVER_ADDR, ClientSetting.SERVER_PORT, mn);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }


    public void startClient(String hostName, int port, String mn) throws InterruptedException {
        if (hostName != null && !"".equals(hostName)) {
            try {
                bootstrap.remoteAddress(new InetSocketAddress(InetAddress.getByName(hostName), port));
            } catch (UnknownHostException e) {
                e.printStackTrace();
                return;
            }
        } else {
            bootstrap.remoteAddress(new InetSocketAddress(port));
        }
        ChannelFuture channelFuture = bootstrap.connect();
        try {
            channelFuture.addListener(future -> {
                if (future.isSuccess()) {
                    log.info("Channel「" + mn + "」" + "已连接");
                    DataRepo.DEVICE_CHANNEL.put(mn, channelFuture.channel());
                }
                else {
                    log.info("Channel「" + mn + "」" + "连接失败，正在尝试重连");
                    channelFuture.channel().eventLoop().schedule(() -> {
                        try {
                            startClient(hostName, port, mn);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }, 10, TimeUnit.SECONDS);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
