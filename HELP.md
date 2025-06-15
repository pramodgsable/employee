# Getting Started

### Reference Documentation
This is standalone Java application to solve the below problems
1.	Use the above table and create similar records [approximately:50 employees] and write the same into a excel file.
* Used reference table to create the employees-data.xlsx file containing 50 employees
2.	Use a library to fetch the data from excel file and do the following tasks. 
* Used apache poi to read from excel file
3.	Identify who all employees are eligible for gratuity [assuming gratuity eligibility is for employee who served more than 60 months] based on DOJ column
* Used java stream to identify the gratiuty eligible employees
4.	Write a method to calculate the employee whose salary is greater than his manager's salary
* identify all the employees who is having managers and compared with its managers salary which is stored in employee map
5.	Build employee hierarchy tree (Org. structure) based on manager_id [output should be written into a JSON file]
* Used jackson library to map the object to json and write the response json into org-structure.json 
6.	Write an SQL to return the row of an employee whose salary is nth highest in descending order
* Printed the plain SQL query to get 
Nth highest salary as well as used java stream to get the nth highest salary

### How to run
* Its a standalone java program having EmployeeAssignment as main class
* Run EmployeeAssignment as java application
* This application reads data from employees-data.xlsx
* Classes created
* Employee: class as POJO to hold the employee details
* EmployeeNode: is created to build org hierarchy structure and stored in org-structure.json 
* ExcelReader: to read the data from the input excel file(employees-data.xlsx)
* EmployeeJsonBuilder: is created for building the org hierarchy and write that data as json into json file(org-structure.json)
