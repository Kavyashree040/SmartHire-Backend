package com.smarthire.job.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpMethod;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.smarthire.job.security.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	 @Autowired
	    private JwtFilter jwtFilter;

	    @Bean
	    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	        http
	            .csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(auth -> auth
	            		  // Public - view jobs
	                    .requestMatchers(HttpMethod.GET, "/jobs/**")
	                    .permitAll()

	                    // Recruiter only
	                    .requestMatchers(HttpMethod.POST, "/jobs/create")
	                    .hasRole("RECRUITER")

	                    .requestMatchers(HttpMethod.PUT, "/jobs/**")
	                    .hasRole("RECRUITER")

	                    .requestMatchers(HttpMethod.DELETE, "/jobs/**")
	                    .hasRole("RECRUITER")

	                    .anyRequest().authenticated()
	                )
	            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	        return http.build();
	    }
}
