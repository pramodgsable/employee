package com.pramod.swissre.employee.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeNode {
    private Long id;
    private String name;
    private String role;
    private List<EmployeeNode> reportees = new ArrayList<>();

    public EmployeeNode(Long id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

}
