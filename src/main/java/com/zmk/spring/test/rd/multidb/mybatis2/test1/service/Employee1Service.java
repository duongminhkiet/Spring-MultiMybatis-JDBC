package com.zmk.spring.test.rd.multidb.mybatis2.test1.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmk.spring.test.rd.multidb.mybatis2.test1.jdbc.Employee1JdbcRepository;
import com.zmk.spring.test.rd.multidb.mybatis2.test1.mapper.Employee10MyBatisRepository;
import com.zmk.spring.test.rd.multidb.mybatis2.test1.mapper.Employee1MyBatisRepository;
import com.zmk.spring.test.rd.multidb.mybatis2.test1.model.Employee1;
import com.zmk.spring.test.rd.multidb.mybatis2.test1.model.Employee10;

@Service
public class Employee1Service {

	@Autowired
	private Employee1MyBatisRepository employeeRepository1;
	@Autowired
	private Employee10MyBatisRepository employeeRepository10;
	@Autowired
	private Employee1JdbcRepository employee1JdbcRepository;
	
	public List<Employee1> getAll() {
		return employeeRepository1.findAll();
	}
	public int deleteEmployee1(Long id) {
		return employeeRepository1.deleteById(id);
	}
	public int insertWithId(Employee1 em) {
		return employeeRepository1.insert(em);
	}
	public int insertWithoutId(Employee1 em) {
		return employeeRepository1.insertWithoutId(em);
	}
	public int update(Employee1 em) {
		return employeeRepository1.update(em);
	}
	@Transactional("sqlServer1TransactionMybatis")//=> fail all
	public void insertListEmployee1NoId_3Ok_1fail_TS() {
		List<Employee1> employees = Arrays.asList(
				new Employee1("1emailx1@email.com", "firstname1", "lastname1"),
				new Employee1("1emailx2@email.com", "firstname2", "lastname2"),
				new Employee1("1emailx3@email.com", "firstname3", "lastname3"),
				new Employee1("1emailx4@email.com", null, "lastname4"));

		employees.forEach(employee -> {
			employeeRepository1.insertWithoutId(employee);
		});
    }
	public void insertListEmployee1NoId_3Ok_1fail_NoTS() {//ok 3, fail 1
		List<Employee1> employees = Arrays.asList(
				new Employee1("1emailx1@email.com", "firstname1", "lastname1"),
				new Employee1("1emailx2@email.com", "firstname2", "lastname2"),
				new Employee1("1emailx3@email.com", "firstname3", "lastname3"),
				new Employee1("1emailx4@email.com", null, "lastname4"));

		employees.forEach(employee -> {
			employeeRepository1.insertWithoutId(employee);
		});
    }
	@Transactional("sqlServer1TransactionMybatis")
	public void insertListEmployee1NoId_OK_10Fail_TS() {// All employee1 correct, all employee10 incorrect
		List<Employee1> employees = Arrays.asList(
				new Employee1("1emailx1@email.com", "firstname1", "lastname1"),
				new Employee1("1emailx2@email.com", "firstname2", "lastname2"),
				new Employee1("1emailx3@email.com", "firstname3", "lastname3"),
				new Employee1("1emailx4@email.com", "firstname4", "lastname4"));

		employees.forEach(employee -> {
			employeeRepository1.insertWithoutId(employee);
		});
		List<Employee10> employees10 = Arrays.asList(
				new Employee10("10emailx1@email.com", "firstname1", "lastname1"),
				new Employee10("10emailx2@email.com", "firstname2", "lastname2"),
				new Employee10("10emailx3@email.com", "firstname3", "lastname3"),
				new Employee10("10emailx4@email.com", null, "lastname4"));

		employees10.forEach(employee -> {
			employeeRepository10.insertWithoutId(employee);
		});
    }
	public void insertListEmployee1NoId_OK_10Fail_NoTS() {// All employee1 correct, all employee10 incorrect
		List<Employee1> employees = Arrays.asList(
				new Employee1("1emailx1@email.com", "firstname1", "lastname1"),
				new Employee1("1emailx2@email.com", "firstname2", "lastname2"),
				new Employee1("1emailx3@email.com", "firstname3", "lastname3"),
				new Employee1("1emailx4@email.com", "firstname4", "lastname4"));

		employees.forEach(employee -> {
			employeeRepository1.insertWithoutId(employee);
		});
		List<Employee10> employees10 = Arrays.asList(
				new Employee10("10emailx1@email.com", "firstname1", "lastname1"),
				new Employee10("10emailx2@email.com", "firstname2", "lastname2"),
				new Employee10("10emailx3@email.com", "firstname3", "lastname3"),
				new Employee10("10emailx4@email.com", null, "lastname4"));

		employees10.forEach(employee -> {
			employeeRepository10.insertWithoutId(employee);
		});
    }
	//JDBC
	public int count() {
		return employee1JdbcRepository.count();
	}
	public List<Employee1> listAllJdbc(){
		return employee1JdbcRepository.findAll();
	}
	public int saveJdbc() {
		return employee1JdbcRepository.save(new Employee1("1emailx1@email.com", "firstname1", "lastname1"));
	}
	
	@Transactional("sqlServer1TransactionMybatis")
	public void saveJdbc_OK_TS() {
		 employee1JdbcRepository.save(new Employee1("1jdbcemailx1@email.com", "firstname1", "lastname1"));
		 employee1JdbcRepository.save(new Employee1("1jdbcemailx2@email.com", "firstname1", "lastname1"));
		 employee1JdbcRepository.save(new Employee1("1jdbcemailx3@email.com", "firstname1", "lastname1"));
	}
	@Transactional("sqlServer1TransactionMybatis")
	public void saveJdbc_Fail_TS() {
		 employee1JdbcRepository.save(new Employee1("1TSjdbcemailx1@email.com", "firstname1", "lastname1"));
		 employee1JdbcRepository.save(new Employee1("1TSjdbcemailx2@email.com", "firstname1", "lastname1"));
		 employee1JdbcRepository.save(new Employee1("1TSjdbcemailx3@email.com", null, "lastname1"));
	}
}
