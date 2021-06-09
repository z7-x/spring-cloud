package com.z7.springcloud.service;

import com.z7.springcloud.pojo.Dept;

import java.util.List;

public interface DeptService {
    boolean saveDept(Dept dept);

    Dept getDeptById(Long id);

    List<Dept> getAll();
}
