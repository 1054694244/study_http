/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/8/26 17:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private String name;

    private Integer age;

}
