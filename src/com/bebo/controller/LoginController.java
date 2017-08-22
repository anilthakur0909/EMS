package com.bebo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.bebo.model.Employee;
import com.bebo.service.EmployeeService;

/*
 * @author Anil.Thakur
 */

@Controller
public class LoginController {

	@Autowired
	public EmployeeService employeeService;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("welcomePage");
		return model;

	}
	
	@RequestMapping(value = { "/adminHomePage" }, method = RequestMethod.GET)
	public ModelAndView adminHomePage() {
		ModelAndView modelAndView = new ModelAndView();
		List<Employee> employees = employeeService.findAllEmployee();
		modelAndView.addObject("employees", employees);
		modelAndView.setViewName("adminHomePage");
		return modelAndView;

	}

	@RequestMapping(value = "/userLoginPage", method = RequestMethod.GET)
	public ModelAndView userLoginPage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		ModelAndView model = new ModelAndView();
		if (session != null && session.getAttribute("username") != null) {
			model.addObject("message", "User Already Logged In.");
			List<Employee> employees = getEmployeeUsingCriteriaUsingRestFul();
			model.addObject("employees", employees);
			model.setViewName("userProfilePage");
		} else {
			model.addObject("employee", new Employee());
			model.setViewName("userLoginPage");
		}
		return model;
	}

	@RequestMapping(value = "/userProfilePage", method = RequestMethod.GET)
	public ModelAndView userProfilePage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		ModelAndView model = new ModelAndView();
		if (session != null && session.getAttribute("username") != null) {
			List<Employee> employees = getEmployeeUsingCriteriaUsingRestFul();
			model.addObject("employees", employees);
			model.setViewName("userProfilePage");
		} else {
			model.addObject("message", "Please login first.");
			model.setViewName("userLoginPage");
		}
		return model;
	}

	@RequestMapping(value = "/userLogout", method = RequestMethod.GET)
	public ModelAndView userLogout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession httpSession = request.getSession();
		httpSession.invalidate();
		ModelAndView model = new ModelAndView();
		model.addObject("employee", new Employee());
		model.addObject("message", "Logged Out  Successfully.");
		model.setViewName("userLoginPage");
		return model;
	}

	@RequestMapping(value = "/processUserLogin", method = RequestMethod.POST)
	public ModelAndView processUserLogin(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("employee") Employee employee) {
		ModelAndView modelAndView = new ModelAndView();
		if (employeeService.employeeExist(employee)) {
			modelAndView.addObject("message", "User Logged In  Successfully.");
			HttpSession session = request.getSession();
			session.setAttribute("username", employee.getUsername());
			List<Employee> employees = getEmployeeUsingCriteriaUsingRestFul();
			modelAndView.addObject("employees", employees);
			modelAndView.setViewName("userProfilePage");
		} else {
			modelAndView.addObject("error", "Invalid Credentials Provided.");
			modelAndView.setViewName("userLoginPage");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/adminLoginPage", method = RequestMethod.GET)
	public ModelAndView adminLoginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid Credentials Provided.");
		}

		if (logout != null) {
			model.addObject("message", "Logged Out  Successfully.");
		}

		model.setViewName("adminLoginPage");
		return model;
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getEmployeeUsingCriteriaUsingRestFul() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8086/EMS/employees";
		return restTemplate.getForObject(url, List.class);
	}

}
