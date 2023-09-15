package com.project.tez.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import com.project.tez.security.JwtAuthenticationEntryPoint;
import com.project.tez.security.JwtAuthenticationFilter;
import com.project.tez.services.UserDetailsServiceImpl;




@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final static Logger log = LoggerFactory.getLogger(SecurityConfig.class);
	private UserDetailsServiceImpl userDetailServiceImpl;

	private JwtAuthenticationEntryPoint handler;
	
	public SecurityConfig(UserDetailsServiceImpl userDetailServiceImpl, JwtAuthenticationEntryPoint handler) {
		log.info("SecurityConfig constructor girdi");
		this.userDetailServiceImpl = userDetailServiceImpl;
		this.handler = handler;
		log.info("SecurityConfig constructor çıktı");
	}
	
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		log.info("jwtAuthenticationFilter() girdi");
		return new JwtAuthenticationFilter();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		log.info("passwordEncoder() girdi");
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		log.info("authenticationManager(AuthenticationConfiguration) girdi");
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("configure(AuthenticationManagerBuilder) girdi");
		auth.userDetailsService(userDetailServiceImpl).passwordEncoder(passwordEncoder());
		log.info("configure(AuthenticationManagerBuilder) çıktı");
	}
	
	@Bean
	public CorsFilter corsFilter() {
		log.info("corsFilter() girdi");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("HEAD");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("DELETE");
		config.addAllowedMethod("PATCH");
		source.registerCorsConfiguration("/**", config);
		log.info("corsFilter() çıktı");
		return new CorsFilter(source);
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		log.info("configure(HttpSecurity) girdi");
		httpSecurity
				.cors()
				.and()
					.csrf().disable().exceptionHandling().authenticationEntryPoint(handler)
				.and()
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.GET,"/forums").permitAll()
				.antMatchers(HttpMethod.POST,"/forumpost").permitAll()
				.antMatchers(HttpMethod.POST,"/users**").permitAll()
					.antMatchers("/auth/**").permitAll()
					.anyRequest().authenticated();

		httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		log.info("configure(HttpSecurity) çıktı");
	}

}
