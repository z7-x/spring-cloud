package com.z7.springcloud;

import com.z7.myrule.Z7Rule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

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
//在微服务启动时候就能去加载自定义的Ribbon类
@RibbonClient(name = "api-service", configuration = Z7Rule.class)
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
