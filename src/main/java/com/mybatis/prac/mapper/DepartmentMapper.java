package com.mybatis.prac.mapper;
import com.mybatis.prac.model.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    @Select("select * from public.department")
    List<Department> findAll();

    @Insert("INSERT INTO public.department(\n" +
            "\tdepartment_id, department_address, department_code, department_name)\n" +
            "\tVALUES (${department_id}, '${department_address}', '${department_code}', '${department_name}');")
    void insert(Department department);

    @Update("UPDATE public.department\n" +
            "\tSET department_id=${department_id} , department_address='${department_address}', department_code='${department_code}', department_name='${department_name}'\n" +
            "\tWHERE department_id=${department_id};")
    void update(Department department);

    @Delete("DELETE FROM public.department\n\tWHERE department_id=${department_id};")
    void delete(Long department_id);
}
