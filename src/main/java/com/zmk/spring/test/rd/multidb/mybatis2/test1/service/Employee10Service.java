package com.zmk.spring.test.rd.multidb.mybatis2.test1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zmk.spring.test.rd.multidb.mybatis2.test1.mapper.Employee10MyBatisRepository;
import com.zmk.spring.test.rd.multidb.mybatis2.test1.model.Employee10;

@Service
public class Employee10Service {

	@Autowired
	private Employee10MyBatisRepository employeeRepository1;
	
	public List<Employee10> getAll() {
		return employeeRepository1.findAll();
	}
	public int deleteEmployee10(Long id) {
		return employeeRepository1.deleteById(id);
	}
	public int insertWithId(Employee10 em) {
		return employeeRepository1.insert(em);
	}
	public int insertWithoutId(Employee10 em) {
		return employeeRepository1.insertWithoutId(em);
	}
	public int update(Employee10 em) {
		return employeeRepository1.update(em);
	}
}
