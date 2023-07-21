package cn.zing.dubbos.framework.protocol.http;


import cn.zing.dubbos.framework.Invoker;
import cn.zing.dubbos.framework.Protocol;
import cn.zing.dubbos.framework.URL;
import cn.zing.dubbos.framework.register.LocalRegister;
import cn.zing.dubbos.framework.register.RemoteMapRegister;

public class HttpProtocol implements Protocol {

    @Override
    public void export(URL url) {
        // 本地注册
        LocalRegister.regist(url.getInterfaceName(), url.getImplClass());
        // 注册中心注册
        RemoteMapRegister.regist(url.getInterfaceName(), url);
        // 启动Tomcat
        new HttpServer().start(url.getHostname(), url.getPort());
    }

    @Override
    public Invoker refer(URL url) {
        return new HttpInvoker(url);
    }

}
