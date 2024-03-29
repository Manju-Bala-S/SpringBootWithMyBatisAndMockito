package com.mybatis.prac.resource;

import com.mybatis.prac.exception.DepartmentNotFoundException;
import com.mybatis.prac.mapper.DepartmentMapper;
import com.mybatis.prac.model.Department;
import com.mybatis.prac.service.DepartmentServiceImpl;
import com.mybatis.prac.service.DepartmentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
class DepartmentResource{

    @Autowired
    private DepartmentServiceImpl departmentService;

    public DepartmentResource(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/all")
    public List<Department> getAll(){
        return departmentService.findAll();
    }

    @GetMapping("/insert")
    public Department insert(@Valid @RequestBody Department department){
        departmentService.insert(department);
        return department;
    }

    @GetMapping("/getById/{department_id}")
    public Department getDeptById(@PathVariable("department_id") int department_id){
        if(departmentService.findDeptById(department_id) == null)
            throw new DepartmentNotFoundException("Department "+department_id+" not found");
        return departmentService.findDeptById(department_id);
    }

    @GetMapping("/getByName/{department_name}")
    public Department getDeptByName(@PathVariable("department_name") String department_name){
        System.out.println("Inside getByDeptName");
        if(departmentService.findDeptByName(department_name) == null) {
            System.out.println("Inside if");
            throw new DepartmentNotFoundException("Department "+department_name+" not found");
        }
        return departmentService.findDeptByName(department_name);
    }

    @PutMapping("/update")
    public Department update(@Valid @RequestBody Department department){
        departmentService.update(department);
        return department;
    }

    @DeleteMapping("/delete/{department_id}")
    public String delete(@PathVariable("department_id") int department_id){
        int status = departmentService.delete(department_id);
        if(status>0){
            return "Successfully deleted";
        }
        else{
            throw new DepartmentNotFoundException("Department not found");
        }
    }
}