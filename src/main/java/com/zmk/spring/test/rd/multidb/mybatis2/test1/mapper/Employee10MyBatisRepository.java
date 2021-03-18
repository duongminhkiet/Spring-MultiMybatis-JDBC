package com.zmk.spring.test.rd.multidb.mybatis2.test1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zmk.spring.test.rd.multidb.mybatis2.test1.model.Employee10;

@Mapper
public interface Employee10MyBatisRepository {
	@Select("select * from employees10")
    public List < Employee10 > findAll();

    @Select("SELECT * FROM employees10 WHERE id = #{id}")
    public Employee10 findById(long id);

    @Delete("DELETE FROM employees10 WHERE id = #{id}")
    public int deleteById(long id);

    @Insert("SET IDENTITY_INSERT employees10 ON; "
    		+ "INSERT INTO employees10(id, first_name, last_name,email_address) " +
        " VALUES (#{id}, #{firstName}, #{lastName}, #{emailId}); "
        + "SET IDENTITY_INSERT employees10 OFF;")
    public int insert(Employee10 employee);
    
    
    @Insert("INSERT INTO employees10(first_name, last_name,email_address) " +
        " VALUES (#{firstName}, #{lastName}, #{emailId}); ")
    public int insertWithoutId(Employee10 employee);

    @Update("Update employees10 set first_name=#{firstName}, " +
        " last_name=#{lastName}, email_address=#{emailId} where id=#{id}")
    public int update(Employee10 employee);
}
