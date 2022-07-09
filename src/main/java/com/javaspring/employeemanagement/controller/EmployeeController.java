package com.javaspring.employeemanagement.controller;

import com.javaspring.employeemanagement.dto.DepartmentDto;
import com.javaspring.employeemanagement.dto.EmployeeDto;
import com.javaspring.employeemanagement.entity.Department;
import com.javaspring.employeemanagement.entity.Employee;
import com.javaspring.employeemanagement.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService service;
    private final ModelMapper mapper;

    @GetMapping
    public List<EmployeeDto> getEmployees() {
        return service.getEmployees().stream().map(employee -> mapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/department/{departmentId}")
    public List<EmployeeDto> getEmployeesByDepartmentId(@PathVariable("departmentId") Long departmentId) {
        return service.getEmployeesByDepartmentId(departmentId).stream().map(employee -> mapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/search/{information}")
    public List<EmployeeDto> searchEmployee(@PathVariable("information") String information) {
        return service.searchEmployee(information).stream().map(employee -> mapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
    }

    @GetMapping("sort/name/{typeSort}")
    public List<EmployeeDto> sortNameEmployee(@PathVariable("typeSort") String typeSort) {
        List<Employee> employees = (typeSort.equals("asc")) ? service.sortName(true) : service.sortName(false);
        return employees.stream().map(employee ->
                mapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
    }

    @GetMapping("sort/salary/{typeSort}")
    public List<EmployeeDto> sortSalaryEmployee(@PathVariable("typeSort") String typeSort) {
        List<Employee> employees = (typeSort.equals("asc")) ? service.sortSalary(true) : service.sortSalary(false);
        return employees.stream().map(employee ->
                mapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("employeeId") Long employeeId) {
        return new ResponseEntity<>(mapper.map(service.getEmployee(employeeId), EmployeeDto.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = mapper.map(employeeDto, Employee.class);
        service.addEmployee(employee);
        return new ResponseEntity<>(mapper.map(employee, EmployeeDto.class), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = mapper.map(employeeDto, Employee.class);
        service.updateEmployee(employee);
        return new ResponseEntity<>(mapper.map(employee, EmployeeDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        service.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
