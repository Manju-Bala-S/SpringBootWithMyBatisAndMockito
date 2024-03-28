package com.mybatis.prac.service;

import com.mybatis.prac.exception.DepartmentNotFoundException;
import com.mybatis.prac.mapper.DepartmentMapper;
import com.mybatis.prac.model.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceImplTest {
    @Autowired
    private DepartmentServiceImpl departmentService;
    @MockBean
    private DepartmentMapper departmentMapper;

    @BeforeEach
    void setUp() {
        Department department = new Department(2,"IT","D02","IT block");
        Mockito.when(departmentMapper.findDeptByName("IT")).thenReturn(department);
    }

    @Test
    public void whenValidDepartmentName_thenDepartmentShouldFound(){
        String department_name = "IT";
        Department department = departmentService.findDeptByName(department_name);
        if(department!=null)
        {
            assertEquals(department_name,department.getDepartment_name());
        }
        else {
            throw new DepartmentNotFoundException("Department not found");
        }
    }
}