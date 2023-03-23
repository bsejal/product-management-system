package com.PMS.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService service;
	
	@Bean
	PasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(service).passwordEncoder(encode());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()
		.antMatchers("/index").hasAnyRole("ADMIN")
		.antMatchers("/index1").hasAnyRole("ADMIN")
		.antMatchers("/register").permitAll()
		.antMatchers("/product").hasAnyRole("ADMIN")
		.antMatchers("/delete/{id}").permitAll()
		//.antMatchers("/registerAdmin").hasAnyRole("ADMIN")
//		.antMatchers("/update/{id}").permitAll()
//		.antMatchers("/updateUsers/{id}").permitAll()
		
		
		//.antMatchers("/getUsers").hasAnyRole("ADMIN")
//		.antMatchers("/adduser").permitAll()
//		//.antMatchers("/updateuser").permitAll()
//		.antMatchers("/deleteuser/{id}").permitAll()
//		.antMatchers("/addadmin").permitAll()
//		.antMatchers("/deleteadmin/{id}").permitAll()
		.and()
		.formLogin();
//		.loginPage("/login").permitAll().and().logout().logoutUrl("/logout").logoutSuccessUrl("/home")
//		;
//		
//		http.exceptionHandling().accessDeniedPage("/access");
//		httpBasic();

	}
}
