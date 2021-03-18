package com.zmk.spring.test.rd.multidb.mybatis2.test1.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zmk.spring.test.rd.multidb.mybatis2.test1.model.Employee1;

@Repository
public class Employee1JdbcImplRepository implements Employee1JdbcRepository{
	@Autowired
    @Qualifier("sqlServer1JdbcMybatisTemplate")
    private JdbcTemplate jdbcTemplate;
	@Override
	public int count() {
		return jdbcTemplate
                .queryForObject("select count(*) from employees1", Integer.class);
	}

	@Override
	public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete employees1 where id = ?",
                id);
	}

	@Override
	public List<Employee1> findAll() {
        return jdbcTemplate.query(
                "select * from employees1",
                (rs, rowNum) ->
                        new Employee1(
                        		rs.getLong("id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("email_address")
                        )
        );
	}

	@Override
	public int save(Employee1 book) {
        return jdbcTemplate.update(
                "insert into employees1 (first_name, last_name,email_address) values(?,?,?)",
                book.getFirstName(), book.getLastName(), book.getEmailId());
	}
}
