package cn.zing.hj212.api.enm;

import cn.zing.hj212.api.code.CodeMean;

/**
 * HJ2017 部分污染物
 */
public enum PollutionAll implements CodeMean {

    /**
     * 表B.2 气监测因子编码表
     */
    a00000("废气","B02","立方米/秒","立方米","N6.1"),
    a01001("温度","--","摄氏度","--","N3.1"),
    a01002("湿度","--","%","--","N3.1"),
    a01006("气压","--","千帕","--","N5.3"),
    a01007("风速","--","米/秒","--","N4.1"),
    a01008("风向","--","[角]度","--","N4"),
    a01010("林格曼黑度","37","无量纲","--","N1"),
    a01011("烟气流速","S02","米/秒","--","N5.2"),
    a01012("烟气温度","S03","摄氏度","--","N3.1"),
    a01013("烟气压力","S08","千帕","--","N5.3"),
    a01014("烟气湿度","S05","%","--","N3.1"),
    a01015("制冷温度","S06","摄氏度","--","N3.1"),
    a01016("烟道截面积","S07","平方米","--","N4.2"),
    a01017("烟气动压","S04","千帕","--","N5.3"),
    a01901("垃圾焚烧炉膛内焚烧平均温度","--","摄氏度","--","N4.1"),
    a01902("垃圾焚烧炉膛内DCS温度","--","摄氏度","--","N4.1"),
    a05001("二氧化碳","30","毫克/立方米","千克","N3.3"),
    a05002("甲烷","--","纳克/立方米","克","N4.1"),
    a05008("三氯一氟甲烷","--","毫克/立方米","千克","N7.3"),
    a05009("二氯二氟甲烷","--","毫克/立方米","千克","N7.3"),
    a05013("三氯三氟乙烷","--","毫克/立方米","千克","N7.3"),
    a19001("氧气含量","S01","%","--","N3.1"),
    a20007("砷","--","纳克/立方米","克","N1.6"),
    a20016("铍及其化合物","36","毫克/立方米","千克","N4.4"),
    a20025("镉及其化合物","33","毫克/立方米","千克","N3.4"),
    a20026("镉","--","纳克/立方米","克","N3.3"),
    a20043("铅及其化合物","32","毫克/立方米","千克","N2.4"),
    a20044("铅","--","纳克/立方米","克","N3.3"),
    a20057("汞及其化合物","31","毫克/立方米","千克","N4.4"),
    a20058("汞","--","纳克/立方米","克","N3.2"),
    a20063("镍及其化合物","35","毫克/立方米","千克","N3.3"),
    a20091("锡及其化合物","34","毫克/立方米","千克","N4.3"),
    a21001("氨（氨气）","10","纳克/立方米","克","N4.3"),
    a21002("氮氧化物","03","毫克/立方米","千克","N5.1"),
    a21003("一氧化氮","--","毫克/立方米","千克","N4.1"),
    a21004("二氧化氮","--","毫克/立方米","千克","N4.1"),
    a21005("一氧化碳","04","毫克/立方米","千克","N3.3"),
    a21017("氰化物","07","毫克/立方米","千克","N3.3"),
    a21018("氟化物","06","毫克/立方米","千克","N2.3"),
    a21022("氯气","11","毫克/立方米","千克","N4.3"),
    a21024("氯化氢","08","毫克/立方米","千克","N4.3"),
    a21026("二氧化硫","02","毫克/立方米","千克","N5.2"),
    a21028("硫化氢","05","毫克/立方米","千克","N3.2"),
    a23001("酚类","27","毫克/立方米","千克","N3.3"),
    a24003("二氯甲烷","--","毫克/立方米","千克","N7.3"),
    a24004("三氯甲烷","--","毫克/立方米","千克","N7.3"),
    a24005("四氯甲烷","--","毫克/立方米","千克","N7.3"),
    a24006("二溴一氯甲烷","--","毫克/立方米","千克","N7.3"),
    a24007("一溴二氯甲烷","--","毫克/立方米","千克","N7.3"),
    a24008("溴甲烷","--","毫克/立方米","千克","N7.3"),
    a24009("三溴甲烷","--","毫克/立方米","千克","N7.3"),
    a24015("氯乙烷","--","毫克/立方米","千克","N7.3"),
    a24016("1,1-二氯乙烷","--","毫克/立方米","千克","N7.3"),
    a24017("1,2-二氯乙烷","--","毫克/立方米","千克","N7.3"),
    a24018("1,1,1-三氯乙烷","--","毫克/立方米","千克","N7.3"),
    a24019("1,1,2-三氯乙烷","--","毫克/立方米","千克","N7.3"),
    a24020("1,1,2,2-四氯乙烷","--","毫克/立方米","千克","N7.3"),
    a24027("1,2-二氯丙烷","--","毫克/立方米","千克","N7.3"),
    a24034("1,2-二溴乙烷","--","毫克/立方米","千克","N7.3"),
    a24036("环己烷","--","毫克/立方米","千克","N7.3"),
    a24042("正己烷","--","毫克/立方米","千克","N7.3"),
    a24043("正庚烷","--","毫克/立方米","千克","N7.3"),
    a24046("氯乙烯","29","毫克/立方米","千克","N4.3"),
    a24047("1,1-二氯乙烯","--","毫克/立方米","千克","N7.3"),
    a24049("三氯乙烯","--","毫克/立方米","千克","N7.3"),
    a24050("四氯乙烯","--","毫克/立方米","千克","N7.3"),
    a24053("丙烯","--","毫克/立方米","千克","N7.3"),
    a24054("1,3-二氯丙烯","--","毫克/立方米","千克","N7.3"),
    a24072("1,4-二恶烷","--","毫克/立方米","千克","N7.3"),
    a24078("1,3-丁二烯","--","毫克/立方米","千克","N7.3"),
    a24087("碳氢化合物","25","毫克/立方米","千克","N5.2"),
    a24088("非甲烷总烃","--","毫克/立方米","千克","N7.3"),
    a24099("氯甲烷","--","毫克/立方米","千克","N7.3"),
    a24110("反式-1,2-二氯乙烯","--","毫克/立方米","千克","N7.3"),
    a24111("顺式-1,2-二氯乙烯","--","毫克/立方米","千克","N7.3"),
    a24112("反式-1,3-二氯丙烯","--","毫克/立方米","千克","N7.3"),
    a24113("六氯-1,3-丁二烯","--","毫克/立方米","千克","N7.3"),
    a25002("苯","--","毫克/立方米","千克","N7.3"),
    a25003("甲苯","17","毫克/立方米","千克","N4.2"),
    a25004("乙苯","--","毫克/立方米","千克","N7.3"),
    a25005("二甲苯","18","毫克/立方米","千克","N4.2"),
    a25006("1,2-二甲基苯","--","毫克/立方米","千克","N7.3"),
    a25007("1,3-二甲基苯","--","毫克/立方米","千克","N7.3"),
    a25008("1,4-二甲基苯","--","毫克/立方米","千克","N7.3"),
    a25010("氯苯","23","毫克/立方米","千克","N4.3"),
    a25011("1,2-二氯苯","--","毫克/立方米","千克","N7.3"),
    a25012("1,3-二氯苯","--","毫克/立方米","千克","N7.3"),
    a25013("1,4-二氯苯","--","毫克/立方米","千克","N7.3"),
    a25014("1-乙基-4-甲基苯","--","毫克/立方米","千克","N7.3"),
    a25015("1,2,4-三氯苯","--","毫克/立方米","千克","N7.3"),
    a25019("1,2,4-三甲基苯","--","毫克/立方米","千克","N7.3"),
    a25020("1,2,3-三甲基苯","--","毫克/立方米","千克","N7.3"),
    a25021("1,3,5-三甲基苯","--","毫克/立方米","千克","N7.3"),
    a25023("硝基苯","22","毫克/立方米","千克","N3.4"),
    a25038("乙烯基苯","--","毫克/立方米","千克","N7.3"),
    a25044("苯并[a]芘","20","毫克/立方米","千克","N4.3"),
    a25072("四氢呋喃","--","毫克/立方米","千克","N7.3"),
    a26001("苯胺类","21","毫克/立方米","千克","N4.3"),
    a29017("乙酸乙酯","--","毫克/立方米","千克","N7.3"),
    a29026("乙酸乙烯酯","--","毫克/立方米","千克","N7.3"),
    a30001("甲醇","28","毫克/立方米","千克","N4.3"),
    a30008("异丙醇","--","毫克/立方米","千克","N7.3"),
    a30022("硫醇","13","毫克/立方米","千克","N4.3"),
    a31001("甲醛","19","毫克/立方米","千克","N3.3"),
    a31002("乙醛","26","毫克/立方米","千克","N3.4"),
    a31024("丙酮","--","毫克/立方米","千克","N7.3"),
    a31025("2-丁酮","--","毫克/立方米","千克","N7.3"),
    a31030("甲基异丁基甲酮","--","毫克/立方米","千克","N7.3"),
    a34001("总悬浮颗粒物TSP","--","纳克/立方米","克","N4.3"),
    a34002("可吸入颗粒物PM10","--","纳克/立方米","克","N3.3"),
    a34004("细微颗粒物PM2.5","--","纳克/立方米","克","N3.3"),
    a34005("亚微米颗粒物PM1.0","--","纳克/立方米","克","N3.3"),
    a34011("降尘","--","吨/平方千米•月","--",""),
    a34013("烟尘","01","毫克/立方米","千克","N4"),
    a34017("炭黑尘","--","毫克/立方米","千克","N4"),
    a34038("沥青烟","09","毫克/立方米","千克","N4.3"),
    a34039("硫酸雾","14","毫克/立方米","千克","N4.3"),
    a34040("铬酸雾","15","毫克/立方米","千克","N2.3"),
    a99010("丙烯腈","--","毫克/立方米","千克","N7.3"),
    a99049("光气","24","毫克/立方米","千克","N3.3"),
    a99051("二硫化碳","12","毫克/立方米","千克","N4.3"),

