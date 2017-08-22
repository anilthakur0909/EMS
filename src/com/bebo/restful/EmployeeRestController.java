/**
 * 
 */
package com.bebo.restful;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bebo.model.Employee;
import com.bebo.service.EmployeeService;

/**
 * @author anthakur
 *
 */
@RestController
public class EmployeeRestController {

	@Autowired
	public EmployeeService employeeService;
	
	@RequestMapping("/employees")
	public List<Employee> getEmployees() {
		return employeeService.findEmployeeUsingCriteia();
	}
}
