package com.feignclientexample;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.feignclientexample.AppController;
import com.feignclientexample.Employee;
import com.feignclientexample.EmployeeServiceClient;
import com.feignclientexample.errors.ProductNotFoundException;

@RunWith(SpringRunner.class)
@WebMvcTest(AppController.class)
public class FeignClientApplicationTests {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private EmployeeServiceClient employeeServiceClient;


  @Test
  public void shouldReturnListOfEmployees_whenFetchEmployeesCalled() throws Exception {

    List<Employee> employees = new ArrayList<Employee>();
    employees.add(new Employee(1, "product-1", 12.0));
    employees.add(new Employee(2, "product-2", 22.0));
    given(employeeServiceClient.getAllEmployees()).willReturn(employees);
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/fetchEmployees");

    mvc.perform(builder).andExpect(status().isOk());
  }

  @Test
  public void shouldReturnProduct_whenvalidProductIdIsPassed() throws Exception {

    Employee employee = new Employee(1, "product-1", 12.0);
    given(employeeServiceClient.getEmployee(1)).willReturn(employee);
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/fetchEmployee/" + 1);

    mvc.perform(builder).andExpect(status().isOk());
  }

  @Test
  public void shouldThrowException_whenInvalidProductIdPassed() throws Exception {

    given(employeeServiceClient.getEmployee(2))
        .willThrow(new ProductNotFoundException("Id not found"));
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/fetchEmployee/" + 2);

    mvc.perform(builder).andExpect(status().isNotFound());
  }

}
