package com.stefan.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefan.pma.dao.ProjectRepository;
import com.stefan.pma.dto.TimeChartData;
import com.stefan.pma.entities.Project;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository proRepo;

	public void save(Project project) {
		proRepo.save(project);

	}

	public List<Project> findAll() {
		return proRepo.findAll();
	}

	public Project findByProjectId(long theId) {
		return proRepo.findByProjectId(theId);
	}

	public void deleteByProjectId(long id) {
		proRepo.deleteById(id);

	}

	public List<TimeChartData> getTimeData() {

		return proRepo.getTimeData();

	}
}
