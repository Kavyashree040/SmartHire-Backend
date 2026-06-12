package com.smarthire.user.security;

import java.io.IOException;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
    	
    	System.out.println("REQUEST URI = " + request.getRequestURI());
        System.out.println("AUTH HEADER = " + request.getHeader("Authorization"));


        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);

            try {
                String email = jwtUtil.extractEmail(token);
                Long userId = jwtUtil.extractUserId(token);
                String role = jwtUtil.extractRole(token);
                
                System.out.println("TOKEN RECEIVED");
                System.out.println("EMAIL: " + email);
                System.out.println("ROLE: " + role);
               
                // ✅ store email for controllers
                request.setAttribute("email", email);
                request.setAttribute("userId", userId);

                SimpleGrantedAuthority authority =
                        new SimpleGrantedAuthority("ROLE_" + role);
                
                System.out.println("AUTHORITY CREATED: ROLE_" + role);
                System.out.println("AUTHORITIES: " + List.of(authority));

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                email,
                                null,
                                List.of(authority)
                        );
                SecurityContextHolder.getContext().setAuthentication(auth);

                System.out.println("AUTHENTICATION SET");
                
            } catch (Exception e) {
            	
            	 System.out.println("JWT ERROR");
            	e.printStackTrace();
            }
        }

        filterChain.doFilter(request, response);
    }
}

