package com.ssafy.enjoytrip.controller;



import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//import javax.servlet.http.HttpSession;

import com.ssafy.enjoytrip.model.Member;
import com.ssafy.enjoytrip.service.UserService;
import com.ssafy.enjoytrip.util.JwtokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthControllers {

//	@Autowired
	private final UserService userService;

//	@Autowired
	private final JwtokenUtil jwtUtil;

	//로그인 요청 처리 - POST /user/login
	@PostMapping("/login")
	public ResponseEntity<?> doLogin( @RequestBody Member member) throws UnsupportedEncodingException{
		//유저 정보 조회
		Member memberInfo = userService.login(member);

		//로그인 성공
		if(memberInfo!=null) {

			String token = jwtUtil.createToken(memberInfo, 60*60 );	//60초 * 60분

			Map<String, String> result = new HashMap<>();
			result.put("token", token);

			return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);

			/*
			 *
			 * {
			 * 	"token":"12311445252112326fgdfgfd gdsf"
			 * }
			 */
		}
		//로그인 실패
		else return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
