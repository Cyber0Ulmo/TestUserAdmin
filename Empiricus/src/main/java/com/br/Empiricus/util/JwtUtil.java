package com.br.Empiricus.util;


import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {


    private String SECRET_KEY = "florestas_de_tundra";


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        try {
            final Claims claims = extractAllClaims(token);
            return claimsResolver.apply(claims);
        } catch (JwtException e) {
            System.out.println("Erro ao extrair claims do token: " + e.getMessage());
            throw new RuntimeException("Erro ao extrair claims", e);
        }
    }


    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            System.out.println("Token JWT expirado.");
            throw new RuntimeException("Token expirado", e);
        } catch (MalformedJwtException e) {
            System.out.println("Token JWT malformado.");
            throw new RuntimeException("Token malformado", e);
        } catch (SignatureException e) {
            System.out.println("Assinatura do Token JWT inválida.");
            throw new RuntimeException("Assinatura inválida", e);
        } catch (JwtException e) {
            System.out.println("Erro ao processar o Token JWT.");
            throw new RuntimeException("Erro ao processar o token JWT", e);
        }
    }


    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }


    public String createToken(Map<String, Object> claims, String subject) {
        try {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(subject)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 3))
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();
        } catch (JwtException e) {
            System.out.println("Erro ao gerar o token JWT: " + e.getMessage());
            throw new RuntimeException("Erro ao gerar o token JWT", e);
        }
    }

    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}

