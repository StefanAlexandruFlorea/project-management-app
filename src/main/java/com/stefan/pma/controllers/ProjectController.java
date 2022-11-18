package com.stefan.pma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stefan.pma.dto.TimeChartData;
import com.stefan.pma.entities.Employee;
import com.stefan.pma.entities.Project;
import com.stefan.pma.services.EmployeeService;
import com.stefan.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectService proService;
	@Autowired
	EmployeeService empService;
	
	@GetMapping
	public String displayProjects(Model model) {		

		List<Project> projects = proService.findAll();
		model.addAttribute("projectsList", projects);

		return "projects/list-projects";
	}

	@GetMapping("/new")
	public String diplayProjectForm(Model model) {
		
		Project aProject = new Project();
		Iterable<Employee> employees = empService.getAll();
		
		
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees", employees);		
		
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Model model, @Valid Project project,  Errors errors) {
		
		if(errors.hasErrors()) {
			return "projects/new-project";
		}
		
		proService.save(project);
		
		//use redirect to prevent duplicate submissions
		//redirect to ProjectController end-point diplayProjectForm(Model model) method
		return "redirect:/projects";
	}
	
	
	@GetMapping("/timelines")
	public String displayProjectTimelines(Model model) throws JsonProcessingException {
		
		List<TimeChartData> timelineData=proService.getTimeData();
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		String jsonTimelineString = objectMapper.writeValueAsString(timelineData);
		
		System.out.println("--------project timelines---------------");
		System.out.println(jsonTimelineString);
		
		model.addAttribute("projectTimeList",jsonTimelineString);
		
		return "projects/project-timelines";
	}
	
	
	@GetMapping("/update")
	public String displayProjectpdateForm(@RequestParam("id") long theId, Model model) {

		Project proj = proService.findByProjectId(theId);
		Iterable<Employee> employees = empService.getAll();
		
		model.addAttribute("project", proj);
		model.addAttribute("allEmployees", employees);

		return "/projects/new-project";
	}
	
	
	
	@GetMapping("/delete")
	public String deleteProject(@RequestParam("id") long id) {
		
		proService.deleteByProjectId(id);
		
		return "redirect:/projects";
		
	}
	
	
}
