package com.z7.springcloud.service;

import com.z7.springcloud.pojo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Component
@FeignClient(name = "api-service",fallbackFactory = DeptClientServiceFallbackFactory.class)//负载的服务名,降级类
public interface DeptClientService {

    @PostMapping(value = "/dept/add")
    boolean saveDept(Dept dept);

    @GetMapping(value = "/dept/{id}")
    Dept getDeptById(@PathVariable("id") Long id);

    @GetMapping(value = "dept/getAll")
    List<Dept> getAll();
}
