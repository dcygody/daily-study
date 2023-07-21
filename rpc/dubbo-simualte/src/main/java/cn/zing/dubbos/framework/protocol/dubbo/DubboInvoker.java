package cn.zing.dubbos.framework.protocol.dubbo;


import cn.zing.dubbos.framework.Invocation;
import cn.zing.dubbos.framework.Invoker;
import cn.zing.dubbos.framework.URL;

public class DubboInvoker implements Invoker {

    private URL url;

    public DubboInvoker(URL url) {
        this.url = url;
    }

    @Override
    public String invoke(Invocation invocation) {
        NettyClient nettyClient = new NettyClient();
        return nettyClient.send(url.getHostname(),url.getPort(), invocation);
    }

}
