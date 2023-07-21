package cn.zing.netty.server.bean;

/**
 * @description:
 * @author: dcy
 * @create: 2023-06-30 14:50
 */
public class DataTypeConst {

    /**
     * 设备第一次上线请求mn
     */
    public static final String NEW = "new";
    /**
     * 设备4G心跳
     */
    public static final String HEART = "heart";
    /**
     * 设备有线心跳
     */
    public static final String NETHEART = "netheart";
    /**
     * 设备断电心跳
     */
    public static final String LOST_HEART = "lostheart";
    /**
     * 报警数据
     */
    public static final String REALTIME = "realtime";
    /**
     * 报警恢复数据
     */
    public static final String RECOVERY = "recovery";
    /**
     * 设备正常上报定时数据
     */
    public static final String DATA = "data";
    /**
     * 客户端连接心跳
     */
    public static final String WEBCLIENT = "webclient";
    /**
     * 客户端下发的指令
     */
    public static final String WEBDATA = "webdata";
    /**
     * 设备倾角
     */
    public static final String XYZ = "XYZ";
    /**
     * 设备参数设置
     */
    public static final String WARNCONFIG = "WarnConfig";
    /**
     * 温度配置
     */
    public static final String TEMP = "temp";
    /**
     * 操作开关相关指令
     */
    public static final String SWITCH = "switch";
    /**
     * 电表相关指令
     */
    public static final String COMMON_METER = "common_meter";
    /**
     * 设备每次链接平台都会发送
     */
    public static final String ONLINE = "online";
    /**
     * 未知数据
     */
    public static final String UK = "UK";
    /**
     * 设备获取升级文件包内容: A0A00001 第一包
     */
    public static final String A0A0 = "A0A0";
    /**
     * 设备获取升级文件信息
     */
    public static final String A1A1 = "A1A1";
    /**
     * 数据来源4G
     */
    public static final String FOUR_G = "FOUR_G";
    /**
     * 数据来源 NET
     */
    public static final String NET = "NET";
}


