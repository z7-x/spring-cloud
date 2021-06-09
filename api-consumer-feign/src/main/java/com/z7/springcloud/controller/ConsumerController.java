package com.z7.springcloud.controller;

import com.z7.springcloud.pojo.Dept;
import com.z7.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

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

    @Autowired
    private DeptClientService deptClientService;

    @RequestMapping("/save")
    public Boolean saveDept(Dept dept) {
        return deptClientService.saveDept(dept);
    }

    @RequestMapping("/id")
    public Dept getDeptById(@PathParam("id") Long id) {
        return deptClientService.getDeptById(id);
    }

    @RequestMapping("/getAll")
    public List<Dept> getAll() {
        return deptClientService.getAll();
    }

}
