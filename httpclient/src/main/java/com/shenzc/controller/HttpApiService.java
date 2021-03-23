/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.controller;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: httpClient封装类
 * @Author Shenzc
 * @Date 2020/8/26 15:35
 */
@Component
public class HttpApiService {


    //private CloseableHttpClient closeableHttpClient;
    private CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();

    /**
     * 不带参数的get请求
     * @param url: url地址
     * @return
     */
    public String doGet(String url){
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        //CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //创建get请求
        HttpGet httpGet = new HttpGet(url);
        //响应模型
        CloseableHttpResponse response = null;
        try {
            //由客户端执行（发送）Get请求
            response = closeableHttpClient.execute(httpGet);
            //从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:"+response.getStatusLine());
            if (responseEntity!=null){
                System.out.println("响应内容长度为:"+responseEntity.getContentLength());
                //EntityUtils.toString(responseEntity,"UTF-8"):只能读取一遍，就会关闭流，不会读取第二遍
                // System.out.println("响应内容为:"+ EntityUtils.toString(responseEntity,"UTF-8"));
                return EntityUtils.toString(responseEntity,"UTF-8");
            }
        }catch (ClientProtocolException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                //释放资源
                if (closeableHttpClient!=null){
                    closeableHttpClient.close();
                }
                if (response!=null){
                    response.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 带参数的get请求
     * @param url: url地址
     * @param map: 参数
     * @return
     */
    public String doGet(String url, Map<String,Object> map) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(url);

        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        return this.doGet(uriBuilder.build().toString());
    }

    /**
     * 不带参数的get请求
     * @param url: url地址
     * @return
     * @throws IOException
     */
    public String doPost(String url) throws IOException {
        return doPost(url,null);
    }

    /**
     * 带参数的post请求
     * @param url: url地址
     * @param map: 参数
     * @return
     * @throws IOException
     */
    public String doPost(String url,Map<String,Object> map) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        //判断map是否为空，不为空则封装form表单
        if (map != null) {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            //构建form表单对象
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list,"UTF-8");
            //把表单放到post
            httpPost.setEntity(urlEncodedFormEntity);
        }
        CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == 200) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        else {
            System.out.println(EntityUtils.toString(response.getEntity(), "UTF-8"));
        }
        return null;
    }

    /**
     * 带参数的post请求，参数类型为json
     * @param url: url地址
     * @param jsonParams: json格式的对象
     * @return
     * @throws IOException
     */
    public String doPostJson(String url,String jsonParams) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        //utf-8解决中文乱码问题
        StringEntity entity = new StringEntity(jsonParams, "UTF-8");
        entity.setContentEncoding("UTF-8");
        //entity.setContentType("application/json");
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        httpPost.setEntity(entity);
        CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == 200) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        return null;
    }
}
