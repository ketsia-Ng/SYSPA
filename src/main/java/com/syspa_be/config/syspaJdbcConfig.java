package com.syspa_be.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class syspaJdbcConfig {
@Autowired
private Environment evt;

@Bean
public DataSource datasource() {	
DriverManagerDataSource ds= new DriverManagerDataSource();
ds.setUrl(evt.getProperty("spring.datasource.url"));
ds.setUsername(evt.getProperty("spring.datasource.username"));
ds.setPassword(evt.getProperty("spring.datasource.password"));
ds.setDriverClassName(evt.getProperty("spring.datasource.driverClassName"));
return  ds;

}
@Bean
public JdbcTemplate jdbcTemplate(@Qualifier("datasource")DataSource datasource) {
return new JdbcTemplate((javax.sql.DataSource) datasource);
}


}
