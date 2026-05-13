package org.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
  @Value("jwt.secret")
  private String secretKey;
  private Key getSignInKey(){
    return Keys.hmacShaKeyFor(secretKey.getBytes());
  }
  public String generateToken(String email){
    Key key= Keys.hmacShaKeyFor(secretKey.getBytes());
    return Jwts.builder()
            .subject(email)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis()+1000*60*60))
            .signWith(key)
            .compact();
  }
  public String extractEmail(String token){
    Claims claims=Jwts.parser()
            .verifyWith((SecretKey) getSignInKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    return claims.getSubject();
  }
  public boolean validateToken(String token){
    try{
      Jwts.parser()
              .verifyWith((SecretKey) getSignInKey())
              .build()
              .parseSignedClaims(token);
      return true;
    }catch (Exception e){
      return false;
    }
  }
}

