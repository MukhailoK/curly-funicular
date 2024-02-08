package com.ait.grooming.service.auth;

import com.ait.grooming.service.exceptions.InvalidJwtException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenProvider {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.life-time}")
    private long jwtLifeTime;

    public String createToken(String userEmail){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtLifeTime);

        return Jwts.builder()
                .setSubject(userEmail)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


    public String getUserEmailFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }


    public boolean validateToken(String authToken){
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(authToken);

            return true;
        } catch (SignatureException e) {
            // Invalid JWT signature
            throw new InvalidJwtException("Invalid JWT signature");
        } catch (MalformedJwtException e){
            // Invalid JWT token
            throw new InvalidJwtException("Invalid JWT token");
        } catch (UnsupportedJwtException e){
            // Unsupported JWT token
            throw new InvalidJwtException("Unsupported JWT token");
        } catch (ExpiredJwtException e){
            // ExpiredJwtException JWT token
            throw new InvalidJwtException("Expired JWT token");
        } catch (IllegalArgumentException e){
            // JWT claims string is empty
            throw new IllegalArgumentException("JWT claims string is empty");
        }
    }

}
