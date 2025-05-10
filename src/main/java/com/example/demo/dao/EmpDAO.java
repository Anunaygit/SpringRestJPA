package com.example.demo.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Employee;


public interface EmpDAO extends JpaRepository<Employee, Integer> {
	
	public List<Employee> getByDesignation(String desig);
	public List<Employee> findBySalaryGreaterThan(int salary);
	public List<Employee> getByAge(int age);
	
	@Query("from Employee e where e.designation=?1 order by e.salary desc")
	public List<Employee> myCustomQuery(String desig);

}
