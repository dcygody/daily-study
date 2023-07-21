package cn.zing.netty.server.bean;

import com.alibaba.fastjson2.JSONObject;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Date;

/**
 * @description: 数据帧封装
 * @author: dcy
 * @create: 2023-06-29 15:15
 */
@Data
@NoArgsConstructor
public class DataFrame {
    /**
     * bytes数据
     */
    private byte[] bytesData;
    /**
     * 字符串数据
     */
    private String stringData;
    /**
     * JSON字符串数据
     */
    private JSONObject jsonData;
    /**
     * ChannelHandlerContext
     */
    private ChannelHandlerContext ctx;
    /**
     * Channel
     */
    private Channel channel;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 收到数据时间
     */
    private Date createTime;

    public DataFrame(byte[] bytesData, String stringData, JSONObject jsonData, ChannelHandlerContext ctx, Channel channel, String dataType, Date createTime) {
        this.bytesData = bytesData;
        this.stringData = stringData;
        this.jsonData = jsonData;
        this.ctx = ctx;
        this.channel = channel;
        this.dataType = dataType;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "DataFrame{" +
                "bytesData=" + Arrays.toString(bytesData) +
                ", stringData='" + stringData + '\'' +
                ", ctx=" + ctx.channel() +
                ", channel=" + channel.toString() +
                ", dataType='" + dataType + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}


