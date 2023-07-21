package cn.zing.dubbos.provider.impl;

import cn.zing.dubbos.provider.api.HelloService;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-21 13:12
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello: " + name;
    }
}


