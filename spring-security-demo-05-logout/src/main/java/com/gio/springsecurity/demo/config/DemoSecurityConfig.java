package com.gio.springsecurity.demo.config;

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

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// add our users for in memory authentication
		
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication()
			.withUser(users.username("keely").password("test123").roles("EMPLOYEE"))
			.withUser(users.username("simone").password("test123").roles("MANAGER"))
			.withUser(users.username("salinger").password("test123").roles("ADMIN"));

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// configure security web paths in app, login/logout
		http.authorizeRequests() // restrict access based on HttpServletRequest
			.anyRequest().authenticated() // any request to the app must be auth
			.and()
			.formLogin() //customize the form login process
				.loginPage("/showMyLoginPage") // custom login page
				.loginProcessingUrl("/authenticateTheUser") // submit POST data to URL
				.permitAll() // everyone can see login page
				.and()
				.logout().permitAll(); // spring takes care of logout control
		
	}

	
	
}
