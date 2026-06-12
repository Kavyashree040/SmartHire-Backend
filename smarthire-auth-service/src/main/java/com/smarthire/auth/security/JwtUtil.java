package com.smarthire.auth.security;

import java.security.Key;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.smarthire.auth.entity.User;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {


	@Value("${jwt.secret}")
	    private String secret;

	    public String generateToken(User user) {
	    	 return Jwts.builder()
	    	            .setSubject(user.getEmail())
	    	            .claim("userId", user.getId())
	    	            .claim("role", user.getRole()) // ✅ ADD ROLE
	    	            .setIssuedAt(new Date())
	    	            .setExpiration(new Date(System.currentTimeMillis() + 86400000))
	    	            .signWith(getKey(), SignatureAlgorithm.HS256)
	    	            .compact();
	    }

	    private Key getKey() {
	        return Keys.hmacShaKeyFor(secret.getBytes());
	    }
	    
	    public String extractEmail(String token) {

	        return Jwts.parserBuilder()
	                .setSigningKey(getKey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody()
	                .getSubject();
	    }
	    
	    public String extractRole(String token) {

	        return Jwts.parserBuilder()
	                .setSigningKey(getKey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody()
	                .get("role", String.class);
	    }
	}
