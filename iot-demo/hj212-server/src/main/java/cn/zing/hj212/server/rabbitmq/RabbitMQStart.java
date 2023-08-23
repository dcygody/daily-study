package cn.zing.hj212.server.rabbitmq;

import cn.zing.hj212.api.util.DateUtil;
import cn.zing.hj212.server.stater.AbstractStarter;
import com.alibaba.fastjson2.JSONObject;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-23 16:32
 */
@Slf4j
public class RabbitMQStart extends AbstractStarter {

    public RabbitMQStart(JSONObject config) {
        super(config);
    }
    @Override
    public void execute(JSONObject data) {

        Connection connection = getRabbitMQConnection(data);
        try {
            Channel channel = connection.createChannel();
            channel.queueDeclare(data.getString("routerKey"), false, false, false, null);
            //Consumer接口还一个实现QueueConsuemr 但是代码注释过期了。
            Consumer myconsumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body) throws IOException {

                    String message = new String(body, StandardCharsets.UTF_8);
                    log.info("RabbitMQ 收到: " + message);
                    data.put("receiveTime", DateUtil.getCurrentMilliTime());
                    data.put("source", "RabbitMQ");
                    // 这是接收到数据的处理
                    JSONObject nodedata = new JSONObject();
                    nodedata.put("message", message);
                    data.put("nodedata", nodedata);
                    if (message.contains("QN") && message.startsWith("##")) {
                        // 处理212数据
                        data.put("protocol", "212-2017");
                        dispatch(data);
                    }
                    long deliveryTag = envelope.getDeliveryTag();
                    //消息处理完后，进行答复。答复过的消息，服务器就不会再次转发。
                    //没有答复过的消息，服务器会一直不停转发。
                    channel.basicAck(deliveryTag, false);
                }
            };
            channel.basicConsume(data.getString("routerKey"), false, myconsumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Connection getRabbitMQConnection(JSONObject data) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(data.getString("host"));
        factory.setPort(data.getInteger("port"));
        factory.setUsername(data.getString("username"));
        factory.setPassword(data.getString("password"));
        factory.setVirtualHost(data.getString("virtualHost"));
        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        return connection;

    }
}


