package com.aaa.project.system.routingPeople.controller;

import com.aaa.project.system.routingPeople.domain.RoutingPeople;
import com.aaa.project.system.routingPeople.service.IRoutingPeopleService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system/login")
public class RoutingPeopleLogin {
    private RoutingPeople routingPeople;
    @Autowired
private IRoutingPeopleService iRoutingPeopleService;

    @RequestMapping("/")
    @ResponseBody
    public RoutingPeople login(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        response.setContentType("text/html;charset=utf-8");
        /* 设置响应头允许ajax跨域访问 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user=request.getParameter("user");
        String psd=request.getParameter("psd");
        System.out.println(user);
        String code=request.getParameter("code");

        //小程序唯一标识   (在微信小程序管理后台获取)
        String wxspAppid = "wx99dc57bef6ee0275";
        //小程序的 app secret (在微信小程序管理后台获取)
       String wxspSecret = "bad25c8ffbdd9c4c5345017bdc8af2f4";
//
//
//        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
//        //请求参数
//        //String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=authorization_code";
       String params="https://api.weixin.qq.com/sns/jscode2session?appid="+wxspAppid+"&secret="+wxspSecret+"&js_code="+code+"&grant_type=authorization_code";
       String s = sendGetReq(params);
        System.out.println(s);
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
       map = gson.fromJson(s, map.getClass());
        String openid=(String) map.get("openid");
           RoutingPeople routingPeople = new RoutingPeople();
        routingPeople.setOpenId(openid);

        routingPeople.setRoutingUsername(user);
        routingPeople.setRoutingPassword(psd);
        session.setAttribute("openId",openid);



        RoutingPeople people = iRoutingPeopleService.selectRoutingPeopleLogin(routingPeople);
        Integer routingId=people.getRoutingId();

        RoutingPeople people1 = iRoutingPeopleService.selectRoutingPeopleById(routingId);
        people1.setOpenId(openid);

        iRoutingPeopleService.updateRoutingPeople(people1);





        session.setAttribute("people",people);
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("catch-control", "no-catch");



      if(people!=null){
          return people;
      }else {
          return null;
      }



    }





    private String sendGetReq(String url) {

        String result = "";

        BufferedReader in = null;

        try {

            String urlNameString = url;

            URL realUrl = new URL(urlNameString);

            // 打开和URL之间的连接

            URLConnection connection = realUrl.openConnection();

            // 设置通用的请求属性

            connection.setRequestProperty("accept", "*/*");

            connection.setRequestProperty("connection", "Keep-Alive");

            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            // 建立实际的连接

            connection.connect();

            // 获取所有响应头字段

            java.util.Map<String, List<String>> map = connection.getHeaderFields();

            // 遍历所有的响应头字段

            for (String key : map.keySet()) {

                System.out.println(key + "--->" + map.get(key));

            }

            // 定义 BufferedReader输入流来读取URL的响应

            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;

            while ((line = in.readLine()) != null) {

                result += line;

            }

        } catch (Exception e) {

            System.out.println("发送GET请求出现异常！" + e);

            e.printStackTrace();

        } // 使用finally块来关闭输入流

        finally {

            try {

                if (in != null) {

                    in.close();

                }

            } catch (Exception e2) {

                e2.printStackTrace();

            }

        }

        return result;

    }




}
