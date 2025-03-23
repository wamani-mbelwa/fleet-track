package com.fleetpay.platform.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    // DEV ONLY: HMAC key. In prod use rotated asymmetric keys.
    private static final Key key = Keys.hmacShaKeyFor("dev-secret-key-please-override-32-bytes!".getBytes());

    public static String issueToken(String subject, Map<String, Object> claims, long ttlMillis) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(subject)
                .addClaims(claims)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + ttlMillis))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public static Map<String, Object> parseClaims(String jwt) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(jwt).getBody();
    }
}
