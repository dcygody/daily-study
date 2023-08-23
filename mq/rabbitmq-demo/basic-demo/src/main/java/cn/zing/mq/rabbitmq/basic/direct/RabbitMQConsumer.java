package cn.zing.mq.rabbitmq.basic.direct;

import cn.zing.mq.rabbitmq.basic.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-23 16:10
 */
public class RabbitMQConsumer {

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RabbitMQUtil.QUEUE_HELLO, false, false, false, null);
        //Consumer接口还一个实现QueueConsuemr 但是代码注释过期了。
        Consumer myconsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("========================");
                String routingKey = envelope.getRoutingKey();
                System.out.println("routingKey >" + routingKey);
                String contentType = properties.getContentType();
                System.out.println("contentType >" + contentType);
                long deliveryTag = envelope.getDeliveryTag();
                System.out.println("deliveryTag >" + deliveryTag);
                System.out.println("content:" + new String(body, StandardCharsets.UTF_8));
                System.out.println("messageId:" + properties.getMessageId());
                properties.getHeaders().forEach((key, value) -> System.out.println("key: " + key + "; value: " + value));
                // (process the message components here ...)
                //消息处理完后，进行答复。答复过的消息，服务器就不会再次转发。
                //没有答复过的消息，服务器会一直不停转发。
                channel.basicAck(deliveryTag, false);
            }
        };

        channel.basicConsume(RabbitMQUtil.QUEUE_HELLO, false, myconsumer);
    }
}


