package com.z7.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName：z7-spring-cloud
 * @ClassName：Z7Rule
 * @Description：TODO 自定义负载算法匹配
 * @Date：2021/6/9 10:12 上午
 * @Author：z7-x
 */
@Configuration
public class Z7Rule {

    @Bean
    public IRule myRule() {
        return new Z7RandomRule();
    }

}
