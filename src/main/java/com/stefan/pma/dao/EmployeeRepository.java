package com.stefan.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.stefan.pma.dto.EmployeeProject;
import com.stefan.pma.entities.Employee;

@RepositoryRestResource(collectionResourceRel="apiemployees", path="apiemployees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>{

	
	@Query(nativeQuery=true, value="SELECT e.first_name as firstName, e.last_name as lastName, count(pe.employee_id) AS projectCount "
			+ "FROM employee e LEFT JOIN project_employee pe ON e.employee_id = pe.employee_id "
			+ "GROUP BY e.first_name, e.last_name "
			+ "ORDER BY 3 DESC")
	
	public List<EmployeeProject> employeeProjects();

	public Employee findByEmail(String email);

	public Employee findByEmployeeId(long theId);
}