    /**
     * 表B.1 水监测因子编码表
     */
    w00000("污水", "B01", "升/秒", "立方米", "N5.2"),
    w01001("PH值", "001", "无量纲", "--","N2.2"),
    w01002("色度", "002", "[色]度", "--","N3.2"),
    w01006("溶解性总固体", "--", "毫克/升", "千克", "N4"),
    w01009("溶解氧", "--", "毫克/升", "--","N3.1"),
    w01010("水温", "--", "摄氏度", "--","N3.1"),
    w01012("悬浮物", "003", "毫克/升", "千克", "N4"),
    w01014("电导率", "--", "微西[门子]/厘米", "--","N3.1"),
    w01017("五日生化需氧量", "010", "毫克/升", "千克", "N5.1"),
    w01018("化学需氧量", "011", "毫克/升", "千克", "N5.1"),
    w01019("高锰酸盐指数", "--", "毫克/升", "千克", "N3.1"),
    w01020("总有机碳", "015", "毫克/升", "千克", "N3.1"),
    w02003("粪大肠菌群", "550", "个/升", "--","N9"),
    w02006("细菌总数", "--", "个/升", "--","N9"),
    w03001("总α放射性", "570", "贝可[勒尔]/升", "--","N3.1"),
    w03002("总β放射性", "571", "贝可[勒尔]/升", "--","N3.1"),
    w19001("表面活性剂", "--", "毫克/升", "千克", "N3.2"),
    w19002("阴离子表面活性剂", "520", "毫克/升", "千克", "N3.2"),
    w20012("钡", "039", "毫克/升", "千克", "N3.3"),
    w20023("硼", "037", "毫克/升", "千克", "N3.3"),
    w20038("钴", "040", "毫克/升", "千克", "N3.4"),
    w20061("钼", "038", "毫克/升", "千克", "N3.4"),
    w20089("铊", "041", "纳克/升", "毫克", "N4"),
    w20092("锡", "036", "毫克/升", "千克", "N3.1"),
    w20111("总汞", "020", "微克/升", "克", "N3.2"),
    w20113("烷基汞", "021", "纳克/升", "毫克", "N4"),
    w20115("总镉", "022", "微克/升", "克", "N3.1"),
    w20116("总铬", "023", "毫克/升", "千克", "N3.3"),
    w20117("六价铬", "024", "微克/升", "克", "N2.3"),
    w20119("总砷", "026", "微克/升", "克", "N2.3"),
    w20120("总铅", "027", "微克/升", "克", "N4"),
    w20121("总镍", "028", "毫克/升", "千克", "N3.2"),
    w20122("总铜", "029", "毫克/升", "千克", "N3.2"),
    w20123("总锌", "030", "毫克/升", "千克", "N3.3"),
    w20124("总锰", "031", "毫克/升", "千克", "N3.3"),
    w20125("总铁", "032", "毫克/升", "千克", "N3.3"),
    w20126("总银", "033", "毫克/升", "千克", "N3.3"),
    w20127("总铍", "034", "微克/升", "克", "N3.3"),
    w20128("总硒", "035", "微克/升", "克", "N4.2"),
    w20138("铜", "毫克/升", "千克", "N3.3"),
    w20139("锌", "毫克/升", "千克", "N3.3"),
    w20140("硒", "毫克/升", "千克", "N3.3"),
    w20141("砷", "毫克/升", "千克", "N3.3"),
    w20142("汞", "微克/升", "克", "N3.3"),
    w20143("镉", "微克/升", "克", "N3.3"),
    w20144("铅", "毫克/升", "千克", "N3.3"),
    w21001("总氮", "065", "毫克/升", "千克", "N4.2"),
    w21003("氨氮", "060", "毫克/升", "千克", "N4.2"),
    w21004("凯氏氮", "062", "毫克/升", "千克", "N3.1"),
    w21006("亚硝酸盐", "063", "毫克/升", "千克", "N2.3"),
    w21007("硝酸盐", "064", "毫克/升", "千克", "N2.3"),
    w21011("总磷", "101", "毫克/升", "千克", "N3.2"),
    w21016("氰化物", "070", "毫克/升", "千克", "N3.3"),
    w21017("氟化物", "072", "毫克/升", "千克", "N4.2"),
    w21019("硫化物", "071", "毫克/升", "千克", "N3.3"),
    w21022("氯化物", "090", "毫克/升", "千克", "N3.1"),
    w21038("硫酸盐", "--", "毫克/升", "千克", "N6"),
    w22001("石油类", "080", "毫克/升", "千克", "N3.2"),
    w23002("挥发酚", "110", "毫克/升", "千克", "N3.4"),
    w25043("苯并[α]芘", "540", "微克/升", "克", "N3.1"),
    w33001("六六六", "350", "纳克/升", "毫克", "N4"),
    w33007("滴滴涕", "351", "纳克/升", "毫克", "N4"),
    w99001("有机氮", "061", "毫克/升", "千克", "N3.1");

