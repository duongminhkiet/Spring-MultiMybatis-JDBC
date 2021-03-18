package com.zmk.spring.test.rd.multidb.mybatis2.test2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zmk.spring.test.rd.multidb.mybatis2.test2.model.Employee2;

@Mapper
public interface Employee2MyBatisRepository {
	@Select("select * from employees2")
    public List < Employee2 > findAll();

    @Select("SELECT * FROM employees2 WHERE id = #{id}")
    public Employee2 findById(long id);

    @Delete("DELETE FROM employees2 WHERE id = #{id}")
    public int deleteById(long id);

    @Insert("SET IDENTITY_INSERT employees2 ON; "
    		+ "INSERT INTO employees2(id, first_name, last_name,email_address) " +
        " VALUES (#{id}, #{firstName}, #{lastName}, #{emailId}); "
        + "SET IDENTITY_INSERT employees2 OFF;")
    public int insert(Employee2 employee);
    
    
    @Insert("INSERT INTO employees2(first_name, last_name,email_address) " +
        " VALUES (#{firstName}, #{lastName}, #{emailId}); ")
    public int insertWithoutId(Employee2 employee);

    @Update("Update employees2 set first_name=#{firstName}, " +
        " last_name=#{lastName}, email_address=#{emailId} where id=#{id}")
    public int update(Employee2 employee);
}
