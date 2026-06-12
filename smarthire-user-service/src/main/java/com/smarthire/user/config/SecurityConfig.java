package com.smarthire.user.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.smarthire.user.security.JwtFilter;

import org.springframework.http.HttpMethod;

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

	                    // Public endpoints
	                    .requestMatchers("/auth/**").permitAll()

	                    // ✅ Profile access
	                    .requestMatchers(HttpMethod.GET, "/candidate/profile/**")
	                   // .hasAnyRole("CANDIDATE", "RECRUITER")
	                    .permitAll()
	                    
	                    //resume access
	                    .requestMatchers(HttpMethod.GET, "/candidate/resume/**")
	                    .permitAll()
	                    
	                    // Recruiter APIs
	                    .requestMatchers("/recruiter/**")
	                    .hasRole("RECRUITER")

	                    // Candidate APIs
	                    .requestMatchers("/candidate/**")
	                    .hasRole("CANDIDATE")

	                    .anyRequest().authenticated()
	                )

	            
	            // 🔥 Add JWT filter
	            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	        return http.build();
	    }

}
