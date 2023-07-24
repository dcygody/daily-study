package cn.zing.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-24 21:00
 */
@Configuration
public class WebServerAutoConfiguration implements AutoConfiguration{

    @Bean
    @DcyConditionOnClass("org.apache.catalina.startup.Tomcat")
    public TomcatWebServer tomcatWebServer() {

        return new TomcatWebServer();
    }

    @Bean
    @DcyConditionOnClass("org.eclipse.jetty.server.Server")
    public JettyWebServer jettyWebServer() {

        return new JettyWebServer();
    }
}


