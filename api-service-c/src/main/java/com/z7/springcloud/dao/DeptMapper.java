package com.z7.springcloud.dao;

import com.z7.springcloud.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName：z7-learn-projects
 * @ClassName：DeptMapper
 * @Description：TODO
 * @Date：2021/6/4 9:55 上午
 * @Author：z7-x
 */
@Mapper
@Repository
public interface DeptMapper {

    boolean saveDept(Dept dept);

    Dept getDeptById(Long id);

    List<Dept> getAll();
}
