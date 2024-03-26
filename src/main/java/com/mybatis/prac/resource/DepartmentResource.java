package com.mybatis.prac.resource;

import com.mybatis.prac.mapper.DepartmentMapper;
import com.mybatis.prac.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
class DepartmentResource{
    private DepartmentMapper departmentMapper;

    public DepartmentResource(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    @GetMapping("/all")
    public List<Department> getAll(){
        return departmentMapper.findAll();
    }

    @GetMapping("/insert")
    public List<Department> insert(@Valid @RequestBody Department department){
        departmentMapper.insert(department);
        return departmentMapper.findAll();
    }

    @PutMapping("/update")
    public List<Department> update(@Valid @RequestBody Department department){
        departmentMapper.update(department);
        return departmentMapper.findAll();
    }

    @DeleteMapping("/delete/{department_id}")
    public String delete(@PathVariable("department_id") Long department_id){
        return "Successfully deleted";
    }

}