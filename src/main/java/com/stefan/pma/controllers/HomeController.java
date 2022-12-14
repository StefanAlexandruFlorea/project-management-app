package com.stefan.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stefan.pma.dao.EmployeeRepository;
import com.stefan.pma.dao.ProjectRepository;
import com.stefan.pma.dto.ChartData;
import com.stefan.pma.dto.EmployeeProject;
import com.stefan.pma.entities.Project;

@Controller
public class HomeController {
	
	@Value("${version}")
	private String ver;

	@Autowired
	ProjectRepository proRepo;

	@Autowired
	EmployeeRepository empRepo;

	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		model.addAttribute("versionNumber", ver);

		List<Project> projects = proRepo.findAll();
		model.addAttribute("projectList", projects);
		
		List<ChartData> projectData = proRepo.getProjectSatus();
		
		//convert projectData object into a json structue for use in javascript
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		//[["NOTSTARTED", 1], ["INPROGRESS", 2], ["COMPLETED", 1]]
		
		model.addAttribute("projectStatusCnt", jsonString);

		List<EmployeeProject> employeesProjectCnt = empRepo.employeeProjects();
		model.addAttribute("employeesListProjectsCnt", employeesProjectCnt);	
		

		return "main/home";

	}
}
