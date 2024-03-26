package com.mybatis.prac.model;

public class Department{
    private long department_id;
    private String department_name;
    private String department_code;
    private String department_address;

    public long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(long department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getDepartment_code() {
        return department_code;
    }

    public void setDepartment_code(String department_code) {
        this.department_code = department_code;
    }

    public String getDepartment_address() {
        return department_address;
    }

    public void setDepartment_address(String department_address) {
        this.department_address = department_address;
    }
}