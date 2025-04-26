package com.assessment.work.grandkapital.service.impl;

import com.assessment.work.grandkapital.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtHandlerService implements JwtService {

    @Value("${spring.security.jwt.key-value}")
    private String secretKey;

    private final String USER_ID_CLAIM = "USER_ID";

    private final Function<Claims, String> userIdClaim = claims -> claims.get(USER_ID_CLAIM, String.class);

    @Override
    public String generateToke(Long id) {

        return Jwts.builder()
                .claim(USER_ID_CLAIM, id.toString())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))     // 1 час
                .signWith(getKey())
                .compact();
    }

    @Override
    public Long getUserId(String token) {
        return Long.valueOf(extractClaim(token, userIdClaim));
    }

    @Override
    public boolean validateToken(String jwtToken) {
        return !isTokenExpired(jwtToken) && extractClaim(jwtToken, userIdClaim) != null;
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwtToken) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    private SecretKey getKey() {
        byte[] decode = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(decode);
    }
}