package com.gio.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc 
@ComponentScan(basePackages="com.gio.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig {

	// variable to hold properties
	@Autowired // to PropertySource
	private Environment env;
	
	// logger for diagnostics
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// define a bean for a ViewResolver
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		// tell spring where to look for views
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	// bean for security datasource
	@Bean
	public DataSource securityDataSource() {
		
		// connection pool
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		
		// jdbc driver class
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}
		
		// log connection props (make sure reading right property
		logger.info(">>> jdbc.url=" + env.getProperty("jdbc.url"));
		logger.info(">>> jdbc.user=" + env.getProperty("jdbc.user"));
		
		// set DB connection props
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));
		
		// set connection pool props
		securityDataSource.setInitialPoolSize(
				this.getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(
				this.getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(
				this.getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(
				this.getIntProperty("connection.pool.maxIdleTime"));

		
		return securityDataSource;
	}
	
	// helper method
	// read environment proper and convert to int
	private int getIntProperty(String propName ) {
		return Integer.parseInt(env.getProperty(propName));
	}
	
}
