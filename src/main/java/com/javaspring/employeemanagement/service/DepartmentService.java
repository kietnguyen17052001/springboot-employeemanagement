package com.javaspring.employeemanagement.service;

import com.javaspring.employeemanagement.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getDepartments();
    List<Department> searchDepartments(String information);
    List<Department> sortDepartmentName(boolean isAsc);
    Department getDepartment(Long departmentId);
    Department addDepartment(Department department);
    Department updateDepartment(Department department);
    void deleteDepartment(Long departmentId);
}
