package cn.zing.mq.rabbitmq.basic.direct;

import cn.zing.mq.rabbitmq.basic.RabbitMQUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-23 16:05
 */
public class Sender {

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(RabbitMQUtil.QUEUE_HELLO, false, false, false, null);

        String msg = "##0122QN=20230823092837664;ST=22;CN=2011;PW=123456;MN=D111;Flag=6;CP=&&DataTime=20230823092837;w00000-Rtd=11.76,w00000-Flag=N;&&E041\r\n";

        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
        builder.deliveryMode(MessageProperties.PERSISTENT_TEXT_PLAIN.getDeliveryMode());
        builder.priority(MessageProperties.PERSISTENT_TEXT_PLAIN.getPriority());
        //携带消息ID
        builder.messageId("" + channel.getNextPublishSeqNo());
        for (;;) {
//            Map<String, Object> headers = new HashMap<>();
//            // header 也可以携带信息
//            headers.put("token", UUID.randomUUID().toString());
//            builder.headers(headers);

            channel.basicPublish("", RabbitMQUtil.QUEUE_HELLO, builder.build(), msg.getBytes(StandardCharsets.UTF_8));
            System.out.println("发送: " + msg);
            Thread.sleep(1000);
        }
//        channel.close();
//        connection.close();

    }
}


