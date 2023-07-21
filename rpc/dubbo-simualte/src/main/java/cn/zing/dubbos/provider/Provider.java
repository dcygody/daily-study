package cn.zing.dubbos.provider;

import cn.zing.dubbos.framework.protocol.http.HttpServer;
import cn.zing.dubbos.framework.register.LocalRegister;
import cn.zing.dubbos.provider.api.HelloService;
import cn.zing.dubbos.provider.impl.HelloServiceImpl;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-21 13:10
 */
public class Provider {

    public static void main(String[] args) {
        // 本地注册
        LocalRegister.regist(HelloService.class.getName(), HelloServiceImpl.class);

        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost", 8080);
    }
}




