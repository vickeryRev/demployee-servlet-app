package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.reavature.models.Employee;
import com.revature.dao.EmployeeDao;
import com.revature.service.EmployeeService;

public class EmployeeServiceTests {

	private EmployeeService eserv;
	private EmployeeDao mockdao;
	
	@Before
	public void setup() {
		mockdao = mock(EmployeeDao.class);
		eserv = new EmployeeService(mockdao);
	}
	
	@After
	public void teardown() {
		mockdao = null;
		eserv = null;
	}
	
	
	@Test
	public void testConfirmLogin_success() {
		//create fake emp list - dummy data fed to mockito
		Employee e1 = new Employee(10, "hiyori","tamura","hiyori","tamura");
		Employee e2 = new Employee(15, "hiro","tamura","hiro","tamura");
		List<Employee> emps = new ArrayList<>();
		emps.add(e1);
		emps.add(e2);
		//set up the dao behaviors on findall method
		when(mockdao.findAll()).thenReturn(emps);
		
		//capture the acutally output from the method
		Employee actually = eserv.confirmLogin("hiyori", "tamura");
		//capture the expected output of the method
		Employee expected = e1;
		//assert equality
		
		assertEquals(expected , actually);
	}
	
	@Test
	public void testConfirmLogin_fail() {
		Employee e1 = new Employee(10, "hiyori","tamura","hiyori","tamura");
		Employee e2 = new Employee(15, "hiro","tamura","hiro","tamura");
		List<Employee> emps = new ArrayList<>();
		emps.add(e1);
		emps.add(e2);
		
		when(mockdao.findAll()).thenReturn(emps);
		
		Employee actually = eserv.confirmLogin("allen", "wake");
		Employee expected = new Employee();
		assertEquals(expected , actually);
		
	}
}
