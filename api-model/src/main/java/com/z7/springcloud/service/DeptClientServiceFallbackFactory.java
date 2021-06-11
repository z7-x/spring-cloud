package com.z7.springcloud.service;


import com.z7.springcloud.pojo.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 客户端：处理服务降级
 *
 * @author z7
 */
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory {

    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public boolean saveDept(Dept dept) {
                return false;
            }

            @Override
            public Dept getDeptById(Long id) {
                return new Dept().setDept_no(id)
                        .setDept_name("id=>" + id + "没有对应的信息，客户端提供了降级信息，此服务器已关闭。")
                        .setDb_source("此服务器已关闭，暂无可用数据。");
            }

            @Override
            public List<Dept> getAll() {
                return null;
            }
        };
    }
}
