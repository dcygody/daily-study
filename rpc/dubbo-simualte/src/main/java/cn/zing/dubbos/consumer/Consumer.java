package cn.zing.dubbos.consumer;

import cn.zing.dubbos.framework.Invocation;
import cn.zing.dubbos.framework.protocol.http.HttpClient;
import cn.zing.dubbos.provider.api.HelloService;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-21 13:09
 */
public class Consumer {

    public static void main(String[] args) {

        HttpClient httpClient = new HttpClient();
        Invocation invocation = new Invocation(HelloService.class.getName(), "sayHello", new Object[]{"DCY"}, new Class[]{String.class});
        String res = httpClient.send("localhost", 8080, invocation);
        System.out.println("收到---->" + res);

    }
}


