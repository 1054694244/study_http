/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */

import com.shenzc.config.MainConfig;
import com.shenzc.entity.Student;
import com.shenzc.service.RestTemplateService;
import com.shenzc.utils.JsonUtil;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/8/26 17:37
 */
@SpringBootTest
@Ignore
public class Test1 {


    String getUrl = "http://localhost:8080/api/getStudent";
    String getUrl2 = "http://localhost:8080/api/setStudent";
    String getUrl3 = "http://localhost:8080/api/setStudentPost";
    String getUrl4 = "http://localhost:8080/api/setStudentJson";
    String getUrl5 = "http://localhost:8080/api/setStudent123/{name}/{age}";

    @Test
    public void test1(){
        AnnotationConfigApplicationContext application = new AnnotationConfigApplicationContext(MainConfig.class);
        RestTemplateService restTemplateService = application.getBean(RestTemplateService.class);
        System.out.println(getUrl);
        String json = restTemplateService.get(getUrl);
        Student student = JsonUtil.toBean(json, Student.class);
        System.out.println(student);
    }

    @Test
    public void test2(){
        AnnotationConfigApplicationContext application = new AnnotationConfigApplicationContext(MainConfig.class);
        RestTemplateService restTemplateService = application.getBean(RestTemplateService.class);
        System.out.println(getUrl5);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name","xiaoming");
        map.put("age",23);
        String json = restTemplateService.getEntity(getUrl5,map);
        Student student = JsonUtil.toBean(json, Student.class);
        System.out.println(student);
    }

    @Test
    public void test3(){
        AnnotationConfigApplicationContext application = new AnnotationConfigApplicationContext(MainConfig.class);
        RestTemplateService restTemplateService = application.getBean(RestTemplateService.class);
        System.out.println(getUrl3);
        Student student = new Student("xiaoming",34);
        String s = restTemplateService.postJson(getUrl4, JsonUtil.toJSON(student));
        Student student1 = JsonUtil.toBean(s, Student.class);
        System.out.println(student1);
    }

    @Test
    public void test4(){
        AnnotationConfigApplicationContext application = new AnnotationConfigApplicationContext(MainConfig.class);
        RestTemplateService restTemplateService = application.getBean(RestTemplateService.class);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name","xiaoming");
        map.put("age",23);
        String s = restTemplateService.postJson(getUrl4, JsonUtil.toJSON(map));
        Student student1 = JsonUtil.toBean(s, Student.class);
        System.out.println(student1);
    }

}
