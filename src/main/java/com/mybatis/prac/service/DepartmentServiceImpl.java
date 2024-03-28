package com.mybatis.prac.service;

import com.mybatis.prac.mapper.DepartmentMapper;
import com.mybatis.prac.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentServiceImpl implements DepartmentServiceInterface {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> findAll() {
        return departmentMapper.findAll();
    }

    @Override
    public Department insert(Department department) {
        departmentMapper.insert(department);
        return department;
    }

    @Override
    public void update(Department department) {
        departmentMapper.update(department);
    }

    @Override
    public Department findDeptById(int department_id) {
        return departmentMapper.findDeptById(department_id);
    }

    @Override
    public Department findDeptByName(String department_name) {
        return departmentMapper.findDeptByName(department_name);
    }

    @Override
    public int delete(int department_id) {
        return departmentMapper.delete(department_id);
    }
}
