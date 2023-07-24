package cn.zing.boot;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Map;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-24 20:30
 */
public class DcySpringApplication {

    public static void run(Class clazz) {

        // Spring 容器
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(clazz);
        applicationContext.refresh();

        WebServer webServer = getWebServer(applicationContext);
        webServer.start();

//        startTomcat(applicationContext);


    }

    /**
     * 获取要启动的容器 tomcat / jetty / undertow
     * @param applicationContext
     * @return
     */
    private static WebServer getWebServer(WebApplicationContext applicationContext) {

        Map<String, WebServer> beansOfType = applicationContext.getBeansOfType(WebServer.class);

        if (beansOfType.isEmpty()) {
            throw new NullPointerException("没有配置启动容器");
        }

        if (beansOfType.size() > 1) {
            throw new IllegalStateException("配置的容器不唯一");
        }

        return beansOfType.values().stream().findFirst().get();
    }

//    public static void startTomcat(WebApplicationContext applicationContext) {
//
//        Tomcat tomcat = new Tomcat();
//
//        Server server = tomcat.getServer();
//        Service service = server.findService("Tomcat");
//
//        Connector connector = new Connector();
//        connector.setPort(8081);
//
//        Engine engine = new StandardEngine();
//        engine.setDefaultHost("localhost");
//
//        Host host = new StandardHost();
//        host.setName("localhost");
//
//        String contextPath = "";
//        Context context = new StandardContext();
//        context.setPath(contextPath);
//        context.addLifecycleListener(new Tomcat.FixContextListener());
//
//        host.addChild(context);
//        engine.addChild(host);
//
//        service.setContainer(engine);
//        service.addConnector(connector);
//
//        tomcat.addServlet(contextPath, "dispatcher", new
//                DispatcherServlet(applicationContext));
//
//        context.addServletMappingDecoded("/*", "dispatcher");
//
//        try {
//            tomcat.start();
//        } catch (LifecycleException e) {
//            e.printStackTrace();
//        }
//    }
}


