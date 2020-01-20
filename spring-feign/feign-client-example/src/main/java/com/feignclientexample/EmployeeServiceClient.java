package com.feignclientexample;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "employee-service", url = "http://localhost:8081" )
public interface EmployeeServiceClient {

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public List<Employee> getAllEmployees();

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public Employee getEmployee(@PathVariable("id") int employeeId);

	@RequestMapping(value = "employee/add", method = RequestMethod.POST)
	public Employee addEmployee(@RequestBody Employee employee);

}
