package com.weixin.demo.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class AuthUtil {
    public static final String APPID="wxcada310d5381fe92";
    public static final String APPSECRET="41baee3844e33824fbcf0788a5a40c9c";
    public static JSONObject doGetJson(String url) throws IOException, JSONException {
        JSONObject jsonObject=null;
        DefaultHttpClient defaultHttpClient=new DefaultHttpClient();
        HttpGet httpGet=new HttpGet(url);
        HttpResponse httpResponse = defaultHttpClient.execute(httpGet);
        HttpEntity httpEntity=httpResponse.getEntity();
        if(httpEntity!=null){
            String result= EntityUtils.toString(httpEntity,"UTF-8");
            jsonObject=new JSONObject(result);
            System.out.println("jsonObject:  "+jsonObject);
        }
        httpGet.releaseConnection();
        return jsonObject;
    }
}
