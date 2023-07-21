package cn.zing.netty.server.util;

import com.alibaba.fastjson2.JSONObject;

/**
 * @description:
 * @author: dcy
 * @create: 2023-06-29 14:01
 */
public class JSONUtil {

    public static boolean isJSON(String string) {

        boolean isjson = true;
        try {
            JSONObject.parseObject(string);
        } catch (Exception e) {
           isjson = false;
        }

        return isjson;
    }

    public static JSONObject parseJSON(String string) {

        JSONObject isjson = null;
        try {
            isjson = JSONObject.parseObject(string);
        } catch (Exception ignored) {

        }

        return isjson;
    }
}


