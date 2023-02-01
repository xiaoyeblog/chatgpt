package com.plugin.chatgpt.util;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

public class StringUtil {

    public static String parse(String source) {
        if (source == null || source.length() <= 6){
            return source;
        }
        source = source.substring(5);
        if ("{}".equals(source.trim())) {
            return "看起来出了一些问题，没有查询到任何数据。";
        }
        JSONObject object = JSON.parseObject(source);
        JSONArray resultArray = object.getJSONObject("message").getJSONObject("content").getJSONArray("parts");
        StringBuilder sb = new StringBuilder();
        for (Object s : resultArray) {
            sb.append(s.toString());
        }
        return sb.toString();
    }

    public static String getText(String source){
        if (source == null || source == "" || source.length() <= 5){
            return "";
        }
        source = source.substring(10);
        StringBuilder sb = new StringBuilder();
        try {
            JSONObject object = JSON.parseObject(source);
            JSONArray choices = object.getJSONArray("choices");
            for (Object choice : choices) {
                JSONObject content = (JSONObject) choice;
                sb.append(content.get("text"));
            }
        }catch (Exception e){
            System.out.println(source);
        }

        return sb.toString();
    }
}
