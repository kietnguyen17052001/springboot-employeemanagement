package com.javaspring.employeemanagement.service;

import com.javaspring.employeemanagement.entity.Department;
import com.javaspring.employeemanagement.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    List<Employee> getEmployees();

    List<Employee> searchEmployee(String information);

    List<Employee> getEmployeesByDepartmentId(Long departmentId);

    List<Employee> sortName(boolean isAsc);

    List<Employee> sortSalary(boolean isAsc);

    Employee getEmployee(Long employeeId);

    Employee addEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Long employeeId);
}
