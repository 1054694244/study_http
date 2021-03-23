/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.contoller;

import com.shenzc.entity.Student;
import com.shenzc.utils.JsonUtil;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/8/26 17:10
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/getStudent")
    public String getStudent(){
        Student student = new Student("小明",24);
        return JsonUtil.toJSON(student);
    }

    @GetMapping("/setStudent")
    public String getStudent12(@RequestParam(value ="name",name = "name")String name,
                             @RequestParam(value = "age",name = "age")Integer age){
        Student student = new Student(name,age);
        return JsonUtil.toJSON(student);
    }

    @GetMapping("/setStudent123/{name}/{age}")
    public String getStudent13(@PathVariable(value = "name")String name,
                               @PathVariable(value = "age")Integer age){
        Student student = new Student(name,age);
        return JsonUtil.toJSON(student);
    }

    @PostMapping("/setStudentPost")
    public String getStudent(Student student){
        System.out.println(student.toString());
        return JsonUtil.toJSON(student);
    }

    @PostMapping("/setStudentJson")
    public String getStudentJson(@RequestBody Student student){
        return JsonUtil.toJSON(student);
    }

}
