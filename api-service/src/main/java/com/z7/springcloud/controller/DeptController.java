package com.z7.springcloud.controller;

import com.z7.springcloud.pojo.Dept;
import com.z7.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

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
    @Autowired
    private DiscoveryClient client;

    @PostMapping("/save")
    public Boolean saveDept(Dept dept) {
        return deptService.saveDept(dept);
    }

    @GetMapping("/id")
    public Dept getDeptById(@PathParam("id") Long id) {
        return deptService.getDeptById(id);
    }

    @GetMapping("/getAll")
    public List<Dept> getAll() {
        return deptService.getAll();
    }

    /**
     * 获取注册服务的信息
     *
     * @return
     */
    @GetMapping("/discovery")
    public Object discovery() {
        //获取服务清单
        List<String> services = client.getServices();
        System.out.println("discovery=>services:" + services);

        //根据 ApplicationName 得到一个具体的微服务信息
        List<ServiceInstance> instances = client.getInstances("API-SERVICE");
        instances.stream().forEach(instance -> {
            System.out.println(instance.getHost() +
                    instance.getPort() +
                    instance.getUri());
        });

        return this.client;
    }
}
