package com.z7.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @ProjectName：z7-spring-cloud
 * @ClassName：HystrixDashboardApplication
 * @Description：TODO
 * @Date：2021/6/11 4:01 下午
 * @Author：z7-x
 */
@SpringBootApplication
//开启DashBoard监控注解
@EnableHystrixDashboard
public class HystrixDashboardApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApplication.class, args);
    }
}
