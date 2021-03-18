package com.zmk.spring.test.rd.multidb.mybatis2;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zmk.spring.test.rd.multidb.mybatis2.test1.jdbc.Employee1JdbcRepository;
import com.zmk.spring.test.rd.multidb.mybatis2.test1.model.Employee1;
import com.zmk.spring.test.rd.multidb.mybatis2.test1.model.Employee10;
import com.zmk.spring.test.rd.multidb.mybatis2.test1.service.Employee10Service;
import com.zmk.spring.test.rd.multidb.mybatis2.test1.service.Employee1Service;
import com.zmk.spring.test.rd.multidb.mybatis2.test2.model.Employee2;
import com.zmk.spring.test.rd.multidb.mybatis2.test2.service.Employee2Service;

@RestController
public class TestController {
//	@Autowired
//	private Employee1JdbcRepository employee1JdbcRepository;
	@Autowired
	private Employee1Service employee1Service;
	@Autowired
	private Employee10Service employee10Service;
	@Autowired
	private Employee2Service employee2Service;
	//DB1
	@GetMapping("/api/db1/insertListEmployee1NoId_OK")
    public void insertListEmployee1NoId_OK() {
		List<Employee1> employees = Arrays.asList(
				new Employee1("1emailx1@email.com", "firstname1", "lastname1"),
				new Employee1("1emailx2@email.com", "firstname2", "lastname2"),
				new Employee1("1emailx3@email.com", "firstname3", "lastname3"),
				new Employee1("1emailx4@email.com", "firstname4", "lastname4"));

		employees.forEach(employee -> {
			employee1Service.insertWithoutId(employee);
		});
    }
	@GetMapping("/api/db1/insertListEmployee1WithId_OK")
    public void insertListEmployee1WithId_OK() {
		List<Employee1> employees = Arrays.asList(
				new Employee1(1001L, "email1@email.com", "firstname1", "lastname1"),
				new Employee1(1002L, "email2@email.com", "firstname2", "lastname2"),
				new Employee1(1003L, "email3@email.com", "firstname3", "lastname3"),
				new Employee1(1004L, "email4@email.com", "firstname4", "lastname4"));

		employees.forEach(employee -> {
			employee1Service.insertWithId(employee);
		});
    }
	//DB10
		@GetMapping("/api/db10/insertListEmployee10NoId_OK")
	    public void insertListEmployee10NoId_OK() {
			List<Employee10> employees = Arrays.asList(
					new Employee10("10emailx1@email.com", "firstname1", "lastname1"),
					new Employee10("10emailx2@email.com", "firstname2", "lastname2"),
					new Employee10("10emailx3@email.com", "firstname3", "lastname3"),
					new Employee10("10emailx4@email.com", "firstname4", "lastname4"));

			employees.forEach(employee -> {
				employee10Service.insertWithoutId(employee);
			});
	    }
		@GetMapping("/api/db10/insertListEmployee10WithId_OK")
	    public void insertListEmployee10WithId_OK() {
			List<Employee10> employees = Arrays.asList(
					new Employee10(1001L, "10email1@email.com", "firstname1", "lastname1"),
					new Employee10(1002L, "10email2@email.com", "firstname2", "lastname2"),
					new Employee10(1003L, "10email3@email.com", "firstname3", "lastname3"),
					new Employee10(1004L, "10email4@email.com", "firstname4", "lastname4"));

			employees.forEach(employee -> {
				employee10Service.insertWithId(employee);
			});
	    }
		//// All employee1 correct, all employee10 incorrect => save all 1, save 3 10
		@GetMapping("/api/db101/insertListEmployee1NoId_OK_10Fail_NoTS")
	    public void insertListEmployee1NoId_OK_10Fail_NoTS() {
			employee1Service.insertListEmployee1NoId_OK_10Fail_NoTS();
	    }
		//// All employee1 correct, all employee10 incorrect => no save all
		@GetMapping("/api/db101/insertListEmployee1NoId_OK_10Fail_TS")
	    public void insertListEmployee1NoId_OK_10Fail_TS() {
			employee1Service.insertListEmployee1NoId_OK_10Fail_TS();
	    }
		////=> fail all 1
		@GetMapping("/api/db1/insertListEmployee1NoId_3Ok_1fail_TS")
	    public void insertListEmployee1NoId_3Ok_1fail_TS() {
			employee1Service.insertListEmployee1NoId_3Ok_1fail_TS();
	    }
		//save 3, fail 1
		@GetMapping("/api/db1/insertListEmployee1NoId_3Ok_1fail_NoTS")
	    public void insertListEmployee1NoId_3Ok_1fail_NoTS() {
			employee1Service.insertListEmployee1NoId_3Ok_1fail_NoTS();
	    }
		
	// DB2
	@GetMapping("/api/db2/insertListEmployee2NoId_OK")
    public void insertListEmployee2NoId_OK() {
		List<Employee2> employees = Arrays.asList(
				new Employee2("2emailx1@email.com", "firstname1", "lastname1"),
				new Employee2("2emailx2@email.com", "firstname2", "lastname2"),
				new Employee2("2emailx3@email.com", "firstname3", "lastname3"),
				new Employee2("2emailx4@email.com", "firstname4", "lastname4"));

		employees.forEach(employee -> {
			employee2Service.insertWithoutId(employee);
		});
    }
	@GetMapping("/api/db2/insertListEmployee2WithId_OK")
    public void insertListEmployee2WithId_OK() {
		List<Employee2> employees = Arrays.asList(
				new Employee2(2001L, "email1@email.com", "firstname1", "lastname1"),
				new Employee2(2002L, "email2@email.com", "firstname2", "lastname2"),
				new Employee2(2003L, "email3@email.com", "firstname3", "lastname3"),
				new Employee2(2004L, "email4@email.com", "firstname4", "lastname4"));

		employees.forEach(employee -> {
			employee2Service.insertWithId(employee);
		});
    }
	
	//Fail all
	@GetMapping("/api/db2/insertListEmployee2WithOutId_Fail_TS")
    public void insertListEmployee2WithOutId_Fail_TS() {
		employee2Service.insertListEmployee2WithOutId_Fail_TS();
    }
	//Fail 1, ok 3
	@GetMapping("/api/db2/insertListEmployee2WithOutId_Fail_NoTS")
    public void insertListEmployee2WithOutId_Fail_NoTS() {
		employee2Service.insertListEmployee2WithOutId_Fail_NoTS();
    }
	// JBDC 
    @GetMapping("/api/db1/countJdbc")
    public int countJdbc() {
    	return employee1Service.count();
    }
    @GetMapping("/api/db1/listAllJdbc")
    public List<Employee1> listAllJdbc() {
    	return employee1Service.listAllJdbc();
    }
    @GetMapping("/api/db1/saveJdbc")
    public int saveJdbc() {
    	return employee1Service.saveJdbc();
    }
    @GetMapping("/api/db1/saveJdbc_OK_TS")
    public void saveJdbc_OK_TS() {
    	 employee1Service.saveJdbc_OK_TS();
    }
    @GetMapping("/api/db1/saveJdbc_Fail_TS")
    public void saveJdbc_Fail_TS() {
    	 employee1Service.saveJdbc_Fail_TS();
    }
}
