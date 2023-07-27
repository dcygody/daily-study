package cn.zing.hj212.api.starter;

import com.alibaba.fastjson2.JSONObject;

/**
 * @description: 启动器
 * @author: dcy
 * @create: 2023-07-27 13:42
 */
public interface Starter {

    void execute(JSONObject data);

    void dispatch(Object data);
}


