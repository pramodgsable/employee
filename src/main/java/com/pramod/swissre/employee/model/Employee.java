package com.pramod.swissre.employee.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Employee {
    private Long id;
    private String name;
    private String city;
    private String state;
    private String category;
    private Long managerId;
    private double salary;
    private LocalDate dateOfJoining;
}
