package com.weixin.demo.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.demo.util.AuthUtil;

@Controller
public class LoginController {
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String backUrl="http://d4jmeu.natappfree.cc/callback";
        /**
        *这儿一定要注意！！首尾不能有多的空格（因为直接复制往往会多出空格），其次就是参数的顺序不能变动
        **/
        String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + AuthUtil.APPID+
                "&redirect_uri=" + URLEncoder.encode(backUrl,"UTF-8")+
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=STATE&connect_redirect=1#wechat_redirect";
        resp.sendRedirect(url);
	}
	
	@RequestMapping(value="/callback",method=RequestMethod.GET)
	@ResponseBody
	public String callback(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		System.out.println("hahah");
        String code=req.getParameter("code");
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + AuthUtil.APPID+
                "&secret=" +AuthUtil.APPSECRET+
                "&code=" +code+
                "&grant_type=authorization_code";
        JSONObject jsonObject = AuthUtil.doGetJson(url);
        String openid=jsonObject.getString("openid");
        String token=jsonObject.getString("access_token");
        String infoUrl="https://api.weixin.qq.com/sns/userinfo?access_token=" +token+
                "&openid=" +openid+
                "&lang=zh_CN";
        JSONObject userInfo=AuthUtil.doGetJson(infoUrl);
        String s = userInfo.toString();
        return s;
        
	}

}
