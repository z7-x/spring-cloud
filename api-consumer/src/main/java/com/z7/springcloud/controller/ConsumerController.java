package com.z7.springcloud.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.z7.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @ProjectName：z7-spring-cloud
 * @ClassName：ConsumerController
 * @Description：TODO
 * @Date：2021/6/4 3:27 下午
 * @Author：z7-x
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    //服务方地址
    private static final String REST_URL_PREFIX = "http://localhost:8001";

    //理解：消费者不应该有service层
    //RestTemplate注册到Spring容器中

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/save")
    public Boolean saveDept(Dept dept) {
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/save", dept, Boolean.class);
    }

    @RequestMapping("/id")
    public Dept getDeptById(@PathParam("id") Long id) {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/id?id=" + id, Dept.class);
    }

    @RequestMapping("/getAll")
    public List<Dept> getAll() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/getAll", List.class);
    }

}
