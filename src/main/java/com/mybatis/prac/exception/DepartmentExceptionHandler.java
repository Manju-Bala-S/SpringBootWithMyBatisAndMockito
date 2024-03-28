package com.mybatis.prac.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DepartmentExceptionHandler {
    @ExceptionHandler(value = {DepartmentNotFoundException.class})
    public ResponseEntity<Object> handleDepartmentNotFoundException(DepartmentNotFoundException departmentNotFoundException){
        DepartmentException departmentException = new DepartmentException(
                departmentNotFoundException.getMessage(),
                departmentNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(departmentException,HttpStatus.NOT_FOUND);
    }
}
