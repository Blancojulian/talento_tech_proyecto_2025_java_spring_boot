package com.techlab.talento_tech_proyecto.service;

import com.techlab.talento_tech_proyecto.entity.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  @Value("${spring.application.security.jwt.secret-key}")
  private String secretKey;
  @Value("${spring.application.security.jwt.expiration}")
  private long accessExpiration;
  @Value("${spring.application.security.jwt.refresh-token.expiration}")
  private long refreshExpiration;

  public String generateAccessToken(final User user) {
    return this.createToken(user, this.accessExpiration);
  }

  public String generateRefreshToken(final User user) {
    return this.createToken(user, this.refreshExpiration);
  }

  public String decodeToken(String token) {
    return "";
  }

  private String createToken(final User user, final long expiration) {
    return Jwts.builder()
        .id(String.valueOf(user.getId()))
        .claims(Map.of("email", user.getEmail()))
        .claims(Map.of("id", user.getId()))
        .subject(user.getEmail())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(this.getSigninKey())
        .compact();
  }

  private SecretKey getSigninKey() {
    byte[] keyByte = Decoders.BASE64.decode(this.secretKey);
    return Keys.hmacShaKeyFor(keyByte);
  }

  //ver
  public String extractUsername(String token) {
    return Jwts.parser()
        .verifyWith(this.getSigninKey())
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getSubject();
  }

  public boolean isTokenValid(String token, UserDetails user) {
    try {
      String username = extractUsername(token);
      return username.equals(user.getUsername());
    } catch (JwtException | IllegalArgumentException e) {
      return false;
    }
  }
}
