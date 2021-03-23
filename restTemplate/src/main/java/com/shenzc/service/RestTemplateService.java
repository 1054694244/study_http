/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.service;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Description: restTemplate服务封装类
 * @Author Shenzc
 * @Date 2020/8/31 10:24
 */
@Component
public class RestTemplateService {

    RestTemplate restTemplate = new RestTemplate();

    /**
     * get请求不带参数
     * @param url：请求地址
     */
    public String get(String url){
        String str = restTemplate.getForObject(url
                , String.class);
        System.out.println(str);
        return str;
    }

    /**
     * get请求带参数，通过占位符传递参数
     * @param url：请求地址
     * @param map：参数
     */
    public String get(String url,Map<String,Object> map){

        String str = restTemplate.getForObject(url
                , String.class,map);
        return str;
    }

    /**
     * get请求返回ResponseEntity
     * @param url：请求地址
     */
    public String getEntity(String url,Map<String,Object> map){
        ResponseEntity<String> entity = restTemplate.getForEntity(url
                , String.class,map);

        HttpStatus statusCode = entity.getStatusCode();
        System.out.println("statusCode.is2xxSuccessful()"+statusCode.is2xxSuccessful());

        String str = entity.getBody();
        System.out.println("Str.toString()"+str);


        ResponseEntity.BodyBuilder status = ResponseEntity.status(statusCode);
        status.contentLength(100);
        status.body("我在这里添加一句话");
        ResponseEntity<Class<String>> body1 = status.body(String.class);
        Class<String> str2 = body1.getBody();
        System.out.println("student2.toString()"+str2.toString());
        return str;
    }

    /**
     * post请求带参数
     * @param url：请求地址
     * @param json：参数
     * @return
     */
    public String postJson(String url, String json) {
        HttpHeaders headers = new HttpHeaders();
        //这里设置的是以payLoad方式提交数据，对于Payload方式，提交的内容一定要是String，且Header要设为“application/json”
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
        ResponseEntity<String> res = restTemplate.postForEntity(url, requestEntity, String.class);
        return res.getBody();
    }

}
