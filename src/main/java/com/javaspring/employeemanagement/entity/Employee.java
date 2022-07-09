package com.javaspring.employeemanagement.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    private float salary;
    @ManyToOne(targetEntity = Department.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_department", nullable = false)
    private Department department;
}
