package com.plugin.chatgpt.util;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class HttpUtil {

    // you need deploy a server to supply SSE service
    private static final String CONVERSATION_URL = "http://localhost:8081/chatGPT/ask2?prompt=";

    public static void showResult(String urlString, JPanel mainPanelContent, JTextArea answer) throws IOException {
        URL url = new URL(CONVERSATION_URL + URLEncoder.encode(urlString, "UTF-8"));

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        // 超时时间10秒
        conn.setConnectTimeout(30000);
        // Set Headers
        conn.setRequestProperty("Accept", "text/event-stream");
        conn.setRequestProperty("Content-Type", "application/json");
        // 连接并发送HTTP请求:
        conn.connect();

        // 判断HTTP响应是否200:
        if (conn.getResponseCode() != 200) {
            conn.disconnect();
            throw new RuntimeException("Bad response");
        }
        // 获取响应内容:
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream(),StandardCharsets.UTF_8));
        String current;
        StringBuffer allAnswer = new StringBuffer();
        boolean hasRemoveLoad = false;
        try {
            while ((current = reader.readLine()) != null) {
                if (!hasRemoveLoad){
                    mainPanelContent.remove(1);
                    hasRemoveLoad = true;
                }
                String text = StringUtil.getText(current);
                if ("[DONE]".equals(text)) {
                    break;
                } else if (!current.isEmpty()) {
                    allAnswer.append(text);
                    answer.append(text);
                }
            }
        }catch (Exception e) {
            throw new RuntimeException("Bad response");
        } finally {
            if (!hasRemoveLoad){
                mainPanelContent.remove(1);
            }
            conn.disconnect();
        }
        reader.close();
    }


    public static String unicodeToChina(String str) throws Exception{
         byte[] byteArr = str.getBytes("UTF-8");
         String chinese=new String(byteArr,"UTF-8");
         return chinese;
     }

}
