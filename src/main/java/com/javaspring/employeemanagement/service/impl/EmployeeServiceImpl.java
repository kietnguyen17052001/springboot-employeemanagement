package com.javaspring.employeemanagement.service.impl;

import com.javaspring.employeemanagement.entity.Employee;
import com.javaspring.employeemanagement.exception.NotFoundException;
import com.javaspring.employeemanagement.repository.EmployeeRepo;
import com.javaspring.employeemanagement.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo repo;

    @Override
    public List<Employee> getEmployees() {
        return repo.findAll();
    }

    @Override
    public List<Employee> searchEmployee(String information) {
        return getEmployees().stream().filter(employee -> employee.getName().contains(information)).collect(Collectors.toList());
    }

    @Override
    public List<Employee> getEmployeesByDepartmentId(Long departmentId) {
        return getEmployees().stream().filter(employee -> employee.getDepartment().getId() == departmentId).collect(Collectors.toList());
    }

    @Override
    public List<Employee> sortName(boolean isAsc) {
        return isAsc ? getEmployees().stream().sorted(Comparator.comparing(Employee::getName)).collect(Collectors.toList()) :
                getEmployees().stream().sorted(Comparator.comparing(Employee::getName).reversed()).collect(Collectors.toList());
    }

    @Override
    public List<Employee> sortSalary(boolean isAsc) {
        return isAsc ? getEmployees().stream().sorted(Comparator.comparing(Employee::getSalary)).collect(Collectors.toList()) :
                getEmployees().stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).collect(Collectors.toList());
    }

    @Override
    public Employee getEmployee(Long employeeId) {
        return repo.findById(employeeId).orElseThrow(() -> {
            log.error("error getting employee {}", employeeId);
            return new NotFoundException("Employee " + employeeId + " not found");
        });

    }

    @Override
    public Employee addEmployee(Employee employee) {
        return repo.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return repo.save(employee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        repo.deleteById(employeeId);
    }
}
