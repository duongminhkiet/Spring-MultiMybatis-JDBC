package com.zmk.spring.test.rd.multidb.mybatis2;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ConfigDB12 {
	public static final String DATASOURCE1 = "sqlServerMybatis1";
	public static final String DATASOURCE2 = "sqlServerMybatis2";

	@Bean(name = DATASOURCE1)
	@ConfigurationProperties(prefix = "spring.datasource1")
	public DataSource sqlServerMybatis1DS() {
		return DataSourceBuilder.create().build();
	}
	@Bean(name = DATASOURCE2)
	@ConfigurationProperties(prefix = "spring.datasource2")
//	@Primary
	public DataSource sqlServerMybatis2DS() {
		return  DataSourceBuilder.create().build();
	}
	
	@Bean(name = "sqlServer1JdbcMybatisTemplate")
	public JdbcTemplate sqlServer1JdbcMybatisTemplate(@Qualifier(DATASOURCE1) DataSource sqlServer1) {
		return new JdbcTemplate(sqlServer1);
	}
}