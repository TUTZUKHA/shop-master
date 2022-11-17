package com.example.demo.servise;

import com.example.demo.excaption.BadRequest;
import org.slf4j.Logger;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Value("${jwtSecret}")
    private String jwtSecret;
    private static String issuer = "movie.uz";

    public String createToken(Integer id, String email) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setId("some Id");
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.setSubject(String.format("%s,%s", id, email));
        jwtBuilder.signWith(SignatureAlgorithm.HS256, jwtSecret);
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000)));
        jwtBuilder.setIssuer(issuer);
        return jwtBuilder.compact();
    }

    public Boolean validate(String token) {
        Logger logger = null;
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }

    public Integer getUserID(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            return Integer.valueOf(claims.getSubject().split(",")[0]);
        } catch (RuntimeException e) {
            throw new BadRequest("token expired");
        }

    }

    public String getUserName(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject().split(",")[1];
    }

    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getExpiration();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Filterga keldi!");

        // Get authorization header and validate
        final String header = request.getHeader("Authorization");
        if (header == null || header.isEmpty() || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
    }
}