    /**
     * 2017编码
     */
    private final String code;
    /**
     * 中文名称
     */
    private final String meaning;
    /**
     * 2015编码
     */
    private final String oldCode;
    /**
     * 浓度
     */
    private final String chromaUnit;
    /**
     * 排放量
     */
    private final String amountUnit;
    /**
     * 数据类型  N3.2表是最多三位整数和两位小数
     */
    private final String type;

    PollutionAll(String meaning, String chromaUnit, String amountUnit, String type) {
        this.code = name();
        this.meaning = meaning;
        this.oldCode = "--";
        this.chromaUnit = chromaUnit;
        this.amountUnit = amountUnit;
        this.type = type;
    }

    PollutionAll(String meaning, String oldCode, String chromaUnit, String amountUnit, String type) {
        this.code = name();
        this.meaning = meaning;
        this.oldCode = oldCode;
        this.chromaUnit = chromaUnit;
        this.amountUnit = amountUnit;
        this.type = type;
    }
    @Override
    public String code() {
        return code;
    }

    @Override
    public String mean() {
        return meaning;
    }


    public String oldCode() {
        return oldCode;
    }

    public String chromaUnit() {
        return chromaUnit;
    }

    public String amountUnit() {
        return amountUnit;
    }

    public String unit() {
        return chromaUnit + " " + amountUnit;
    }

    public String type() {
        return type;
    }

    /**
     * 得到小数位
     * @param code 2017编码
     */
    public static int getScale(String code) {
        int res = 0;
        try {
            String type = PollutionAll.valueOf(code).type;
            if (null != type && type.contains(".")) {
                res = Integer.parseInt(type.split("\\.")[1]);
            }
        } catch (NumberFormatException e) {
            return res;
        }
        return res;
    }
}
