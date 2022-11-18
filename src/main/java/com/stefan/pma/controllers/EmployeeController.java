package com.stefan.pma.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stefan.pma.entities.Employee;
import com.stefan.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeService empService;

	@GetMapping
	public String displayEmployees(Model model) {

		Iterable<Employee> employees = empService.getAll();
		model.addAttribute("employeesList", employees);

		return "employees/list-employees";
	}

	@GetMapping("/new")
	public String diplayEmployeeForm(Model model) {

		Employee anEmployee = new Employee();

		model.addAttribute("employee", anEmployee);

		return "employees/new-employee";
	}

	@PostMapping("/save")
	public String createEmployee(Model model, @Valid Employee employee, Errors errors) {
		
		if(errors.hasErrors()) {
			return "employees/new-employee";
		}
		
		empService.save(employee);

		// use redirect to prevent duplicate submissions
		return "redirect:/employees";
	}

	@GetMapping("/update")
	public String displayEmployeeUpdateForm(@RequestParam("id") long theId, Model model) {

		Employee emp = empService.findByEmployeeId(theId);
		
		model.addAttribute("employee", emp);

		return "/employees/new-employee";
	}
	
	
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") long id) {
		
		empService.deleteByEmployeeId(id);
		
		return "redirect:/employees";
		
	}

}
