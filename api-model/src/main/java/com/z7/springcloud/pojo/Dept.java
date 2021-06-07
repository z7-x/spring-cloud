package com.z7.springcloud.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ProjectName：z7-learn-projects
 * @ClassName：Dept
 * @Description：TODO
 * @Date：2021/6/3 4:34 下午
 * @Author：z7-x
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * @JsonIgnoreProperties 此注解是类注解，作用是json序列化时将Java bean中的一些属性忽略掉，序列化和反序列化都受影响。
 */
@JsonIgnoreProperties(value = {"handler"})
/**
 * @Accessors(chain = true) 链式写法 类似于 @Builder
 */
@Accessors(chain = true)
public class Dept implements Serializable {
    private Long dept_no;
    private String dept_name;
    /**
     * 这个数据库存在哪个数据库的字段-微服务
     * 一个服务对应一个数据库，同一个信息可能存在不同的数据库
     */
    private String db_source;


}
