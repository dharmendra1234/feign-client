package com.employeeservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private static List<Employee> employeeList = new ArrayList<>();
    static {
        employeeList.add(new Employee(1, "employee-1", 12.0));
        employeeList.add(new Employee(2, "employee-2", 34.0));
        employeeList.add(new Employee(3, "employee-3", 9.0));
    }

    @GetMapping("/employees")
    public ResponseEntity<?> getEmployees() {
        return ResponseEntity.ok(employeeList);

    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable int id) {
        Employee employee = findEmployee(id);
        if (employee == null) {
            return ResponseEntity.badRequest()
                .body("Invalid employee Id");
        }
        return ResponseEntity.ok(employee);
    }
    
    
    @PostMapping ("/employee/add")
     public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
    	employeeList.add(employee);
 	  	return ResponseEntity.ok(employee);
       }

    private Employee findEmployee(int id) {
        return employeeList.stream()
            .filter(user -> user.getId()
                .equals(id))
            .findFirst()
            .orElse(null);
    }

}
