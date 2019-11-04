package com.happiness.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// add a reference to our security data source
	
	@Autowired
	private DataSource securityDataSource;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		User.UserBuilder users = User.withDefaultPasswordEncoder();
		auth.inMemoryAuthentication()
                .withUser(users.username("Marat").password("Password123!").roles("USER"))
                .withUser(users.username("Seth").password("Password123!").roles("USER"))
                .withUser((users.username("Admin").password("Password123!").roles("ADMIN")));
	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		http.authorizeRequests()
//			.antMatchers("/").hasRole("EMPLOYEE")
//			.antMatchers("/leaders/**").hasRole("MANAGER")
//			.antMatchers("/systems/**").hasRole("ADMIN")
//			.and()
//			.formLogin()
//				.loginPage("/showMyLoginPage")
//				.loginProcessingUrl("/authenticateTheUser")
//				.permitAll()
//			.and()
//			.logout().permitAll()
//			.and()
//			.exceptionHandling().accessDeniedPage("/access-denied");
//
//	}
//
//	@Bean
//	public UserDetailsManager userDetailsManager() {
//
//		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
//
//		jdbcUserDetailsManager.setDataSource(securityDataSource);
//
//		return jdbcUserDetailsManager;
//	}
	
}






