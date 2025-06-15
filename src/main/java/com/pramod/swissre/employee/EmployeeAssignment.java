package com.pramod.swissre.employee;

import com.pramod.swissre.employee.fileUtility.EmployeeJsonBuilder;
import com.pramod.swissre.employee.fileUtility.ExcelReader;
import com.pramod.swissre.employee.model.Employee;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeAssignment {

    public static void main(String[] args) throws Exception {
		// 1. manually create 50 employee records and write to Excel employees-data.xlsx
		System.out.println("##########");
		System.out.println("Data is created and saved in employees-data.xlsx");
		// 2. Read data from Excel
		List<Employee> employees = ExcelReader.readExcel("employees-data.xlsx");

		// 3. Gratuity Eligibility (more than 60 months)
		gratuityEligibleEmployees(employees);

		// 4. Employees with salary > manager's salary
		employeesWithSalaryGreaterThanManager(employees);

		// 5. Build org hierarchy
		System.out.println("\nEmployee hierarchy structure is written in org-structure.json");
		EmployeeJsonBuilder.buildHierarchy(employees, "org-structure.json");

		// 6. SQL statement to get Nth highest salary
		System.out.println("\nSQL Query to get employees with salary Nth highest salary");
		String query = """ 
                    SELECT * FROM employees e WHERE N-1 =
                    (SELECT COUNT(DISTINCT salary) FROM employees salary > e.salary)
                    """;
		System.out.println(query);
		System.out.println("\nJava Stream example to get employees with Nth(2nd) highest salary");
		employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed())
				.skip(1).findFirst().ifPresent(System.out::println);
    }

	public static void gratuityEligibleEmployees(List<Employee> employees){
		List<Employee> gratuityEligible = employees.stream()
				.filter(e -> Period.between(e.getDateOfJoining(), LocalDate.now()).toTotalMonths() > 60)
				.collect(Collectors.toList());

		System.out.println("Gratuity Eligible Employees:");
		gratuityEligible.forEach(System.out::println);
	}

	public static void employeesWithSalaryGreaterThanManager(List<Employee> employees ){
		Map<Long, Employee> employeeMap = employees.stream()
				.collect(Collectors.toMap(Employee::getId, e -> e));
		// filter all employees who is having manager and then compare with
		// against its managers salary using employeeMap
		List<Employee> higherPaidThanManager = employees.stream()
				.filter(e -> e.getManagerId() != null && employeeMap.containsKey(e.getManagerId()))
				.filter(e -> e.getSalary() > employeeMap.get(e.getManagerId()).getSalary())
				.collect(Collectors.toList());
		System.out.println("\nEmployees with salary greater than their manager:");
		higherPaidThanManager.forEach(System.out::println);
	}
}










