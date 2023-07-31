package cn.zing.kafka.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-31 15:04
 */
@RestController
public class KafkaController {

    private final static String TOPIC_NAME = "test";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/send")
    public void send() {
//        kafkaTemplate.send(TOPIC_NAME, 0, "key", "this is a msg");
        kafkaTemplate.send(TOPIC_NAME, "key", "this is a msg");
    }

}


