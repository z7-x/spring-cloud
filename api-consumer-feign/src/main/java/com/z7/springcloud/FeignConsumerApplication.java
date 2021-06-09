package com.z7.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


/**
 * @ProjectName：z7-learn-projects
 * @ClassName：ConsumerApplication
 * @Description：TODO 启动类
 * @Date：2021/6/4 10:56 上午
 * @Author：z7-x
 */
//ribbon与eureka整合后，客户端可以直接调用，不用关心ip地址
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.z7.springcloud"})//扫描feign的接口
public class FeignConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignConsumerApplication.class, args);
    }
}
