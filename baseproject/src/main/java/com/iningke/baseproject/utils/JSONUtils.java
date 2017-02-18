package com.iningke.baseproject.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class JSONUtils {

    /**
     * 把Java对象转换为JSON数据格式
     *
     * @param object
     * @return
     */
    public static String getJson(Object object) {
        try {
//            return JSON.toJSONString(object);
            return JSON.toJSONString(object);
        } catch (Exception e) {
            // logger.error("[JsonUtils]Fail to getjson", e);
        }
        return null;
    }

    private static boolean checkMap(Map map){
        return map.containsKey("sentences") && map.containsKey("format");
    }

    private static String getJsonForUpload(Map object){
        String wmJson = SshhjrJSONUtil.map2Json((Map<String, ? extends Object>) object);
        LogUtils.i("wmJson === " + wmJson);
        return wmJson;
    }

    /**
     * 把Java对象转换为JSON数据格式
     *
     * @param object
     * @return
     */
    public static String getJsonStr(Object object) {
        if (object instanceof Map) {
            if (checkMap((Map) object)) {
                return getJsonForUpload((Map) object);
            }
            JSONObject obj = new JSONObject((Map) object);
            LogUtils.i("obj === " + obj.toString());
            return obj.toString();
        }
        return null;
    }

    /**
     * 把JSON数据格式转换为JAVA对象
     *
     * @param <T>
     * @param jsonData
     * @param clz
     * @return
     */
    public static <T> T readValue(String jsonData, Class<T> clz) {
        try {
            return JSON.parseObject(jsonData, clz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据参数,值封装成 json字符串
     *
     * @param title   json头
     * @param params  键
     * @param params2 值
     * @return json字符串
     * @throws JSONException json封装不匹配
     */
    public static String createJSON(String title, String[] params, Object[] params2) throws JSONException {
        JSONObject titleNode = new JSONObject();
        JSONObject paramsNode = new JSONObject();
        LogUtils.d("params 长度:" + params.length);
        LogUtils.d("params2 长度:" + params2.length);

        for (int i = 0; i < params.length; i++) {
            paramsNode.put(params[i], params2[i]);
        }
        titleNode.put(title, paramsNode);
        return titleNode.toString();
    }

}
