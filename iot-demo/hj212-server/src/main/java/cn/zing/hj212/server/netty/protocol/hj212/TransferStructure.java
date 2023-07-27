package cn.zing.hj212.server.netty.protocol.hj212;

import cn.zing.hj212.api.enm.PollutionAll;
import cn.zing.hj212.api.processer.Processor;
import cn.zing.hj212.api.util.DoubleUtil;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author dcy
 */
@Slf4j
public class TransferStructure extends Processor {

    public TransferStructure(JSONObject config) {
        super(config);
    }

    /**
     * @throws Exception
     * @dta <br>
     * headMap<br>
     * dataMap<br>
     */
    @Override
    public void execute(Object dta) throws Exception {
        JSONObject nodedata = (JSONObject) dta;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//        JSONObject params = this.config.getJSONObject("params");

//        String key = params.getString("key") == null ? "snCode" : params.getString("key");

        JSONObject headMap = nodedata.getJSONObject("headMap");
        JSONObject dataMap = nodedata.getJSONObject("dataMap");
        JSONObject data = new JSONObject();
//        JSONObject tags = new JSONObject();
        JSONObject metrics = new JSONObject();

        Date now = new Date();
        String createTime = sdf2.format(now);
        String DataTime = dataMap.getString("DataTime");

        data.put("_id", headMap.getString("MN") + "#" + DataTime);
        data.put("time", sdf2.format(sdf1.parse(DataTime)));
        data.put("createTime", createTime);
//        data.put("tags", tags);
        data.put("from", headMap.getString("from"));
        data.put("dataType", headMap.getString("dataType"));
        data.put("isAudit", "0");
        data.put("metrics", metrics);

//        tags.put(key, headMap.getString("MN"));

        String cn = headMap.getString("CN");

        Set<String> factors = new HashSet<String>();
        for (String factor : dataMap.keySet()) {
            if (factor.contains("-")) {
                factors.add(factor.split("-")[0]);
            }
        }

        // 取因子数据
        JSONObject metric = new JSONObject();
        for (String factor : factors) {
            metric = new JSONObject();

            if (cn.equals("2011")) {
                metric.put("itemValue", dataMap.get(factor + "-Rtd") == null ? 0 : dataMap.get(factor + "-Rtd"));
                metric.put("charValue", String.valueOf(DoubleUtil.get((dataMap.get(factor + "-Rtd") == null ? 0 : dataMap.get(factor + "-Rtd")), PollutionAll.getScale(factor))));
                metric.put("isValid", (dataMap.containsKey(factor + "-Flag") && dataMap.get(factor + "-Flag") != null && dataMap.getString(factor + "-Flag").equalsIgnoreCase("n")) ? "N" : "F");
            } else {
                metric.put("itemValue", dataMap.get(factor + "-Avg") == null ? 0 : dataMap.get(factor + "-Avg"));
                metric.put("charValue", String.valueOf(DoubleUtil.get((dataMap.get(factor + "-Avg") == null ? 0 : dataMap.get(factor + "-Avg")), PollutionAll.getScale(factor))));
                metric.put("maxValue", dataMap.get(factor + "-Max"));
                metric.put("minValue", dataMap.get(factor + "-Min"));
                metric.put("isValid", (dataMap.containsKey(factor + "-Flag") && dataMap.get(factor + "-Flag") != null && dataMap.getString(factor + "-Flag").equalsIgnoreCase("n")) ? "1" : "0");
            }

            metric.put("isAudit", "0");
            if (dataMap.containsKey(factor + "-Cou")) {
                metric.put("Cou", dataMap.get(factor + "-Cou"));
            }
            if (dataMap.containsKey(factor + "-ZsRtd")) {
                metric.put("ZsRtd", dataMap.get(factor + "-ZsRtd"));
            }
            if (dataMap.containsKey(factor + "-ZsMin")) {
                metric.put("ZsMin", dataMap.get(factor + "-ZsMin"));
            }
            if (dataMap.containsKey(factor + "-ZsAvg")) {
                metric.put("ZsAvg", dataMap.get(factor + "-ZsAvg"));
            }
            if (dataMap.containsKey(factor + "-ZsMax")) {
                metric.put("ZsMax", dataMap.get(factor + "-ZsMax"));
            }
            if (dataMap.containsKey(factor + "-EFlag")) {
                metric.put("EFlag", dataMap.get(factor + "-EFlag"));
            }
            if (dataMap.containsKey(factor + "-SampleTime")) {
                metric.put("SampleTime", dataMap.get(factor + "-SampleTime"));
            }
            metrics.put(factor, metric);
        }

        nodedata.put("data", data);
//        nodedata.put("key", key);
        log.info("二级解析:::{}", nodedata);
    }
}


