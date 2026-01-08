package com.application.booker.jwtSecurity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


@Component
public class JwtUtil {

    private final long jwtExpirationMs = 1000 * 60 * 60;
    private final String jwtSecret = "my-super-secret-key-random-unique-precise-long-enough-12345";
    private final Key secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    ;

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean validateJwtToken(String token, UserDetails userDetails, String username) {
        return userDetails.getUsername().equalsIgnoreCase(username) && !isTokenExpired(token);
    }

}
