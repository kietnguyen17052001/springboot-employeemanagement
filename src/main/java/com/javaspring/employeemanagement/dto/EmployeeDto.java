package com.javaspring.employeemanagement.dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;
    private String name;
    private float salary;
    private Long departmentId;
}
