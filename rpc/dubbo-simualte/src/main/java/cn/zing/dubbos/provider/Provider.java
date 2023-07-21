package cn.zing.dubbos.provider;

import cn.zing.dubbos.framework.Protocol;
import cn.zing.dubbos.framework.ProtocolFactory;
import cn.zing.dubbos.framework.URL;
import cn.zing.dubbos.provider.api.HelloService;
import cn.zing.dubbos.provider.impl.HelloServiceImpl;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-21 13:10
 */
public class Provider {

    public static void main(String[] args) {


        URL url = new URL("dubbo", "localhost", 8080, HelloService.class.getName(), HelloServiceImpl.class);

        Protocol protocol = ProtocolFactory.getProtocol("dubbo");
        protocol.export(url);
    }
}




