package com.seif.TaskManager.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final SecretKey signingKey;
    private final Long expiration;

    public JwtService(@Value("${jwt.secret}") String key,@Value("${jwt.expiration}") Long expiration ){
        this.signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
        this.expiration = expiration;
    }


    public String generateToken(UserDetails userDetails) {
        return generateToken(Map.of(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
       return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(signingKey)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && (!isTokenExpired(token));
    }

   public Claims extractAllClaims(String token) {
       return Jwts
                .parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

   }

   public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
   }

   public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
   }

   public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
   }

   public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
   }

   public boolean isTokenValid(String token, UserDetails userDetails) {
        return (!isTokenExpired(token)) && validateToken(token, userDetails);
   }
}
