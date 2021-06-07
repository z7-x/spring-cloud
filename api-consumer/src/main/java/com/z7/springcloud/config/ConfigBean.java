package com.z7.springcloud.config;

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

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
