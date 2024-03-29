/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.ibs.proyecto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * The Class SpringSecurityConfig.
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsService userDetailservice;

	/**
	 * Configure.
	 *
	 * @param http the http
	 * @throws Exception the exception
	 */ 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers().permitAll()
				.antMatchers("/static/**", "/chart-pie-demo.js/**","/Chart.min.js/**","/demo/**","/vendor/**","/chart-area-demo.js/**","/chart-bar-demo.js/**","/datatables-demo.js/**","/js/**").permitAll()
				.antMatchers("/").hasAnyRole("Administrador")
				//.antMatchers("/compra/**").hasAnyRole("Facturador")
				.anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll()
				.and()  
	            .logout()
	            .logoutSuccessUrl("/logout")
	            .invalidateHttpSession(true)
	            .clearAuthentication(true)
	            .permitAll();
	}

	/**
	 * Password encoder.
	 *
	 * @return the b crypt password encoder
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * Password encoder.
	 *
	 * @return the b crypt password encoder
	 */
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailservice);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	/**
	 * Configure global.
	 *
	 * @param builder the builder
	 * @throws Exception the exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		// configuración global de los permisos
		builder.userDetailsService(userDetailservice)
		       .passwordEncoder(passwordEncoder)
		       .and()
			   .authenticationProvider(authenticationProvider());
	}

}