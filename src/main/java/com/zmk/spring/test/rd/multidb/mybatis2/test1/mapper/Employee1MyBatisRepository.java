package com.zmk.spring.test.rd.multidb.mybatis2.test1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zmk.spring.test.rd.multidb.mybatis2.test1.model.Employee1;

@Mapper
public interface Employee1MyBatisRepository {
	@Select("select * from employees1")
    public List < Employee1 > findAll();

    @Select("SELECT * FROM employees1 WHERE id = #{id}")
    public Employee1 findById(long id);

    @Delete("DELETE FROM employees1 WHERE id = #{id}")
    public int deleteById(long id);

    @Insert("SET IDENTITY_INSERT employees1 ON; "
    		+ "INSERT INTO employees1(id, first_name, last_name,email_address) " +
        " VALUES (#{id}, #{firstName}, #{lastName}, #{emailId}); "
        + "SET IDENTITY_INSERT employees1 OFF;")
    public int insert(Employee1 employee);
    
    
    @Insert("INSERT INTO employees1(first_name, last_name,email_address) " +
        " VALUES (#{firstName}, #{lastName}, #{emailId}); ")
    public int insertWithoutId(Employee1 employee);

    @Update("Update employees1 set first_name=#{firstName}, " +
        " last_name=#{lastName}, email_address=#{emailId} where id=#{id}")
    public int update(Employee1 employee);
}
