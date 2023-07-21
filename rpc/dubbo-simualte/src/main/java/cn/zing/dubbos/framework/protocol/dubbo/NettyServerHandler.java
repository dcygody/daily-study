package cn.zing.dubbos.framework.protocol.dubbo;

import cn.zing.dubbos.framework.Invocation;
import cn.zing.dubbos.framework.register.LocalRegister;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Invocation invocation = (Invocation) msg;

        Class serviceImpl = LocalRegister.get(invocation.getInterfaceName());

        Method method = serviceImpl.getMethod(invocation.getMethodName(), invocation.getParamType());
        Object result = method.invoke(serviceImpl.newInstance(), invocation.getParams());

        System.out.println("Netty-------------" + result.toString());
        ctx.writeAndFlush("Netty:" + result);
    }
}
