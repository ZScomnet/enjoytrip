//package com.ssafy.enjoytrip.util;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class JwtTokenFilter extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        final String authorizationHeader = request.getHeader("Authorization");
//
//        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            return;
//        }
//
//        String token = authorizationHeader.substring(7);
//        if (!JwtUtil.validateToken(token)) {
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            return;
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//}