package com.zmk.spring.test.rd.multidb.mybatis2.test2.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import com.zmk.spring.test.rd.multidb.mybatis2.test2.mapper.Employee2MyBatisRepository;
import com.zmk.spring.test.rd.multidb.mybatis2.test2.model.Employee2;

@Service
public class Employee2Service {
	@Autowired
	private Employee2MyBatisRepository employeeRepository2;
	
	public List<Employee2> getAll() {
		return employeeRepository2.findAll();
	}
	public int deleteEmployee2(Long id) {
		return employeeRepository2.deleteById(id);
	}
	public int insertWithId(Employee2 em) {
		return employeeRepository2.insert(em);
	}
	public int insertWithoutId(Employee2 em) {
		return employeeRepository2.insertWithoutId(em);
	}
	public int update(Employee2 em) {
		return employeeRepository2.update(em);
	}
	
	@Transactional("sqlServer2TransactionMybatis")
    public void insertListEmployee2NoId_OK() {
		List<Employee2> employees = Arrays.asList(
				new Employee2("2emailx1@email.com", "firstname1", "lastname1"),
				new Employee2("2emailx2@email.com", "firstname2", "lastname2"),
				new Employee2("2emailx3@email.com", "firstname3", "lastname3"),
				new Employee2("2emailx4@email.com", "firstname4", "lastname4"));

		employees.forEach(employee -> {
			employeeRepository2.insertWithoutId(employee);
		});
    }
	@Transactional("sqlServer2TransactionMybatis")
    public void insertListEmployee2WithId_OK() {
		List<Employee2> employees = Arrays.asList(
				new Employee2(2001L, "email1@email.com", "firstname1", "lastname1"),
				new Employee2(2002L, "email2@email.com", "firstname2", "lastname2"),
				new Employee2(2003L, "email3@email.com", "firstname3", "lastname3"),
				new Employee2(2004L, "email4@email.com", "firstname4", "lastname4"));

		employees.forEach(employee -> {
			employeeRepository2.insert(employee);
		});
    }
	@Transactional("sqlServer2TransactionMybatis")
    public void insertListEmployee2WithOutId_Fail_TS() {
		List<Employee2> employees = Arrays.asList(
				new Employee2("2emailx1@email.com", "firstname1", "lastname1"),
				new Employee2("2emailx2@email.com", "firstname2", "lastname2"),
				new Employee2("2emailx3@email.com", "firstname3", "lastname3"),
				new Employee2("2emailx4@email.com", null, "lastname4"));

		employees.forEach(employee -> {
			employeeRepository2.insertWithoutId(employee);
		});
    }
    public void insertListEmployee2WithOutId_Fail_NoTS() {
		List<Employee2> employees = Arrays.asList(
				new Employee2("2emailx1@email.com", "firstname1", "lastname1"),
				new Employee2("2emailx2@email.com", "firstname2", "lastname2"),
				new Employee2("2emailx3@email.com", "firstname3", "lastname3"),
				new Employee2("2emailx4@email.com", null, "lastname4"));

		employees.forEach(employee -> {
			employeeRepository2.insertWithoutId(employee);
		});
    }
}
