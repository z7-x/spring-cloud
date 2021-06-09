package com.z7.springcloud.service.impl;

import com.z7.springcloud.dao.DeptMapper;
import com.z7.springcloud.pojo.Dept;
import com.z7.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName：z7-learn-projects
 * @ClassName：DeptService
 * @Description：TODO
 * @Date：2021/6/4 10:42 上午
 * @Author：z7-x
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptDao;

    @Override
    public boolean saveDept(Dept dept) {
        boolean flag = deptDao.saveDept(dept);
        return flag;
    }

    @Override
    public Dept getDeptById(Long id) {
        return deptDao.getDeptById(id);
    }

    @Override
    public List<Dept> getAll() {
        return deptDao.getAll();
    }
}
