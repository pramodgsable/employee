package com.pramod.swissre.employee.fileUtility;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.pramod.swissre.employee.model.Employee;
import com.pramod.swissre.employee.model.EmployeeNode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeJsonBuilder {
    public static void buildHierarchy(List<Employee> employees, String outputPath) throws Exception {
        Map<Long, EmployeeNode> map = new HashMap<>();
        //For each employee created EmployeeNode structure which will be used to create hierarchy
        employees.forEach(e -> map.put(e.getId(), new EmployeeNode(e.getId(), e.getName(), e.getCategory())));

        //List directors will have managers which will again have list of employees as reportees
        List<EmployeeNode> employeeNodes = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getManagerId() == 0) {
                employeeNodes.add( map.get(e.getId()));
            } else if (map.containsKey(e.getManagerId())) {
                map.get(e.getManagerId()).getReportees().add(map.get(e.getId()));
            }
        }
        //Using jackson object mapper to write the employee json hierarchy structure into file
        ObjectMapper mapper = new ObjectMapper();
        //formatting json
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File(outputPath), employeeNodes);
    }
}