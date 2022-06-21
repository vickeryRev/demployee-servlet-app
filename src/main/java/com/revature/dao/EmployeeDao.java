package com.revature.dao;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.Transaction;

import com.reavature.models.Employee;
import com.revature.util.HibernateUtil;

public class EmployeeDao {
	//crud methods
	//create (maybe register in the service layer)
	public int insert(Employee e) {
		//grab session object
		Session ses = HibernateUtil.getSession();
//		beging tx
		Transaction tx = ses.beginTransaction();
		//cap the pk
		int pk = (int) ses.save(e);
		//return the pk
		return pk;
	}
	//read all
	public List<Employee> findAll(){
		//grab sessions
		Session ses = HibernateUtil.getSession();
		
		//make an HQL statement - odd mix of OOP and native sql
		List<Employee> emps =  ses.createQuery("from Employee", Employee.class).list();
		
		//return said list
		return emps;
		
	}
	
	//del
	
	public boolean delete(int id) {
		
		return false;
	}
	
	//update
	
	public boolean update(Employee e) {
		return false;
	}
}
