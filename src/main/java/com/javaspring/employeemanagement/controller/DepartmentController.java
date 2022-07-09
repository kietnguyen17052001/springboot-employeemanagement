package com.javaspring.employeemanagement.controller;

import com.javaspring.employeemanagement.dto.DepartmentDto;
import com.javaspring.employeemanagement.entity.Department;
import com.javaspring.employeemanagement.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService service;
    private final ModelMapper mapper;

    @GetMapping
    public List<DepartmentDto> getDepartments() {
        return service.getDepartments().stream().map(department ->
                mapper.map(department, DepartmentDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/search/{information}")
    public List<DepartmentDto> searchDepartments(@PathVariable("information") String information) {
        return service.searchDepartments(information).stream().map(department ->
                mapper.map(department, DepartmentDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("departmentId") Long departmentId) {
        return new ResponseEntity<>(mapper.map(service.getDepartment(departmentId), DepartmentDto.class), HttpStatus.OK);
    }

    @GetMapping("/sort/{typeSort}")
    public List<DepartmentDto> sortDepartments(@PathVariable("typeSort") String typeSort) {
        List<Department> departments = (typeSort.equals("asc")) ? service.sortDepartmentName(true) : service.sortDepartmentName(false);
        return departments.stream().map(department ->
                mapper.map(department, DepartmentDto.class)).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> addDepartment(@RequestBody DepartmentDto departmentDto) {
        Department entity = mapper.map(departmentDto, Department.class);
        service.addDepartment(entity);
        return new ResponseEntity<>(mapper.map(entity, DepartmentDto.class), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto departmentDto) {
        Department entity = mapper.map(departmentDto, Department.class);
        service.updateDepartment(entity);
        return new ResponseEntity<>(mapper.map(entity, DepartmentDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("departmentId") Long departmentId) {
        service.deleteDepartment(departmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
