package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.EmpDAO;
import com.example.demo.entities.Employee;

@RestController
public class EmpController {
	@Autowired
	EmpDAO dao;
	
	@GetMapping("/")
	public String welcome() {
		return "<h1> Welcome to Employee Management Application</h1>";
	}
	
	@GetMapping("/employees")
	public Iterable<Employee> getEmployee(){
		return dao.findAll();
	}
	
	@GetMapping("/employees/{id}")
	public Optional<Employee> getEmployee(@PathVariable int id) {
		return dao.findById(id);
	}
	
	@PostMapping("/employees")
	public String addEmployee(@RequestBody Employee e) {
		if(dao.existsById(e.getEid()))
			return "Already Exist";
		dao.save(e);
		return "Successfully added";
	}
	
	@PutMapping("/employees")
	public String updateEmployee(@RequestBody Employee e) {
		if(dao.existsById(e.getEid())) {
			dao.save(e);
		return "Successfully updated";
		}
		return "Sorry, No record with given id";

	}
	
	
	@DeleteMapping("/employees/{uid}")
	public String removeEmployee(@PathVariable("uid") int id) {
		if(dao.existsById(id)) {
			dao.deleteById(id);
		return "Successfully deleted";
		}
		return "Sorry, No record with given id to remove";

	}
	
	
	@GetMapping("/employees/roles")
	public List<Employee> getEmployeeBasedonDesignation(String desig){
		return dao.getByDesignation(desig);
	}
	
	@GetMapping("/employees/above")
	public List<Employee> getEmployeeAbove(int salary){
		return dao.findBySalaryGreaterThan(salary);
	}
	
	@GetMapping("/employees/below")
	public List<Employee> getEmployeeBelow(int age){
		return dao.getByAge(age);
	}
	
	@GetMapping("/employees/custom")
	public List<Employee> customMethod(String desig){
		return dao.myCustomQuery(desig);
	}
	//adding line for git update
}
