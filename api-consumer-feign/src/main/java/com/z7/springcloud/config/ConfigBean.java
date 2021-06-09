package com.z7.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ProjectName：z7-spring-cloud
 * @ClassName：ConfigBean
 * @Description：TODO 注入Bean到Spring容器中
 * @Date：2021/6/4 3:40 下午
 * @Author：z7-x
 */
@Configuration
public class ConfigBean {//@Configuration 相当于spring中的applicationContext.xml中的配置Bean


    /**
     * 配置负载均衡 实现 RestTemplate
     * IRule
     * AvailabilityFilteringRule：优先过滤掉跳闸的服务，然后轮询
     * RoundRobinRule：轮询
     * RandomRule：随机
     * RetryRule：会先按照轮询服务获取服务，如果服务获取失败，则会在指定时间内进行重试
     */
    @Bean
    @LoadBalanced //ribbon:基于客户端实现，负载方式默认是轮询
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
