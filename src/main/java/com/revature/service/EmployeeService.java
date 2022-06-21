package com.revature.service;

import java.util.List;
import java.util.Optional;

import com.reavature.models.Employee;
import com.revature.dao.EmployeeDao;

public class EmployeeService {
	private EmployeeDao edao;
	
	/*
	 * dependency injection via constructor injection
	 * constructor injection is a way to ensure that the employeeservice object always has an emloyeedao object
	 */
	public EmployeeService(EmployeeDao edao) {
		this.edao = edao;
	}
	
	public Employee confirmLogin(String username, String password){
		Optional<Employee>  possibleEmp = edao.findAll().stream()
				.filter(e -> (e.getUsername().equals(username) && e.getPassword().equals(password))).findFirst();
		
		//if emp is preset rutnr int, other wise return empty emp with id of 0
		
		return(possibleEmp.isPresent() ? possibleEmp.get() : new Employee());
		//could be optimized with a custom exception
		
	}
	
	public List<Employee> getAll(){
		return edao.findAll();
	}
}
