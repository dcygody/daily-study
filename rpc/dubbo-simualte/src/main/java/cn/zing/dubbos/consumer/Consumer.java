package cn.zing.dubbos.consumer;

import cn.zing.dubbos.framework.ProxyFactory;
import cn.zing.dubbos.provider.api.HelloService;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-21 13:09
 */
public class Consumer {

    public static void main(String[] args) {

        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String res = helloService.sayHello("张三");

        System.out.println("收到---->" + res);

    }
}


