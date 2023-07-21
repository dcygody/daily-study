package cn.zing.dubbos.framework.protocol.http;


import cn.zing.dubbos.framework.Invocation;
import cn.zing.dubbos.framework.Invoker;
import cn.zing.dubbos.framework.URL;

public class HttpInvoker implements Invoker {

    private URL url;

    public HttpInvoker(URL url) {
        this.url = url;
    }

    @Override
    public String invoke(Invocation invocation) {
        HttpClient httpClient = new HttpClient();
        return httpClient.send(url.getHostname(), url.getPort(), invocation);
    }
}
