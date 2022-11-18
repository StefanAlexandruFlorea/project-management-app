package com.stefan.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.stefan.pma.dto.ChartData;
import com.stefan.pma.dto.TimeChartData;
import com.stefan.pma.entities.Project;

@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long>{
	
	@Override
	public List<Project> findAll();
	
	@Query(nativeQuery=true, value="SELECT stage as label, count(*) as value "
			+ "FROM project "
			+ "GROUP BY stage")
	public List<ChartData> getProjectSatus();
	
	@Query(nativeQuery=true, value="SELECT name as projectName, start_date as startDate, end_date as endDate "
			+"FROM project WHERE start_date IS NOT NULL")
	public List<TimeChartData> getTimeData();
	

	public Project findByProjectId(long theId);
}
