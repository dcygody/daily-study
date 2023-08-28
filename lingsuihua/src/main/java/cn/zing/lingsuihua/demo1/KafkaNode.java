package cn.zing.lingsuihua.demo1;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: kafka节点
 * @author: dcy
 * @create: 2023-08-28 10:04
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class KafkaNode extends Node{

    private String name;

    public KafkaNode(String name) {
        this.name = name;
    }
}


