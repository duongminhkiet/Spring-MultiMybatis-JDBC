package com.zmk.spring.test.rd.multidb.mybatis2;

import javax.inject.Named;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zmk.spring.test.rd.multidb.mybatis2.test1.mapper.Employee10MyBatisRepository;
import com.zmk.spring.test.rd.multidb.mybatis2.test1.mapper.Employee1MyBatisRepository;
import com.zmk.spring.test.rd.multidb.mybatis2.test2.mapper.Employee2MyBatisRepository;

@Configuration
public class MyBatisConfiguration {
	private static final String EMPLOYEE1_SESSION_FACTORY = "employee1SessionFactory";
	private static final String EMPLOYEE2_SESSION_FACTORY = "employee2SessionFactory";

	@Bean(name = "sqlServer1TransactionMybatis")
	@Autowired
	@Primary
	DataSourceTransactionManager tm1(@Named(ConfigDB12.DATASOURCE1) final DataSource datasource) {
		DataSourceTransactionManager txm = new DataSourceTransactionManager(datasource);
		return txm;
	}
	@Bean(name = "sqlServer2TransactionMybatis")
	@Autowired
	@Primary
	DataSourceTransactionManager tm2(@Named(ConfigDB12.DATASOURCE2) final DataSource datasource) {
		DataSourceTransactionManager txm = new DataSourceTransactionManager(datasource);
		return txm;
	}

	@Bean(name = EMPLOYEE1_SESSION_FACTORY, destroyMethod = "")
	@Primary
	public SqlSessionFactoryBean sqlSessionFactory(@Named(ConfigDB12.DATASOURCE1) final DataSource oneDataSource)
			throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(oneDataSource);
		SqlSessionFactory sqlSessionFactory;
		sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(Employee1MyBatisRepository.class);
		sqlSessionFactory.getConfiguration().addMapper(Employee10MyBatisRepository.class);
		// Various other SqlSessionFactory settings
		return sqlSessionFactoryBean;
	}

	@Bean
	public MapperFactoryBean<Employee1MyBatisRepository> etrMapper(
			@Named(EMPLOYEE1_SESSION_FACTORY) final SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<Employee1MyBatisRepository> factoryBean = new MapperFactoryBean<>(
				Employee1MyBatisRepository.class);
		factoryBean.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
		return factoryBean;
	}

	@Bean
	public MapperFactoryBean<Employee10MyBatisRepository> etrMapper10(
			@Named(EMPLOYEE1_SESSION_FACTORY) final SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<Employee10MyBatisRepository> factoryBean = new MapperFactoryBean<>(
				Employee10MyBatisRepository.class);
		factoryBean.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
		return factoryBean;
	}

	@Bean(name = EMPLOYEE2_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean censoSqlSessionFactory(
			@Named(ConfigDB12.DATASOURCE2) final DataSource anotherDataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(anotherDataSource);
		final SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(Employee2MyBatisRepository.class);
		// Various other SqlSessionFactory settings
		return sqlSessionFactoryBean;
	}

	@Bean
	public MapperFactoryBean<Employee2MyBatisRepository> dbMapper(
			@Named(EMPLOYEE2_SESSION_FACTORY) final SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<Employee2MyBatisRepository> factoryBean = new MapperFactoryBean<>(
				Employee2MyBatisRepository.class);
		factoryBean.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
		return factoryBean;
	}

}
