/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.config;

import com.shenzc.service.RestTemplateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/8/26 17:42
 */
@Configuration
public class MainConfig {

    @Bean
    public RestTemplateService restTemplateService(){
        return new RestTemplateService();
    }

}
