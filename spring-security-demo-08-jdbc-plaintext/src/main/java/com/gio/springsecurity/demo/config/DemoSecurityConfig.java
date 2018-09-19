package com.gio.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	// reference to our security data source
	@Autowired
	private DataSource securityDataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// jdbc authentication
		
		auth.jdbcAuthentication().dataSource(securityDataSource);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// configure security web paths in app, login/logout
		http.authorizeRequests() // restrict access based on HttpServletRequest
		//	.anyRequest().authenticated() // any request to the app must be auth (all accesses)
			.antMatchers("/").hasRole("EMPLOYEE") // specific accesses
			.antMatchers("/leaders/**").hasRole("MANAGER")
			.antMatchers("/systems/**").hasRole("ADMIN")
			.and()
			.formLogin() //customize the form login process
				.loginPage("/showMyLoginPage") // custom login page
				.loginProcessingUrl("/authenticateTheUser") // submit POST data to URL
				.permitAll() // everyone can see login page
				.and()
				.logout().permitAll() // spring takes care of logout control
				.and()
				.exceptionHandling().accessDeniedPage("/access-denied"); // used for unauth page
		
	}

	
	
}
