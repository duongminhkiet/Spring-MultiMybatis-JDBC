package com.zmk.spring.test.rd.multidb.mybatis2.test1.jdbc;

import java.util.List;

import com.zmk.spring.test.rd.multidb.mybatis2.test1.model.Employee1;

public interface Employee1JdbcRepository {
	int count();
	int deleteById(Long id);
	int save(Employee1 book);
    List<Employee1> findAll();
}
