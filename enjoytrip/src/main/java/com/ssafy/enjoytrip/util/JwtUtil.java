//package com.ssafy.enjoytrip.util;
//
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class JwtUtil {
//    private static final String secretKey = "EnjoyTripSecretKey"; // JWT secret key
//    private final long validateSeconds = 3600000L; // JWT 수명 (1시간)
//    public String createToken(String username){
//        Date now = new Date();
//        Date validity = new Date(now.getTime() + validateSeconds);
//
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(now)
//                .setExpiration(validity)
//                .signWith(SignatureAlgorithm.HS256,secretKey)
//                .compact();
//    }
//
//    public String getUsernameFromToken(String token){
//        return Jwts.parserBuilder().
//                setSigningKey(secretKey)
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//    public static boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            return false;
//        }
//    }
//}