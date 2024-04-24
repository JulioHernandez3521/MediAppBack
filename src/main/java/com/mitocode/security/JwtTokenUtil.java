package com.mitocode.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.websocket.OnClose;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil implements Serializable {
    private final long JWT_TOKEN_VALIDITY = 5*60*60*1000; // 5 Horas
    @Value("${jwt.secret}") // ${} El Expression Language
    private String secret;

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")));
        claims.put("test","JulioTests");

        return doGenerateToken(claims, userDetails.getUsername());
    }
    private String doGenerateToken(Map<String, Object> claims, String username){

        SecretKey key = Keys.hmacShaKeyFor(this.secret.getBytes());

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(key)
                .compact();
    }

    public Claims getAllClaimsToken(String token){
        SecretKey key = Keys.hmacShaKeyFor(this.secret.getBytes());
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver){
        final Claims claim = getAllClaimsToken(token);
        return claimResolver.apply(claim);
    }

    public String getUserNameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }
    public Date getDateExpirationFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }


}
