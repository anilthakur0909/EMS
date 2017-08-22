package com.bebo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bebo.component.EmployeeEmail;
import com.bebo.model.ContractEmployee;
import com.bebo.model.Employee;
import com.bebo.model.OfficeAddress;
import com.bebo.model.RegularEmployee;
import com.bebo.model.Role;
import com.bebo.service.EmployeeService;

/*
 * @author Anil.Thakur
 */

@Controller
public class RegistrationController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeEmail employeeEmail;

	@Autowired
	@Qualifier("regularEmployeeAddress")
	private OfficeAddress regularEmployeeAddress;

	@Autowired
	@Qualifier("contractEmployeeAddress")
	private OfficeAddress contractEmployeeAddress;

	@RequestMapping(value = "/userRegister", method = RequestMethod.GET)
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("regularEmployee", new RegularEmployee());
		modelAndView.addObject("contractEmployee", new ContractEmployee());
		modelAndView.setViewName("userRegister");
		return modelAndView;
	}

	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public ModelAndView addEmployee(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("regularEmployee") RegularEmployee regularEmployee,
			@ModelAttribute("contractEmployee") ContractEmployee contractEmployee) {
		ModelAndView model = new ModelAndView();
		try {
			if (regularEmployee != null && regularEmployee.getSalary() != null && regularEmployee.getBonus() != null) {

				regularEmployee.setAddress(regularEmployeeAddress);
				regularEmployee.setRoles(getRoles());
				employeeService.register(regularEmployee);
				employeeEmail.sendEmail(regularEmployee);
			} else {
				contractEmployee.setAddress(contractEmployeeAddress);
				contractEmployee.setRoles(getRoles());
				employeeService.register(contractEmployee);
				employeeEmail.sendEmail(contractEmployee);
			}

			model.addObject("message", "Employee Added Successfully..");
			List<Employee> employees = employeeService.findAllEmployee();
			model.addObject("employees", employees);
			model.setViewName("adminHomePage");
		} catch (Exception exception) {
			model.addObject("error", "UserName Already Exist");
			model.setViewName("userRegister");
		}

		return model;
	}

	@RequestMapping(value = "/deleteEmployee/{employeeId}", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(@PathVariable int employeeId) {

		employeeService.deleteEmployee(employeeId);
		ModelAndView model = new ModelAndView();
		model.addObject("message", "Employee Added Successfully..");
		List<Employee> employees = employeeService.findAllEmployee();
		model.addObject("employees", employees);
		model.setViewName("adminHomePage");
		return model;
	}

	private List<Role> getRoles() {
		List<Role> roles = new ArrayList<Role>();
		Role role1 = new Role();
		role1.setRoleName("Engineer");
		Role role2 = new Role();
		role2.setRoleName("Developer");
		roles.add(role1);
		roles.add(role2);
		return roles;
	}
}
