package com.mybatis.prac;

import com.mybatis.prac.model.Department;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.mybatis.prac.mapper")
@MappedTypes(Department.class)
@SpringBootApplication
public class PracApplication {
	public static void main(String[] args) {
		SpringApplication.run(PracApplication.class, args);
	}

}
