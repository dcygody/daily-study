package cn.zing.dubbos.framework.protocol.dubbo;


import cn.zing.dubbos.framework.Invoker;
import cn.zing.dubbos.framework.Protocol;
import cn.zing.dubbos.framework.URL;
import cn.zing.dubbos.framework.register.LocalRegister;
import cn.zing.dubbos.framework.register.RemoteMapRegister;

public class DubboProtocol implements Protocol {

    @Override
    public void export(URL url) {
        LocalRegister.regist(url.getInterfaceName(), url.getImplClass());
        RemoteMapRegister.regist(url.getInterfaceName(), url);
        new NettyServer().start(url.getHostname(), url.getPort());
    }

    @Override
    public Invoker refer(URL url) {
        return new DubboInvoker(url);
    }

}
