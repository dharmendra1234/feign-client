package com.feignclientexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	@Autowired
	EmployeeServiceClient employeeServiceClient;

	@GetMapping("/fetchEmployees")
	public ResponseEntity<?> fetchEmployees() {

		return ResponseEntity.ok(employeeServiceClient.getAllEmployees());
	}

	@GetMapping("/fetchEmployee/{id}")
	public ResponseEntity<?> fetchEmploye(@PathVariable int id) {
		Employee employee = employeeServiceClient.getEmployee(id);
		return ResponseEntity.ok(employee);
	}

	@PostMapping("/addEmployee")
	public ResponseEntity<Employee> addEmploye(@RequestBody Employee newemployee) {
		Employee employee = employeeServiceClient.addEmployee(newemployee);
		return ResponseEntity.ok(employee);
	}

}
