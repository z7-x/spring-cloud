package com.z7.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.z7.springcloud.pojo.Dept;
import com.z7.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;


/**
 * @ProjectName：z7-learn-projects
 * @ClassName：DeptController
 * @Description：TODO
 * @Date：2021/6/4 10:46 上午
 * @Author：z7-x
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;


    @GetMapping("/get/{id}")
    @HystrixCommand(fallbackMethod = "hystrixGet")
    public Dept get(@PathVariable("id") Long id) {
        Dept dept = deptService.getDeptById(id);
        if (dept == null) {
            throw new RuntimeException("id:" + id + "不存在此id的用户信息");
        }
        return dept;
    }


    public Dept hystrixGet(Long id) {
        return new Dept().setDept_name("id:" + id + "不存在此id的用户信息,null-@Hystrix")
                .setDb_source("no the database in mysql");
    }
}
