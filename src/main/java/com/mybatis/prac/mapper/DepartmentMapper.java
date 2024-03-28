package com.mybatis.prac.mapper;
import com.mybatis.prac.model.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    @Select("select * from public.department")
    List<Department> findAll();

    @Select("SELECT department_id, department_address, department_code, department_name FROM public.department where department_name='${department_name}';")
    Department findDeptByName(String department_name);

    @Insert("INSERT INTO public.department(\n" +
            "\tdepartment_id, department_address, department_code, department_name)\n" +
            "\tVALUES (${department_id}, '${department_address}', '${department_code}', '${department_name}');")
    Department insert(Department department);

    @Update("UPDATE public.department\n" +
            "\tSET department_id=${department_id} , department_address='${department_address}', department_code='${department_code}', department_name='${department_name}'\n" +
            "\tWHERE department_id=${department_id};")
    void update(Department department);

    @Select("select * from public.department where department_id=${department_id}")
    Department findDeptById(int department_id);

    @Delete("DELETE FROM public.department WHERE department_id=${department_id}")
    int delete(int department_id);
}
