package com.javaspring.employeemanagement.service.impl;

import com.javaspring.employeemanagement.entity.Department;
import com.javaspring.employeemanagement.exception.NotFoundException;
import com.javaspring.employeemanagement.repository.DepartmentRepo;
import com.javaspring.employeemanagement.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepo repo;

    @Override
    public List<Department> getDepartments() {
        return repo.findAll();
    }

    @Override
    public List<Department> searchDepartments(String information) {
        return getDepartments().stream().filter(d -> d.getName().contains(information)).collect(Collectors.toList());
    }

    @Override
    public List<Department> sortDepartmentName(boolean isAsc) {
        return (isAsc) ? getDepartments().stream().sorted(Comparator.comparing(Department::getName)).collect(Collectors.toList()) :
                getDepartments().stream().sorted(Comparator.comparing(Department::getName).reversed()).collect(Collectors.toList());
    }

    @Override
    public Department getDepartment(Long departmentId) {
        return repo.findById(departmentId).orElseThrow(() -> {
            log.error("error getting department {}", departmentId);
            return new NotFoundException("Department " + departmentId + " not found");
        });
    }

    @Override
    public Department addDepartment(Department department) {
        return repo.save(department);
    }

    @Override
    public Department updateDepartment(Department department) {
        return repo.save(department);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        Department department = repo.findById(departmentId).orElseThrow(() ->
                new NotFoundException("Department " + departmentId + " not found")
        );
        repo.deleteById(departmentId);
    }
}
