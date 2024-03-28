package com.mybatis.prac.resource;

import com.mybatis.prac.mapper.DepartmentMapper;
import com.mybatis.prac.model.Department;
import com.mybatis.prac.service.DepartmentServiceImpl;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest
class DepartmentResourceTest {

    @MockBean
    private DepartmentServiceImpl departmentService;

    @MockBean
    private DepartmentMapper departmentMapper;

    @Autowired
    private MockMvc mockMvc;

    private Department department = new Department(4,"ECE","D04","ECE Block");;

    @BeforeEach
    void setUp() {
        Mockito.when(departmentMapper.insert(department)).thenReturn(department);
    }

    @Test
    void insert() throws Exception {
        Mockito.when(departmentService.insert(department)).thenReturn(department);
        mockMvc.perform(MockMvcRequestBuilders.get("/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\t\"department_id\": 4,\n" +
                                "\t\t\"department_name\": \"ECE\",\n" +
                                "\t\t\"department_code\": \"D04\",\n" +
                                "\t\t\"department_address\": \"ECE Block\"\n" +
                                "\t}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}