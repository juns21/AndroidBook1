package com.example.juns.loginsharedpreferences;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by Juns on 2015-03-30.
 */
public class JSONParser {
    InputStream is = null;
    String json = null;

    public JSONObject getJSONFromUrl(String url) {
        /**
         * 인터넷 접속
         */
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            is = httpEntity.getContent();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * response 에서 문자열 읽기
         */
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "ios-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line +"\n");
            }
            is.close();
            json = sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 문자열에서 JSON 객체 만들기
         */
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
