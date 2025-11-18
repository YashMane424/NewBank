package com.project.NewBank.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final String SECRET_KEY = "your_secret_key_here";
    
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

    private Key secretKey(){
        byte[] key = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractClaims(String token){
        return Jwts.parserBuilder()
            .setSigningKey(secretKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    private String extractUsername(String token) {
        return extractClaims(token, Claims:: getSubject);
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
    }

    public boolean isTokenExpired(String token) {
        Date expiration = extractClaims(token, Claims:: getExpiration);
        return expiration.before(new Date());
    }

    public String extractUsernameFromToken(String token) {
        return extractUsername(token);
    }

    


    
}
