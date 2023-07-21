package cn.zing.netty.server.handler;

import cn.zing.netty.server.bean.DataTypeConst;
import cn.zing.netty.server.util.JSONUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: dcy
 * @create: 2023-06-22 23:31
 */
@Slf4j
public class DataDecoderBak extends ByteToMessageDecoder {

    private static final Integer UP_PACK_LEN = 8;
    private static final Integer BASE_LEN = 4;
    private static final Integer JSON_MIN_LEN = 5;
    /**
     * 标记是否半包
     */
    private final Map<ChannelHandlerContext, AtomicBoolean> isHalfPackMap = new HashMap<>();

    /**
     * 记录上一个半包位置
     */
    private final Map<ChannelHandlerContext, AtomicInteger> readIndexMap = new HashMap<>();

    private static final AttributeKey<Integer> READ_INDEX = AttributeKey.valueOf("0");

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
            if (strData.startsWith(DataTypeConst.A0A0)) {
                // 完整的升级包
                if (packLength >= UP_PACK_LEN) {
                    // 重置读索引
                    in.resetReaderIndex();
                    // 只读取8
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
            if (strData.startsWith(DataTypeConst.A1A1)) {
                if (packLength >= BASE_LEN) {
                    // 重置读索引
                    in.resetReaderIndex();
                    // 只读取4
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
            // 这是设备发送的数据 JSON 格式 {
        } else if (bytes[0] == 123) {
            // 如果不是json
            if (!JSONUtil.isJSON(strData)) {
//                log.info("不是json: {}", strData);
                List<Byte> byteList = new ArrayList<>(10);
                // 重置读索引
                in.resetReaderIndex();
                // 下面开始逐个字节解析json
                while (true) {
                    // 如果已经读到数据末尾, 还没有跳出循环, 说明是半包
                    if (in.readableBytes() == 0) {
                        // 如果已经是半包, 又来一个半包, 应该舍弃第一个半包
                        // {"a": "A",{"a": "A",
                        if (isHalfPack(ctx)) {
                            // 当前索引
                            int index = in.readerIndex();
                            log.info("上次标记索引位置: {}, 当前索引位置: {}", getReaderIndex(ctx), index);
                            // 重置读索引
                            in.resetReaderIndex();
                            byte[] b = new byte[getReaderIndex(ctx)];
                            in.readBytes(b);
                            log.warn("丢弃无效数据: {}", new String(b));
                        } else {
                            // 记录下半包索引
                            setReaderIndex(ctx, in.readerIndex());
                            log.warn("json半包, 等待重新读: {}. 标记索引: {}", strData, in.readerIndex());
                            // 重置读索引
                            in.resetReaderIndex();
                            // 半包标识
                            setHalfPack(ctx);
                        }
                        break;
                    }
                    byte b = in.readByte();
                    // 忽略\r\n
                    if (b == 10 || b == 13) {
                        continue;
                    }
                    byteList.add(b);
                    // 解析到一个json
                    if (byteList.size() > JSON_MIN_LEN) {
                        if (listIsJSON(byteList)) {
                            bytes = listToBytes(byteList);
                            // 清空list
                            byteList.clear();
                            break;
                        }
                    }
                }
                // 这是半包数据
                if (isHalfPack(ctx)) {
                    // {"csq":"29","ver":"1.9.4",{"csq":"29","ver":"1.9.4","iccid":"898604811920C0630316","imei":"863488059961766"}
                    // 如果后半部分数据包丢失, 这个包需要丢弃
                    log.warn("这是半包数据: {}", strData);
                } else {
                    // 半包标识
                    setNotHalfPack(ctx);
                    out.add(bytes);
                    // 记录读索引的位置
                    in.markReaderIndex();
                }
            } else {
                setNotHalfPack(ctx);
                out.add(bytes);
                // 记录读索引的位置
//                in.markReaderIndex();
            }

        } else { // 这里是未知报文
            log.warn("这是未知报文: {}", strData);
            out.add(bytes);
        }

    }

    /**
     * 判断一个list里的数据是否可以拼接成json
     */
    private boolean listIsJSON(List<Byte> list) {
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i);
        }
        return JSONUtil.isJSON(new String(bytes));
    }

    /**
     * list 转 bytes
     */
    private byte[] listToBytes(List<Byte> list) {
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    /**
     * 记录第一个半包末端索引
     */
    private void setReaderIndex(ChannelHandlerContext ctx, int readIndex) {
        if (!readIndexMap.containsKey(ctx)) {
            readIndexMap.put(ctx, new AtomicInteger(readIndex));
        } else {
            readIndexMap.get(ctx).set(readIndex);
        }
    }

    /**
     * 拿到第一个半包末端索引
     */
    private int getReaderIndex(ChannelHandlerContext ctx) {
        if (!readIndexMap.containsKey(ctx)) {
            return 0;
        }
        return readIndexMap.get(ctx).get();

    }

    /**
     * 判断是否半包,如果半包, 那么下一个包大概率可以把它修复
     */
    private boolean isHalfPack(ChannelHandlerContext ctx) {
        if (!isHalfPackMap.containsKey(ctx)) {
            return false;
        }
        return isHalfPackMap.get(ctx).get();
    }

    /**
     * 发生半包 记录
     */
    private void setHalfPack(ChannelHandlerContext ctx) {
        if (!isHalfPackMap.containsKey(ctx)) {
            isHalfPackMap.put(ctx, new AtomicBoolean(true));
            return;
        }
        isHalfPackMap.get(ctx).set(true);
    }

    /**
     * 半包修复, 删除
     */
    private void setNotHalfPack(ChannelHandlerContext ctx) {
        isHalfPackMap.remove(ctx);
        readIndexMap.remove(ctx);
    }

}


