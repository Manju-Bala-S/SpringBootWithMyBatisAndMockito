package com.mybatis.prac.service;

import com.mybatis.prac.model.Department;

import java.util.List;

public interface DepartmentServiceInterface {
    List<Department> findAll();
    Department insert(Department department);
    void update(Department department);
    Department findDeptById(int department_id);

    Department findDeptByName(String department_name);

    int delete(int department_id);
}
