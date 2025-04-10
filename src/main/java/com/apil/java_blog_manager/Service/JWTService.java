package com.apil.java_blog_manager.Service;

import com.apil.java_blog_manager.Entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JWTService {


    private static final String SECRET_KEY = "7t8/JSbiSpM064SQCETpC6O1oiPHhN//UJhObYzkJYA="; // Replace with a secure key
    private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(User user) {
        // Implement JWT token generation logic here
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
    }

}
