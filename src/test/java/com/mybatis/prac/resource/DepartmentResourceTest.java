package com.mybatis.prac.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybatis.prac.mapper.DepartmentMapper;
import com.mybatis.prac.model.Department;
import com.mybatis.prac.service.DepartmentServiceImpl;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class DepartmentResourceTest {

    @MockBean
    private DepartmentServiceImpl departmentService;

    @MockBean
    private DepartmentMapper departmentMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Department department;
    String jsonString;
//            = new Department(4,"ECE","D04","ECE Block");;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        jsonString = "{\n" +
                "\t\t\"department_id\": 4,\n" +
                "\t\t\"department_name\": \"ECE\",\n" +
                "\t\t\"department_code\": \"D04\",\n" +
                "\t\t\"department_address\": \"ECE Block\"\n" +
                "\t}";
        department = objectMapper.readValue(jsonString,Department.class);
    }

    @Test
    @DisplayName("Testing insert operation")
    void insert() throws Exception {
        mockMvc.perform(get("/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andExpect(status().isOk());
        ArgumentCaptor<Department> departmentArgumentCaptor = ArgumentCaptor.forClass(Department.class);
        verify(departmentService, times(1)).insert(departmentArgumentCaptor.capture());
        validateRequestToEntity(department,departmentArgumentCaptor.getValue());
    }

    private void validateRequestToEntity(Department department, Department value) {
        assertEquals(department.getDepartment_name(),value.getDepartment_name());
        assertEquals(department.getDepartment_id(),value.getDepartment_id());
        assertEquals(department.getDepartment_code(),value.getDepartment_code());
        assertEquals(department.getDepartment_address(),value.getDepartment_address());
    }

    @Test
    @DisplayName("Testing get dept. by Id operation")
    void getDeptById() throws Exception {
        when(departmentService.findDeptById(4)).thenReturn(department);
        mockMvc.perform(get("/getById/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.department_name")
                        .value(department.getDepartment_name()));
        ArgumentCaptor<Integer> departmentIdArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(departmentService, times(2)).findDeptById(departmentIdArgumentCaptor.capture());
    }

    @Test
    @DisplayName("Testing get all dept. operation")
    void getAll() throws Exception {
        List<Department> departmentList = new ArrayList<>();
        departmentList.add(department);
        when(departmentService.findAll()).thenReturn(departmentList);
        mockMvc.perform(get("/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].department_name").value(departmentList.get(0).getDepartment_name()));
        verify(departmentService, times(1)).findAll();
    }

    @Test
    void update() throws Exception {
        department = new Department(4,"ECE","D04","ECE block");;
        when(departmentService.update(department)).thenReturn(department);
        mockMvc.perform(put("/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\t\"department_id\": 4,\n" +
                                "\t\t\"department_name\": \"ECE\",\n" +
                                "\t\t\"department_code\": \"D04\",\n" +
                                "\t\t\"department_address\": \"ECE block\"\n" +
                                "\t}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.department_address")
                        .value(department.getDepartment_address()));;
        ArgumentCaptor<Department> departmentArgumentCaptor = ArgumentCaptor.forClass(Department.class);
        verify(departmentService, times(1)).update(departmentArgumentCaptor.capture());
        validateRequestToEntity(department,departmentArgumentCaptor.getValue());
    }

    @Test
    void delete()
    {

    }
}