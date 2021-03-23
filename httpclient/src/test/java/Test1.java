/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */

import com.shenzc.config.MainConfig;
import com.shenzc.controller.HttpApiService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.net.URISyntaxException;
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



    @Test
    public void test1() throws URISyntaxException, IOException {
        AnnotationConfigApplicationContext application = new AnnotationConfigApplicationContext(MainConfig.class);
        //HttpApiService httpApiService = (HttpApiService)application.getBean("httpApiService");
        HttpApiService httpApiService = application.getBean(HttpApiService.class);
        System.out.println(httpApiService);
        Map<String,Object> map = new HashMap<String, Object>();
        //Student student = new Student("小明",45);
        //map.put("student",JsonUtil.toJSON(student));
        //String json = JsonUtil.toJSON(student);
        map.put("name","小明");
        map.put("age",23);
        String str = httpApiService.doPost("http://localhost:8080/api/setStudentPost",map);
        System.out.println(str);
    }

}
