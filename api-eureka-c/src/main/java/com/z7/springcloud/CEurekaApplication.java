package com.z7.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ProjectName：z7-spring-cloud
 * @ClassName：EurekaApplication
 * @Description：TODO 集群3启动类
 * @Date：2021/6/7 3:14 下午
 * @Author：z7-x
 */
/**
 *  @EnableEurekaServer 服务端启动类，可以接受别人注册进来
 */
@EnableEurekaServer
@SpringBootApplication
public class CEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CEurekaApplication.class, args);
    }
}
