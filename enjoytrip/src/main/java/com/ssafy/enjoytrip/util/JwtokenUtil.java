package com.ssafy.enjoytrip.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.ssafy.enjoytrip.dto.SignInDto;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtokenUtil {
	private final String SECRET_KEY = "EnjoyTripSecretKeyfffffffffffffff";

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY.getBytes("UTF-8")).parseClaimsJws(token);
			log.debug("토큰 존재하고 유효하므로 요청 정상 처리");
			return true;

		} catch (Exception e) {
			log.debug("토큰은 존재하나 유효하지 않음.\n 에러내용 : {}", e.getMessage());
			return false;
		}
}
	public String createToken(SignInDto signInDto, int expirationSecond) throws UnsupportedEncodingException {

		String token = Jwts.builder()
				//header 
				.setHeaderParam("typ", "JWT")
				.setHeaderParam("alg","HS256")
				//payload
				.claim("id", signInDto.getUser_id())
				.claim("name", signInDto.getUsername())
				.setExpiration( new Date(System.currentTimeMillis() + 1000 * expirationSecond) )
				//singature
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes("UTF-8"))
				.compact();
		log.debug("발급된 토큰 : {}", token);
		return token;
	}

}
