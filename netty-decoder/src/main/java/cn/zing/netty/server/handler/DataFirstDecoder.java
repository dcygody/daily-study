package cn.zing.netty.server.handler;

import cn.zing.netty.server.util.JSONUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @description:
 * @author: dcy
 * @create: 2023-06-22 23:31
 */
@Slf4j
public class DataFirstDecoder extends ByteToMessageDecoder {

    private static final String updatePrefix = "A1A1";
    private static final String updateInfoPrefix = "A0A0";
    private static final Integer UP_PACK_LEN = 6;
    private static final Integer BASE_LEN = 4;
    /**
     * JSON 包的最大长度. 超过这个长度 json出现问题
     */
    private static final Integer JSON_MAX_LEN = 100;

    Map<ChannelHandlerContext, String> jsonMap = new HashMap<>();


    Map<ChannelHandlerContext, AtomicBoolean> isHalfPackMap = new HashMap<>();


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        // 包长度
        int packLength = in.readableBytes();
//        log.info("收到包长度: {}", packLength);
        // 不满足 等待
        if (packLength < BASE_LEN) {
            log.info("包长度不足, 等待: {}", packLength);
            return;
        }
        byte[] bytes = new byte[packLength];
        in.readBytes(bytes);
        // 收到的字符串
        String strData = new String(bytes);
//        System.out.println(Arrays.toString(bytes));
        // 这是升级包A开头
        if (bytes[0] == 65) {
            // 升级完整包头
            if (strData.startsWith(updatePrefix)) {
                // 完整的升级包
                if (packLength >= UP_PACK_LEN) {
                    // 重置读索引
                    in.resetReaderIndex();
                    // 只读取6
                    bytes = new byte[UP_PACK_LEN];
                    in.readBytes(bytes);
                } else { // 升级到没到够, 等待
                    log.info("升级包没到够, 等待: {}", packLength);
                    // 重置读索引
                    in.resetReaderIndex();
                    return;
                }
            }
            // 获取升级文件信息
            if (strData.startsWith(updateInfoPrefix)) {
                if (packLength >= BASE_LEN) {
                    // 重置读索引
                    in.resetReaderIndex();
                    // 只读取6
                    bytes = new byte[BASE_LEN];
                    in.readBytes(bytes);
                } else {
                    log.info("升级包信息没到够, 等待: {}", packLength);
                    // 重置读索引
                    in.resetReaderIndex();
                    return;
                }
            }
            out.add(bytes);
            // 记录读索引的位置
            in.markReaderIndex();
            // 这是设备发送的数据 {
        } else if (bytes[0] == 123) {
            // 如果不是json
            if (!JSONUtil.isJSON(strData)) {
                log.info("不是json: {}", strData);
                List<Byte> byteList = new ArrayList<>(10);
                // 先判断是否是半包的
                if (isHalfPack(ctx)) {
                    bytes = dealHalfPack(strData, ctx);
                } else {
                    // 重置读索引
                    in.resetReaderIndex();
                    // 下面开始逐个字节解析json
                    while (true) {
                        // 如果已经读到数据末尾, 还没有跳出循环, 说明是半包
                        if (in.readableBytes() == 0) {
                            log.warn("json半包, 等待重新读: {}", strData);
                            jsonMap.put(ctx, strData);
                            // 重置读索引
//                            in.resetReaderIndex();
                            // 半包标识
                            setHalfPack(ctx);
                            break;
                        }
                        byte b = in.readByte();
                        byteList.add(b);
                        // 解析到一个json
                        if (listIsJSON(byteList)) {
                            bytes = listToBytes(byteList);
                            // 清空list
                            byteList.clear();
                            setNotHalfPack(ctx);
                            break;
                        }
                    }
                }
                // 这是半包数据
                if (isHalfPack(ctx)) {
                    // 如果后半部分数据包丢失, 这个包需要丢弃
                    log.warn("这是半包数据: {}", strData);
                } else {
                    out.add(bytes);
                    // 记录读索引的位置
                    in.markReaderIndex();
                }
            } else {
                out.add(bytes);
                // 记录读索引的位置
//                in.markReaderIndex();
            }

        } else { // 这里是未知报文, 可能是半包数据
            log.warn("这是未知报文: {}", strData);
            if (isHalfPack(ctx)) {
                bytes = dealHalfPack(strData, ctx);
            }
            out.add(bytes);
        }

    }

    // 判断一个list数据是否可以组装成json
    private boolean listIsJSON(List<Byte> list) {
//        if (list.size() > 100)
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i);
        }
        return JSONUtil.isJSON(new String(bytes));
    }

    // list 转bytes
    private byte[] listToBytes(List<Byte> list) {
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    /*这种半包处理逻辑最大的漏洞在于, 舍弃的更多
    * 比如发送{"a": "A","obj": {"a":1,"b":2}}{"a": "A","obj": {"a":1,"b":2}}{"a": "A","obj": {"a":1,"b":2}}
    * 第一包 {"a": "A","obj": {"a":1,"b":2}}{"a":
    * 第二包  "A","obj": {"a":1,"b":2}}{"a": "A","obj": {"a":1,"b":2}}
    * 那么这三条报文只能成功发送一个json, 有两个json舍弃了
     */
    private byte[] dealHalfPack(String strData, ChannelHandlerContext ctx) {
        log.warn("进入半包处理逻辑: {}", strData);
        String jsonHeader = jsonMap.get(ctx);
        if (JSONUtil.isJSON(jsonHeader + strData)) {
            log.info("JSON修复: {}", (jsonHeader + strData));
        }
        jsonMap.remove(ctx);
        setNotHalfPack(ctx);
        return (jsonHeader + strData).getBytes();
    }

    private boolean isHalfPack(ChannelHandlerContext ctx) {
        if (isHalfPackMap.isEmpty()) {
            return false;
        }
        return isHalfPackMap.get(ctx).get();
    }

    private void setHalfPack(ChannelHandlerContext ctx) {
        if (isHalfPackMap.isEmpty()) {
            isHalfPackMap.put(ctx, new AtomicBoolean(false));
        }
        isHalfPackMap.get(ctx).set(true);
    }

    private void setNotHalfPack(ChannelHandlerContext ctx) {
        if (isHalfPackMap.isEmpty()) {
            isHalfPackMap.put(ctx, new AtomicBoolean(false));
        }
        isHalfPackMap.get(ctx).set(false);
    }

}


