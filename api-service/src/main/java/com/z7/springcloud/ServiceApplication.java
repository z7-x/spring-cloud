package com.z7.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ProjectName：z7-learn-projects
 * @ClassName：Application
 * @Description：TODO 启动类
 * @Date：2021/6/4 10:56 上午
 * @Author：z7-x
 */
@SpringBootApplication
@EnableEurekaClient //服务启动注册到eureka中
@EnableDiscoveryClient //服务发现
@MapperScan(basePackages = "com.z7.springboot.dao")
public class ServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
}
