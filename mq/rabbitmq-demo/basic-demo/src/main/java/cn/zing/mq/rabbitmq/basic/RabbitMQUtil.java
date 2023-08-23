package cn.zing.mq.rabbitmq.basic;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-23 16:02
 */
public class RabbitMQUtil {


    private static Connection connection;
    private static final String HOST_NAME="192.168.198.133";
    private static final int HOST_PORT=5672;

    public static final String QUEUE_HELLO="hj212-q";

    private RabbitMQUtil() {}

    public static Connection getConnection() throws Exception {
        if(null == connection) {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(HOST_NAME);
            factory.setPort(HOST_PORT);
            factory.setUsername("admin");
            factory.setPassword("admin");
            factory.setVirtualHost("/mirror");
            connection = factory.newConnection();
        }
        return connection;
    }
}


