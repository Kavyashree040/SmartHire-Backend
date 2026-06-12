package com.smarthire.auth.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.smarthire.auth.security.JwtFilter;

@EnableMethodSecurity
@Configuration
public class SecurityConfig {

	@Autowired
    private JwtFilter jwtFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    	 http
    	 .csrf(csrf -> csrf.disable())
         .authorizeHttpRequests(auth -> auth
                 .requestMatchers("/auth/**").permitAll()
                 .requestMatchers("/admin/**").hasRole("ADMIN")
                 .requestMatchers("/recruiter/**").hasRole("RECRUITER")
                 .requestMatchers("/candidate/**").hasRole("CANDIDATE")
                 .anyRequest().authenticated()
         )
         .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

     return http.build();
 }

 // SIMPLE PASSWORD ENCODER (NO ENCRYPTION)
 @Bean
 public PasswordEncoder passwordEncoder() {
	 return new PasswordEncoder() {

         @Override
         public String encode(CharSequence rawPassword) {
             return rawPassword.toString();
         }

         @Override
         public boolean matches(CharSequence rawPassword, String encodedPassword) {
             return rawPassword.toString().equals(encodedPassword);
         }
     };
    }
}
