package cn.zing.hj212.server.parse.resolving;

import cn.zing.hj212.api.processer.Processor;
import cn.zing.hj212.api.util.CRC16Util;
import cn.zing.hj212.api.util.DateUtil;
import cn.zing.hj212.api.util.StringUtil;
import cn.zing.hj212.server.netty.session.SessionMgr;
import com.alibaba.fastjson2.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * @description:
 * @author: dcy
 * @create: 2021-10-28 18:07
 */
@Slf4j
public class HJ212ResolvingProcessor extends Processor {


    public HJ212ResolvingProcessor(JSONObject config) {
        super(config);
    }

    /**
     * 这个方法处理212报文, 处理完后, 交给了谁? TransferStructure
     *
     * @param data
     * @throws Exception
     */
    @Override
    public void execute(Object data) throws Exception {
        JSONObject dta = (JSONObject) data;
        ChannelHandlerContext ctx = (ChannelHandlerContext) dta.get("ctx");
        dta.remove("ctx");
        String msg = (String) dta.get("message");

        String error = "";
        error = "";

        String QnRtn = "1";
        String ExeRtn = "1";

        String mnstr = msg.substring(msg.indexOf("MN"));
        String code = mnstr.substring(mnstr.indexOf("=") + 1, mnstr.indexOf(";"));
        dta.put("code", code);

        String[] body = null;
        if (msg.contains("QN")) {
            body = msg.substring(msg.indexOf("QN")).split("&&");
        } else {
            body = msg.substring(6).split("&&");
        }
        String head = body[0];
        String monitorData = body[1];
        String crc = body[2].replace("\r\n", "");

        String[] heads = head.split(";");
        JSONObject headMap = new JSONObject();
        headMap.put("from", "212");
        for (String string : heads) {
            if (string.split("=").length > 1) {
                headMap.put(string.split("=")[0], string.split("=")[1]);
            }
        }

        if (StringUtil.isBlank(monitorData)) {
            ExeRtn = "100";
        }
        String[] datas = monitorData.split(";");
        JSONObject dataMap = new JSONObject();
        for (String string : datas) {
            String[] factors = string.split(",");

            for (String factor : factors) {
                if (string.split("=").length > 1) {
                    dataMap.put(factor.split("=")[0], factor.split("=")[1]);
                }
            }
        }

        String qn = headMap.getString("QN");
        String st = headMap.getString("ST");
        String cn = headMap.getString("CN");
        String pw = headMap.getString("PW");
        String mn = headMap.getString("MN");

        String flag = headMap.getString("Flag");
        String dataTime = dataMap.getString("DataTime");
        // 数据包长度校验 212包长度不会少于67
        if (msg.length() < 67) {
            QnRtn = "100";
            ExeRtn = "3";
            error += "包总长度不正确";
        }
        // 数据包开始结尾校验
        if (!msg.startsWith("##")) {
            QnRtn = "100";
            ExeRtn = "3";
            error += "包开始或结尾不正确";
        }

        if (!StringUtils.hasLength(qn)) {
            QnRtn = "7";
            ExeRtn = "3";
            error += "缺少QN";
        }
        if (!StringUtils.hasLength(st)) {
            QnRtn = "5";
            ExeRtn = "3";
            error += "缺少ST";
        }
        if (!StringUtils.hasLength(cn)) {
            QnRtn = "8";
            ExeRtn = "3";
            error += "缺少CN";
        }
        if (!StringUtils.hasLength(pw)) {
            QnRtn = "3";
            ExeRtn = "3";
            error += "缺少PW";
        }
        if (!StringUtils.hasLength(mn)) {
            QnRtn = "4";
            ExeRtn = "3";
            error += "缺少MN";
        }
        if (!code.equals(mn)) {
            QnRtn = "4";
            ExeRtn = "3";
            error += "缺少MN";
        }
        if (!StringUtils.hasLength(flag)) {
            QnRtn = "6";
            ExeRtn = "3";
            error += "缺少FLAG";
        }
        if (!StringUtils.hasLength(dataTime)) {
            error += "缺少DataTime";
        }

        int length = Integer.parseInt(msg.substring(2, 6));
        // 数据长度与数据中长度信息校验
        if (length != msg.substring(6, msg.lastIndexOf("&&") + 2).length()) {
            QnRtn = "100";
            ExeRtn = "3";
            error += "包长度与信息中长度不匹配";
        }

        // crc校验
        if (!CRC16Util.calcCrc16(msg.substring(6, msg.lastIndexOf("&&") + 2)).equals(crc)) {
            QnRtn = "9";
            ExeRtn = "3";
            error += "crc校验错误";
        }

        // 校时
        if (cn.equals("9011") || cn.equals("9012")) {
            throw new Exception("校时");
        }
        if (cn.equals("1013")) {
            String ret = "QN=" + qn + ";ST=91;CN=1012;PW=" + pw + ";MN=" + mn + ";Flag=4;CP=&&&&";
            ret = "##" + StringUtil.getLength(ret) + ret + CRC16Util.calcCrc16(ret) + "\r\n";
            ctx.writeAndFlush(ret);

            String PolId = dataMap.getString("PolId");
            ret = "QN=" + qn + ";ST=91;CN=1012;PW=" + pw + ";MN=" + mn + ";Flag=4;CP=&&" + ((StringUtils.hasLength(dataTime)) ? ("PolId=" + PolId + ";") : "") + "SystemTime=" + DateUtil.getCurrentDate(DateUtil.DF_SALL14) + "&&";
            ret = "##" + StringUtil.getLength(ret) + ret + CRC16Util.calcCrc16(ret) + "\r\n";
            ctx.writeAndFlush(ret);
            throw new Exception("校时");
        }

        dta.put("time", DateUtil.formatDF19(DateUtil.parseDate(dataTime, DateUtil.DF_SALL14)));

        if (flag != null) {
            String flagStr = StringUtil.byte2binary((byte) Integer.parseInt(flag));

            // 拆包
            if (flagStr.substring(6, 7).equals("1")) {
                String pnum = headMap.getString("PNUM");
                String pno = headMap.getString("PNO");
            }

            // 需要回复
            if (flagStr.substring(7, 8).equals("1")) {
                String ret = "QN=" + qn + ";ST=91;CN=9014;PW=" + pw + ";MN=" + mn + ";Flag=4;CP=&&QnRtn=" + QnRtn + ";ExeRtn=" + ExeRtn + "&&";
                ret = "##" + StringUtil.getLength(ret) + ret + CRC16Util.calcCrc16(ret) + "\r\n";
                // Session 管理
                SessionMgr.putSession(ctx, mn);
                ctx.writeAndFlush(ret);
            }
        }

        if (error.length() > 0) {
            throw new Exception(error);
        }

        String dataType = "";
        switch (cn) {
            case "2051":
                dataType = "MIN";
                break;
            case "2061":
                dataType = "HOUR";
                break;
            case "2031":
                dataType = "DAY";
                break;
            case "2011":
                dataType = "REAL";
                break;
        }

        headMap.put("dataType", dataType);
        dta.put("headMap", headMap);
        dta.put("dataMap", dataMap);
//        log.info("一级解析:::{}", dta);
    }
}


