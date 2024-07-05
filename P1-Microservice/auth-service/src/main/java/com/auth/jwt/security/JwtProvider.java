package com.auth.jwt.security;


import com.auth.jwt.dto.RequestDto;
import com.auth.jwt.entity.AuthUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SecureRandom;

import java.util.*;

@Component
public class JwtProvider {
    /*@Value("${jwt.secret}")
    private String secret;*/
    private Key secret;
    @Autowired
    private RouterValidator routerValidator;

    @PostConstruct
    protected void init(){
        byte[] apiKeySecretBytes = new byte[64];
        new SecureRandom().nextBytes(apiKeySecretBytes);
        secret = Keys.hmacShaKeyFor(apiKeySecretBytes);
        //secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }
    public String createToken(AuthUser authUser){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", authUser.getId());
        claims.put("role", authUser.getRole());
        Date now = new Date();
        Date exp = new Date(now.getTime() + 3600000);
        return Jwts.builder()
                .claims(claims)
                .subject(authUser.getUserName())
                .issuedAt(now)
                .expiration(exp)
                .signWith(Keys.hmacShaKeyFor(secret.getEncoded()))
                .compact();
    }
    public boolean validate(String token, RequestDto requestDto){
        try{
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getEncoded())).build().parseSignedClaims(token);
        }catch(Exception e){
            return false;
        }
        if(!isAdmin(token) && routerValidator.isAdmin(requestDto)){
            return false;
        }
        return true;
    }
    public String getUserNameFromToken(String token){
        try{
            return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getEncoded()))
                    .build()
                    .parseSignedClaims(token).getPayload().getSubject();
        }catch(Exception e){
            return "Bad token";
        }
    }
    private boolean isAdmin(String token){
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getEncoded()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role").equals("admin");
    }
}
