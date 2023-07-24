package cn.zing.boot;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-24 20:55
 */
public class JettyWebServer implements WebServer {
    @Override
    public void start() {
        System.out.println("启动Jetty");
    }
}


