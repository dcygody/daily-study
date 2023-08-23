package cn.zing.hj212.server.kafka;

import cn.zing.hj212.api.util.DateUtil;
import cn.zing.hj212.server.stater.AbstractStarter;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @description: 数据来源是kafka
 * @author: dcy
 * @create: 2023-08-22 16:23
 */
@Slf4j
public class KafkaStart extends AbstractStarter {

    public KafkaStart(JSONObject config) {
        super(config);
    }


    @Override
    public void execute(JSONObject data) {
        final String brokerList = data.getString("brokerList");
        final String topic = data.getString("topic");
        final String groupId = data.getString("groupId");
//        log.info("kafka启动参数： {}", data.toString());
        //0. 配置客户端的参数
        Properties prop = new Properties();

        /*
         * 消费者 的key 反序列化器，必须和生产者一致
         */
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        /*
         * 消费者 的value 反序列化器，必须和生产者一致
         */
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        /*
         * 0.1 设置broker服务端ip列表
         * ProducerConfig.BOOTSTRAP_SERVERS_CONFIG 属性：指定生产者连接的kafka集群的地址
         * 格式： host1:port1,host2:port2,host3:port3
         * 可以设置一个或者多个地址，中间以逗号分隔。此参数默认为 ""
         * 这里并不需要填写所有的kafka集群的所有broker的地址，因为消费者会从给定的broker查找到其他的broker的信息
         * 建议至少设置两个或两个以上的broker地址，当其中一个宕机的时候，生产者仍然可以连接到kafka集群
         */
        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        /*
         * 0.2 设置消费组的名称,默认为""
         * 一般该参数设置为 具有业务的值
         */
        prop.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        /*
         * ProducerConfig.CLIENT_ID_CONFIG=client.id属性： 设置KafkaConsumer对应的客户端id
         * 默认为""
         * 如果客户端不设置，那么kafka会自动生成一个：形式如 consumer-1，consumer-2 等
         */
        prop.put(ConsumerConfig.CLIENT_ID_CONFIG, "hj212-client-1");

        //1. 创建一个消费客户端实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(prop);

        //2. 订阅主题
        consumer.subscribe(Collections.singletonList(topic));

        //3. 循环消费消息
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : records) {
                log.info("Kafka 收到: {}", record.value());

                String message = record.value();
                data.put("receiveTime", DateUtil.getCurrentMilliTime());
                data.put("source", "Kafka");
                data.put("topic", record.topic());
                // 这是接收到数据的处理
                JSONObject nodedata = new JSONObject();
                nodedata.put("message", message);
                data.put("nodedata", nodedata);
                if (message.contains("QN") && message.startsWith("##")) {
                    // 处理212数据
                    data.put("protocol", "212-2017");
                    dispatch(data);
                }
            }
        }


    }
}


