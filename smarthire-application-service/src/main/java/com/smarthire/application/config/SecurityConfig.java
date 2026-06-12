package com.smarthire.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.smarthire.application.security.JwtFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
            		
            		 .requestMatchers(HttpMethod.PUT, "/applications/{applicationId}/status")
                     .hasRole("RECRUITER")
                     

                // Candidate can apply jobs
                .requestMatchers(HttpMethod.POST, "/applications/apply")
                .hasRole("CANDIDATE")

                .requestMatchers(HttpMethod.GET, "/applications/my")
                .hasRole("CANDIDATE")
                
                
                .requestMatchers(HttpMethod.GET, "/applications/stats/{jobId}")
                .hasRole("RECRUITER")
                
                // Recruiter can view applications
                .requestMatchers(HttpMethod.GET, "/applications/**")
                .hasAnyRole("RECRUITER", "ADMIN")

                
                
                .anyRequest().authenticated()
            )

            .addFilterBefore(jwtFilter,
                    UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
