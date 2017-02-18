package com.iningke.baseproject.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.util.Date;
import java.util.Map;

/**
 * Created by Mckiera on 2016-04-27.
 */
public class SshhjrJSONUtil {
    private static SerializeConfig config = new SerializeConfig();

    static {
        // 提供其它默认设置
        config.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
    }

    public static String object2Json(Object object) {
        return JSON.toJSONString(object, config, SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteMapNullValue, SerializerFeature.NotWriteDefaultValue,
                SerializerFeature.SortField, SerializerFeature.WriteNonStringKeyAsString);
    }


    /**
     * QuoteFieldNames———-输出key时是否使用双引号,默认为true
     * WriteMapNullValue———-是否输出值为null的字段,默认为false
     * WriteNullNumberAsZero———-数值字段如果为null,输出为0,而非null
     * WriteNullListAsEmpty———-List字段如果为null,输出为[],而非null
     * WriteNullStringAsEmpty———-字符类型字段如果为null,输出为”“,而非null
     * WriteNullBooleanAsFalse———-Boolean字段如果为null,输出为false,而非null
     * WriteDateUseDateFormat–———-日期格式,默认为false
     *
     * @param map
     * @return
     */
    public static String map2Json(Map<String, ? extends Object> map) {
        return JSON.toJSONString(map, config, SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteMapNullValue, SerializerFeature.NotWriteDefaultValue,
                SerializerFeature.SortField, SerializerFeature.WriteNonStringKeyAsString);
    }

    public static Map json2Map(String text) {
        return JSON.parseObject(text, Map.class, Feature.UseBigDecimal);
    }
//
//    public static String getJsonString4JavaPOJOJSONArray(Object javaObj)
//    {
//        JSONArray json;
//        json = JSONArray.fromObject(javaObj);
//        return json.toString();
//    }
}
